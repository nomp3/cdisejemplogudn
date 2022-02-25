package com.cdisejemplo.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisejemplo.springboot.app.errors.DataBaseBancoException;
import com.cdisejemplo.springboot.app.models.entity.Cuenta;

@Repository
public class CuentaDaoimpl implements ICuentaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cuenta").getResultList();
	}

	@Override
	@Transactional
	public void save(Cuenta cuenta) throws DataBaseBancoException{
		// TODO Auto-generated method stub
		if(cuenta.getId_cuenta() != null && cuenta.getId_cuenta() > 0) {
			try{
				em.merge(cuenta);
				
			}catch(DataException e){
				throw new DataBaseBancoException();
			}
		}else {
			try{
				em.persist(cuenta);
				
			}catch(DataException e){
				throw new DataBaseBancoException();
			}
		} 
	
	}
	@Override
	@Transactional
	public  Cuenta findOne(Long id_cuenta) {
		return em.find(Cuenta.class, id_cuenta);
		
	}
	
	@Override
	@Transactional
	public void delete(Long id_cuenta) {
		em.remove(findOne(id_cuenta));
	}


		

	@Override
	@Transactional
	public List<Cuenta> findByNumeroTelefono(String term) {
		// TODO Auto-generated method stub
				List<Cuenta> cuentas = new ArrayList<Cuenta>();
				for(Cuenta cuenta : this.findAll()) {
					if(cuenta.getNumeroTelefono().equals(term)) {
						cuentas.add(cuenta);
					}
				}
				return cuentas;
			}


}
