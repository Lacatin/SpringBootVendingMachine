package com.incrys.homework.vendingmachine.services;

import com.incrys.homework.vendingmachine.currency.Bills;
import com.incrys.homework.vendingmachine.currency.Coins;
import com.incrys.homework.vendingmachine.dao.Items;
import com.incrys.homework.vendingmachine.exceptions.*;
import com.incrys.homework.vendingmachine.repositories.IVendingMachineItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsServiceImpl implements IItemsService{

    private Double totalCredit = 0d;
    private Double change;
    private HashMap<Bills, Integer> clientChangeBillsMap = new HashMap<>();
    private HashMap<Coins, Integer> clientChangeCoinsMap = new HashMap<>();

    private IBillsService billsService;
    private ICoinsService coinsService;
    private IVendingMachineItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(IVendingMachineItemsRepository itemsRepository, IBillsService billsService, ICoinsService coinsService) {
        this.itemsRepository = itemsRepository;
        this.billsService = billsService;
        this.coinsService = coinsService;
    }

    @PostConstruct
    public void mapsInit(){

        clientChangeBillsMap.put(Bills.RON50, 0);
        clientChangeBillsMap.put(Bills.RON10, 0);
        clientChangeBillsMap.put(Bills.RON5, 0);
        clientChangeBillsMap.put(Bills.RON1, 0);

        clientChangeCoinsMap.put(Coins.Bani50, 0);
        clientChangeCoinsMap.put(Coins.Bani10, 0);

    }

    public HashMap<Bills, Integer> getClientChangeBillsMap() {
        return clientChangeBillsMap;
    }

    public void setClientChangeBillsMap(HashMap<Bills, Integer> clientChangeBillsMap) {
        this.clientChangeBillsMap = clientChangeBillsMap;
    }

    public HashMap<Coins, Integer> getClientChangeCoinsMap() {
        return clientChangeCoinsMap;
    }

    public void setClientChangeCoinsMap(HashMap<Coins, Integer> clientChangeCoinsMap) {
        this.clientChangeCoinsMap = clientChangeCoinsMap;
    }

    public Double getTotalCredit() {
        return totalCredit = billsService.getCredit() + coinsService.getCredit();
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    @Override
    public List<Items> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public Items findItemById(int id) {

        Optional<Items> isItem = itemsRepository.findById(id);

        if(isItem.isEmpty()){
            throw new NotAnExistingItemException("Could not find the item with ID: " + id);
        }

        return isItem.get();
    }

    @Override
    public Items buyItemById(int id){

        Optional<Items> isItem = itemsRepository.findById(id);

        if (isItem.isEmpty()){
            throw new NotAnExistingItemException("Could not find the item with the ID: " + id);
        }

        Items item = isItem.get();

        totalCredit = billsService.getCredit() + coinsService.getCredit();

        if (item.getQuantity() < 0){
            throw new SoldOutException("The requested item is no longer in stock!");
        }

        if (isChange(totalCredit - item.getPrice())){
            throw new NotSufficientChangeException("The machine does not have enough change!");
        }

        if (totalCredit < item.getPrice()){
            throw new NotFullPaidException("Insufficient founds!");
        }

        if (totalCredit > item.getPrice()){
            change = totalCredit - item.getPrice();
            totalCredit = 0d;
            clientChangeBillsMap = calculateBillsChange(change, Bills.RON50, clientChangeBillsMap);
            clientChangeCoinsMap = calculateCoinsChange(change, Coins.Bani50, clientChangeCoinsMap);
            item.setQuantity(item.getQuantity() - 1);
            itemsRepository.save(item);
        }

        return item;
    }

    @Override
    public Items addItem(Items item) {

        Optional<Items> isItemInDB = itemsRepository.findById(item.getId());

        if (isItemInDB.isPresent()){
            throw new AlreadyInDatabaseException("Already in DB!");
        }

        itemsRepository.save(item);
        return item;
    }

    @Override
    public String deleteItemByID(int id) {

        itemsRepository.deleteById(id);
        return "Item deleted successfully";
    }


    @Override
    public Items updateItem(Items item){
        itemsRepository.save(item);
        return item;
    }

    @Override
    public HashMap<Bills, Integer> calculateBillsChange(double change, Bills bill, HashMap<Bills, Integer> clientChangeBillsMap) {

        HashMap<Bills, Integer> billsStorage = billsService.findAll();


        if (bill == null) {
            return clientChangeBillsMap;
        }

        if (billsStorage.get(bill) > 0
                && getTotalBillsFunds(clientChangeBillsMap) + getTotalCoinsFunds(clientChangeCoinsMap) + bill.getValue() <= change) {

            clientChangeBillsMap.put(bill, clientChangeBillsMap.get(bill) + 1);
            billsStorage.put(bill, billsStorage.get(bill)- 1);
            return calculateBillsChange(change, bill, clientChangeBillsMap);
        }

        if (billsStorage.get(bill) > 0
                && getTotalBillsFunds(clientChangeBillsMap) + getTotalCoinsFunds(clientChangeCoinsMap) + bill.getValue() > change) {
            return calculateBillsChange(change, bill.nextBill(bill), clientChangeBillsMap);
        }

        return clientChangeBillsMap;
    }

    @Override
    public HashMap<Coins, Integer> calculateCoinsChange(double change, Coins coin, HashMap<Coins, Integer> clientChangeCoinsMap) {

        HashMap<Coins, Integer> coinsStorage = coinsService.findAll();

        if (coin == null) {
            return clientChangeCoinsMap;
        }

        if (coinsStorage.get(coin) > 0
                && getTotalBillsFunds(clientChangeBillsMap) + getTotalCoinsFunds(clientChangeCoinsMap) + coin.getValue() <= change) {

            clientChangeCoinsMap.put(coin, clientChangeCoinsMap.get(coin) + 1);
            coinsStorage.put(coin, coinsStorage.get(coin)- 1);
            return calculateCoinsChange(change, coin, clientChangeCoinsMap);
        }

        if (coinsStorage.get(coin) > 0
                && getTotalBillsFunds(clientChangeBillsMap) + getTotalCoinsFunds(clientChangeCoinsMap) + coin.getValue() > change) {
            return calculateCoinsChange(change, coin.nextCoin(coin), clientChangeCoinsMap);
        }

        return clientChangeCoinsMap;
    }

    @Override
    public double getTotalBillsFunds(HashMap<Bills, Integer> billsMap) {
        return billsMap.entrySet().stream().mapToDouble(map -> map.getKey().getValue() * map.getValue()).sum();
    }

    @Override
    public double getTotalCoinsFunds(HashMap<Coins, Integer> coinsMap) {
        return coinsMap.entrySet().stream().mapToDouble(map -> map.getKey().getValue() * map.getValue()).sum();
    }

    public boolean isChange (double change) {

        if (change == 0){
            return true;
        }

        clientChangeBillsMap = calculateBillsChange(change, Bills.RON50, clientChangeBillsMap);
        clientChangeCoinsMap = calculateCoinsChange(change, Coins.Bani50, clientChangeCoinsMap);

        return getTotalBillsFunds(clientChangeBillsMap) < 0 && getTotalCoinsFunds(clientChangeCoinsMap) < 0;
    }

}
