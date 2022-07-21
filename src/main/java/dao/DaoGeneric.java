package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;
import hibernate.e.jpa.HibernateUtil;


public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(E entity) 
		{
			EntityTransaction transaction= entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
			
		
		}
	public E pesquisar(Object entity) 
	{
		 Object id = HibernateUtil.getPrimaryKey(entity);
		 E e  = (E) entityManager.find(entity.getClass(), id);
		return e;
	}
	public E pesquisar(Long id,Class<E> entity) 
	{
		
		 E e  = (E) entityManager.find(entity, id);
		return e;
	}
	/*
	 * Salva ou atualiza
	 * */
	public E updateMerge(E entity) 
		{
			EntityTransaction transaction= entityManager.getTransaction();
			transaction.begin();
			E entidade = entityManager.merge(entity);
			transaction.commit();
			
			return entidade;
		}
		
	public void deleteById(E entity) 
		{
			Object id = HibernateUtil.getPrimaryKey(entity);
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.createNativeQuery("delete  from "+entity.getClass().getSimpleName().toLowerCase() + " where id =" +id)
			.executeUpdate();
			transaction.commit();
		}
	public List<E> listar(Class<E> entity)
		{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			List<E> lista = entityManager.createQuery("from "+ entity.getName()).getResultList();
			transaction.commit();
			
			return lista;
		}
	/*
	 * Permite acessar o entityManager de outras partes do projeto
	 * */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
}
