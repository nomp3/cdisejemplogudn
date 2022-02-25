package com.cdisejemplo.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdisejemplo.springboot.app.models.entity.Cuenta;

@Service
public class CuentaServiceImp implements ICuentaService {

	private List<Cuenta> lista;
	
	
	public CuentaServiceImp() {
		
	}



	@Override
	public Cuenta getById(Long id_cuenta, List<Cuenta> lista) {
		this.lista= lista;
		Cuenta cuentaResult = null;
		
		for(Cuenta cuenta : this.lista) {
			if(id_cuenta == cuenta.getId_cuenta()) {
				cuentaResult = cuenta;
				break;
			}
			
		}
		return cuentaResult;
	}

}
