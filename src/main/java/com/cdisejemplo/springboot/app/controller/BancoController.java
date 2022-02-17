package com.cdisejemplo.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdisejemplo.springboot.app.models.dao.IBancoDao;

@Controller
public class BancoController {

	@Autowired
	private IBancoDao bancoDao;
	
	@RequestMapping(value = "/lista-bancos", method = RequestMethod.GET)
	public String bancoLista(Model model) {
		model.addAttribute("titulo", "Lista de bancos");
		model.addAttribute("bancos", bancoDao.findAll());
		return "lista-bancos";
	}
}
