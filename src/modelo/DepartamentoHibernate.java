package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial") //Este ignora um aviso
@Entity //@Entity diz que a classe vai virar tabela
public class DepartamentoHibernate {
	@Id //@Id diz que o atributo, que tem id, será a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue diz que o valor será auto incrementado
	private int IdcodDepto;
	private String descricao;
	
	public int getIdCodDepto() {
		return IdcodDepto;
	}

	public void setCodDepto(int IdcodDepto) {
		this.IdcodDepto = IdcodDepto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Código Departamento: " + getIdCodDepto() + "\nDepartamento: "
				+ getDescricao();
	}	
}