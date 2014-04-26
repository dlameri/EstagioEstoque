$(function() {

	$(".btn-danger").click(function() {
		var urlToSend = "deletesubcategory?id=" + $(this).closest("li").attr("id");
		console.log("Url do request: " + urlToSend);

		$("#toBeDeleted").empty();
		
		$.ajax({
			type : "GET",
			url : urlToSend,
			dataType : 'json',
			beforeSend : console.log("Enviando dados pro serv"),
			success : function(data) {
				if (data.length > 0) {
					$("#toBeDeleted").append('<ul class="products"></ul>');
					$.each(data, function(index) {
						var name = data[index].name;
						var productId = data[index].id;
						var items = data[index].items;
						$('ul.products').append('<li class="product'+productId+'">' + name + '</li>');
						$('li.product'+productId).append('<ul class="product'+productId+'"></ul>');
						$.each(items, function(index) {
							var name = items[index].sku;
							$('ul.product'+productId).append('<li>' + name + '</li>');
						});
					});
					$("#toBeDeleted").append('<button id="'+urlToSend+'&confirmation=ok" type="button" class="btn btn-xs btn-danger btn-delete">OK</button>');
					$("#toBeDeleted").append('<button type="button" class="btn-cancel btn btn-xs btn-warning">Cancelar</button>');
					return false;
				}
				else {
					window.location.replace("/EstagioEstoque/web/subcategorias?deleted=true");
				}
			}
		});
	});
	
	$(document).on('click', ".btn-delete", function() {
		var urlToSend = $(this).attr("id");
		console.log("Url do segundo request: " + urlToSend);

		$.ajax({
			type : "GET",
			url : urlToSend,
			dataType : 'json',
			beforeSend : console.log("Enviando dados pro serv"),
			success : console.log("Deletou com sucesso.")
		}).done(function(){ 
			window.location.replace("/EstagioEstoque/web/subcategorias?deleted=true");
		});
	});
	
	$(document).on('click', ".btn-cancel", function() {
		window.location.replace("/EstagioEstoque/web/subcategorias");
	});
	
});