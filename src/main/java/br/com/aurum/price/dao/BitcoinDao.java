package br.com.aurum.price.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.aurum.price.model.Bitcoin;

@Repository
public class BitcoinDao implements IBitcoinDao {

	public List<Bitcoin> listar() {
		return WebClient.create("https://www.mercadobitcoin.net").get().uri("/api/BTC/trades/1501871369/1501891200/")
				.retrieve().bodyToFlux(Bitcoin.class).toStream().collect(Collectors.toList());
	}
}
