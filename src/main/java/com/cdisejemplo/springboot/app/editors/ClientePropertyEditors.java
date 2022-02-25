package com.cdisejemplo.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdisejemplo.springboot.app.models.dao.IClienteDao;
import com.cdisejemplo.springboot.app.services.IClienteService;


@Component
public class ClientePropertyEditors extends PropertyEditorSupport {
	
	
	@Autowired 
	private IClienteService clienteService;
	
	@Autowired
	private IClienteDao clienteDao;
	
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		try {
			Long id_cliente = Long.parseLong(idStr);
			this.setValue(clienteService.getById(id_cliente, clienteDao.findAll()));
		}catch (Exception e) {
			System.out.println("Hubo un error al asignar el objeto cliente a la cuenta");
		}
	}
	
}
