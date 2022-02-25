package com.cdisejemplo.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisejemplo.springboot.app.errors.DataBaseBancoException;
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
	@Transactional
	public void save(Banco banco) throws DataBaseBancoException {
		// TODO Auto-generated method stub
		if(banco.getId_banco() != null && banco.getId_banco() > 0) {
			try {
				em.merge(banco);
			}catch (DataException e){
				throw new DataBaseBancoException();
			}	
		}else {
			try {
				em.persist(banco);
			}catch (DataException e){
				throw new DataBaseBancoException();
			}
		} 
	

	}

	@Override
	public Banco findOne(Long id_banco) {
		// TODO Auto-generated method stub
		return em.find(Banco.class, id_banco);
	}

	@Override
	@Transactional
	public void delete(Long id_banco) {
		// TODO Auto-generated method stub
		em.remove(findOne(id_banco));
	}

}
