<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/estilo.css" />
<script type="text/javascript" src="jquery/jquery-1.11.1.min.js"></script>
<script language="javascript">
$(document).ready(function(){
   $("#cadastrar").mouseenter(function(evento) {
     $("#mensagem").css("display", "");
   });
   $("#cadastrar").mouseleave(function(evento){
     $("#mensagem").css("display", "none");
   });
   
   $("#alterar").mouseenter(function(evento) {
     $("#mensagem2").css("display", "");
   });  
   $("#alterar").mouseleave(function(evento){
     $("#mensagem2").css("display", "none");
   });

   $("#excluir").mouseenter(function(evento) {
	 $("#mensagem3").css("display", "");
	});  
	$("#excluir").mouseleave(function(evento){
	 $("#mensagem3").css("display", "none");
	});
	
	$("#consultar").mouseenter(function(evento) {
	 $("#mensagem4").css("display", "");
	});  
	$("#consultar").mouseleave(function(evento){
	 $("#mensagem4").css("display", "none");
	});
	
	$("#relatorio").mouseenter(function(evento) {
	 $("#mensagem5").css("display", "");
	});  
	$("#relatorio").mouseleave(function(evento){
	 $("#mensagem5").css("display", "none");
	});
	
	$("#retornar").mouseenter(function(evento) {
	 $("#mensagem6").css("display", "");
	 });  
	 $("#retornar").mouseleave(function(evento){
	  $("#mensagem6").css("display", "none");
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
<title>Banco de dados - Web</title>
</head>
<body>
	<div class="window" id="janela1" rel="modal">
		<h1 style="text-align: center;">Cargo</h1>
		<table>
			<caption
				style="font-size: 200%; padding-top: 10px; padding-bottom: 10px;">Menu Cargo</caption>
			<tr>
				<td class="op1" id="cadastrar" style="font-size: 150%;"><a
					href="CargoCadastrar.jsp" />Cadastrar</a></td>
			</tr>
			<tr>
				<td class="op1" id="alterar" style="font-size: 150%;"><a
					href="CargoAlterar.jsp" />Alterar</a></td>
			</tr>
			<tr>
				<td class="op1" id="excluir" style="font-size: 150%;"><a
					href="CargoExcluir.jsp" />Excluir</a></td>
			</tr>
			<tr>
				<td class="op1" id="consultar" style="font-size: 150%;"><a
					href="CargoConsultar.jsp" />Consultar</a></td>
			</tr>
			<tr>
				<td class="op1" id="retornar" style="font-size: 150%;"><a
					href="index.jsp" />Menu Principal</a></td>
			</tr>
		</table>
		<div style="padding: 15px;">
			<span id="mensagem"
				style="display: none; width: 550px; font-weight: bold; margin: auto;">
				Cadastrar Cargo</span> <span id="mensagem2"
				style="display: none; width: 550px; font-weight: bold; margin: auto;">
				Alterar Cargo</span> <span id="mensagem3"
				style="display: none; width: 550px; font-weight: bold; margin: auto;">
				Excluir Cargo</span> <span id="mensagem4"
				style="display: none; width: 550px; font-weight: bold; margin: auto;">
				Consultar Cargo</span> <span id="mensagem5"
				style="display: none; width: 550px; font-weight: bold; margin: auto;">
				Relatório dos Cargos</span> <span id="mensagem6"
				style="display: none; width: 550px; font-weight: bold; margin: auto;">
				Retornar ao Menu Principal</span>
		</div>
	</div>
	<div id="mascara"></div>
</body>
</html>