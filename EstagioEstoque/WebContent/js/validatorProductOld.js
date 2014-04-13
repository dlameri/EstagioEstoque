$(function() {
	alert(VAR.myvalue);
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
	
	var nameError = $(".nameError");
	var shortDescError = $(".shortDescError");
	var longDescError = $(".longDescError");
	var weightError = $(".weightError");
	var warrantyError = $(".warrantyError");
	var brandError = $(".brandError");
	var modelError = $(".modelError");
	var widthError = $(".widthError");
	var heightError = $(".heightError");
	var depthError = $(".depthError");
	
	productName.click(function(){
		productName.removeClass("wrongField");
	});
	
	shortDesc.click(function(){
		shortDesc.removeClass("wrongField");
	});
	longDesc.click(function(){
		longDesc.removeClass("wrongField");
	});
	weight.click(function(){
		weight.removeClass("wrongField");
	});
	warranty.click(function(){
		warranty.removeClass("wrongField");
	});
	brand.click(function(){
		brand.removeClass("wrongField");
	});
	model.click(function(){
		model.removeClass("wrongField");
	});
	width.click(function(){
		width.removeClass("wrongField");
	});
	height.click(function(){
		height.removeClass("wrongField");
	});
	depth.click(function(){
		depth.removeClass("wrongField");
	});
	
	$(".btn-submit").click(function(){
		nameError.addClass("hidden");
		shortDescError.addClass("hidden");
		longDescError.addClass("hidden");
		weightError.addClass("hidden");
		warrantyError.addClass("hidden");
		brandError.addClass("hidden");
		modelError.addClass("hidden");
		widthError.addClass("hidden");
		heightError.addClass("hidden");
		depthError.addClass("hidden");
		
		var hasError = 0;
		
		if (productName.val().trim().length < 3) { 
			productName.addClass("wrongField");
			shortDescError.removeClass("hidden");
			hasError++;
		}
		
		if (shortDesc.val().trim().length < 3) { 
			shortDesc.addClass("wrongField");
			shortDescError.removeClass("hidden");
			hasError++;
		}
		
		if (longDesc.val().trim().length < 3) { 
			longDesc.addClass("wrongField");
			longDescError.removeClass("hidden");
			hasError++;
		}
		
		if (weight.val().trim().length < 3) { 
			weight.addClass("wrongField");
			weightError.removeClass("hidden");
			hasError++;
		}
		
		if (warranty.val().trim().length < 3) { 
			warranty.addClass("wrongField");
			warrantyError.removeClass("hidden");
			hasError++;
		}
		
		if (model.val().trim().length < 3) { 
			model.addClass("wrongField");
			modelError.removeClass("hidden");
			hasError++;
		}
		
		if (brand.val().trim().length < 3) { 
			brand.addClass("wrongField");
			shortDescError.removeClass("hidden");
			hasError++;
		}
		
		if ( !(width.val()| '/\d/') ) { 
			width.addClass("wrongField");
			widthError.removeClass("hidden");
			hasError++;
		}
		
		if ( !(height.val()| '/\d/') ) { 
			height.addClass("wrongField");
			heightError.removeClass("hidden");
			hasError++;
		}
		
		if ( !(depth.val() | '/\d/') ) { 
			depth.addClass("wrongField");
			depthError.removeClass("hidden");
			hasError++;
		}
		
		if ($(".categoryList").val() < 1) {
			hasError++;
		}
		
		if ($(".subcategoryList").val() < 1) {
			hasError++;
		}	
		
		if (hasError) 
			return false;
	});
		
});