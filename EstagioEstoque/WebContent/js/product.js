$(function() {

	$("#categoryList").change(function() {
		var categoryId = $(this).val();
		
		$.ajax({
			type : "GET",
			url : "selectSubcategories?id=" + categoryId,
			dataType: 'json',
//			statusCode : {
//				500 : function() {
//					alert("deu ruim");
//				},
//				200 : function() {
//					$(".subcategoryList").get(0).options[0] = new Option("--SELECIONE A SUBCATEGORIA--", "-1");
//					alert("deu certo");
//				}
//			}
			success: function(data) {
				$.each(data, function(index){
					alert(data[index].name);
				});
			}
		});

	}).change();
});