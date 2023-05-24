package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DepositoHibernate;
import dao.DaoDepositoHibernate;

public class ControleDepositoServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleDepositoServletAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descricao = request.getParameter("txtDeposito");
		int codigo;
		String verificarCodigo = request.getParameter("txtCodDepto");
		String verificarCodigoConsulta = request.getParameter("selDeposito");
		String botao = request.getParameter("btnOpcao");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String resultado;
		String nomeretorno = "", retorno = "";
		try {
			DaoDepositoHibernate daodeposito = new DaoDepositoHibernate();
			if (botao.equals("Cadastrar")) {
				retorno = "DepositoCadastrar.jsp";
				nomeretorno = "Deposito Cadastrado";
				if (descricao.equals("")) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				DepositoHibernate deposito = new DepositoHibernate();
				deposito.setDescricao(descricao);
				daodeposito.incluir(deposito);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Cadastro realizado com sucesso</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Deposito: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ deposito.getIdCodDepto() + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ deposito.getDescricao() + "</label><br><br>");
				out.println("<a href='DepositoCadastrar.jsp'/>Deposito Cadastrar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Cancelar")) {
				String redirectURL = "TelaDeposito.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Consultar")) {
				retorno = "DepositoConsultar.jsp";
				nomeretorno = "Deposito Consultado";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Deposito
				DepositoHibernate deposito = daodeposito.consultar(codigo);

				if (deposito == null) {
					throw new Exception("Deposito não está Cadastrado.");
				}
				daodeposito.consultar(codigo);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");
				out.println(
						"<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h2>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Deposito: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ deposito.getIdCodDepto() + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ deposito.getDescricao() + "</label><br><br>");
				out.println("<a href='DepositoConsultar.jsp'/>Retornar para Deposito Consultar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Excluir")) {
				retorno = "DepositoExcluir.jsp";
				nomeretorno = "Deposito Excluido";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Deposito
				DepositoHibernate deposito = daodeposito.consultar(codigo);

				if (deposito == null) {
					throw new Exception("Deposito não está Cadastrado.");
				}
				daodeposito.excluir(deposito);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");
				out.println(
						"<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Exclusão realizada com sucesso</h2>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Deposito: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ deposito.getIdCodDepto() + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ deposito.getDescricao() + "</label><br><br>");
				out.println("<a href='DepositoExcluir.jsp'/>Retornar para Deposito Excluir</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Alterar")) {
				retorno = "DepositoAlterar.jsp";
				nomeretorno = "Deposito Alterado";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Deposito
				DepositoHibernate deposito = daodeposito.consultar(codigo);
				if (deposito == null) {
					throw new Exception("Deposito não está Cadastrado.");
				}
				codigo = deposito.getIdCodDepto();
				descricao = deposito.getDescricao();
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");
				out.println(
						"<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h2>");
				out.println("<form name='frmDeposito2' action='ControleDepositoServletAction' method='post'>");
				out.println(
						"<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Altere os dados</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Deposito: </label>");
				out.println(
						"<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodDepto' maxlength='10'+"
								+ " readonly size='10' value='" + codigo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtDeposito'"
								+ " maxlength='45' size='60' value='" + descricao + "'><br><br>");
				out.println("<input type='submit' name='btnOpcao' value='Confirmar Alteração'>");
				out.println("<input type='submit' name='btnOpcao' value='Cancelar'>");
				out.println("</form>");
				out.println("<p><a href='DepositoAlterar.jsp'/>Retornar para Deposito Alterar</a></p>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Confirmar Alteração")) {
				retorno = "DepositoAlterar.jsp";
				nomeretorno = "Deposito Alterar";
				if (verificarCodigo == null || verificarCodigo == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigo);
				DepositoHibernate deposito = new DepositoHibernate();
				deposito.setCodDepto(codigo);
				deposito.setDescricao(descricao);
				daodeposito.alterar(deposito);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");
				out.println(
						"<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Dados alterados com sucesso</h2>");
				out.println("<form name='frmDeposito2' action='DepositoAlterar.jsp' method='post'>");
				out.println(
						"<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Deposito</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Deposito: </label>");
				out.println(
						"<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodDepto' maxlength='10'+"
								+ " readonly size='10' value='" + codigo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Deposito: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtDeposito'"
								+ " maxlength='45' readonly size='60' value='" + descricao + "'><br><br>");
				out.println("<input style='font-size:110%;font-weight:bold;background:black;color: white;'"
						+ " type='submit' name='btnOpcao' value='Retornar Deposito Alterar'>");
				out.println("</form>");
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