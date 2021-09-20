package com.gdudek.movieRental.service.business.impl;

import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.business.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService  {

    private final StaffRepository staffRepository;
    private final AddressServiceImpl addressService;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(Long id) throws NotFoundException {
        return staffRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Staff with id "+ id+ " not found"));
    }

    @Override
    @Transactional
    public Staff save(Object objectToSave) throws AlreadyExistException {
        Staff staff = (Staff)objectToSave;
        Address address = staff.getAddress();

        if(staffRepository.existsByUsername(staff.getUsername())){

            throw new AlreadyExistException("Staff with username "+ staff.getUsername()+" already exist");
        }

        if(staffRepository.existsByEmail(staff.getEmail())){

            throw new AlreadyExistException("Customer with email "+ staff.getEmail()+" already exist");
        }

        address.getStaff().add(staff);
        addressService.save(address);
        return staffRepository.save(staff);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {

        if(staffRepository.existsById(id)){
            Staff staffToDelete = staffRepository.getById(id);

            staffToDelete.getAddress().getStaff().remove(staffToDelete);
            if(staffToDelete.getAddress().getCustomers().isEmpty())
            {
                addressService.deleteById(staffToDelete.getAddress().getId());
            }
            staffRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Staff with id "+id+" not found");
        }
    }

    @Override
    @Transactional
    public Staff findStaffByUsername(String username) throws NotFoundException {

        return staffRepository.findStaffByUsername(username)
                .orElseThrow(()->new NotFoundException("Staff with username "+username+ " not found"));

    }
}
