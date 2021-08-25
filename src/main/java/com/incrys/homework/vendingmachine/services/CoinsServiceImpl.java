package com.incrys.homework.vendingmachine.services;

import com.incrys.homework.vendingmachine.currency.Coins;
import com.incrys.homework.vendingmachine.dao.CoinsClass;
import com.incrys.homework.vendingmachine.repositories.IVendingMachineCoinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CoinsServiceImpl implements ICoinsService{

    private Double credit = 0d;

    private IVendingMachineCoinsRepository coinsRepository;

    @Autowired
    public CoinsServiceImpl(IVendingMachineCoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
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
    public HashMap<Coins, Integer> findAll() {

        List<CoinsClass> coinsList = coinsRepository.findAll();

        HashMap<Coins, Integer> coinsMap = new HashMap<>();

        coinsList.stream().forEach(coin -> coinsMap.put(coin.getCoin(coin.getValue()), coin.getCount()));

        return coinsMap;
    }

    @Override
    public CoinsClass findCoinByValue(int value) {
        return coinsRepository.findCoinsClassByValue(value);
    }

    @Override
    public CoinsClass addCoin(CoinsClass coin) {

        CoinsClass coinsClass = coinsRepository.findCoinsClassByValue(coin.getValue());
        coinsClass.setCount(coinsClass.getCount() + 1);

        coinsRepository.save(coinsClass);
        credit += Coins.findCoinByValue(coin.getValue()).getValue();

        return coinsClass;
    }

    @Override
    public void deleteCoinByValue(int value) {

        CoinsClass coinsClass = coinsRepository.findCoinsClassByValue(value);
        coinsClass.setCount(coinsClass.getCount() - 1);

        coinsRepository.save(coinsClass);


    }
}
