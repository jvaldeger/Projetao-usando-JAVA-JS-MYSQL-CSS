package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFornecedorHibernate;
import modelo.FornecedorHibernate;

public class ControleFornecedorServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleFornecedorServletAction() {
		super();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("txtNome");
		String c = request.getParameter("txtCnpj");
		String cidade = request.getParameter("txtCidade");
		String estado = request.getParameter("txtEstado");
		int codigo;	
		int cnpj;
		String verificarCodigo=request.getParameter("txtCodFornecedor");
		String verificarCodigoConsulta=request.getParameter("selFornecedor");
		String botao = request.getParameter("btnOpcao");
		PrintWriter out = response.getWriter();
		HttpSession session =  request.getSession();
		String resultado;
		String nomeretorno="",retorno="";		
		try {
			DaoFornecedorHibernate daofornecedor = new DaoFornecedorHibernate();	
		
			if (botao.equals("Cadastrar")) {
				retorno="FornecedorCadastrar.jsp";
				nomeretorno="Fornecedor Cadastrado";
				if (nome.equals("")||c.equals("")||cidade.equals("")) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				
				cnpj = Integer.parseInt(c);
				
				FornecedorHibernate fornecedor = new FornecedorHibernate();
				
				fornecedor.setNome(nome);
				fornecedor.setCnpj(cnpj);
				fornecedor.setCidade(cidade);
				fornecedor.setEstado(estado);
				
				daofornecedor.incluir(fornecedor);
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Cadastro realizado com sucesso</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do fornecedor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCodFornecedor()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getNome()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cnpj: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCnpj()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCidade()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getEstado()+"</label><br><br>");	
				out.println("<a href='FornecedorCadastrar.jsp'/>Fornecedor Cadastrar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Cancelar")) {
				String redirectURL = "Telafornecedor.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Consultar")) {
				retorno="ClienteConsultar.jsp";
				nomeretorno="Cliente Consultado";
				if (verificarCodigoConsulta==null || verificarCodigoConsulta=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo=Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do fornecedor
				FornecedorHibernate fornecedor = daofornecedor.consultar(codigo);
				
				if (fornecedor == null){
					throw new Exception ("fornecedor não está Cadastrado.");
				}
				daofornecedor.consultar(codigo);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");				
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");				
				out.println("<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h2>");					
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do fornecedor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCodFornecedor()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getNome()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cnpj: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCnpj()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCidade()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getEstado()+"</label><br><br>");	
				
				out.println("<a href='FornecedorConsultar.jsp'/>Retornar para Fornecedor Consultar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Excluir")) {
				retorno="fornecedorExcluir.jsp";
				nomeretorno="fornecedor Excluido";
				if (verificarCodigoConsulta==null || verificarCodigoConsulta=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo=Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do fornecedor
				FornecedorHibernate fornecedor = daofornecedor.consultar(codigo);
				
				if (fornecedor == null){
					throw new Exception ("fornecedor não está Cadastrado.");
				}				
				daofornecedor.excluir(fornecedor);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");					
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");				
				out.println("<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Exclusão realizada com sucesso</h2>");		
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do fornecedor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCodFornecedor()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getNome()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cnpj: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCnpj()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getCidade()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+fornecedor.getEstado()+"</label><br><br>");	
				
				out.println("<a href='FornecedorExcluir.jsp'/>Retornar para Fornecedor Excluir</a>");
				out.println("</body>");
				out.println("</html>");
			}						
			if (botao.equals("Alterar")) {
				retorno = "fornecedorAlterar.jsp";
				nomeretorno = "fornecedor Alterado";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do fornecedor
				FornecedorHibernate fornecedor = daofornecedor.consultar(codigo);
				if (fornecedor == null) {
					throw new Exception("fornecedor não está Cadastrado.");
				}
				codigo = fornecedor.getCodFornecedor();
				nome = fornecedor.getNome();
				cnpj = fornecedor.getCnpj();
				cidade = fornecedor.getCidade();
				estado = fornecedor.getEstado();
				
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
				out.println("<form name='frmfornecedor2' action='ControleFornecedorServletAction' method='post'>");
				out.println(
						"<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Altere os dados</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do fornecedor: </label>");
				out.println(
						"<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodFornecedor' maxlength='10'+"
								+ " readonly size='10' value='" + codigo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtNome'"
								+ " maxlength='45' size='60' value='" + nome + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cpnj: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCnpj'"
								+ " maxlength='45' size='60' value='" + cnpj + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cidade: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCidade'"
								+ " maxlength='45' size='60' value='" + cidade + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtEstado'"
								+ " maxlength='45' size='60' value='" + estado + "'><br><br>");
				out.println("<input type='submit' name='btnOpcao' value='Confirmar Alteração'>");
				out.println("<input type='submit' name='btnOpcao' value='Cancelar'>");
				out.println("</form>");
				out.println("<p><a href='FornecedorAlterar.jsp'/>Retornar para Fornecedor Alterar</a></p>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Confirmar Alteração")) {
				retorno = "FornecedorAlterar.jsp";
				nomeretorno = "Fornecedor Alterado";
				if (verificarCodigo == null || verificarCodigo == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigo);
				FornecedorHibernate fornecedor = new FornecedorHibernate();
				cnpj = Integer.parseInt(c);
				fornecedor.setCodFornecedor(codigo);
				fornecedor.setNome(nome);
				fornecedor.setCnpj(cnpj);
				fornecedor.setCidade(cidade);
				fornecedor.setEstado(estado);

				daofornecedor.alterar(fornecedor);
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
				out.println("<form name='frmfornecedor2' action='FornecedorAlterar.jsp' method='post'>");
				
				out.println("<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>fornecedor</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Código do fornecedor: </label>");
				out.println("<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodfornecedor' maxlength='10'+"+ " readonly size='10' value='" + codigo + "'><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Fornecedor: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtNome'"
								+ " maxlength='45' readonly size='60' value='" + nome + "'><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Cnpj: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCnpj'"+ " maxlength='45' readonly size='60' value='" + cnpj + "'><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Cidade: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCidade'"+ " maxlength='45' readonly size='60' value='" + cidade + "'><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtEstado'"+ " maxlength='45' readonly size='60' value='" + estado + "'><br><br>");
				
				out.println("<input style='font-size:110%;font-weight:bold;background:black;color: white;'"+ " type='submit' name='btnOpcao' value='Retornar fornecedor Alterar'>");
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
			out.println("<a href='"+retorno+"'/>Retornar para o "+nomeretorno+"</a>");
			out.println("</body>");
			out.println("</html>");
			
		}
	}
}