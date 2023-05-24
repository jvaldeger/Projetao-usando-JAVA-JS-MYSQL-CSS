package dao;

import java.util.List;
import modelo.DepartamentoHibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import javax.persistence.Entity;

public class DaoDepartamentoHibernate {
	private SessionFactory fabrica;

	public DaoDepartamentoHibernate() throws Exception {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		fabrica = configuration.buildSessionFactory();
	}

	public void incluir(DepartamentoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.save(e); // comando de incluir no bd
		transacao.commit();
		sessao.close();
	}

	public void alterar(DepartamentoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.update(e); // comando de alterar no bd
		transacao.commit();
		sessao.close();
	}

	public void excluir(DepartamentoHibernate e) throws Exception {
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		sessao.delete(e); // comado de excluir
		transacao.commit();
		sessao.close();
	}

	public DepartamentoHibernate consultar(int IdcodDepto) throws Exception {
		DepartamentoHibernate ev = null;
		Session sessao = fabrica.openSession();
		Transaction transacao = sessao.beginTransaction();
		Query consulta = sessao.createQuery("from DepartamentoHibernate where IdcodDepto= :parametro");
		consulta.setInteger("parametro", IdcodDepto);
		ev = (DepartamentoHibernate) consulta.uniqueResult(); // comando de consultar
		transacao.commit();
		sessao.close();
		return ev;
	}
	
	public DepartamentoHibernate consultarPorDepartamento(String descricao) throws Exception{
    	DepartamentoHibernate ev = null;
    	Session sessao = fabrica.openSession();
    	Transaction transacao = sessao.beginTransaction();
    	Query consulta = sessao.createQuery("from  DepartamentoHibernate where descricao= :parametro");
    	consulta.setString("parametro", descricao);
    	ev = (DepartamentoHibernate) consulta.uniqueResult(); // comando de consultar
    	transacao.commit();
    	sessao.close();
    	return ev;
    }

	public List<DepartamentoHibernate> listarPorDepartamento(String descricao) throws Exception {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		Query consulta = session.createQuery("from DepartamentoHibernate where descricao = :parametro");
		consulta.setString("parametro", descricao);
		List<DepartamentoHibernate> lista = consulta.list();
		t.commit();
		return lista;
	}

	public List<DepartamentoHibernate> listar() {
		Session session = fabrica.openSession();
		Transaction t = session.beginTransaction();
		List<DepartamentoHibernate> lista = session.createQuery("from DepartamentoHibernate").list();
		t.commit();
		return lista;
	}
}