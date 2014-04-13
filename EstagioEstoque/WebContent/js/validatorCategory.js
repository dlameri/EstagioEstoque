$(function() {
	
	var categoryName = $(".categoryName");
	var nameError = $(".nameError");
	
	categoryName.click(function(){
		categoryName.removeClass("wrongField");
	});
	
	$(".btn-submit").click(function(){
		nameError.addClass("hidden");
		
		var hasError = 0;
		
		if (categoryName.val().trim().length < 3) { 
			categoryName.addClass("wrongField");
			nameError.removeClass("hidden");
			hasError++;
		}
		
		if (hasError) 
			return false;
	});
		
});