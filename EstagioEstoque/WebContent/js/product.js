$(function() {
	$("#categoryList").change(function(){
		var categoryId = $(this).val();
		$(".subcategoryList").addClass("hidden");
		$(".subcategoryList[data-categoryid="+categoryId+"]").removeClass("hidden");
	}).change();
});