package com.incrys.homework.vendingmachine.controllers;

import com.incrys.homework.vendingmachine.currency.Coins;
import com.incrys.homework.vendingmachine.dao.CoinsClass;
import com.incrys.homework.vendingmachine.services.CoinsServiceImpl;
import com.incrys.homework.vendingmachine.services.ICoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class CoinsController {

    private ICoinsService coinsService;

    @Autowired
    public CoinsController(ICoinsService coinsService) {
        this.coinsService = coinsService;
    }

    @GetMapping("/coins")
    public HashMap<Coins, Integer> showCoins(){

        return coinsService.findAll();
    }

    @GetMapping("/coins/{coinValue}")
    public CoinsClass showCoin(@PathVariable int coinValue){

        return coinsService.findCoinByValue(coinValue);
    }

    @PostMapping("/coins")
    public CoinsClass addCoin(@RequestBody CoinsClass coin){
        coinsService.addCoin(coin);
        return coin;
    }

}
