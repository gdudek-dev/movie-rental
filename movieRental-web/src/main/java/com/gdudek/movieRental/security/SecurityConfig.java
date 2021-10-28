package com.gdudek.movieRental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    @Autowired
    public AuthenticationManager authenticationManager(UserDetailsService securityUserDetailsService) throws Exception {
        ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessor<>() {
            public <T> T postProcess(T object) {
                return object;
            }
        };

        AuthenticationManagerBuilder m = new AuthenticationManagerBuilder(objectPostProcessor);
        m.userDetailsService(securityUserDetailsService);

        return m.build();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/css/**", "/images/**").permitAll()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/locations").permitAll()
                .antMatchers("/browse/movies").permitAll()
                .antMatchers("/browse/movies/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/index")
                .permitAll();

        httpSecurity.addFilterBefore(new CustomUsernameAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
