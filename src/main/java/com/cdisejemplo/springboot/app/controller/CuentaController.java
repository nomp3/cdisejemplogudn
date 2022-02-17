package com.cdisejemplo.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdisejemplo.springboot.app.models.dao.ICuentaDao;

@Controller
public class CuentaController {

	@Autowired
	private ICuentaDao cuentaDao;
	

	@RequestMapping(value = "/lista-cuentas", method = RequestMethod.GET)
	public String cuentaLista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		return "lista-cuentas";
	}
}
