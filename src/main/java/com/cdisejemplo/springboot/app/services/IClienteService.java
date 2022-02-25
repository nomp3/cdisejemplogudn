package com.cdisejemplo.springboot.app.services;

import java.util.List;

import com.cdisejemplo.springboot.app.models.entity.Cliente;

public interface IClienteService {
	
	
	public Cliente getById(Long id_cliente, List<Cliente> lista);
}
