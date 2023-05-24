<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.FuncionarioHibernate"%>
<%@ page import="dao.DaoFuncionarioHibernate"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/estilo.css" />
<script type="text/javascript" src="jquery/jquery-1.11.1.min.js"></script>
<script language="javascript">
$(document).ready(function(){
   //Quando o mouse entrar dentro do elemento de id="departamento"
   $("#departamento").mouseenter(function(evento) {
     //Faça o elemento de id="mensagem" ficar visivel
     $("#mensagem").css("display", "");
   });
   
   //Quando o mouse sair de dentro do elemento de id="departamento"
   $("#departamento").mouseleave(function(evento){
     //Faça o elemento de id="mensagem" ficar invisivel
     $("#mensagem").css("display", "none");
   });
   
   $("#funcionario").mouseenter(function(evento) {
     //Faça o elemento de id="mensagem" ficar visivel
     $("#mensagem2").css("display", "");
   });  
   $("#funcionario").mouseleave(function(evento){
     //Faça o elemento de id="mensagem" ficar invisivel
     $("#mensagem2").css("display", "none");
   });

		var alturaTela = $(document).height();
		var larguraTela = $(window).width();
	
		//colocando o fundo preto
		$('#mascara').css({'width':larguraTela,'height':alturaTela});
		$('#mascara').fadeIn(1000);	
		$('#mascara').fadeTo("slow",0.8);

		var left = ($(window).width() /2) - ( $("#janela1").width() / 2 );
		var top = ($(window).height() / 2) - ( $("#janela1").height() / 2 );
	
		$("#janela1").css({'top':top,'left':left});
		$("#janela1").show();	
		$("img").css("Heigth","100");
});
</script>
<title>Tela Funcionário - Alterar</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	DaoFuncionarioHibernate daofuncionariohibernate = new DaoFuncionarioHibernate();
	ArrayList<FuncionarioHibernate> funcionarios = (ArrayList) daofuncionariohibernate.listar();
	%>
	<div class="window2" id="janela1" rel="modal">
		<h1 style="text-align: center;">Funcionário - Alterar</h1>
		<table>
			<form name="frmFuncionario" action="ControleFuncionarioServletAction"
				method="post">
				<caption
					style="font-size: 200%; padding-top: 10px; padding-bottom: 10px;">Consultar</caption>
				<tr>
					<th class="op2" style="font-size: 100%;">Funcionário(a):</th>
					<td style='font-size: 110%; font-weight: bold; color: darkblue;'>
						<select class="op2" name="selFuncionario">
							<%
							for (FuncionarioHibernate funcionario : funcionarios) {
							%>
							<option value="<%=funcionario.getMatricula()%>"><%=funcionario.getNomeA()%></option>
							<%
							}
							%>
					</select> <br>
					<br>
					</td>
				</tr>
				<input type="hidden" name="txtNascimento" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtSalario" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtCargo" size="30" maxlength="30"
					value="">
				</td> <input type="hidden" name="txtNomeA" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtFiliacaoPai" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtFiliacaoMae" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtCpf" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtRg" size="10" maxlength="10" value="">
				</td> <input type="hidden" name="txtOrgaoemissor" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtDatadeValidade" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtNaturalidade" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtNacionalidade" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtDataNascimento" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtSexo" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtEstadoCivil" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtLogradouro" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtNumero" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtComplemento" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtMunicipio" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtEstado" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtTelefone" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtNumeroDependentes" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtCtps" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtTituloEleitor" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtZona" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name=txtSecao size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtComprovanteReservista" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtPis" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtDataAdmissao" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="txtDataDemissao" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="selCargo" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="selDepartamento" size="10"
					maxlength="10" value="">
				</td> <input type="hidden" name="selFuncionario" size="10" maxlength="10"
					value="">
				</td> <input type="hidden" name="txtCodFuncionario" size="10"
					maxlength="10" value="">
				</td>






				<tr>
					<input type="hidden" name="txtMatricula" maxlength="15" value="0">
					<td class="op1"><input type="submit" value="Alterar"
						name="btnOpcao"></td>
					<td class="op1"><input type="submit" value="Cancelar"
						name="btnOpcao"></td>
				</tr>
			</form>
		</table>
		<h3>
			Retornar Tela Principal: <a href="TelaFuncionario.jsp" />Clicar</a>
		</h3>
	</div>
	<div id="mascara"></div>
</body>
</html>