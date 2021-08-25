package com.incrys.homework.vendingmachine.currency;

public enum Coins {

	Bani10(0.1), Bani50(0.5);

	private double value;

	Coins(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public Coins nextCoin(Coins coin) {

		switch (coin) {

		case Bani50:
			coin = Coins.Bani10;
			break;

		default:
			coin = null;
		}

		return coin;
	}
	
	public static Coins findCoinByValue(double value) {
		
		Coins coin = null;
		
		if (value == 5) {
			coin = Coins.Bani50;
			
		}
		
		if (value == 1) {
			coin = Coins.Bani10;
		}

		if(value != 1 && value != 5) {
			System.out.println("Va rugam sa introduceti o moneda in intervalul 0.1-0.5 RONI!");
		}
		
		return coin;
	}
}
