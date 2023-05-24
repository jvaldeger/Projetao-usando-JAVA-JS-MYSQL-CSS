package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.CargoHibernate;
import modelo.DepartamentoHibernate;
import dao.DaoCargoHibernate;
import dao.DaoDepartamentoHibernate;
import modelo.FuncionarioHibernate;
import dao.DaoFuncionarioHibernate;

public class ControleFuncionarioServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControleFuncionarioServletAction() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String m = request.getParameter("txtMatricula");
		String nomeA = request.getParameter("txtNomeA");
		String FiliacaoPai = request.getParameter("txtFiliacaoPai");
		String FiliacaoMae = request.getParameter("txtFiliacaoMae");
		String cpf = request.getParameter("txtCpf");
		String rg = request.getParameter("txtRg"); 
		String orgaoemissor= request.getParameter("txtOrgaoemissor");
		String datav = request.getParameter("txtDatadeValidade");
		String naturalidade = request.getParameter("txtNaturalidade");
		 String nacionalidade = request.getParameter("txtNacionalidade");
		 String dn = request.getParameter("txtDataNascimento");
		 String sexo = request.getParameter("txtSexo");
		 String estadocivil = request.getParameter("txtEstadoCivil");
		 String logradouro = request.getParameter("txtLogradouro");
		 String numero = request.getParameter("txtNumero");
		 String complemento = request.getParameter("txtComplemento"); 
		 String cep= request.getParameter("txtCep");
		 String bairro= request.getParameter("txtBairro");
		 String municipio= request.getParameter("txtMunicipio");
		 String estado= request.getParameter("txtEstado");
		 String telefone= request.getParameter("txtTelefone");
		 String nd = request.getParameter("txtNumeroDependentes");
		 String ctps = request.getParameter("txtCtps");
		 String te = request.getParameter("txtTituloEleitor") ;
		 String z = request.getParameter("txtZona");
		 String s = request.getParameter("txtSecao") ;
		 String comprovantereservista=request.getParameter("txtComprovanteReservista");
		 String pis= request.getParameter("txtPis");
		 String dta= request.getParameter("txtDataAdmissao");
		 String dtd= request.getParameter("txtDataDemissao");
		
		String idcargo = request.getParameter("selCargo");
		String iddepartamento = request.getParameter("selDepartamento");
		String selfuncionario = request.getParameter("selFuncionario");
		
		String verificarCodigo=request.getParameter("txtMatricula");
		String verificarCodigoConsulta=request.getParameter("selFuncionario");
		
		int codigo;
		int matricula;
		int numerodependentes; 
		int codcargo;
		int coddepartamento;
		int tituloeleitor;
		int zona;
		int secao;
		
		Date datademissao;
		Date dataadmissao;
		Date datavalidade,nascimento;
		
		String botao = request.getParameter("btnOpcao");
		PrintWriter out = response.getWriter();
		HttpSession session =  request.getSession();
		String resultado;
		String nomeretorno="",retorno="";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 		
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			DaoDepartamentoHibernate daodepartamentohibernate = new DaoDepartamentoHibernate();
			ArrayList<DepartamentoHibernate> departamentos = (ArrayList) daodepartamentohibernate.listar();
			DaoCargoHibernate daocargohibernate = new DaoCargoHibernate();
			ArrayList<CargoHibernate> cargos = (ArrayList) daocargohibernate.listar();
			DaoFuncionarioHibernate daofuncionario = new DaoFuncionarioHibernate();
			if (botao.equals("Cancelar")) {
				String redirectURL = "TelaFuncionario.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Cadastrar")) {
				retorno="FuncionarioCadastrar.jsp";
				nomeretorno="Funcionario Cadastrado";
				if ( m.equals("") ||nomeA.equals("") ||FiliacaoPai.equals("") ||FiliacaoMae.equals("") ||cpf.equals("") ||rg.equals("") ||orgaoemissor.equals("")||datav.equals("") ||naturalidade.equals("") ||nacionalidade.equals("") ||
						dn.equals("") ||sexo.equals("") ||estadocivil.equals("") ||logradouro.equals("") ||numero.equals("") ||cep.equals("") ||bairro.equals("") ||municipio.equals("") ||estado.equals("") ||telefone.equals("") ||
						nd.equals("") ||ctps.equals("") ||te.equals("") ||z.equals("") ||s.equals("") ||comprovantereservista.equals("")||pis.equals("") ||idcargo.equals("") ||iddepartamento.equals("") ||dta.equals("")) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				
				
				DepartamentoHibernate departamentohibernate = new DepartamentoHibernate();
				departamentohibernate.setCodDepto(Integer.parseInt(iddepartamento));
				
				CargoHibernate cargohibernate = new CargoHibernate();
				cargohibernate.setCodCargo(Integer.parseInt(idcargo));
				
				FuncionarioHibernate funcionario = new FuncionarioHibernate();
				
				matricula = Integer.parseInt(m);
				funcionario.setMatricula(matricula);
				
				funcionario.setNomeA(nomeA);
				funcionario.setFiliacaoMae(FiliacaoMae);
				funcionario.setFiliacaoPai(FiliacaoPai);
				funcionario.setCpf(cpf);
				funcionario.setRg(rg);
				funcionario.setOrgaoemissor(orgaoemissor);
				
				datavalidade = formato.parse(datav);
				funcionario.setDatadevalidade(datavalidade);
				
				funcionario.setNaturalidade(naturalidade);
				funcionario.setNacionalidade(nacionalidade);
				
				nascimento = formato.parse(dn);
				funcionario.setNascimento(nascimento);
				
				funcionario.setSexo(sexo);
				funcionario.setEstadocivil(estadocivil);
				funcionario.setLogradouro(logradouro);
				funcionario.setNumero(numero);
				funcionario.setComplemento(complemento);
				funcionario.setCep(cep);
				funcionario.setBairro(bairro);
				funcionario.setMunicipio(municipio);
				funcionario.setEstado(estado);
				funcionario.setTelefone(telefone);
				
				numerodependentes = Integer.parseInt(nd);
				funcionario.setNumerodependentes(numerodependentes);
				
				funcionario.setCtps(ctps);
				
				tituloeleitor=Integer.parseInt(te);
				funcionario.setTituloeleitor(tituloeleitor);
				
				zona=Integer.parseInt(z);
				funcionario.setZona(zona);
				
				secao=Integer.parseInt(s);
				funcionario.setSecao(secao);
				
				funcionario.setComprovantereservista(comprovantereservista);
				funcionario.setPis(pis);

				dataadmissao=formato.parse(dta);
				funcionario.setDataadimissao(dataadmissao);
				
				datademissao=formato.parse(dtd);
				funcionario.setDatademissao(datademissao);
				
				funcionario.setDepartamento(departamentohibernate);
				funcionario.setCargo(cargohibernate);
				
				daofuncionario.incluir(funcionario);			
				departamentohibernate=daodepartamentohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				String descricao= departamentohibernate.getDescricao();
				
				cargohibernate=daocargohibernate.consultar(funcionario.getCargo().getCodCargo());
				String cargo= cargohibernate.getCargo();
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");	
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Cadastro realizado com sucesso</h1>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Matrícula: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMatricula()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionário(a): </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNomeA()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Pai: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoPai()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Mãe: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoMae()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>CPF: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCpf()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>RG: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getRg()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Orgão Emissor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getOrgaoemissor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatadevalidade())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Naturalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNaturalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nacionalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNacionalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nascimento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getNascimento())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Sexo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSexo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado Civil: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstadocivil()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Logradouro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getLogradouro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Número: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumero()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Complemento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComplemento()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cep: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCep()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Bairro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getBairro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Município: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMunicipio()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstado()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Telefone: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTelefone()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Numero de Dependentes: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumerodependentes()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Ctps: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCtps()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Titulo de Eleitor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTituloeleitor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Zona: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getZona()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Seção: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSecao()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Comprovante Reservista: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComprovantereservista()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Pis: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getPis()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Admissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDataadimissao())+"</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Demissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatademissao())+"</label><br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Departamento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getDepartamento().getIdCodDepto()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'><select name='selDepartamento'>");		
				for(DepartamentoHibernate departamento : departamentos){
					if (departamento.getIdCodDepto()==funcionario.getDepartamento().getIdCodDepto())
					{
						out.println("<option value="+departamento.getIdCodDepto()+">"+departamento.getDescricao()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'>"+funcionario.getCargo().getCodCargo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selCargo'>");
				for(CargoHibernate cargoo : cargos){
					if (cargoo.getCodCargo()==funcionario.getCargo().getCodCargo())
					{
						out.println("<option value="+cargoo.getCodCargo()+">"+cargoo.getCargo()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='"+retorno+"'"+"/>"+nomeretorno+"</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Consultar")) {
				retorno="FuncionarioConsultar.jsp";
				nomeretorno="Funcionario Consultado";
				if (selfuncionario==null || selfuncionario=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				
				codigo=Integer.parseInt(verificarCodigoConsulta);
				
				FuncionarioHibernate funcionario = new FuncionarioHibernate();
				DepartamentoHibernate departamentohibernate = new DepartamentoHibernate();
				CargoHibernate cargohibernate = new CargoHibernate();	
				
				funcionario=daofuncionario.consultar(codigo);
				
				departamentohibernate=daodepartamentohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				cargohibernate=daocargohibernate.consultar(funcionario.getCargo().getCodCargo());
				
				String cargo = cargohibernate.getCargo();
				String descricao= departamentohibernate.getDescricao();
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");	
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h1>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Matrícula: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMatricula()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionário(a): </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNomeA()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Pai: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoPai()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Mãe: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoMae()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>CPF: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCpf()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>RG: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getRg()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Orgão Emissor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getOrgaoemissor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatadevalidade())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Naturalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNaturalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nacionalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNacionalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nascimento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getNascimento())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Sexo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSexo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado Civil: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstadocivil()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Logradouro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getLogradouro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Número: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumero()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Complemento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComplemento()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cep: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCep()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Bairro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getBairro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Município: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMunicipio()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstado()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Telefone: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTelefone()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Numero de Dependentes: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumerodependentes()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Ctps: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCtps()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Titulo de Eleitor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTituloeleitor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Zona: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getZona()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Seção: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSecao()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Comprovante Reservista: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComprovantereservista()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Pis: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getPis()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Admissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDataadimissao())+"</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Demissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatademissao())+"</label><br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Departamento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getDepartamento().getIdCodDepto()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'><select name='selDepartamento'>");		
				for(DepartamentoHibernate departamento : departamentos){
					if (departamento.getIdCodDepto()==funcionario.getDepartamento().getIdCodDepto())
					{
						out.println("<option value="+departamento.getIdCodDepto()+">"+departamento.getDescricao()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'>"+funcionario.getCargo().getCodCargo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selCargo'>");
				for(CargoHibernate cargoo : cargos){
					if (cargoo.getCodCargo()==funcionario.getCargo().getCodCargo())
					{
						out.println("<option value="+cargoo.getCodCargo()+">"+cargoo.getCargo()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='"+retorno+"'"+"/>"+nomeretorno+"</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Excluir")) {
				retorno="FuncionarioExcluir.jsp";
				nomeretorno="Funcionario Excluido";
				if (selfuncionario==null || selfuncionario=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}				
				codigo=Integer.parseInt(selfuncionario);
				FuncionarioHibernate funcionario = new FuncionarioHibernate();
				DepartamentoHibernate departamentohibernate = new DepartamentoHibernate();
				CargoHibernate cargohibernate = new CargoHibernate();
				funcionario=daofuncionario.consultar(codigo);		
				departamentohibernate=daodepartamentohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				cargohibernate=daocargohibernate.consultar(funcionario.getCargo().getCodCargo());

				String descricao= departamentohibernate.getDescricao();
				String cargo = cargohibernate.getCargo();				
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");	
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Consulta realizada com sucesso</h1>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Matrícula: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMatricula()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionário(a): </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNomeA()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Pai: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoPai()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Mãe: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoMae()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>CPF: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCpf()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>RG: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getRg()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Orgão Emissor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getOrgaoemissor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatadevalidade())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Naturalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNaturalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nacionalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNacionalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nascimento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getNascimento())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Sexo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSexo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado Civil: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstadocivil()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Logradouro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getLogradouro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Número: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumero()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Complemento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComplemento()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cep: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCep()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Bairro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getBairro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Município: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMunicipio()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstado()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Telefone: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTelefone()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Numero de Dependentes: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumerodependentes()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Ctps: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCtps()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Titulo de Eleitor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTituloeleitor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Zona: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getZona()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Seção: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSecao()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Comprovante Reservista: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComprovantereservista()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Pis: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getPis()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Admissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDataadimissao())+"</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Demissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatademissao())+"</label><br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Departamento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getDepartamento().getIdCodDepto()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'><select name='selDepartamento'>");		
				for(DepartamentoHibernate departamento : departamentos){
					if (departamento.getIdCodDepto()==funcionario.getDepartamento().getIdCodDepto())
					{
						out.println("<option value="+departamento.getIdCodDepto()+">"+departamento.getDescricao()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'>"+funcionario.getCargo().getCodCargo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selCargo'>");
				for(CargoHibernate cargoo : cargos){
					if (cargoo.getCodCargo()==funcionario.getCargo().getCodCargo())
					{
						out.println("<option value="+cargoo.getCodCargo()+">"+cargoo.getCargo()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<form name='frmFuncionario' action='ControleFuncionarioServletAction' method='post'>");
				out.println("<input type='submit' name='btnOpcao' value='Confirmar Exclusão'>");
				out.println("<input type='submit' name='btnOpcao' value='Cancelar Exclusão'>");
				out.println("<input type='hidden' name='txtMatricula' maxlength='20' value='"+funcionario.getMatricula()+"'>");								
				out.println("</form>");
				out.println("<a href='"+retorno+"'"+"/>"+nomeretorno+"</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Confirmar Exclusão")) {
				retorno="FuncionarioExcluir.jsp";
				nomeretorno="Funcionario Excluir";
				if (verificarCodigo==null || verificarCodigo=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo=Integer.parseInt(verificarCodigo);
				// consultar pelo id do Funcionário
				FuncionarioHibernate funcionario = daofuncionario.consultar(codigo);
				
				if (funcionario == null){
					throw new Exception ("Funcionário não está Cadastrado.");
				}			
				DepartamentoHibernate departamentohibernate = new DepartamentoHibernate();
				daofuncionario.excluir(funcionario);
				
				departamentohibernate=daodepartamentohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				String descricao= departamentohibernate.getDescricao();
				
				CargoHibernate cargohibernate = new CargoHibernate();
				cargohibernate=daocargohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				String cargo= cargohibernate.getCargo();
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");	
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Funcionário excluído com sucessoo</h1>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Matrícula: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMatricula()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionário(a): </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNomeA()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Pai: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoPai()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Mãe: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoMae()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>CPF: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCpf()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>RG: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getRg()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Orgão Emissor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getOrgaoemissor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatadevalidade())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Naturalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNaturalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nacionalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNacionalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nascimento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getNascimento())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Sexo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSexo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado Civil: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstadocivil()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Logradouro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getLogradouro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Número: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumero()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Complemento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComplemento()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cep: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCep()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Bairro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getBairro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Município: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMunicipio()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstado()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Telefone: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTelefone()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Numero de Dependentes: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumerodependentes()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Ctps: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCtps()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Titulo de Eleitor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTituloeleitor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Zona: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getZona()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Seção: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSecao()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Comprovante Reservista: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComprovantereservista()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Pis: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getPis()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Admissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDataadimissao())+"</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Demissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatademissao())+"</label><br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Departamento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getDepartamento().getIdCodDepto()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'><select name='selDepartamento'>");		
				for(DepartamentoHibernate departamento : departamentos){
					if (departamento.getIdCodDepto()==funcionario.getDepartamento().getIdCodDepto())
					{
						out.println("<option value="+departamento.getIdCodDepto()+">"+departamento.getDescricao()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'>"+funcionario.getCargo().getCodCargo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selCargo'>");
				for(CargoHibernate cargoo : cargos){
					if (cargoo.getCodCargo()==funcionario.getCargo().getCodCargo())
					{
						out.println("<option value="+cargoo.getCodCargo()+">"+cargoo.getCargo()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='"+retorno+"'"+"/>"+nomeretorno+"</a>");
				out.println("</body>");
				out.println("</html>");
			}
			if (botao.equals("Cancelar Exclusão")) {
				String redirectURL = "FuncionarioExcluir.jsp";
				response.sendRedirect(redirectURL);
			}
			if (botao.equals("Alterar")) {
				retorno="FuncionarioAlterar.jsp";
				nomeretorno="Funcionario Alterado";
				if (selfuncionario==null || selfuncionario=="") {
					throw new Exception("Favor preencher todas as caixas.");
				}				
				codigo=Integer.parseInt(selfuncionario);
				
				FuncionarioHibernate funcionario = new FuncionarioHibernate();
				CargoHibernate cargohibernate = new CargoHibernate();
				DepartamentoHibernate departamentohibernate = new DepartamentoHibernate();	
				
				funcionario=daofuncionario.consultar(codigo);	
				
				departamentohibernate=daodepartamentohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				String descricao= departamentohibernate.getDescricao();	
				
				cargohibernate=daocargohibernate.consultar(funcionario.getCargo().getCodCargo());
				String cargo= cargohibernate.getCargo();	
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");	
				out.println("<h1 style='text-align: center;'>Funcionário</h1>");
				out.println("<table>");
				out.println("<form name='frmFuncionario' action='ControleFuncionarioServletAction' method='post'>");
				out.println("<caption style='font-size:200%;padding-top:10px;padding-bottom:10px;'>Dados do Funcionário - Alterar </caption>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Matrícula: </th>");
				out.println("<td class='op2'><input type='text' readonly name='txtMatricula' size='60' maxlength='60' value='"+funcionario.getMatricula()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Nome: </th>");
				out.println("<td class='op2'><input type='text' autofocus name='txtNomeA' size='60' maxlength='60' value='"+funcionario.getNomeA()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>FiliaçãoPai: </th>");
				out.println("<td class='op2'><input type='text' name='txtFiliacaoPai' size='60' maxlength='60' value='"+funcionario.getFiliacaoPai()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>FiliaçãoMãe: </th>");
				out.println("<td class='op2'><input type='text' name='txtFiliacaoMae' size='60' maxlength='60' value='"+funcionario.getFiliacaoMae()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>CPF: </th>");
				out.println("<td class='op2'><input type='text' name='txtCpf' size='60' maxlength='60' value='"+funcionario.getCpf()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>RG: </th>");
				out.println("<td class='op2'><input type='text' name='txtRg' size='60' maxlength='60' value='"+funcionario.getRg()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Orgão Emissor: </th>");
				out.println("<td class='op2'><input type='text' name='txtOrgaoemissor' size='60' maxlength='60' value='"+funcionario.getOrgaoemissor()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Data de Validade: </th>");
				out.println("<td class='op2'><input type='text' name='txtDatadeValidade' size='8' maxlength='10' value='"+formato.format(funcionario.getDatadevalidade())+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Naturalidade: </th>");
				out.println("<td class='op2'><input type='text' name='txtNaturalidade' size='10' maxlength='10' value='"+funcionario.getNaturalidade()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Nacionalidade: </th>");
				out.println("<td class='op2'><input type='text' name='txtNacionalidade' size='10' maxlength='10' value='"+funcionario.getNacionalidade()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Data de Nascimento: </th>");
				out.println("<td class='op2'><input type='text' name='txtDataNascimento' size='8' maxlength='10' value='"+formato.format(funcionario.getNascimento())+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Sexo: </th>");
				out.println("<td class='op2'><input type='text' name='txtSexo' size='10' maxlength='10' value='"+funcionario.getSexo()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Estado Civil: </th>");
				out.println("<td class='op2'><input type='text' name='txtEstadoCivil' size='10' maxlength='10' value='"+funcionario.getEstadocivil()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Logradouro: </th>");
				out.println("<td class='op2'><input type='text' name='txtLogradouro' size='10' maxlength='10' value='"+funcionario.getLogradouro() +"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Numero: </th>");
				out.println("<td class='op2'><input type='text' name='txtNumero' size='10' maxlength='10' value='"+funcionario.getNumero()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Complemento: </th>");
				out.println("<td class='op2'><input type='text' name='txtComplemento' size='10' maxlength='10' value='"+funcionario.getComplemento()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Cep: </th>");
				out.println("<td class='op2'><input type='text' name='txtCep' size='10' maxlength='10' value='"+funcionario.getCep()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Bairro: </th>");
				out.println("<td class='op2'><input type='text' name='txtBairro' size='10' maxlength='10' value='"+funcionario.getBairro()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Município: </th>");
				out.println("<td class='op2'><input type='text' name='txtMunicipio' size='10' maxlength='10' value='"+funcionario.getMunicipio()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Estado: </th>");
				out.println("<td class='op2'><input type='text' name='txtEstado' size='10' maxlength='10' value='"+funcionario.getEstado()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Telefone: </th>");
				out.println("<td class='op2'><input type='text' name='txtTelefone' size='10' maxlength='10' value='"+funcionario.getTelefone()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Número de Dependes : </th>");
				out.println("<td class='op2'><input type='text' name='txtNumeroDependentes' size='10' maxlength='10' value='"+funcionario.getNumerodependentes()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Ctps: </th>");
				out.println("<td class='op2'><input type='text' name='txtCtps' size='10' maxlength='10' value='"+funcionario.getCtps()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Titulo Eleitor: </th>");
				out.println("<td class='op2'><input type='text' name='txtTituloEleitor' size='10' maxlength='10' value='"+funcionario.getTituloeleitor()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Zona: </th>");
				out.println("<td class='op2'><input type='text' name='txtZona' size='10' maxlength='10' value='"+funcionario.getZona()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Seção: </th>");
				out.println("<td class='op2'><input type='text' name='txtSecao' size='10' maxlength='10' value='"+funcionario.getSecao()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Comprovante Reservista: </th>");
				out.println("<td class='op2'><input type='text' name='txtComprovanteReservista' size='10' maxlength='10' value='"+funcionario.getComprovantereservista()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Pis: </th>");
				out.println("<td class='op2'><input type='text' name='txtPis' size='10' maxlength='10' value='"+funcionario.getPis()+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Data de Admissao: </th>");
				out.println("<td class='op2'><input type='text' name='txtDataAdmissao' size='8' maxlength='10' value='"+formato.format(funcionario.getDataadimissao())+"'></td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<th class='op2'>Data de Demissão: </th>");
				out.println("<td class='op2'><input type='text' name='txtDataDemissao' size='8' maxlength='10' value='"+formato.format(funcionario.getDatademissao())+"'></td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<th class='op2'>Departamento: </th>");
				out.println("<td class='op2'><select name='selDepartamento'>");
				for(DepartamentoHibernate departamento : departamentos){
					out.println("<option value="+departamento.getIdCodDepto()+">"+departamento.getDescricao()+"</option>");
				} // fim do for %>  	
				out.println("<option value='' selected>Selecionar...</option>");
				out.println("</select> <br></td>");
				out.println("</tr>");
			
				
				
				out.println("<tr>");
				out.println("<th class='op2'>Cargo: </th>");
				out.println("<td class='op2'><select name='selCargo'>");
				for(CargoHibernate cargoo : cargos){
					out.println("<option value="+cargoo.getCodCargo()+">"+cargoo.getCargo()+"</option>");
				} // fim do for %>  	
				out.println("<option value='' selected>Selecionar...</option>");
				out.println("</select> <br></td>");
				out.println("</tr>");
				
				out.println("<td class='op1'><input type='submit' value='Confirmar Alteração' name='btnOpcao'></td>");
				out.println("<td class='op1'><input type='reset' value='Cancelar Alteração' name='btnOpcao'></td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</table>");
				out.println("<h3>Retornar Tela Principal: <a href='TelaFuncionario.jsp' />Clicar</a></h3>");
				out.println("</div>");
				out.println("<div id='mascara'></div>");
				out.println("</body>");
				out.println("</html>");
			}
			
			if (botao.equals("Confirmar Alteração")) {
				retorno="FuncionarioAlterar.jsp";
				nomeretorno="Funcionario Alterar";
				if  (m.equals("") ||nomeA.equals("") ||FiliacaoPai.equals("") ||FiliacaoMae.equals("") ||cpf.equals("") ||rg.equals("") ||orgaoemissor.equals("")||datav.equals("") ||naturalidade.equals("") ||nacionalidade.equals("") ||
				dn.equals("") ||sexo.equals("") ||estadocivil.equals("") ||logradouro.equals("") ||numero.equals("") ||cep.equals("") ||bairro.equals("") ||municipio.equals("") ||estado.equals("") ||telefone.equals("") ||
				nd.equals("") ||ctps.equals("") ||te.equals("") ||z.equals("") ||s.equals("") ||comprovantereservista.equals("")||pis.equals("") ||idcargo.equals("") ||iddepartamento.equals("") ||dta.equals("")) {
					throw new Exception("Favor preencher todas as caixas.");
				}
				codigo=Integer.parseInt(verificarCodigo);
				FuncionarioHibernate funcionario = new FuncionarioHibernate();
				DepartamentoHibernate departamentohibernate = new DepartamentoHibernate();
				CargoHibernate cargohibernate = new CargoHibernate();
				
				departamentohibernate.setCodDepto(Integer.parseInt(iddepartamento));
				
				cargohibernate.setCodCargo(Integer.parseInt(idcargo));
				
			
				funcionario.setMatricula(codigo);
				funcionario.setNomeA(nomeA);
				funcionario.setFiliacaoMae(FiliacaoMae);
				funcionario.setFiliacaoPai(FiliacaoPai);
				funcionario.setCpf(cpf);
				funcionario.setRg(rg);
				funcionario.setOrgaoemissor(orgaoemissor);
				
				datavalidade = formato.parse(datav);
				funcionario.setDatadevalidade(datavalidade);
				
				funcionario.setNaturalidade(naturalidade);
				funcionario.setNacionalidade(nacionalidade);
				
				nascimento = formato.parse(dn);
				funcionario.setNascimento(nascimento);
				
				funcionario.setSexo(sexo);
				funcionario.setEstadocivil(estadocivil);
				funcionario.setLogradouro(logradouro);
				funcionario.setNumero(numero);
				funcionario.setComplemento(complemento);
				funcionario.setCep(cep);
				funcionario.setBairro(bairro);
				funcionario.setMunicipio(municipio);
				funcionario.setEstado(estado);
				funcionario.setTelefone(telefone);
				
				numerodependentes = Integer.parseInt(nd);
				funcionario.setNumerodependentes(numerodependentes);
				
				funcionario.setCtps(ctps);
				
				tituloeleitor=Integer.parseInt(te);
				funcionario.setTituloeleitor(tituloeleitor);
				
				zona=Integer.parseInt(z);
				funcionario.setZona(zona);
				
				secao=Integer.parseInt(s);
				funcionario.setSecao(secao);
				
				funcionario.setComprovantereservista(comprovantereservista);
				funcionario.setPis(pis);

				dataadmissao=formato.parse(dta);
				funcionario.setDataadimissao(dataadmissao);
				
				datademissao=formato.parse(dtd);
				funcionario.setDatademissao(datademissao);
				
				funcionario.setDepartamento(departamentohibernate);
				funcionario.setCargo(cargohibernate);
				
				daofuncionario.alterar(funcionario);			
				departamentohibernate=daodepartamentohibernate.consultar(funcionario.getDepartamento().getIdCodDepto());
				String descricao= departamentohibernate.getDescricao();
				
				cargohibernate=daocargohibernate.consultar(funcionario.getCargo().getCodCargo());
				String cargo= cargohibernate.getCargo();
				
				out.println("<!doctype html>");
				out.println("<html lang='pt-br'>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='stylesheet' href='css/estilo.css' />");
				out.println("</head>");
				out.println("<body>");	
				out.println("<h1 style='font-size:150%;padding-top:10px;padding-bottom:10px;'>Alteração realizada com sucesso</h1>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Matrícula: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMatricula()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Funcionário(a): </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNomeA()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Pai: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoPai()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Filiação Mãe: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getFiliacaoMae()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>CPF: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCpf()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>RG: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getRg()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Orgão Emissor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getOrgaoemissor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Validade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatadevalidade())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Naturalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNaturalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nacionalidade: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNacionalidade()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Nascimento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getNascimento())+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Sexo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSexo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado Civil: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstadocivil()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Logradouro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getLogradouro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Número: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumero()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Complemento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComplemento()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cep: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCep()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Bairro: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getBairro()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Município: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getMunicipio()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Estado: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getEstado()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Telefone: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTelefone()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Numero de Dependentes: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getNumerodependentes()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Ctps: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getCtps()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Titulo de Eleitor: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getTituloeleitor()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Zona: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getZona()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Seção: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getSecao()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Comprovante Reservista: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getComprovantereservista()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Pis: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getPis()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Admissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDataadimissao())+"</label><br><br>");

				out.println("<label style='font-size:110%;font-weight:bold;'>Data de Demissão: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+formato.format(funcionario.getDatademissao())+"</label><br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Departamento: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'>"+funcionario.getDepartamento().getIdCodDepto()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Departamento: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'><select name='selDepartamento'>");		
				for(DepartamentoHibernate departamento : departamentos){
					if (departamento.getIdCodDepto()==funcionario.getDepartamento().getIdCodDepto())
					{
						out.println("<option value="+departamento.getIdCodDepto()+">"+departamento.getDescricao()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				
				out.println("<br><br><label style='font-size:110%;font-weight:bold;'>Cód. Cargo: </label>");				
				out.println("<label style='font-size:110%;font-weight:bold;color: black;'>"+funcionario.getCargo().getCodCargo()+"</label><br><br>");
				
				out.println("<label style='font-size:110%;font-weight:bold;'>Cargo: </label>");
				out.println("<label style='font-size:110%;font-weight:bold;color: darkblue;'><select name='selCargo'>");
				for(CargoHibernate cargoo : cargos){
					if (cargoo.getCodCargo()==funcionario.getCargo().getCodCargo())
					{
						out.println("<option value="+cargoo.getCodCargo()+">"+cargoo.getCargo()+"</option>");
					}
				} // fim do for %>
				out.println("</select> <br><br>");
				out.println("<a href='"+retorno+"'"+"/>"+nomeretorno+"</a>");
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
}//ULTIMO