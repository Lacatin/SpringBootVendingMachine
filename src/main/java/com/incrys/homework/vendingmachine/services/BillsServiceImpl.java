package com.incrys.homework.vendingmachine.services;

import com.incrys.homework.vendingmachine.currency.Bills;
import com.incrys.homework.vendingmachine.dao.BillsClass;
import com.incrys.homework.vendingmachine.repositories.IVendingMachineBillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BillsServiceImpl implements IBillsService{

    private Double credit = 0d;

    private IVendingMachineBillsRepository billsRepository;

    @Autowired
    public BillsServiceImpl(IVendingMachineBillsRepository billsRepository) {
        this.billsRepository = billsRepository;
    }

    @Override
    public Double getCredit() {
        return credit;
    }

    @Override
    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Override
    public HashMap<Bills, Integer> findAll() {

        List<BillsClass> billList = billsRepository.findAll();

        HashMap<Bills, Integer> billsMap = new HashMap<>();

        billList.stream().forEach(bill -> billsMap.put(bill.getBill(bill.getValue()), bill.getCount()));

        return billsMap;
    }

    @Override
    public BillsClass findBillByValue(int value) {

        return billsRepository.findBillClassByValue(value);
    }

    @Override
    public BillsClass addBill(BillsClass bill) {

        BillsClass billsClass = billsRepository.findBillClassByValue(bill.getValue());
        billsClass.setCount(billsClass.getCount() + 1);

        billsRepository.save(billsClass);
        credit += bill.getValue();

        return billsClass;
    }

    @Override
    public void deleteBillByValue(int value) {

        BillsClass billsClass = billsRepository.findBillClassByValue(value);
        billsClass.setCount(billsClass.getCount() - 1);

        billsRepository.save(billsClass);
    }
}
