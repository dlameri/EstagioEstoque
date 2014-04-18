$(function() {
	
	$(".btn-danger").click(function(){
		var urlToSend = $(this).attr("id");
		console.log("Url do request: " + urlToSend);
		
		$.ajax({
			type : "GET",
			url : urlToSend,
			dataType: 'json',
			beforeSend: console.log("Enviando dados pro serv"),
			success: function(data) {
				alert("retornou a bagaceta");
				$.each(data, function(index){
					var name = data[index].name;
					$("#toBeDeleted").append(name + '<br/>');
				});
			}
		});
	});
});