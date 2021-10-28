package com.gdudek.movieRental.service.business.impl;

import com.gdudek.movieRental.enums.RoleType;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.Role;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.repository.RoleRepository;
import com.gdudek.movieRental.repository.address.AddressRepository;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.business.StaffService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService  {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final AddressServiceImpl addressService;

    PasswordEncoder passwordEncoder;

    public StaffServiceImpl(StaffRepository staffRepository, RoleRepository roleRepository, AddressRepository addressRepository, AddressServiceImpl addressService, PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

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
    public Staff save(Object objectToSave){
        Staff staff = (Staff) objectToSave;
        addRole(staff);
        encodePassword(staff);
        Address staffAddress = staff.getAddress();

        if (addressRepository.existsByMainAddressAndCity_NameAndCity_Country_Name(staffAddress.getMainAddress()
                , staffAddress.getCity().getName()
                , staffAddress.getCity().getCountry().getName())) {
            staffAddress = addressRepository.findAddressByMainAddressAndCity_NameAndCity_Country_Name(staffAddress.getMainAddress()
                    , staffAddress.getCity().getName()
                    , staffAddress.getCity().getCountry().getName()).get();
            staffAddress.getStaff().add(staff);

            addressRepository.save(staffAddress);
            staff.setAddress(staffAddress);
            return staffRepository.save(staff);

        }

        staffAddress.getStaff().add(staff);
        addressService.save(staffAddress);
        return staffRepository.save(staff);
    }

    private void encodePassword(Staff staff)
    {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
    }

    private void addRole(Staff staff){
        Role staffRole = roleRepository.findByRoleType(RoleType.ROLE_USER);
        staff.getRoles().add(staffRole);
        staffRole.getStaff().add(staff);
        roleRepository.save(staffRole);
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

    @Override
    public boolean existByUsername(String username) {
        return staffRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return staffRepository.existsByEmail(email);
    }
}
