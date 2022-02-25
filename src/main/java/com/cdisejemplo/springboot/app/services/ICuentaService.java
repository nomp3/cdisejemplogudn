package com.cdisejemplo.springboot.app.services;

import java.util.List;

import com.cdisejemplo.springboot.app.models.entity.Cuenta;

public interface ICuentaService {
	
	public Cuenta getById(Long id_cuenta, List<Cuenta> lista);


}
