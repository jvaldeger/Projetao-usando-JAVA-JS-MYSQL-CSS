package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoCargoHibernate;
import modelo.CargoHibernate;
import modelo.CargoHibernate;

public class ControleCargoServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
//joao pronto
	public ControleCargoServletAction() {
		super();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String nomecargo = request.getParameter("txtCargo");
		String s = request.getParameter("txtSalario");
		String cargahoraria = request.getParameter("txtCargaHoraria");
		int codigo;	
		double salario;
		String verificarCodigo=request.getParameter("txtCodCargo");
		String verificarCodigoConsulta=request.getParameter("selCargo");
		String botao = request.getParameter("btnOpcao");
		PrintWriter out = response.getWriter();
		HttpSession session =  request.getSession();
		String resultado;
		String nomeretorno="",retorno="";		
		try {
			DaoCargoHibernate daocargo = new DaoCargoHibernate();	
		
			if (botao.equals("Cadastrar")) {
				retorno="CargoCadastrar.jsp";
				nomeretorno="Cargo Cadastrado";
				if (nomecargo.equals("")||s.equals("")||cargahoraria.equals("")) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				
				salario = Double.parseDouble(s);
				
				CargoHibernate cargo = new CargoHibernate();
				
				cargo.setCargo(nomecargo);
				cargo.setSalario(salario);
				cargo.setCargahoraria(cargahoraria);
				
				daocargo.incluir(cargo);
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Cadastro realizado com sucesso</h1>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCodCargo()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCargo()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Salario: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getSalario()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Carga Horaria: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCargahoraria()+"</label><br><br>");			
				out.println("<a href='CargoCadastrar.jsp'/>Cargo Cadastrar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Cancelar")) {
				String redirectURL = "TelaCargo.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Consultar")) {
				retorno="ClienteConsultar.jsp";
				nomeretorno="Cliente Consultado";
				if (verificarCodigoConsulta==null || verificarCodigoConsulta=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo=Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Cargo
				CargoHibernate cargo = daocargo.consultar(codigo);
				
				if (cargo == null){
					throw new Exception ("Cargo não está Cadastrado.");
				}
				daocargo.consultar(codigo);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");				
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");				
				out.println("<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h2>");		
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCodCargo()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCargo()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Salario: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getSalario()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Carga Horária: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCargahoraria()+"</label><br><br>");
				
				out.println("<a href='CargoConsultar.jsp'/>Retornar para Cliente Consultar</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Excluir")) {
				retorno="CargoExcluir.jsp";
				nomeretorno="Cargo Excluido";
				if (verificarCodigoConsulta==null || verificarCodigoConsulta=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo=Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Cargo
				CargoHibernate cargo = daocargo.consultar(codigo);
				
				if (cargo == null){
					throw new Exception ("Cargo não está Cadastrado.");
				}				
				daocargo.excluir(cargo);
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");					
				out.println("<h1 style='text-align: center;'>Sistema de Banco de dados</h1>");				
				out.println("<h2 style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Exclusão realizada com sucesso</h2>");		
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCodCargo()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCargo()+"</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Salário: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getSalario()+"</label><br><br>");				
				out.println("<label style='font-size:110%;font-weight:bold;'>Carga Horária: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+cargo.getCargahoraria()+"</label><br><br>");
				
				out.println("<a href='CargoExcluir.jsp'/>Retornar para Cargo Excluir</a>");
				out.println("</body>");
				out.println("</html>");
			}						
			if (botao.equals("Alterar")) {
				retorno = "CargoAlterar.jsp";
				nomeretorno = "Cargo Alterado";
				if (verificarCodigoConsulta == null || verificarCodigoConsulta == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigoConsulta);
				// consultar pelo id do Cargo
				CargoHibernate cargo = daocargo.consultar(codigo);
				if (cargo == null) {
					throw new Exception("Cargo não está Cadastrado.");
				}
				codigo = cargo.getCodCargo();
				nomecargo = cargo.getCargo();
				salario = cargo.getSalario();
				cargahoraria = cargo.getCargahoraria();
				
				
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
				out.println("<form name='frmCargo2' action='ControleCargoServletAction' method='post'>");
				out.println(
						"<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Altere os dados</label><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Cargo: </label>");
				out.println(
						"<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodCargo' maxlength='10'+"
								+ " readonly size='10' value='" + codigo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCargo'"
								+ " maxlength='45' size='60' value='" + nomecargo + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Salário: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtSalario'"
								+ " maxlength='45' size='60' value='" + salario + "'><br><br>");
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println(
						"<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCargaHoraria'"
								+ " maxlength='45' size='60' value='" + cargahoraria + "'><br><br>");
				out.println("<input type='submit' name='btnOpcao' value='Confirmar Alteração'>");
				out.println("<input type='submit' name='btnOpcao' value='Cancelar'>");
				out.println("</form>");
				out.println("<p><a href='CargoAlterar.jsp'/>Retornar para Cargo Alterar</a></p>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Confirmar Alteração")) {
				retorno = "CargoAlterar.jsp";
				nomeretorno = "Cargo Alterado";
				if (verificarCodigo == null || verificarCodigo == "") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo = Integer.parseInt(verificarCodigo);
				CargoHibernate cargo = new CargoHibernate();
				salario = Float.parseFloat(s);
				cargo.setCodCargo(codigo);
				cargo.setCargo(nomecargo);
				cargo.setSalario(salario);
				cargo.setCargahoraria(cargahoraria);
				daocargo.alterar(cargo);
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
				out.println("<form name='frmCargo2' action='CargoAlterar.jsp' method='post'>");
				
				out.println("<label style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Cargo</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Código do Cargo: </label>");
				out.println("<input style='font-weight:bold;color: darkblue;' type='text' name='txtCodCargo' maxlength='10'+"+ " readonly size='10' value='" + codigo + "'><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCargo'"
								+ " maxlength='45' readonly size='60' value='" + nomecargo + "'><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Salário: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtSalario'"+ " maxlength='45' readonly size='60' value='" + salario + "'><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Carga Horária: </label>");
				out.println("<input style='font-size:110%;font-weight:bold;color: darkblue;' type='text' name='txtCargaHoraria'"+ " maxlength='45' readonly size='60' value='" + cargahoraria + "'><br><br>");
				
				out.println("<input style='font-size:110%;font-weight:bold;background:black;color: white;'"+ " type='submit' name='btnOpcao' value='Retornar Cargo Alterar'>");
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