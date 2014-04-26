$(function() {
	$("#pagination").paging($(".elementsCount").val(), {
		
		format: "[ < > ] . (qq -) nnncnnn (- pp)",
		perpage: 10,
		lapping: 1,
		page: 1,
		
		// Resultado aqui
		onSelect: function(page) {
			
			$('.productsList').empty();
			var offsettedPage = page - 1;

			$.ajax({
				type : "GET",
				url : "getPaginatedProducts?page=" + offsettedPage,
				dataType : 'json',
				beforeSend : console.log("Enviando dados pro serv"),
				success : function(data) { 
					$.each(data, function(index) {
						$('.productsList').append('<li>Nome: <a href="productForm?id='+data[index].id+'">'+data[index].name+'</a></li>');	
					});
				}
			});
		},
		onFormat: function(type) {
	
			switch (type) {
	
			case 'block':
	
				if (!this.active)
					return '<span class="disabled">' + this.value + '</span>';
				else if (this.value != this.page)
					return '<em><a href="produtos/?page=' + this.value + '">' + this.value + '</a></em>';
				return '<span class="current">' + this.value + '</span>';
	
			case 'next':
	
				if (this.active)
					return '<a href="produtos/?page=' + this.value + '" class="next">></a>';
				return '<span class="disabled">></span>';
	
			case 'prev':
	
				if (this.active)
					return '<a href="produtos/?page=' + this.value + '" class="prev"><</a>';
				return '<span class="disabled"><</span>';
	
			case 'first':
	
				if (this.active)
					return '<a href="produtos/?page=' + this.value + '" class="first">First</a>';
				return '<span class="disabled">First</span>';
	
			case 'last':
	
				if (this.active)
					return '<a href="produtos/?page=' + this.value + '" class="last">Last</a>';
				return '<span class="disabled">Last</span>';
	
			case "leap":
	
				if (this.active)
					return "   ";
				return "";
	
			case 'fill':
	
				if (this.active)
					return "...";
				return "";
			}
		}
	});
});