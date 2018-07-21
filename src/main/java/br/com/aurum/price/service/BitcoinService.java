package br.com.aurum.price.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.aurum.price.dao.BitcoinDao;
import br.com.aurum.price.model.Bitcoin;
import br.com.aurum.price.model.BitcoinComparator;

@Service
public class BitcoinService {

	@Autowired
	private BitcoinDao bitcoinDao;

	int medium = 0;
	Double mediaSell = 0.0;
	Double mediaBuy = 0.0;

	public List<Bitcoin> getJustBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> getJson = bitcoinDao.getResult();
		List<Bitcoin> getJustBuy = getJson.stream()
				.filter(buy -> buy.getType().equals("buy")).collect(Collectors.toList());
		return getJustBuy;
	}

	public List<Bitcoin> getJustSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> getJson = bitcoinDao.getResult();
		List<Bitcoin> getJustSell = getJson.stream()
				.filter(sell -> sell.getType().equals("sell")).collect(Collectors.toList());
		return getJustSell;
	}

	public List<Double> listHighestBuys() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Double> highestFiveBuysValues = new ArrayList<>();
		List<Bitcoin>getJustBuy = this.getJustBuy();
		Collections.sort(getJustBuy, new BitcoinComparator());
		List<Bitcoin> getFiveBuys = getJustBuy.subList(getJustBuy.size()-5, getJustBuy.size());
		for(Bitcoin resultFiveBuys : getFiveBuys) {
			highestFiveBuysValues.add(resultFiveBuys.getPrice());
		}
		return highestFiveBuysValues;
	}

	public List<Double> listHighestSells() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Double> highestFiveSellsValues = new ArrayList<>();
		List<Bitcoin> getJustSell = this.getJustSell(); 
		Collections.sort(getJustSell, new BitcoinComparator());
		List<Bitcoin> getFiveSells = getJustSell.subList(getJustSell.size()-5,getJustSell.size());
		for (Bitcoin resultFiveSells : getFiveSells) {
			highestFiveSellsValues.add(resultFiveSells.getPrice());
		}
		return highestFiveSellsValues;
	}

	public Double averageBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		int cont = 0;
		List<Bitcoin> getJustBuy = this.getJustBuy();
		for (Bitcoin result : getJustBuy) {
			cont++;
			mediaBuy = mediaBuy + result.getPrice();
		}
		return mediaBuy / cont;
	}

	public Double averageSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		int cont = 0;
		List<Bitcoin> getJustSell = this.getJustSell();
		for (Bitcoin result : getJustSell) {
			cont ++;
			mediaSell = mediaSell + result.getPrice();
		}
		return mediaSell / cont;
	}

	public Double medianBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> getJustBuy = this.getJustBuy();
		Collections.sort(getJustBuy, new BitcoinComparator());
		medium = getJustBuy.size() /2;
		if (getJustBuy.size() % 2 == 1) {
			Bitcoin median = getJustBuy.get(medium);
			return median.getPrice();
		} else {
			Bitcoin left = getJustBuy.get(medium - 1);
			Bitcoin right = getJustBuy.get(medium);
			return (left.getPrice() + right.getPrice()) / 2; 
		}
	}

	public Double medianSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> getJustSell = this.getJustSell();
		Collections.sort(getJustSell, new BitcoinComparator());
		medium = getJustSell.size() /2;
		if (getJustSell.size() % 2 == 1) {
			Bitcoin median = getJustSell.get(medium);
			return median.getPrice();
		} else {
			Bitcoin left = getJustSell.get(medium - 1);
			Bitcoin right = getJustSell.get(medium);
			return (left.getPrice() + right.getPrice()) / 2; 
		}
	}

	public Double standardDeviationBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> getJustBuy = this.getJustBuy();
		Collections.sort(getJustBuy, new BitcoinComparator());
		Double average = this.averageBuy();
		Double standardDeviation = 0.0;
		for (Bitcoin value : getJustBuy) {
			Double aux = value.getPrice() - average;
			standardDeviation = standardDeviation * aux;
		}
		return Math.sqrt(standardDeviation / (getJustBuy.size() - 1));
	}

	public Double standardDeviationSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> getJustSell = this.getJustSell();
		Collections.sort(getJustSell, new BitcoinComparator());
		Double average = this.averageSell();
		Double standardDeviation = 0.0;
		for (Bitcoin value : getJustSell) {
			Double aux = value.getPrice() - average;
			standardDeviation = standardDeviation * aux;
		}
		return Math.sqrt(standardDeviation / (getJustSell.size() - 1));
	}
}


