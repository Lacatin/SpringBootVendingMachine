package com.incrys.homework.vendingmachine.controllers;

import com.incrys.homework.vendingmachine.currency.Bills;
import com.incrys.homework.vendingmachine.dao.BillsClass;
import com.incrys.homework.vendingmachine.services.BillsServiceImpl;
import com.incrys.homework.vendingmachine.services.IBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class BillsController {

    private IBillsService billsService;

    @Autowired
    public BillsController(IBillsService billsService) {
        this.billsService = billsService;
    }

    @GetMapping("/bills")
    public HashMap<Bills, Integer> showBills(){

        return billsService.findAll();
    }

    @GetMapping("/bills/{billValue}")
    public BillsClass showBill(@PathVariable int billValue){

        return billsService.findBillByValue(billValue);
    }

    @PostMapping("/bills")
    public BillsClass addBill(@RequestBody BillsClass bill){
        billsService.addBill(bill);
        return bill;
    }

}
