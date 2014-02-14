function confirmationDelete(){
	var remover = document.getElementById('btn-remover');
	if( !window.confirm('Confirma a remoção do produto selecionado?') ){
		remover.href = 'listar.action';
		//location = 'http://localhost:8080/catalogoRodizio/update.jsp';
		//window.history.pushState('Object', '', 'http://localhost:8080/catalogoRodizio/update.jsp');
	}
};

window.onload = function(){
	var remover = document.getElementById('btn-remover');
	remover.onclick = confirmationDelete;
	
};