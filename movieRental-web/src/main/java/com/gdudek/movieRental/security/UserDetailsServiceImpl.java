package com.gdudek.movieRental.security;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.AbstractUser;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.repository.customer.CustomerRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static String USER_TYPE = "userType";

    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository, StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {

        String userType = (String) RequestContextHolder.getRequestAttributes().getAttribute(USER_TYPE, RequestAttributes.SCOPE_SESSION);
        if(StringUtils.isEmpty(userType)) {
            return null;
        }

        AbstractUser user;

        switch (userType){
            case "Customer": user =  customerRepository.findCustomerByUsername(username).orElseThrow(
                    ()->new NotFoundException("Customer with this username not found"));break;
            case "Staff":   user =staffRepository.findStaffByUsername(username).orElseThrow(
                    ()->new NotFoundException("Staff with this username not found"));break;
            default: user = null;
        }

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return AuthorityUtils.createAuthorityList("user");
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
