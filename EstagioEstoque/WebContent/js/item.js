$(function(){
		var imageAddButton = $("span#imageAddButton");

		imageAddButton.click(function() {
			$("div.imagesTemplate").clone().removeClass("imagesTemplate").addClass("imageForm").prependTo("div.images").children("input").prop("disabled", false);
		});

		$(document).on('click', ".removeButton", function() {
			$(this).parent().remove();
		});
});