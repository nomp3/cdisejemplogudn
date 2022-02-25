package com.cdisejemplo.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdisejemplo.springboot.app.models.entity.Cliente;


@Service
public class ClienteServiceimp implements IClienteService {

	
	private List<Cliente> lista;
	
	public ClienteServiceimp() {
		
	}
	
	@Override
	public Cliente getById(Long id_cliente, List<Cliente> lista) {
		this.lista= lista;
		Cliente clienteResult= null;
		
		for(Cliente cliente : this.lista) {
			if(id_cliente == cliente.getId_cliente()) {
				clienteResult = cliente;
				break;
			}
		}
		
		return clienteResult;
	}

}
