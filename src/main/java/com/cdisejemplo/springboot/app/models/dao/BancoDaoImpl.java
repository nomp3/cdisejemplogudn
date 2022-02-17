package com.cdisejemplo.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisejemplo.springboot.app.models.entity.Banco;

@Repository
public class BancoDaoImpl implements IBancoDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Banco> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Banco").getResultList();
	}

	@Override
	public void save(Banco banco) {
		// TODO Auto-generated method stub
		if(banco.getId_banco() != null && banco.getId_banco() > 0) {
			em.merge(banco);
		}else {
			em.persist(banco);
		} 
	

	}

	@Override
	public Banco findOne(Long id_banco) {
		// TODO Auto-generated method stub
		return em.find(Banco.class, id_banco);
	}

	@Override
	public void delete(Long id_banco) {
		// TODO Auto-generated method stub
		em.remove(findOne(id_banco));
	}

}
