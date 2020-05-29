package br.com.aurum.price.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aurum.price.service.IBitcoinService;

@RestController
@RequestMapping
public class BitcoinController {

	@Autowired
	private IBitcoinService bitcoinService;

	@GetMapping("/higher")
	public List<Double> showHighers(@RequestParam String type) {
		return bitcoinService.showHighers(type);
	}

	@GetMapping("/average")
	public Double showAverage(@RequestParam String type) {
		return bitcoinService.showAverage(type);
	}

	@GetMapping("/median")
	public Double showMedian(@RequestParam String type) {
		return bitcoinService.showMedian(type);
	}

	@GetMapping("/standarddeviation")
	public Double showStandardDeviation(@RequestParam String type) {
		return bitcoinService.showStandardDeviation(type);
	}
}
