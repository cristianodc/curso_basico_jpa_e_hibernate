package hibernate.e.jpa.teste;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


import dao.DaoGeneric;
import model.TelefoneUser;
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
	@Test
	public void testeQueryListParameter() 
		{
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista   = daoGeneric
										.getEntityManager()
										/*.createQuery("from UsuarioPessoa where nome = :nome or sobrenome =:sobrenome")*/
										.createQuery("from UsuarioPessoa where nome = :nome and sobrenome =:sobrenome")
										.setParameter("nome","Fulano ")
										.setParameter("sobrenome","coffy").getResultList();
			
			for (UsuarioPessoa usuarioPessoa : lista) {
				System.out.println(usuarioPessoa);
				
			}
		}
	@Test
	public void testeQuerySomaIdades() 
		{
	
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			Long somaIdade = (Long) daoGeneric	
							 .getEntityManager()
							 .createQuery("select sum(u.idade)from UsuarioPessoa u")
							 .getSingleResult();
			
			System.out.println("A soma das idades foi -->> " +somaIdade);
		
		}
	
	@Test
	public void testeNamedQuery() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			List<UsuarioPessoa> lista   = daoGeneric
											.getEntityManager()
											.createNamedQuery("UsuarioPessoa.getAll")
											.getResultList();
			for (UsuarioPessoa usuarioPessoa : lista) {
				System.out.println(usuarioPessoa);
				
			}
		}
	@Test
	public void testeNamedQueryComParametros() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			List<UsuarioPessoa> lista   = daoGeneric
											.getEntityManager()
											.createNamedQuery("UsuarioPessoa.buscaPorNome")
											.setParameter("nome", "Beltrano ")
											.getResultList();
			for (UsuarioPessoa usuarioPessoa : lista) {
				System.out.println(usuarioPessoa);
				
			}
		}
	@Test
	public void testeGravafone() 
		{
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			DaoGeneric<TelefoneUser> daoGenericFone = new DaoGeneric<TelefoneUser>();
			UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);
			TelefoneUser userFone = new TelefoneUser();
			
			userFone.setTipo("Celular");
			userFone.setNumero("068967896789");
			userFone.setUsuarioPessoa(pessoa);
			
			daoGenericFone.salvar(userFone);
			
		}
	
	@Test
	public void testeConsultaFone() 
		{
			DaoGeneric daoGeneric = new DaoGeneric();
			UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(4L, UsuarioPessoa.class);
			
			for (TelefoneUser fone : pessoa.getTelefoneUser()) {
				
				System.out.println(fone.getUsuarioPessoa().getNome());
				System.out.println(fone.getTipo());
				System.out.println(fone.getNumero());
				
			}
		}
	
	
}
