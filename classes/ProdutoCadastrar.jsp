<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.DepositoHibernate"%>
<%@ page import="dao.DaoDepositoHibernate"%>
<%@ page import="modelo.FuncionarioHibernate"%>
<%@ page import="dao.DaoFuncionarioHibernate"%>
<%@ page import="modelo.FornecedorHibernate"%>
<%@ page import="dao.DaoFornecedorHibernate"%>
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
<title>Deposito - Web</title>
</head>
<body>
	<%
	DaoDepositoHibernate daodepositohibernate = new DaoDepositoHibernate();
	ArrayList<DepositoHibernate> depositos = (ArrayList) daodepositohibernate.listar();
	
	DaoFornecedorHibernate daofornecedorhibernate = new DaoFornecedorHibernate();
	ArrayList<FornecedorHibernate> fornecedores = (ArrayList) daofornecedorhibernate.listar();
	
	DaoFuncionarioHibernate daofucionariohibernate = new DaoFuncionarioHibernate();
	ArrayList<FuncionarioHibernate> funcionarios = (ArrayList) daofucionariohibernate.listar();
	%>
	<div class="window2" id="janela1" rel="modal">
		<h1 style="text-align: center;"></h1>
		<table>
			<form name="frmProduto" action="ControleProdutoServletAction"
				method="post">
				<caption
					style="font-size: 200%; padding-top: 10px; padding-bottom: 10px;">Cadastrar</caption>
				<tr>
					<th class="op2" style="font-size: 100%;">Produto:</th>
					<td class="op2" style="font-size: 100%;"><input type="text"
						autofucos name="txtNome" size="60" maxlength="60" value=""></td>
				</tr>
				<tr>
					<th class="op2" style="font-size: 100%;">Data de Validade:</th>
					<td class="op2" style="font-size: 100%;"><input type="text"
						name="txtValidade" size="12" maxlength="12"
						placeholder="00/00/0000" value=""></td>
				</tr>
				<tr>
					<th class="op2" style="font-size: 100%;">Valor:</th>
					<td class="op2" style="font-size: 100%;"><input type="text"
						name="txtValor" size="10" maxlength="10" value=""></td>
				</tr>
				<tr>
				<tr>
					<th class="op2" style="font-size: 100%;">Tipo:</th>
					<td class="op2" style="font-size: 100%;"><input type="text"
						name="txtTipo" size="30" maxlength="30" value=""></td>
				</tr>
				<tr>
					<th class="op2" style="font-size: 100%;">Deposito:</th>
					<td class="op2" style="font-size: 100%;"><select
						name="selDeposito">
							<%
							for (DepositoHibernate deposito : depositos) {
							%>
							<option value="<%=deposito.getIdCodDepto()%>"><%=deposito.getDescricao()%></option>
							<%
							} // fim do for
							%>
							<option value="" selected>Selecionar...</option>
					</select> <br></td>
				</tr>
				
				<tr>
					<th class="op2" style="font-size: 100%;">Fornecedor:</th>
					<td class="op2" style="font-size: 100%;"><select
						name="selFornecedor">
							<%
							for (FornecedorHibernate fornecedor : fornecedores) {
							%>
							<option value="<%=fornecedor.getCodFornecedor()%>"><%=fornecedor.getNome()%></option>
							<%
							} // fim do for
							%>
							<option value="" selected>Selecionar...</option>
					</select> <br></td>
				</tr>
				
				<tr>
					<th class="op2" style="font-size: 100%;">Funcionario:</th>
					<td class="op2" style="font-size: 100%;"><select
						name="selFuncionario">
							<%
							for (FuncionarioHibernate funcionario : funcionarios) {
							%>
							<option value="<%=funcionario.getMatricula()%>"><%=funcionario.getNomeA()%></option>
							<%
							} // fim do for
							%>
							<option value="" selected>Selecionar...</option>
					</select> <br></td>
				</tr>
				
				<input type="hidden" name="txtProduto" maxlength="15" value="0">
			<input type="hidden" name="txtFornecedor" maxlength="15" value="0">
						<input type="hidden" name="txtFuncionario" maxlength="15" value="0">
			
							
				<td class="op1"><input type="submit" value="Cadastrar"
					name="btnOpcao"></td>
				<td class="op1"><input type="reset" value="Limpar"
					name="btnLimpar"></td>
				</tr>
			</form>
		</table>
		<h3>
			Retornar Tela Principal: <a href="TelaProduto.jsp" />Clicar</a>
		</h3>
	</div>
	<div id="mascara"></div>
</body>
</html>