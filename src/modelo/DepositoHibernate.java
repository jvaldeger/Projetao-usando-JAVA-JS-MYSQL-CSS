package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial") //Este ignora um aviso
@Entity //@Entity diz que a classe vai virar tabela
public class DepositoHibernate {
	@Id //@Id diz que o atributo, que tem id, ser� a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue diz que o valor ser� auto incrementado
	private int IdcodDepto;
	private String descricao;
	private String endereco;
	private String bairro;
	private String cidade;
	
	
	
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
		return "C�digo Deposito: " + getIdCodDepto() + "\nDeposito: "
				+ getDescricao();
	}	
}