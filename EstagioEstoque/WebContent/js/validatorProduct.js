$(function() {
	var doubleTypeInputs = new Array();

	var form = $(".form");

	var productName = $(".productName");
	var shortDesc = $(".shortDesc");
	var longDesc = $(".longDesc");
	var weight = $(".weight");
	var warranty = $(".warranty");
	var brand = $(".brand");
	var model = $(".model");
	var width = $(".width");
	var height = $(".height");
	var depth = $(".depth");

	doubleTypeInputs.push(weight);
	doubleTypeInputs.push(width);
	doubleTypeInputs.push(height);
	doubleTypeInputs.push(depth);

	// Apply Double Type masks
	$.each(doubleTypeInputs, function(index, data) {
		data.mask('099.00');
	});

	// Apply Int Type masks
	warranty.mask('00');
	
	form.validate({
		submitHandler : function(form) {
			form.submit();
		},
		rules : {
			"product.name" : {
				required : true,
				minlength : 3
			},
			"product.shortDesc" : {
				required : true
			}, 
			"product.longDesc" : {
				required : true
			},
			"product.weight" : {
				required : true,
				number: true
			},
			"product.warranty" : {
				required : true,
				number: true
			},
			"product.brand" : {
				required : true
			},
			"product.model" : {
				required : true
			},
			"product.width" : {
				required : true,
				number: true
			},
			"product.height" : {
				required : true,
				number: true
			},
			"product.depth" : {
				required : true,
				number: true
			}
		},
		messages : {
			"product.name" : {
				required : "Este campo é obrigatório.",
				minlength : jQuery
						.format("Deve ter pelo menos {0} caracteres.")
			},
			"product.shortDesc"  : {
				required : "Este campo é obrigatório."
			}, 
			"product.longDesc"  : {
				required : "Este campo é obrigatório."
			},
			"product.weight"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			},
			"product.warranty"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			},
			"product.brand"  : {
				required : "Este campo é obrigatório."
			},
			"product.model"  : {
				required : "Este campo é obrigatório."
			},
			"product.width"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			},
			"product.height"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			},
			"product.depth"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			}
		}
	});
});