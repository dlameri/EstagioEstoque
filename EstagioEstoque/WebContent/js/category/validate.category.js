$(function() {
	$(".jtable-dialog-form").on('validate', { 
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
	
//	form.validate();
//	var form = $(".jtable-dialog-form");
//	
//	form.validate({
//		submitHandler : function(form) {
//			alert("egeagg");
//			form.submit();
//		},
//		rules : {
//			"category.name" : {
//				required : true,
//				minlength : 3
//			}
//		},
//		messages : {
//			"category.name" : {
//				required : "Este campo é obrigatório.",
//				minlength : jQuery
//						.format("Deve ter pelo menos {0} caracteres.")
//			}
//		}
//	});
});