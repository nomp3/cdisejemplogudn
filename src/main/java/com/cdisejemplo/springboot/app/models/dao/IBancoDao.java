package com.cdisejemplo.springboot.app.models.dao;

import java.util.List;

import com.cdisejemplo.springboot.app.models.entity.Banco;

public interface IBancoDao {
	
public List<Banco> findAll();
	
	public void save(Banco banco);

	public Banco findOne(Long id_banco);

	void delete(Long id_banco);
}
