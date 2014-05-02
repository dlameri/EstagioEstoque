$(function() {
	$(document).on('click', "#fade", function() {
		killLightBoxWithFire();
	});
});

function summonLightBox(url) {
	var content = '<img class="imageInLightBox" src="'+url+'" alt="Imagem" />';
	$('#light').removeClass("hidden").html(content);
	$('#fade').removeClass("hidden");
}

function killLightBoxWithFire() {
	$('#light').addClass("hidden");
	$('#fade').addClass("hidden");
}