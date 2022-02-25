package com.cdisejemplo.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdisejemplo.springboot.app.models.dao.ICuentaDao;
import com.cdisejemplo.springboot.app.services.ICuentaService;

@Component
public class CuentaPropertyEditor extends PropertyEditorSupport{

	
	@Autowired
	private ICuentaService cuentaService;
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		try {
			Long id_cuenta = Long.parseLong(idStr);
			this.setValue(cuentaService.getById(id_cuenta, cuentaDao.findAll()));
		}catch (Exception e) {
			System.out.println("Hubo un error al asignar el objeto cuenta a la tarjeta");
		}
	} 
	
	
	
}
