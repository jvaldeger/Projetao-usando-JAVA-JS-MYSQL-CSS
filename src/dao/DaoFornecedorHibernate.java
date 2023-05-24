package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import modelo.FornecedorHibernate;

public class DaoFornecedorHibernate {
	private SessionFactory fabrica;

	public DaoFornecedorHibernate() throws Exception {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		fabrica = configuration.buildSessionFactory();
	}

	public void incluir(FornecedorHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.save(e); // comando de incluir no bd
		transacao.commit();
		sessao.close();
	}

	public void alterar(FornecedorHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.update(e); // comando de alterar no bd
		transacao.commit();
		sessao.close();
	}

	public void excluir(FornecedorHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.delete(e); // comado de excluir
		transacao.commit();
		sessao.close();
	}

	public FornecedorHibernate consultar(int codFornecedor) throws Exception {
		FornecedorHibernate ev = null;
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		Query consulta = sessao.createQuery("from FornecedorHibernate where codFornecedor= :parametro");
		consulta.setInteger("parametro", codFornecedor);
		ev = (FornecedorHibernate) consulta.uniqueResult(); // comando de consultar
		transacao.commit();
		sessao.close();
		return ev;
	}
	
	public FornecedorHibernate consultarPorCliente(String cargo) throws Exception{
		FornecedorHibernate ev = null;
    	Session sessao = fabrica.openSession();
    	Transaction transacao = sessao.beginTransaction();
    	Query consulta = sessao.createQuery("from  FornecedorHibernate where cargo= :parametro");
    	consulta.setString("parametro", cargo);
    	ev = (FornecedorHibernate) consulta.uniqueResult(); // comando de consultar
    	transacao.commit();
    	sessao.close();
    	return ev;
    }

	public List<FornecedorHibernate> listarPorCliente(String cargo) throws Exception {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		Query consulta = session.createQuery("from FornecedorHibernate where cargo = :parametro");
		consulta.setString("parametro", cargo);
		List<FornecedorHibernate> lista = consulta.list();
		t.commit();
		return lista;
	}

	public List<FornecedorHibernate> listar() {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		List<FornecedorHibernate> lista = session.createQuery("from FornecedorHibernate").list();
		t.commit();
		return lista;
	}
}