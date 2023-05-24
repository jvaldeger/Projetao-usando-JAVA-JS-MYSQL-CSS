package dao;

import java.util.List;

import modelo.FuncionarioHibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.persistence.Entity;

public class DaoFuncionarioHibernate {
	private SessionFactory fabrica;

	public DaoFuncionarioHibernate() throws Exception {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		fabrica = configuration.buildSessionFactory();
	}

	public void incluir(FuncionarioHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.save(e); // comando de incluir no bd
		transacao.commit();
		sessao.close();
	}

	public void alterar(FuncionarioHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.update(e); // comando de alterar no bd
		transacao.commit();
		sessao.close();
	}

	public void excluir(FuncionarioHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.delete(e); // comado de excluir
		transacao.commit();
		sessao.close();
	}

	public FuncionarioHibernate consultar(int matricula) throws Exception {
		FuncionarioHibernate ev = null;
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		Query consulta = sessao.createQuery("from FuncionarioHibernate where matricula= :parametro");
		consulta.setInteger("parametro", matricula);
		ev = (FuncionarioHibernate) consulta.uniqueResult(); // comando de consultar
		transacao.commit();
		sessao.close();
		return ev;
	}
	
	public FuncionarioHibernate consultarPorFuncionario(String nomeA) throws Exception{
    	FuncionarioHibernate ev = null;
    	Session sessao = fabrica.openSession();
    	Transaction transacao = sessao.beginTransaction();
    	Query consulta = sessao.createQuery("from  FuncionarioHibernate where nomeA= :parametro");
    	consulta.setString("parametro", nomeA);
    	ev = (FuncionarioHibernate) consulta.uniqueResult(); // comando de consultar
    	transacao.commit();
    	sessao.close();
    	return ev;
    }

	public List<FuncionarioHibernate> listarPorFuncionario(String nomeA) throws Exception {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		Query consulta = session.createQuery("from FuncionarioHibernate where nomeA = :parametro");
		consulta.setString("parametro", nomeA);
		List<FuncionarioHibernate> lista = consulta.list();
		t.commit();
		return lista;
	}

	public List<FuncionarioHibernate> listar() {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		List<FuncionarioHibernate> lista = session.createQuery("from FuncionarioHibernate").list();
		t.commit();
		return lista;
	}
}