package dao;

import java.util.List;
import modelo.DepositoHibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import javax.persistence.Entity;

public class DaoDepositoHibernate {
	private SessionFactory fabrica;

	public DaoDepositoHibernate() throws Exception {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		fabrica = configuration.buildSessionFactory();
	}

	public void incluir(DepositoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.save(e); // comando de incluir no bd
		transacao.commit();
		sessao.close();
	}

	public void alterar(DepositoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.update(e); // comando de alterar no bd
		transacao.commit();
		sessao.close();
	}

	public void excluir(DepositoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.delete(e); // comado de excluir
		transacao.commit();
		sessao.close();
	}

	public DepositoHibernate consultar(int IdcodDepto) throws Exception {
		DepositoHibernate ev = null;
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		Query consulta = sessao.createQuery("from DepositoHibernate where IdcodDepto= :parametro");
		consulta.setInteger("parametro", IdcodDepto);
		ev = (DepositoHibernate) consulta.uniqueResult(); // comando de consultar
		transacao.commit();
		sessao.close();
		return ev;
	}
	
	public DepositoHibernate consultarPorDeposito(String descricao) throws Exception{
    	DepositoHibernate ev = null;
    	Session sessao = fabrica.openSession();
    	Transaction transacao = sessao.beginTransaction();
    	Query consulta = sessao.createQuery("from  DepositoHibernate where descricao= :parametro");
    	consulta.setString("parametro", descricao);
    	ev = (DepositoHibernate) consulta.uniqueResult(); // comando de consultar
    	transacao.commit();
    	sessao.close();
    	return ev;
    }

	public List<DepositoHibernate> listarPorDeposito(String descricao) throws Exception {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		Query consulta = session.createQuery("from DepositoHibernate where descricao = :parametro");
		consulta.setString("parametro", descricao);
		List<DepositoHibernate> lista = consulta.list();
		t.commit();
		return lista;
	}

	public List<DepositoHibernate> listar() {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		List<DepositoHibernate> lista = session.createQuery("from DepositoHibernate").list();
		t.commit();
		return lista;
	}
}