package com.gdudek.movieRental.service.business.impl;

import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.repository.business.StoreRepository;
import com.gdudek.movieRental.repository.customer.CustomerRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.business.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService  {

   private final StoreRepository storeRepository;
   private final StaffRepository staffRepository;
   private final CustomerRepository customerRepository;
   private final AddressServiceImpl addressService;

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Long id) throws NotFoundException {
        return storeRepository.findById(id).orElseThrow(()->new NotFoundException("Store with id "+id+" not found"));
    }

    @Override
    @Transactional
    public Store save(Object objectToSave) throws AlreadyExistException {
        Store store = (Store) objectToSave;
        Address storeAddress = store.getAddress();

        if(storeRepository.existsByAddress(storeAddress)) {
            throw new AlreadyExistException("Store at address "+storeAddress.getMainAddress()+" ," +storeAddress.getPostalCode()+" already exist");
        }

        storeAddress.getStores().add(store);
        addressService.save(storeAddress);
        return storeRepository.save(store);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {

        if(storeRepository.existsById(id)) {
            Store storeToDelete = storeRepository.getById(id);
            storeToDelete.getAddress().getStores().remove(storeToDelete);

            if(storeToDelete.getAddress().getStores().isEmpty()) {
                addressService.deleteById(storeToDelete.getAddress().getId());
            }

            storeRepository.deleteById(id);
        }
        else {
            notFoundException("Store",id);
        }



    }

    @Override
    public List<Store> findAllByManager(String firstName, String lastName) throws NotFoundException {
        return storeRepository.findAllByManager_FirstNameAndManager_LastName(firstName,lastName)
                .orElseThrow(()->new NotFoundException("There is no store managed by manager "+firstName+" "+lastName));
    }

    @Override
    @Transactional
    public void addCustomer(Customer customer, Long storeId)  {

        Store store = storeRepository.getById(storeId);
        customer.setStore(store);
        store.getCustomers().add(customer);
    }

    @Override
    @Transactional
    public void addStaff(Staff staff, Long storeId) {
        Store store = storeRepository.getById(storeId);
        staff.setStore(store);
        store.getStaff().add(staff);
    }

    @Override
    @Transactional
    public void addManager(Staff manager, Long storeId)  {
        Store store = storeRepository.getById(storeId);
        manager.setStore(store);
        manager.getManagedStores().add(store);
        store.getStaff().add(manager);
        store.setManager(manager);

    }

    @Override
    @Transactional
    public void deleteStaff(Long id, Long storeId) throws NotFoundException {

        if(!staffRepository.findById(id).isPresent()) {
        notFoundException("Staff", id);
         }

        if(!storeRepository.findById(storeId).isPresent()){
            notFoundException("Store",storeId);
        }

        storeRepository.getById(storeId).getStaff().remove(staffRepository.getById(id));
        staffRepository.getById(id).setStore(null);
    }


    @Override
    @Transactional
    public void deleteCustomer(Long id, Long storeId) throws NotFoundException {

        if(!customerRepository.findById(id).isPresent()) {
            notFoundException("Customer", id);
        }

        if(!storeRepository.findById(storeId).isPresent()){
            notFoundException("Store",storeId);
        }

        storeRepository.getById(storeId).getCustomers().remove(customerRepository.getById(id));
        customerRepository.getById(id).setStore(null);

    }

    @Override
    @Transactional
    public void changeManager(Long newManagerId, Long oldManagerId, Long storeId) throws NotFoundException {

        if(!staffRepository.findById(oldManagerId).isPresent()) {
            notFoundException("Manager", oldManagerId);
        }

        if(!staffRepository.findById(newManagerId).isPresent()) {
            notFoundException("New manager", oldManagerId);
        }

        if(!storeRepository.findById(storeId).isPresent()){
            notFoundException("Store",storeId);
        }

        Store store = storeRepository.getById(storeId);
        staffRepository.getById(oldManagerId).getManagedStores().remove(store);
        staffRepository.getById(newManagerId).getManagedStores().add(store);
        store.setManager(staffRepository.getById(newManagerId));

    }


    private void notFoundException(String entity,Long storeId) throws NotFoundException {
        throw new NotFoundException(entity+" with id "+storeId+" not found");
    }
}
