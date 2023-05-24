<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="modelo.DepartamentoHibernate" %>
<%@ page import="dao.DaoDepartamentoHibernate" %>
<%@ page import="modelo.CargoHibernate" %>
<%@ page import="dao.DaoCargoHibernate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
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
   
   $("#departamento").mouseenter(function(evento) {
     //Faça o elemento de id="mensagem" ficar visivel
     $("#mensagem2").css("display", "");
   });  
   $("#departamento").mouseleave(function(evento){
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
<title>Tela Funcionário - Cadastrar</title>
</head>
<body>
<% 
	DaoDepartamentoHibernate daodepartamentohibernate = new DaoDepartamentoHibernate();
	ArrayList<DepartamentoHibernate> departamentos = (ArrayList) daodepartamentohibernate.listar();
	DaoCargoHibernate daocargohibernate = new DaoCargoHibernate();
	ArrayList<CargoHibernate> cargos = (ArrayList) daocargohibernate.listar();
%>
<div class="window2" id="janela1" rel="modal">
<h1 style="text-align: center;">Funcionário</h1>
<table>
<form name="frmFuncionario" action="ControleFuncionarioServletAction" method="post">
	<caption style="font-size:200%;padding-top:10px;padding-bottom:10px;">Cadastrar</caption>
	<tr>
	<th class="op2" style="font-size:100%;">Matrícula: </th>
	<td class="op2" style="font-size:100%;"><input type="text" autofucos name="txtMatricula" size="60" maxlength="60" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Nome: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtNomeA" size="12" maxlength="45" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">FiliaçãoPai: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtFiliacaoPai" size="12" maxlength="45" value=""></td>
	</tr>
	
		<tr>
	<th class="op2" style="font-size:100%;">FiliaçãoMae: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtFiliacaoMae" size="12" maxlength="45"  value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">CPF: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtCpf" size="12" maxlength="14" placeholder="***.***.***-**" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">RG: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtRg" size="12" maxlength="15" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Orgão Emissor: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtOrgaoemissor" size="12" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Data de Validade: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtDatadeValidade" size="12" maxlength="12" placeholder="00/00/0000" value=""></td>
	</tr>
			
	<tr>
	<th class="op2" style="font-size:100%;">Naturalidade: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtNaturalidade" size="12" maxlength="12" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Nacionalidade: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtNacionalidade" size="12" maxlength="45" value=""></td>
	</tr>				
			
	<tr>
	<th class="op2" style="font-size:100%;">Data de Nascimento: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtDataNascimento" size="12" maxlength="12" placeholder="00/00/0000" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Sexo: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtSexo" size="10" maxlength="10" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Estado Civil: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtEstadoCivil" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Logradouro: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtLogradouro" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Numero: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtNumero" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Complemento: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtComplemento" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Cep: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtCep" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Bairro: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtBairro" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Municipio: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtMunicipio" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Estado: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtEstado" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Telefone : </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtTelefone" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Numero Dependentes: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtNumeroDependentes" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Ctps: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtCtps" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Titulo de Eleitor: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtTituloEleitor" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Zona: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtZona" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Seção: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtSecao" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Comprovante Reservista: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtComprovanteReservista" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">PIS: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtPis" size="30" maxlength="30" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Data de Admissão: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtDataAdmissao" size="30" maxlength="12" placeholder="00/00/0000" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Data de Demissão: </th>
	<td class="op2" style="font-size:100%;"><input type="text" name="txtDataDemissao" size="30" maxlength="12" placeholder="00/00/0000" value=""></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Departamento: </th>
	<td class="op2" style="font-size:100%;"><select name="selDepartamento">
	<% for(DepartamentoHibernate departamento : departamentos){ %>
	<option value="<%= departamento.getIdCodDepto() %>"><%= departamento.getDescricao() %></option>
	<% } // fim do for %>  	
	<option value="" selected>Selecionar...</option>
	</select> <br></td>
	</tr>
	
	<tr>
	<th class="op2" style="font-size:100%;">Cargo: </th>
	<td class="op2" style="font-size:100%;"><select name="selCargo">
	<% for(CargoHibernate cargo : cargos){ %>
	<option value="<%= cargo.getCodCargo() %>"><%= cargo.getCargo() %></option>
	<% } // fim do for %> 
	<option value="" selected>Selecionar...</option>
	</select> <br></td>
	</tr>	
	<input type="hidden" name="txtCodFuncionario" maxlength="15" value="0">
	<td class="op1"><input type="submit" value="Cadastrar" name="btnOpcao"></td>
	<td class="op1"><input type="reset" value="Limpar" name="btnLimpar"></td>
	</tr>
</form>
</table>
<h3>Retornar Tela Principal: <a href="TelaFuncionario.jsp" />Clicar</a></h3>
</div>
<div id="mascara"></div>
</body>
</html>