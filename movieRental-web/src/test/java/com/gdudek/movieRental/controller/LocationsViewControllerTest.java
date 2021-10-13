package com.gdudek.movieRental.controller;


import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.service.business.impl.StoreServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LocationsViewController.class)
public class LocationsViewControllerTest {


    @MockBean
    StoreServiceImpl storeService;

    @Autowired
    MockMvc mockMvc;

    List<Store>stores;


    @BeforeEach
    public void setUp() {
        stores = new ArrayList<>();
    }

    @Test
    public void shouldShowLocationsPage() throws Exception {
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/locations"));
    }

    @Test
    public void shouldShowStores() throws Exception {
        when(storeService.findAll()).thenReturn(stores);

       mockMvc.perform(get("/locations"))
               .andExpect(model().attributeExists("stores"))
               .andExpect(model().attribute("stores",Matchers.is(stores)));
    }

}
