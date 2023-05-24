package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DepartamentoHibernate;
import dao.DaoDepartamentoHibernate;

public class ControleDepartamentoServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleDepartamentoServletAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descricao = request.getParameter("txtDepartamento");
		int codigo;
		String verificarCodigo = request.getParameter("txtCodDepto");
		String verificarCodigoConsulta = request.getParameter("selDepartamento");
		String botao = request.getParameter("btnOpcao");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String resultado;
		String nomeretorno = "", retorno = "";
		try {
			DaoDepartamentoHibernate daodepartamento = new DaoDepartamentoHibernate();
			if (botao.equals("Cadastrar")) {
				retorno = "DepartamentoCadastrar.jsp";
				nomeretorno = "Departamento Cadastrado";
				if (descricao.equals("")) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				DepartamentoHibernate departamento = new DepartamentoHibernate();
				departamento.setDescricao(descricao);
				daodepartamento.incluir(departamento);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println(
						"<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Cadastro realizado com sucesso</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ departamento.getIdCodDepto() + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ departamento.getDescricao() + "</label><br><br>");
				out.println("<a href='DepartamentoCadastrar.jsp'/>Departamento Cadastrar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Cancelar")) {
				String redirectURL = "TelaDepartamento.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Consultar")) {
				retorno = "DepartamentoConsultar.jsp";
				nomeretorno = "Departamento Consultado";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Departamento
				DepartamentoHibernate departamento = daodepartamento.consultar(codigo);

				if (departamento == null) {
					throw new Exception("Departamento não está Cadastrado.");
				}
				daodepartamento.consultar(codigo);
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
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ departamento.getIdCodDepto() + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ departamento.getDescricao() + "</label><br><br>");
				out.println("<a href='DepartamentoConsultar.jsp'/>Retornar para Departamento Consultar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Excluir")) {
				retorno = "DepartamentoExcluir.jsp";
				nomeretorno = "Departamento Excluido";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Departamento
				DepartamentoHibernate departamento = daodepartamento.consultar(codigo);

				if (departamento == null) {
					throw new Exception("Departamento não está Cadastrado.");
				}
				daodepartamento.excluir(departamento);
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
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ departamento.getIdCodDepto() + "</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"
						+ departamento.getDescricao() + "</label><br><br>");
				out.println("<a href='DepartamentoExcluir.jsp'/>Retornar para Departamento Excluir</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Alterar")) {
				retorno = "DepartamentoAlterar.jsp";
				nomeretorno = "Departamento Alterado";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Departamento
				DepartamentoHibernate departamento = daodepartamento.consultar(codigo);
				if (departamento == null) {
					throw new Exception("Departamento não está Cadastrado.");
				}
				codigo = departamento.getIdCodDepto();
				descricao = departamento.getDescricao();
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
				out.println("<form name='frmDepartamento2' action='ControleDepartamentoServletAction' method='post'>");
				out.println(
						"<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Altere os dados</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Departamento: </label>");
				out.println(
						"<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodDepto' maxlength='10'+"
								+ " readonly size='10' value='" + codigo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtDepartamento'"
								+ " maxlength='45' size='60' value='" + descricao + "'><br><br>");
				out.println("<input type='submit' name='btnOpcao' value='Confirmar Alteração'>");
				out.println("<input type='submit' name='btnOpcao' value='Cancelar'>");
				out.println("</form>");
				out.println("<p><a href='DepartamentoAlterar.jsp'/>Retornar para Departamento Alterar</a></p>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Confirmar Alteração")) {
				retorno = "DepartamentoAlterar.jsp";
				nomeretorno = "Departamento Alterar";
				if (verificarCodigo == null || verificarCodigo == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigo);
				DepartamentoHibernate departamento = new DepartamentoHibernate();
				departamento.setCodDepto(codigo);
				departamento.setDescricao(descricao);
				daodepartamento.alterar(departamento);
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
				out.println("<form name='frmDepartamento2' action='DepartamentoAlterar.jsp' method='post'>");
				out.println(
						"<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Departamento</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Departamento: </label>");
				out.println(
						"<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodDepto' maxlength='10'+"
								+ " readonly size='10' value='" + codigo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtDepartamento'"
								+ " maxlength='45' readonly size='60' value='" + descricao + "'><br><br>");
				out.println("<input style='font-size:110%;font-weight:bold;background:black;color: white;'"
						+ " type='submit' name='btnOpcao' value='Retornar Departamento Alterar'>");
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