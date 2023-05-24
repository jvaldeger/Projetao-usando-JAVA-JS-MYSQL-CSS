package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoDepositoHibernate;
import dao.DaoFornecedorHibernate;
import dao.DaoFuncionarioHibernate;
import dao.DaoProdutoHibernate;
import modelo.DepositoHibernate;
import modelo.FornecedorHibernate;
import modelo.FuncionarioHibernate;
import modelo.ProdutoHibernate;

public class ControleProdutoServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleProdutoServletAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("txtNome");
		String txtvalidade = request.getParameter("txtValidade");
		String tipo = request.getParameter("txtTipo");
		String txtvalor = request.getParameter("txtValor");
		String iddeposito = request.getParameter("selDeposito");
		String idfuncionario = request.getParameter("selFuncionario");
		String idfornecedor = request.getParameter("selFornecedor");

		
		String selproduto = request.getParameter("selProduto");
		int matricula;
		double valor;
		Date validade;
		String verificarProduto = request.getParameter("txtProduto");
		String botao = request.getParameter("btnOpcao");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String resultado;
		String nomeretorno = "", retorno = "";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			DaoDepositoHibernate daodepositohibernate = new DaoDepositoHibernate();
			ArrayList<DepositoHibernate> depositos = (ArrayList) daodepositohibernate.listar();
			
			DaoFornecedorHibernate daofornecedorhibernate = new DaoFornecedorHibernate();
			ArrayList<FornecedorHibernate> fornecedores = (ArrayList) daofornecedorhibernate.listar();
			
			DaoFuncionarioHibernate daofuncionariohibernate = new DaoFuncionarioHibernate();
			ArrayList<FuncionarioHibernate> funcionarios = (ArrayList) daofuncionariohibernate.listar();
			
			DaoProdutoHibernate daoproduto = new DaoProdutoHibernate();
			if (botao.equals("Cancelar")) {
				String redirectURL = "TelaProduto.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Cadastrar")) {
				retorno = "ProdutoCadastrar.jsp";
				nomeretorno = "Produto Cadastrado";
				if (nome.equals("") || txtvalidade.equals("") || tipo.equals("") || txtvalor.equals("")
						|| iddeposito.equals("") || idfuncionario.equals("") || idfornecedor.equals("") ) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				ProdutoHibernate produto = new ProdutoHibernate();
				
				DepositoHibernate depositohibernate = new DepositoHibernate();
				depositohibernate.setCodDepto(Integer.parseInt(iddeposito));
				
				FornecedorHibernate fornecedorhibernate = new FornecedorHibernate();
				fornecedorhibernate.setCodFornecedor(Integer.parseInt(idfornecedor));
				
				FuncionarioHibernate funcionariohibernate = new FuncionarioHibernate();
				funcionariohibernate.setMatricula(Integer.parseInt(idfuncionario));
				
				validade = formato.parse(txtvalidade);
				valor = Double.parseDouble(txtvalor);
				produto.setNome(nome);
				produto.setValidade(validade);
				produto.setTipo(tipo);
				produto.setValor(valor);
				produto.setDeposito(depositohibernate);
				produto.setFornecedor(fornecedorhibernate);
				produto.setFuncionario(funcionariohibernate);
				
				daoproduto.incluir(produto);
				
				depositohibernate = daodepositohibernate.consultar(produto.getDeposito().getIdCodDepto());
			fornecedorhibernate = daofornecedorhibernate.consultar(produto.getFornecedor().getCodFornecedor());
				funcionariohibernate = daofuncionariohibernate.consultar(produto.getFuncionario().getMatricula());

				String descricao = depositohibernate.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Cadastro realizado com sucesso</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cód Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getIdproduto()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getNome()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ formato.format(produto.getValidade()) + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Valor: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getValor()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Tipo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getTipo()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selDeposito'>");
				for (DepositoHibernate deposito : depositos) {
					if (deposito.getIdCodDepto() == produto.getDeposito().getIdCodDepto()) {
						out.println("<option value=" + deposito.getIdCodDepto() + ">" + deposito.getDescricao()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFornecedor'>");
				for (FornecedorHibernate fornecedor : fornecedores) {
					if (fornecedor.getCodFornecedor() == produto.getFornecedor().getCodFornecedor()) {
						out.println("<option value=" + fornecedor.getCodFornecedor() + ">" + fornecedor.getNome()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionario: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFuncionario'>");
				for (FuncionarioHibernate funcionario : funcionarios) {
					if (funcionario.getMatricula() == produto.getFuncionario().getMatricula()) {
						out.println("<option value=" + funcionario.getMatricula() + ">" + funcionario.getNomeA()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<a href='" + retorno + "'" + "/>" + nomeretorno + "</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Consultar")) {
				retorno = "ProdutoConsultar.jsp";
				nomeretorno = "Produto Consultado";
				if (selproduto == null || selproduto == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				matricula = Integer.parseInt(selproduto);
				ProdutoHibernate produto = new ProdutoHibernate();
				DepositoHibernate depositohibernate = new DepositoHibernate();
				FornecedorHibernate fornecedorhibernate = new FornecedorHibernate();
				FuncionarioHibernate funcionariohibernate = new FuncionarioHibernate();
				
				produto = daoproduto.consultar(matricula);
				depositohibernate = daodepositohibernate.consultar(produto.getDeposito().getIdCodDepto());
				
				fornecedorhibernate = daofornecedorhibernate.consultar(produto.getFornecedor().getCodFornecedor());
				
				funcionariohibernate = daofuncionariohibernate.consultar(produto.getFuncionario().getMatricula());
				
				String descricao = depositohibernate.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cód Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getIdproduto()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getNome()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ formato.format(produto.getValidade()) + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Valor: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getValor()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Tipo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getTipo()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selDeposito'>");
				for (DepositoHibernate deposito : depositos) {
					if (deposito.getIdCodDepto() == produto.getDeposito().getIdCodDepto()) {
						out.println("<option value=" + deposito.getIdCodDepto() + ">" + deposito.getDescricao()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFornecedor'>");
				for (FornecedorHibernate fornecedor : fornecedores) {
					if (fornecedor.getCodFornecedor() == produto.getFornecedor().getCodFornecedor()) {
						out.println("<option value=" + fornecedor.getCodFornecedor() + ">" + fornecedor.getNome()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionario: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFuncionario'>");
				for (FuncionarioHibernate funcionario : funcionarios) {
					if (funcionario.getMatricula() == produto.getFuncionario().getMatricula()) {
						out.println("<option value=" + funcionario.getMatricula() + ">" + funcionario.getNomeA()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='" + retorno + "'" + "/>" + nomeretorno + "</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Excluir")) {
				retorno = "ProdutoExcluir.jsp";
				nomeretorno = "Produto Excluido";
				if (selproduto == null || selproduto == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				matricula = Integer.parseInt(selproduto);
				ProdutoHibernate produto = new ProdutoHibernate();
				DepositoHibernate depositohibernate = new DepositoHibernate();
				FornecedorHibernate fornecedorhibernate = new FornecedorHibernate();
				FuncionarioHibernate funcionariohibernate = new FuncionarioHibernate();
				produto = daoproduto.consultar(matricula);
				
				depositohibernate = daodepositohibernate.consultar(produto.getDeposito().getIdCodDepto());
						
				fornecedorhibernate = daofornecedorhibernate.consultar(produto.getFornecedor().getCodFornecedor());
						
				funcionariohibernate = daofuncionariohibernate.consultar(produto.getFuncionario().getMatricula());
				String descricao = depositohibernate.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Dados do Funcionário</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cód Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getIdproduto()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getNome()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ formato.format(produto.getValidade()) + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Valor: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getValor()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Tipo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getTipo()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selDeposito'>");
				for (DepositoHibernate deposito : depositos) {
					if (deposito.getIdCodDepto() == produto.getDeposito().getIdCodDepto()) {
						out.println("<option value=" + deposito.getIdCodDepto() + ">" + deposito.getDescricao()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFornecedor'>");
				for (FornecedorHibernate fornecedor : fornecedores) {
					if (fornecedor.getCodFornecedor() == produto.getFornecedor().getCodFornecedor()) {
						out.println("<option value=" + fornecedor.getCodFornecedor() + ">" + fornecedor.getNome()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionario: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFuncionario'>");
				for (FuncionarioHibernate funcionario : funcionarios) {
					if (funcionario.getMatricula() == produto.getFuncionario().getMatricula()) {
						out.println("<option value=" + funcionario.getMatricula() + ">" + funcionario.getNomeA()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<form name='frmProduto' action='ControleProdutoServletAction' method='post'>");
				out.println("<input type='submit' name='btnOpcao' value='Confirmar Exclusão'>");
				out.println("<input type='submit' name='btnOpcao' value='Cancelar Exclusão'>");
				out.println("<input type='hidden' name='txtProduto' maxlength='20' value='" + produto.getIdproduto()
						+ "'>");
				out.println("</form>");
				out.println("<a href='" + retorno + "'" + "/>" + nomeretorno + "</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Confirmar Exclusão")) {
				retorno = "ProdutoExcluir.jsp";
				nomeretorno = "Produto Excluir";
				if (verificarProduto == null || verificarProduto == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				matricula = Integer.parseInt(verificarProduto);
				// consultar pelo id do Funcionário
				ProdutoHibernate produto = daoproduto.consultar(matricula);

				if (produto == null) {
					throw new Exception("Funcionário não está Cadastrado.");
				}
				DepositoHibernate depositohibernate = new DepositoHibernate();
				FornecedorHibernate fornecedorhibernate = new FornecedorHibernate();
				FuncionarioHibernate funcionariohibernate = new FuncionarioHibernate();
				daoproduto.excluir(produto);
				depositohibernate = daodepositohibernate.consultar(produto.getDeposito().getIdCodDepto());
				
				fornecedorhibernate = daofornecedorhibernate.consultar(produto.getFornecedor().getCodFornecedor());
						
				funcionariohibernate = daofuncionariohibernate.consultar(produto.getFuncionario().getMatricula());
				String descricao = depositohibernate.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Produto excluído com sucessoo</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cód Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getIdproduto()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getNome()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ formato.format(produto.getValidade()) + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Valor: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getValor()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Tipo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getTipo()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selDeposito'>");
				for (DepositoHibernate deposito : depositos) {
					if (deposito.getIdCodDepto() == produto.getDeposito().getIdCodDepto()) {
						out.println("<option value=" + deposito.getIdCodDepto() + ">" + deposito.getDescricao()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFornecedor'>");
				for (FornecedorHibernate fornecedor : fornecedores) {
					if (fornecedor.getCodFornecedor() == produto.getFornecedor().getCodFornecedor()) {
						out.println("<option value=" + fornecedor.getCodFornecedor() + ">" + fornecedor.getNome()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionario: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFuncionario'>");
				for (FuncionarioHibernate funcionario : funcionarios) {
					if (funcionario.getMatricula() == produto.getFuncionario().getMatricula()) {
						out.println("<option value=" + funcionario.getMatricula() + ">" + funcionario.getNomeA()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='" + retorno + "'" + "/>" + nomeretorno + "</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Cancelar Exclusão")) {
				String redirectURL = "ProdutoExcluir.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Alterar")) {
				retorno = "ProdutoAlterar.jsp";
				nomeretorno = "Produto Alterado";
				if (selproduto == null || selproduto == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				matricula = Integer.parseInt(selproduto);
				ProdutoHibernate produto = new ProdutoHibernate();
				DepositoHibernate depositohibernate = new DepositoHibernate();
				FornecedorHibernate fornecedorhibernate = new FornecedorHibernate();
				FuncionarioHibernate funcionariohibernate = new FuncionarioHibernate();
				produto = daoproduto.consultar(matricula);
				depositohibernate = daodepositohibernate.consultar(produto.getDeposito().getIdCodDepto());
				
				fornecedorhibernate = daofornecedorhibernate.consultar(produto.getFornecedor().getCodFornecedor());
						
				funcionariohibernate = daofuncionariohibernate.consultar(produto.getFuncionario().getMatricula());
				String descricao = depositohibernate.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='text-align: center;'>Funcionário</h1>");
				out.println("<table>");
				out.println("<form name='frmProduto' action='ControleProdutoServletAction' method='post'>");
				out.println(
						"<caption style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Dados do Funcionário - Alterar </caption>");
				out.println("<tr>");
				out.println("<th class='op2'>Cód Produto: </th>");
				out.println(
						"<td class='op2'><input type='text' readonly name='txtProduto' size='10' maxlength='10' value='"
								+ produto.getIdproduto() + "'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th class='op2'>Produto: </th>");
				out.println(
						"<td class='op2'><input type='text' autofucos name='txtNome' size='60' maxlength='60' value='"
								+ produto.getNome() + "'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th class='op2'>Data de Validade: </th>");
				out.println("<td class='op2'><input type='text' name='txtValidade' size='8' maxlength='10' value='"
						+ formato.format(produto.getValidade()) + "'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th class='op2'>Valor: </th>");
				out.println("<td class='op2'><input type='text' name='txtValor' size='10' maxlength='10' value='"
						+ produto.getValor() + "'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<tr>");
				out.println("<th class='op2'>Tipo: </th>");
				out.println("<td class='op2'><input type='text' name='txtTipo' size='100' maxlength='30' value='"
						+ produto.getTipo() + "'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th class='op2'>Deposito: </th>");
				out.println("<td class='op2'><select name='selDeposito'>");
				for (DepositoHibernate deposito : depositos) {
					out.println(
							"<option value=" + deposito.getIdCodDepto() + ">" + deposito.getDescricao() + "</option>");
				} // fim do for %>
				out.println("<option value='' selected>Selecionar...</option>");
				out.println("</select> <br></td>");
				out.println("</tr>");

				
				out.println("<th class='op2'>Fornecedor: </th>");
				out.println("<td class='op2'><select name='selFornecedor'>");
				for (FornecedorHibernate fornecedor : fornecedores) {
					out.println(
							"<option value=" + fornecedor.getCodFornecedor()+ ">" + fornecedor.getNome() + "</option>");
				} // fim do for %>
				out.println("<option value='' selected>Selecionar...</option>");
				out.println("</select> <br></td>");
				out.println("</tr>");

				
				out.println("<th class='op2'>Funcionario: </th>");
				out.println("<td class='op2'><select name='selFuncionario'>");
				for (FuncionarioHibernate funcionario : funcionarios) {
					out.println(
							"<option value=" + funcionario.getMatricula()+ ">" + funcionario.getNomeA() + "</option>");
				} // fim do for %>
				out.println("<option value='' selected>Selecionar...</option>");
				out.println("</select> <br></td>");
				
				out.println("</tr>");
				out.println("<td class='op1'><input type='submit' value='Confirmar Alteração' name='btnOpcao'></td>");
				out.println("<td class='op1'><input type='reset' value='Cancelar Alteração' name='btnOpcao'></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</table>");
				out.println("<h3>Retornar Tela Principal: <a href='TelaProduto.jsp' />Clicar</a></h3>");
				out.println("</div>");
				out.println("<div id='mascara'></div>");
				out.println("</body>");
				out.println("</html>");
			}
			
			if (botao.equals("Confirmar Alteração")) {
				retorno = "ProdutoAlterar.jsp";
				nomeretorno = "Produto Alterado";
				if (nome.equals("") || txtvalidade.equals("") || tipo.equals("") || txtvalor.equals("")
						|| iddeposito.equals("") || idfuncionario.equals("") || idfornecedor.equals("")) {
					
					throw new Exception("Favor preencher todas as caixas.");
					
				}
				matricula = Integer.parseInt(verificarProduto);
				ProdutoHibernate produto = new ProdutoHibernate();
				DepositoHibernate depositohibernate = new DepositoHibernate();
				depositohibernate.setCodDepto(Integer.parseInt(iddeposito));
				
				FornecedorHibernate fornecedorhibernate = new FornecedorHibernate();
				fornecedorhibernate.setCodFornecedor(Integer.parseInt(idfornecedor));
				
				FuncionarioHibernate funcionariohibernate = new FuncionarioHibernate();
				funcionariohibernate.setMatricula(Integer.parseInt(idfuncionario));
				validade = formato.parse(txtvalidade);
				valor = Double.parseDouble(txtvalor);
				produto.setIdproduto(matricula);
				produto.setNome(nome);
				produto.setValidade(validade);
				produto.setTipo(tipo);
				produto.setValor(valor);
				produto.setDeposito(depositohibernate);
				produto.setFornecedor(fornecedorhibernate);
				produto.setFuncionario(funcionariohibernate);
				daoproduto.alterar(produto);
				
				depositohibernate = daodepositohibernate.consultar(produto.getDeposito().getIdCodDepto());
				fornecedorhibernate = daofornecedorhibernate.consultar(produto.getFornecedor().getCodFornecedor());
				funcionariohibernate = daofuncionariohibernate.consultar(produto.getFuncionario().getMatricula());
				String descricao = depositohibernate.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Dados alterados com sucessoo</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cód Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getIdproduto()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Produto: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getNome()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ formato.format(produto.getValidade()) + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Valor: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getValor()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Tipo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>" + produto.getTipo()
						+ "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selDeposito'>");
				for (DepositoHibernate deposito : depositos) {
					if (deposito.getIdCodDepto() == produto.getDeposito().getIdCodDepto()) {
						out.println("<option value=" + deposito.getIdCodDepto() + ">" + deposito.getDescricao()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFornecedor'>");
				for (FornecedorHibernate fornecedor : fornecedores) {
					if (fornecedor.getCodFornecedor() == produto.getFornecedor().getCodFornecedor()) {
						out.println("<option value=" + fornecedor.getCodFornecedor() + ">" + fornecedor.getNome()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionario: </label>");
				out.println(
						"<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selFuncionario'>");
				for (FuncionarioHibernate funcionario : funcionarios) {
					if (funcionario.getMatricula() == produto.getFuncionario().getMatricula()) {
						out.println("<option value=" + funcionario.getMatricula() + ">" + funcionario.getNomeA()
								+ "</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='" + retorno + "'" + "/>" + nomeretorno + "</a>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception ex) {
			out.println("<!doctype html>");
			out.println("<html lang='pt-br'>");
			out.println("<head>");
			out.println("<meta charset='ISO-8859-1'>");
			out.println("<link rel='stylesheet' href='css/estilo.css' />");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");
			out.println("<h2>Erro: " + ex.getMessage() + "</h2><br><br>");
			out.println("<a href='" + retorno + "'/>Retornar para o " + nomeretorno + "</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}