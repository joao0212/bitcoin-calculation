package br.com.aurum.price.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aurum.price.dao.IBitcoinDao;
import br.com.aurum.price.model.Bitcoin;

@Service
public class BitcoinService implements IBitcoinService {

	private IBitcoinDao bitcoinDao;

	@Autowired
	public BitcoinService(IBitcoinDao bitcoinDao) {
		this.bitcoinDao = bitcoinDao;
	}

	private Stream<Bitcoin> transformer(String type) {
		return this.bitcoinDao.listar().stream().filter(b -> b.getType().equals(type));
	}

	public List<Double> showHighers(String type) {
		return transformer(type).map(Bitcoin::getPrice).sorted(Comparator.reverseOrder()).limit(5)
				.collect(Collectors.toList());
	}

	public Double showAverage(String type) {
		return transformer(type).map(Bitcoin::getPrice).mapToDouble(Double::doubleValue).summaryStatistics()
				.getAverage();
	}

	public Double showMedian(String type) {
		List<Double> prices = transformer(type).map(Bitcoin::getPrice).sorted().collect(Collectors.toList());
		Integer size = prices.size();
		if (size % 2 != 0) {
			return prices.get(size / 2);
		}
		return (prices.get((size / 2) - 1) + prices.get(size / 2)) / 2;
	}

	public Double showStandardDeviation(String type) {
		List<Double> prices = transformer(type).map(Bitcoin::getPrice).sorted().collect(Collectors.toList());
		Double standardDeviation = 0.0;
		Integer size = prices.size();
		double sum = prices.stream().mapToDouble(Double::doubleValue).summaryStatistics().getSum();
		double mean = sum / size;
		for (Double price : prices) {
			standardDeviation += Math.pow(price - mean, 2);
		}
		return Math.sqrt(standardDeviation / size);
	}
}
