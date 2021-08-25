package com.incrys.homework.vendingmachine.repositories;

import com.incrys.homework.vendingmachine.dao.BillsClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendingMachineBillsRepository extends JpaRepository<BillsClass, Integer> {

    public BillsClass findBillClassByValue(int value);
}
