$(function() {

	$("#categoryList").change(function() {
		var categoryId = $(this).val();
		var subcategories = $(".subcategoryList");
		
		subcategories.empty();
		subcategories.get(0).options[0] = new Option("--SELECIONE A SUBCATEGORIA--", "-1");		
		
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

	}).change();
});