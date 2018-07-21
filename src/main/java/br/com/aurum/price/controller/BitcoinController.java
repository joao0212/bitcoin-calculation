package br.com.aurum.price.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.aurum.price.service.BitcoinService;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

	@Autowired
	private BitcoinService bitcoinService;

	@GetMapping("/highestsells")
	public List<Double> listHighestSells() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.listHighestSells();
	}

	@GetMapping("/highestbuys")
	public List<Double> listHighestBuys() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.listHighestBuys();
	}

	@GetMapping("/averagebuy")
	public Double averageBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.averageBuy();
	}

	@GetMapping("/averagesell")
	public Double averageSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.averageSell();
	}

	@GetMapping("/medianbuy")
	public Double medianBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.medianBuy();
	}

	@GetMapping("/mediansell")
	public Double medianSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.medianSell();
	}

	@GetMapping("/standarddeviationbuy")
	public Double standardDeviationBuy() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.standardDeviationBuy();
	}

	@GetMapping("/standarddeviationsell")
	public Double standardDeviationSell() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		return bitcoinService.standardDeviationSell();
	}
}
