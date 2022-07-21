package hibernate.e.jpa.teste;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {

	
	@Test
	public void testeHibernateUtil() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			UsuarioPessoa pessoa = new UsuarioPessoa();
			pessoa.setNome("WWWWWWw ");
			pessoa.setEmail("aaaaaaaaa@gmail.com");
			pessoa.setIdade(52);
			pessoa.setLogin("aaaaaaa@1009");
			pessoa.setSenha("22aaaa1144");
			pessoa.setSobreNome("qualaaaaaquer coisa");
			
			daoGeneric.salvar(pessoa);
		}
	@Test
	public void testeBuscar() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			UsuarioPessoa pessoa = new UsuarioPessoa();
			pessoa.setId(2L);
			pessoa = daoGeneric.pesquisar(pessoa);
			
			System.out.println(pessoa);
		}
	@Test
	public void testeBuscar01() 
	{
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa = daoGeneric.pesquisar(1L,UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	@Test
	public void updateESalvar() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			UsuarioPessoa pessoa = new UsuarioPessoa();
			
			pessoa = daoGeneric.pesquisar(1L,UsuarioPessoa.class);
			pessoa.setIdade(100);
			
			System.out.println(daoGeneric.updateMerge(pessoa));
		
		}
	
	@Test
	public void teteDelete()
	{
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa = daoGeneric.pesquisar(1L,UsuarioPessoa.class);
		daoGeneric.deleteById(pessoa);
	}
	
	
	@Test
	public void testeConsultar()
	{
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista  = new ArrayList<>();
		
		 lista = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("-------------------------------------------------------------------");
		}
	}
	@Test
	public void  testeQueryList() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			List<UsuarioPessoa> lista  = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = 'cristiano 02' ").getResultList();
			
			for (UsuarioPessoa usuarioPessoa : lista) {
				System.out.println(usuarioPessoa);
				
			}
		}
	@Test
	public void  testeQueryMaxResults() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			List<UsuarioPessoa> lista  = daoGeneric.getEntityManager().
					   createQuery("from UsuarioPessoa order by id ").setMaxResults(3)
					   .getResultList();
			
			for (UsuarioPessoa usuarioPessoa : lista) {
				System.out.println(usuarioPessoa);
				
			}
		}
	
	
	
	
}
