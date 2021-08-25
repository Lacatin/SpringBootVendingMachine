package com.incrys.homework.vendingmachine.services;

import com.incrys.homework.vendingmachine.currency.Bills;
import com.incrys.homework.vendingmachine.currency.Coins;
import com.incrys.homework.vendingmachine.dao.Items;

import java.util.HashMap;
import java.util.List;

public interface IItemsService {

    public List<Items> findAll();

    public Items findItemById(int id);

    public Items buyItemById(int id);

    public Items addItem(Items item);

    public String deleteItemByID(int id);

    public Items updateItem(Items item);

    public HashMap<Bills, Integer> calculateBillsChange(double change, Bills bill, HashMap<Bills, Integer> clientChangeBillsMap);

    public HashMap<Coins, Integer> calculateCoinsChange(double change, Coins coin, HashMap<Coins, Integer> clientChangeCoinssMap);

    public double getTotalBillsFunds(HashMap<Bills, Integer> billMap);

    public double getTotalCoinsFunds(HashMap<Coins, Integer> coinsMap);

    public boolean isChange (double change);
}
