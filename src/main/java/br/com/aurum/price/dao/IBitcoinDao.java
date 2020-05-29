package br.com.aurum.price.dao;

import java.util.List;

import br.com.aurum.price.model.Bitcoin;

public interface IBitcoinDao {

	public List<Bitcoin> listar();
}
