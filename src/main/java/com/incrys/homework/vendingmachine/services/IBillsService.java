package com.incrys.homework.vendingmachine.services;

import com.incrys.homework.vendingmachine.currency.Bills;
import com.incrys.homework.vendingmachine.dao.BillsClass;

import java.util.HashMap;

public interface IBillsService {

    public Double credit = 0d;

    public Double getCredit();

    public void setCredit(Double credit);

    public HashMap<Bills, Integer> findAll();

    public BillsClass findBillByValue(int value);

    public BillsClass addBill(BillsClass bill);

    public void deleteBillByValue(int value);


}
