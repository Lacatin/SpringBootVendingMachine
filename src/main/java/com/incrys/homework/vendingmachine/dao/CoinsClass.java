package com.incrys.homework.vendingmachine.dao;

import com.incrys.homework.vendingmachine.currency.Coins;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vending_machine_coins")
public class CoinsClass {

    @Id
    @Column(name = "value")
    private int value;

    @Column(name = "count")
    private int count;

    public CoinsClass() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Coins getCoin(int value) {
        return Coins.findCoinByValue(value);
    }

    @Override
    public String toString() {
        return "Coin = " + getValue() + " BANI " + " x " + count + ';';
    }
}
