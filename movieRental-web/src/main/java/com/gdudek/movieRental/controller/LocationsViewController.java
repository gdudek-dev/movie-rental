package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.service.business.StoreService;
import com.gdudek.movieRental.utils.ConvertStringToCaseSensitive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LocationsViewController {

    private final StoreService storeService;

    public LocationsViewController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/locations")
    public ModelAndView getLocations(@RequestParam( value = "cityName",required =false)String cityName){
        String viewName = "views/locations";
        Map<String,Object> model = new HashMap<>();

        List<Store>allStores = (List<Store>) storeService.findAll();
        List<Store> storesByCityName = new ArrayList<>();
        if(cityName!=null){
            storesByCityName = getStoresByCityName(allStores,cityName);
        }
        if(storesByCityName.isEmpty()) {
            model.put("stores",allStores);
        }else {
            model.put("stores",storesByCityName);
        }
        return new ModelAndView(viewName,model);
    }

    @PostMapping("/locations")
    public ModelAndView searchForCity(String cityName)
    {
        return getLocations(cityName);
    }

    private List<Store>getStoresByCityName(List<Store> allStores,String cityName) {

        cityName = ConvertStringToCaseSensitive.getConvertedString(cityName);
        String finalCityName = cityName;
        return allStores.stream().filter(store -> store.getAddress().getCity().getName().equals(finalCityName)).collect(Collectors.toList());
    }
}
