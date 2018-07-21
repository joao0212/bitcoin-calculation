package br.com.aurum.price.model;

import java.util.Comparator;

public class BitcoinComparator implements Comparator<Bitcoin>{

	@Override
	public int compare(Bitcoin bitcoin, Bitcoin anotherBitcoin) {
		return bitcoin.getPrice()
				.compareTo(anotherBitcoin.getPrice());
	}

}
