<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Tela Cargo - Cadastrar</title>
</head>
<body>
<% session = null; %>
<div class="window" id="janela1" rel="modal">
<h1 style="text-align: center;">Cargo</h1>
<table>
<form name="frmFornecedor" action="ControleFornecedorServletAction" method="post">
	<caption style="font-size:200%;padding-top:10px;padding-bottom:10px;">Cadastrar</caption>
	<tr>
	<th class="op1" style="font-size:100%;">Fornecedor</th>
	<td class="op1" style="font-size:100%;"><input type="text" autofucos name="txtNome" maxlength="45" value=""></td>
	</tr>
	<tr>
	<th class="op1" style="font-size:100%;">Cnpj</th>
	<td class="op1" style="font-size:100%;"><input type="text" autofucos name="txtCnpj" maxlength="45" value=""></td>
	</tr>
	<tr>
	<th class="op1" style="font-size:100%;">Cidade</th>
	<td class="op1" style="font-size:100%;"><input type="text" autofucos name="txtCidade" maxlength="45" value=""></td>
	</tr>
	<tr>
	<th class="op1" style="font-size:100%;">Estado</th>
	<td class="op1" style="font-size:100%;"><input type="text" autofucos name="txtEstado" maxlength="45" value=""></td>
	</tr>
	<tr>
	<input type="hidden" name="txtCodFornecedor" maxlength="45" value="0">
	<td class="op1" style="font-size:100%;"><input type="submit" value="Cadastrar" name="btnOpcao"></td>
	<td class="op1" style="font-size:100%;"><input type="reset" value="Limpar" name="btnLimpar"></td>
	</tr>
</form>
</table>
<h3>Retornar Tela Principal: <a href="TelaCargo.jsp" />Clicar</a></h3>
</div>
<div id="mascara"></div>
</body>
</html>