package com.incrys.homework.vendingmachine.repositories;

import com.incrys.homework.vendingmachine.dao.CoinsClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendingMachineCoinsRepository extends JpaRepository<CoinsClass, Integer> {

    public CoinsClass findCoinsClassByValue(int value);
}
