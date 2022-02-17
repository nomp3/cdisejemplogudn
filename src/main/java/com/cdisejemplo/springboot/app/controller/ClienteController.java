package com.cdisejemplo.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdisejemplo.springboot.app.models.dao.IClienteDao;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "/lista-clientes", method = RequestMethod.GET)
	public String clienteLista(Model model) {
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "lista-clientes";
	}

}
