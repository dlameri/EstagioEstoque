$(function() {

	$(".btn-danger").click(function() {
		var urlToSend = $(this).attr("id");
		console.log("Url do request: " + urlToSend);

		$("#toBeDeleted").empty();
		
		$.ajax({
			type : "GET",
			url : urlToSend,
			dataType : 'json',
			beforeSend : console.log("Enviando dados pro serv"),
			success : function(data) {

				if (data.length > 0) {
					$("#toBeDeleted").append('<ul class="subcategories"></ul>');
					$.each(data, function(index) {
						var name = data[index].name;
						var subcategoryId = data[index].id;
						var products = data[index].products;
						$("ul.subcategories").append('<li class="subcategory'+subcategoryId+'">' + name + '</li>');
						$('li.subcategory'+subcategoryId).append('<ul class="subcategory'+subcategoryId+'"></ul>');
						$.each(products, function(index) {
							var name = products[index].name;
							var productId = products[index].id;
							var items = products[index].items;
							$('ul.subcategory'+subcategoryId).append('<li class="product'+productId+'">' + name + '</li>');
							$('li.product'+productId).append('<ul class="product'+productId+'"></ul>');
							$.each(items, function(index) {
								var name = items[index].sku;
								$('ul.product'+productId).append('<li>' + name + '</li>');
							});
						});
					});
					$("#toBeDeleted").append('<button id="'+urlToSend+'&confirmation=ok" type="button" class="removeButton btn btn-xs btn-danger">OK</button>');
					$("#toBeDeleted").append('<button type="button" class="cancel btn btn-xs btn-warning">Cancelar</button>');
				}
				else {
					window.location.replace("/EstagioEstoque/web/categorias?deleted=true");
				}
			}
		});
	});
	
	$(document).on('click', ".removeButton", function() {
		var urlToSend = $(this).attr("id");
		console.log("Url do segundo request: " + urlToSend);

		$.ajax({
			type : "GET",
			url : urlToSend,
			dataType : 'json',
			beforeSend : console.log("Enviando dados pro serv"),
			success : window.location.replace("/EstagioEstoque/web/categorias?deleted=true")
		});
	});
	
	$(document).on('click', ".cancel", function() {
		window.location.replace("/EstagioEstoque/web/categorias");
	});
	
});