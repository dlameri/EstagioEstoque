$(function() {
	
	var categoryName = $(".categoryName");
	var nameError = $(".categoryNameError");
	
	categoryName.click(function(){
		categoryName.removeClass("wrongField");
	});
	
	$(".btn-submit").click(function(){
		nameError.addClass("hidden");
		
		var hasError = 0;
		
		if (productName.val().trim().length < 3) { 
			productName.addClass("wrongField");
			nameError.removeClass("hidden");
			hasError++;
		}
		
		if (hasError) 
			return false;
	});
		
});