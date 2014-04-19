$(function() {
	var doubleTypeInputs = new Array();

	var form = $(".form");

	var sku = $(".sku");
	var priceFrom = $(".priceFrom");
	var priceFor = $(".priceFor");
	var optionName = $(".optionName");
	var optionValue = $(".optionValue");
	var stock = $(".stock");

	doubleTypeInputs.push(priceFor);
	doubleTypeInputs.push(priceFrom);

	// Apply Double Type masks
	$.each(doubleTypeInputs, function(index, data) {
		data.mask('099.00');
	});

	// Apply Int Type masks
	stock.mask('000');

	$.validator.addMethod("greaterThan",

	function (value, element, param) {
		var $min = $(param);
		if (this.settings.onfocusout) {
			$min.off(".validate-greaterThan").on("blur.validate-greaterThan", function () {
			$(element).valid();
			});
		}
		
		return parseInt(value) > parseInt($min.val());
	}, "Preço de deve ser maior que preço por.");

	form.validate({
		submitHandler : function(form) {
			form.submit();
		},
		rules : {
			"product.id" : {
				required : true,
				number: true
			},
			"item.sku" : {
				required : true,
				number: true
			},
			"item.priceFrom" : {
				required : true,
				number: true,
				greaterThan: '.priceFor'
			}, 
			"item.priceFor" : {
				required : true,
				number: true
			},
			"item.optionName" : {
				required : true,
			},
			"item.optionValue" : {
				required : true,
			},
			"item.stock" : {
				required : true,
				number: true
			}
		},
		messages : {
			"product.id" : {
				required : "Selecione uma subcategoria.",
				number: "Deve ser um numero."
			},
			"item.sku" : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			},
			"item.priceFrom"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			}, 
			"item.priceFor"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			},
			"item.optionName"  : {
				required : "Este campo é obrigatório.",
			},
			"item.optionValue"  : {
				required : "Este campo é obrigatório.",
			},
			"item.stock"  : {
				required : "Este campo é obrigatório.",
				number: "Deve ser um numero."
			}
		}
	});
});