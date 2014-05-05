$(function() {
	$(document).on('click', "#fade", function() {
		killLightBoxWithFire();
	});
	$(document).on('focus', ".warranty", function() {
		$(".warranty").mask('00');
	});
	$(document).on('focus', ".weight", function() {
		$(".weight").mask('0000');
	});
	$(document).on('focus', ".width", function() {
		$(".width").mask('099.00');
	});
	$(document).on('focus', ".height", function() {
		$(".height").mask('099.00');
	});
	$(document).on('focus', ".depth", function() {
		$(".depth").mask('099.00');
	});
	$(document).on('focus', ".sku", function() {
		$(".sku").mask('0000000');
	});
	$(document).on('focus', ".priceFor", function() {
		$(".priceFor").mask('099.00');
	});
	$(document).on('focus', ".priceFrom", function() {
		$(".priceFrom").mask('099.00');
	});
	$(document).on('focus', ".stock", function() {
		$(".stock").mask('000');
	});
});

function summonLightBox(url) {
	var content = '<img class="imageInLightBox" src="' + url
			+ '" alt="Imagem" />';
	$('#light').removeClass("hidden").html(content);
	$('#fade').removeClass("hidden");
}

function killLightBoxWithFire() {
	$('#light').addClass("hidden");
	$('#fade').addClass("hidden");
}

function leThan(field, rules, i, options) {
	var a = rules[i + 2];
	if (parseFloat(field.val()) >= parseFloat(jQuery("." + a).val())) {
		return "Deve ser menor que Preço De";
	}
}