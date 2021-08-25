package com.incrys.homework.vendingmachine.repositories;

import com.incrys.homework.vendingmachine.dao.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendingMachineItemsRepository extends JpaRepository<Items, Integer> {

}
