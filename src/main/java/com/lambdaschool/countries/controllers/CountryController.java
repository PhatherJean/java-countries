package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@RestController
public class CountryController
{
    @Autowired
    CountryRepository countrepos;

    private List<Country> findCountries(List<Country> myList, CheckCountry tester)
    {
        List<Country> tempList = new ArrayList<>();
        for (Country c : myList)
        {
            if(tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }
    @GetMapping(value ="/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllNames()
    {
        List<Country> myList = new ArrayList<>();
        countrepos.findAll()
                .iterator()
                .forEachRemaining(myList::add);

        myList.sort((c1, c2)-> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value ="/population/total", produces = {"application/json"})
    public ResponseEntity<?> populationTotal()
    {
        List<Country> myList = new ArrayList<>();
        countrepos.findAll()
                .iterator()
                .forEachRemaining(myList::add);
        long popTotal = 0;
        for (Country c : myList)
        {
            popTotal = popTotal + c.getPopulation();
        }
//        Long popTotal = myList.stream()
//                .mapToLong(Population::getPopulation)
//                .sum();

        System.out.println("The Total Population is " + popTotal);
        return new ResponseEntity<>(popTotal, HttpStatus.OK);
    }


    @GetMapping(value ="/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> countryNameByLetter(@PathVariable char letter)
    {
        List<Country> myList = new ArrayList<>();
        countrepos.findAll()
                .iterator()
                .forEachRemaining(myList::add);

        List<Country> rtnList = findCountries(myList, c -> c.getName().charAt(0)==letter);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value ="/population/min", produces = {"application/json"})
    public ResponseEntity<?> populationMin()
    {
        List<Country> myList = new ArrayList<>();
        countrepos.findAll()
                .iterator()
                .forEachRemaining(myList::add);
        long popMin = 0;
        for (Country c : myList)
        {
            popMin = Collections.min(Arrays.asList(c.getPopulation()));
        }
//        Long popTotal = myList.stream()
//                .mapToLong(Population::getPopulation)
//                .sum();
        long finalPopMin = popMin;
        Stream<Country> min = myList.stream().filter((c) -> c.getPopulation()  == finalPopMin);
        System.out.println("min" + min);
        return new ResponseEntity<>(min, HttpStatus.OK);
    }


}
