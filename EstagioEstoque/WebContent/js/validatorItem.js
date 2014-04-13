$(function() {
	
	var itemName = $(".itemName");
	var nameError = $(".categoryNameError");
	
	itemName.click(function(){
		categoryName.removeClass("wrongField");
	});
	
	$(".btn-submit").click(function(){
		nameError.addClass("hidden");
		
		var hasError = 0;
		
		if (itemName.val().trim().length < 3) { 
			itemName.addClass("wrongField");
			nameError.removeClass("hidden");
			hasError++;
		}
		
		if (hasError) 
			return false;
	});
		
});