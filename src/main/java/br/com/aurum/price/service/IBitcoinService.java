package br.com.aurum.price.service;

import java.util.List;

public interface IBitcoinService {

	public List<Double> showHighers(String type);

	public Double showAverage(String type);

	public Double showMedian(String type);

	public Double showStandardDeviation(String type);
}
