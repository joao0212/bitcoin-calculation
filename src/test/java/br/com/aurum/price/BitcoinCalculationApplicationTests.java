package br.com.aurum.price;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.aurum.price.service.BitcoinService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BitcoinCalculationApplicationTests {

	@Autowired
	private BitcoinService bitcoinService;

	@Test
	public void mustGetFiveBuysValues() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Double> listHighestBuys = bitcoinService.listHighestBuys();
		assertEquals(5, listHighestBuys.size());
	}

	@Test
	public void mustGetFiveSellsValues() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Double> listHighestSells = bitcoinService.listHighestSells();
		assertEquals(5, listHighestSells.size());
	}

	@Test
	public void mustBeNotEmpty() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		Double averageBuy = bitcoinService.averageBuy();
		Double averageSell = bitcoinService.averageSell();
		assertNotNull(averageBuy);
		assertNotNull(averageSell);
	}
	
	@Test
	public void mustBeZero() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		Double standardDeviationBuy = bitcoinService.standardDeviationBuy();
		Double standardDeviationSell = bitcoinService.standardDeviationSell();
		assertEquals(Double.valueOf(0), standardDeviationBuy);
		assertEquals(Double.valueOf(0), standardDeviationSell);
	}

}
