package com.incrys.homework.vendingmachine.services;


import com.incrys.homework.vendingmachine.currency.Coins;
import com.incrys.homework.vendingmachine.dao.CoinsClass;

import java.util.HashMap;

public interface ICoinsService {

    public Double credit = 0d;

    public Double getCredit();

    public void setCredit(Double credit);

    public HashMap<Coins, Integer> findAll();

    public CoinsClass findCoinByValue(int value);

    public CoinsClass addCoin(CoinsClass coin);

    public void deleteCoinByValue(int value);
}
