package com.cdisejemplo.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisejemplo.springboot.app.errors.DataBaseBancoException;
import com.cdisejemplo.springboot.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Override
	@Transactional
	public void save(Cliente cliente) throws DataBaseBancoException{
		if (cliente.getId_cliente() != null && cliente.getId_cliente() > 0) {
			try {
				em.merge(cliente);
			}catch (DataException e){
				throw new DataBaseBancoException();
			}			
		}else {
			try {
				em.persist(cliente);
			}catch (DataException e){
				throw new DataBaseBancoException();
			}	
			
		}
	}
	
	@Override
	@Transactional
	public void delete(Long id_cliente) {
		em.remove(findOne(id_cliente));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id_cliente) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id_cliente);
	}

}
