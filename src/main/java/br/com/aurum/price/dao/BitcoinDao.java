package br.com.aurum.price.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aurum.price.model.Bitcoin;

@Repository
public class BitcoinDao {

	public List<Bitcoin> getResult() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Bitcoin> listBitcoin = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		Bitcoin[] bitcoin = mapper.readValue(new URL("https://www.mercadobitcoin.net/api/BTC/trades/1501871369/1501891200/"), Bitcoin[].class);
		for (Bitcoin resultBitcoin : bitcoin) {
			listBitcoin.add(resultBitcoin);
		}
		return listBitcoin;
	}
}
