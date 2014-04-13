$(function() {
	
	var subcategoryName = $(".subcategoryName");
	var nameError = $(".nameError");
	
	subcategoryName.click(function(){
		subcategoryName.removeClass("wrongField");
	});
	
	$(".btn-submit").click(function(){
		nameError.addClass("hidden");

		var hasError = 0;
		
		if (subcategoryName.val().trim().length < 3) { 
			subcategoryName.addClass("wrongField");
			nameError.removeClass("hidden");
			hasError++;
		}
		
		if (hasError) 
			return false;
	});
		
});