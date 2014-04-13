$(function() {
	
	$("#categoryList").change(function() {
		var categoryId = $(this).val();
		var subcategories = $(".subcategoryList");

		if(categoryId == -1) {
			subcategories.prop("disabled", "disabled");
			$(".subcategoryList>option").html("SELECIONE UMA CATEGORIA");
			return false;
		}
		
		subcategories.empty();
		subcategories.append('<option value=\"-1\">SELECIONE A SUBCATEGORIA</option>');		
		subcategories.prop("disabled", false);
		
		$.ajax({
			type : "GET",
			url : "selectSubcategories?id=" + categoryId,
			dataType: 'json',
			success: function(data) {
				$.each(data, function(index){
					var name = data[index].name;
					var id = data[index].id;
					subcategories.append('<option value='+id+'>'+name+'</option>');
				});
			}
		});

	});
});


