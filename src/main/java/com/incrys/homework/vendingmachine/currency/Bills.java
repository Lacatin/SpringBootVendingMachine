package com.incrys.homework.vendingmachine.currency;

public enum Bills {

	RON1(1), RON5(5), RON10(10), RON50(50);

	private int value;

	Bills(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public Bills nextBill(Bills bill) {

		switch (bill) {

		case RON50:
			bill = Bills.RON10;
			break;
		case RON10:
			bill = Bills.RON5;
			break;
		case RON5:
			bill = Bills.RON1;
			break;

		default:
			bill = null;
		}

		return bill;
	}
	
	public static Bills findBillByValue(int value) {
		
		Bills bill = null;
		
		switch (value) {

		case 50:
			bill = Bills.RON50;
			break;
		case 10:
			bill = Bills.RON10;
			break;
		case 5:
			bill = Bills.RON5;
			break;
		case 1:
			bill = Bills.RON1;

		}

		return bill;
	}

}
