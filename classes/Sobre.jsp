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
		<h1 style="text-align: center;">Sistema de Inclusões de Produtos em Depósitos</h1>
		<table>
			<caption
				style="font-size: 200%; padding-top: 10px; padding-bottom: 30px;position: relative;left:50%;">GRUPO</caption>
				<center>
				<tr>
				<td>
				<h3> João Pedro Valdeger - 10 <br><br> Gabriel Souza - 7<br><br> Turma: 3211 <br><br> Professor: Biancovilli <br> <br> Disciplina: PWEB </h1>
				</td>
				</tr>
				</center>
				
				
		
			<center>
				<tr>
				<td>
				<a href="index.jsp"><button id="retornar" style="background: #069cc2; border-radius: 6px; padding: 15px; cursor: pointer; color: #fff; border: none; font-size: 16px;text-align:center;position:relative;left: 100%;width:72%;top: -100px;">Menu Principal</button></a>
				</td>
				</tr>
				</center>
			
		</table>
		
	</div>
	<div id="mascara"></div>
</body>
</html>