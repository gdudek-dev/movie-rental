package com.gdudek.movieRental.service;


import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.business.impl.StaffServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class StaffServiceTest {


    @Mock
    AddressServiceImpl addressService;

    @Mock
    StaffRepository staffRepository;

    @InjectMocks
    StaffServiceImpl staffService;

    @Mock
    Set<Staff> staffs;

    Staff staff;
    Address address;

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.initMocks(this);

        address = mock(Address.class);
        staff = mock(Staff.class);
    }

    @Test
    void shouldReturnStaffByUsername() throws NotFoundException {

        //given
        when(staffRepository.findStaffByUsername(any(String.class))).thenReturn(Optional.of(staff));
        //when
        Staff staffByUsername = staffService.findStaffByUsername("myUsername");
        //then
        assertThat(staffByUsername).isEqualTo(staff);
    }


    @Test
    void shouldAddStaff() throws AlreadyExistException {

        //given
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);
        doReturn(staffs).when(address).getStaff();
        doReturn(address).when(staff).getAddress();
        when(address.getStaff().add(any(Staff.class))).thenReturn(true);
        //when
        Staff addedStaff = staffService.save(staff);
        //then
        assertThat(addedStaff).isEqualTo(staff);
    }

    @Test
    void shouldNotAddStaffWhenUsernameExist(){
        //given
        when(staffRepository.existsByUsername(staff.getUsername())).thenReturn(true);
        //when
        //then
        assertThatThrownBy(()->staffService.save(staff)).isInstanceOf(AlreadyExistException.class);
    }

    @Test
    void shouldNotAddWhenEmailExist()  {
        //given
        when(staffRepository.existsByEmail(staff.getUsername())).thenReturn(true);
        //when
        //then
        assertThatThrownBy(()->staffService.save(staff)).isInstanceOf(AlreadyExistException.class);
    }

    @Test
    void shouldDeleteStaff() throws NotFoundException {
        // given
        when(staffRepository.existsById(any(Long.class))).thenReturn(true);
        when(staffRepository.getById(any(Long.class))).thenReturn(staff);
        doReturn(address).when(staff).getAddress();
        doReturn(staffs).when(address).getStaff();
        when(staff.getAddress().getStaff().remove(staff)).thenReturn(true);
        // when
        staffService.deleteById(1L);
        // then
        verify(staffRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteStaffWhenNotFound()  {
        // given
        // when
        // then
        assertThatThrownBy(()->staffService.deleteById(1L)).isInstanceOf(NotFoundException.class);
    }
}