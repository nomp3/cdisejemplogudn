package com.cdisejemplo.springboot.app.models.dao;

import java.util.List;

import com.cdisejemplo.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();

	public void save(Cliente cliente);

	public void delete(Long id_cliente);

	public Cliente findOne(Long id_cliente);
	
	
}
