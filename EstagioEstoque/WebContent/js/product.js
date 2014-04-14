$(function() {
	
	$("#categoryList").change(function() {
		var categoryId = $(this).val();
		var subcategories = $(".subcategoryList");

		subcategories.empty();
		
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


