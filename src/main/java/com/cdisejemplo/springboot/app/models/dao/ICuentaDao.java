package com.cdisejemplo.springboot.app.models.dao;

import java.util.List;

import com.cdisejemplo.springboot.app.models.entity.Cuenta;

public interface ICuentaDao {
	
	public List<Cuenta> findAll();
	
	public void save(Cuenta cuenta);

	public Cuenta findOne(Long id_cuenta);

	void delete(Long id_cuenta);

}
