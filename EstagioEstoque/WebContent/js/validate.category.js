$(function() {

	var form = $(".form");
	
	form.validate({
		submitHandler : function(form) {
			form.submit();
		},
		rules : {
			"category.name" : {
				required : true,
				minlength : 3
			}
		},
		messages : {
			"category.name" : {
				required : "Este campo é obrigatório.",
				minlength : jQuery
						.format("Deve ter pelo menos {0} caracteres.")
			}
		}
	});
});