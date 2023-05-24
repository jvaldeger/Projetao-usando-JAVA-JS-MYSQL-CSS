package modelo;

import java.util.Date;

public class Produto {
	private String nome;
	private Date validade;
	private double valor;
	private String tipo;
	private String codDepto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getValidade() {
		return validade;
	}

	public void setvValidade(Date validade) {
		this.validade = validade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodDepto() {
		return codDepto;
	}

	public void setCodDepto(String codDepto) {
		this.codDepto = codDepto;
	}

	@Override
	public String toString() {
		return "\nProduto: " + getNome() + "\nValidade: " + getValidade() + "\nTipo: " + getTipo() + "\nCód. Depto: "
				+ getCodDepto();
	}
} // fim da class Funcionario
