package modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial") // Este ignora um aviso
@Entity // @Entity diz que a classe vai virar tabela
public class ProdutoHibernate {
	@Id // @Id diz que o atributo, que tem id, será a chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue diz que o valor será auto incrementado
	private int Idproduto;

	@ManyToOne
	@JoinColumn(name = "ceIdcodDepto") // nome da chave estrangeira na tabela Funcionário
	private DepositoHibernate deposito;
	
	@ManyToOne
	@JoinColumn(name = "ceIdcodFornecedor") // nome da chave estrangeira na tabela Funcionário
	private FornecedorHibernate fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "ceIdcodFuncionario") // nome da chave estrangeira na tabela Funcionário
	private FuncionarioHibernate funcionario;

	private String nome;
	private Date validade;
	private double valor;
	private String tipo;
	
	public FornecedorHibernate getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorHibernate fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FuncionarioHibernate getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioHibernate funcionario) {
		this.funcionario = funcionario;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdproduto() {
		return Idproduto;
	}

	public void setIdproduto(int idproduto) {
		Idproduto = idproduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public DepositoHibernate getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoHibernate deposito) {
		this.deposito = deposito;
	}

	@Override
	public String toString() {
		return "Matrícula: " + getIdproduto() + "\nProduto: " + getNome() + "\nValidade: " + getValidade() + "\nTipo: "
				+ getTipo() + "\nCód. Deposito: " + getDeposito();
	}
} // fim da class FuncionarioHibernate