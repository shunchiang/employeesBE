package com.shunchiangdev.webemployees.controllers;

import com.shunchiangdev.webemployees.models.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.shunchiangdev.webemployees.repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository emprepos;
    private List<Employees> findEmployees(List<Employees> myList, CheckEmployee tester){
        List<Employees> tempList = new ArrayList<>();
        for (Employees e: myList){
            if (tester.test(e)) {
                tempList.add(e);

            }
        }
        return tempList;
    }
    // .../employees/all endpoint
    @GetMapping(value="/employees/all", produces = {"application/json"})
    public ResponseEntity<?> listAllEmployees(){
        List<Employees> myList= new ArrayList<>();

        emprepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((e1,e2)->e1.getLname().compareToIgnoreCase(e2.getLname()));

        for(Employees e:myList){
            System.out.println(e);
        }
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
    // .../employees/name/{letter} endpoint
    @GetMapping(value="/employees/name/{letter}", produces={"application/json"})
    public ResponseEntity<?> listAllByFirstName(@PathVariable char letter){
        List<Employees> myList = new ArrayList<>();
        emprepos.findAll().iterator().forEachRemaining(myList::add);

        List<Employees> rtnList = findEmployees(myList, e->e.getFname().charAt(0)==letter);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
    // .../employees/total
    @GetMapping(value="/employees/total", produces = {"application/json"})
    public ResponseEntity<?> displaySalary(){
        List<Employees> myList= new ArrayList<>();

        emprepos.findAll().iterator().forEachRemaining(myList::add);

        double total = 0.0;
        for(Employees e:myList){
            total += e.getSalary();
        }
        System.out.println("Salary" + total);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }


}
