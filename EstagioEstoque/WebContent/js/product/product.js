$(function() {
	
	$("#categoryList").change(function() {
		var categoryId = $(this).val();
		var subcategoryId = $('.subcategoryId').val();
		console.log("Subcategoria: " + subcategoryId);
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
					if (subcategoryId == id)
						subcategories.append('<option value='+id+' selected>'+name+'</option>');
					else
						subcategories.append('<option value='+id+'>'+name+'</option>');
				});
			}
		});

	}).change();
});


