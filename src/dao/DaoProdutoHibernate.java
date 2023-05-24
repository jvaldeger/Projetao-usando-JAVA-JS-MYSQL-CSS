package dao;

import java.util.List;

import modelo.ProdutoHibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.persistence.Entity;

public class DaoProdutoHibernate {
	private SessionFactory fabrica;

	public DaoProdutoHibernate() throws Exception {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		fabrica = configuration.buildSessionFactory();
	}

	public void incluir(ProdutoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.save(e); // comando de incluir no bd
		transacao.commit();
		sessao.close();
	}

	public void alterar(ProdutoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.update(e); // comando de alterar no bd
		transacao.commit();
		sessao.close();
	}

	public void excluir(ProdutoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.delete(e); // comado de excluir
		transacao.commit();
		sessao.close();
	}

	public ProdutoHibernate consultar(int Idproduto) throws Exception {
		ProdutoHibernate ev = null;
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		Query consulta = sessao.createQuery("from ProdutoHibernate where Idproduto= :parametro");
		consulta.setInteger("parametro", Idproduto);
		ev = (ProdutoHibernate) consulta.uniqueResult(); // comando de consultar
		transacao.commit();
		sessao.close();
		return ev;
	}
	
	public ProdutoHibernate consultarPorFuncionario(String nome) throws Exception{
    	ProdutoHibernate ev = null;
    	Session sessao = fabrica.openSession();
    	Transaction transacao = sessao.beginTransaction();
    	Query consulta = sessao.createQuery("from  ProdutoHibernate where nome= :parametro");
    	consulta.setString("parametro", nome);
    	ev = (ProdutoHibernate) consulta.uniqueResult(); // comando de consultar
    	transacao.commit();
    	sessao.close();
    	return ev;
    }

	public List<ProdutoHibernate> listarPorFuncionario(String nome) throws Exception {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		Query consulta = session.createQuery("from ProdutoHibernate where nome = :parametro");
		consulta.setString("parametro", nome);
		List<ProdutoHibernate> lista = consulta.list();
		t.commit();
		return lista;
	}

	public List<ProdutoHibernate> listar() {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		List<ProdutoHibernate> lista = session.createQuery("from ProdutoHibernate").list();
		t.commit();
		return lista;
	}
}