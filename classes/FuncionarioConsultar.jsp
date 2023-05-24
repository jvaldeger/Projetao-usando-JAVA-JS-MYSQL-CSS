<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="modelo.FuncionarioHibernate" %>
<%@ page import="dao.DaoFuncionarioHibernate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<title>Tela Funcionário - Consultar</title>
</head>
<body>
<% 
	request.setCharacterEncoding("UTF-8");
	DaoFuncionarioHibernate daofuncionariohibernate = new DaoFuncionarioHibernate();	
	ArrayList<FuncionarioHibernate> funcionarios = (ArrayList) daofuncionariohibernate.listar();	
%>
<div class="window2" id="janela1" rel="modal">
<h1 style="text-align: center;">Funcionário - Consultar</h1>
<table>
<form name="frmFuncionario" action="ControleFuncionarioServletAction" method="post">
	<caption style="font-size:200%;padding-top:10px;padding-bottom:10px;">Consultar</caption>
	<tr>
	<th class="op2" style="font-size:100%;">Funcionário(a): </th>	
	<td style='font-size:110%;font-weight:bold;color: darkblue;'>
	<select class="op2" name="selFuncionario">	
	<% for(FuncionarioHibernate funcionario : funcionarios){  %>
	   <option value="<%=funcionario.getMatricula()%>"><%=funcionario.getNomeA()%></option>
	<% }  %>
	</select> <br><br></td>
	</tr>	
	<input type="hidden" name="txtNascimento" size="10" maxlength="10" value=""></td>
	<input type="hidden" name="txtSalario" size="10" maxlength="10" value=""></td>
	<input type="hidden" name="txtCargo" size="30" maxlength="30" value=""></td>
	<tr>		
	<input type="hidden" name="txtCodFuncionario" maxlength="15" value="0">
	<td class="op1"><input type="submit" value="Consultar" name="btnOpcao"></td>
	<td class="op1"><input type="submit" value="Cancelar" name="btnOpcao"></td>
	</tr>
</form>
</table>
<h3>Retornar Tela Principal: <a href="TelaFuncionario.jsp" />Clicar</a></h3>
</div>
<div id="mascara"></div>
</body>
</html>