var cachedCategoryOptions = null;

$(function() {
	
	 $('#categoryContainer').jtable({
         title: 'Produtos',
         paging: true,
         pageSize: 10,
         pageSizes: [10, 20, 35, 50],
         deleteConfirmation: function(data) {
        	
        	 $.ajax({
     			type : "GET",
     			url : '/EstagioEstoque/web/checkCategoryBeforeDeleting?id=' + data.record.id,
     			dataType : 'json',
     			async: false,
     			beforeSend : console.log("Enviando dados pro serv"),
     			success : function(response) {
     				if (response.length > 0 ) {
     					data.deleteConfirmMessage = "Esta categoria tem as seguintes subcategorias atreladas a ela: <br/> ";
	 					$.each(response, function(index) {
	 						var name = response[index].name;
	 						var subcategoryId = response[index].id;
	 						data.deleteConfirmMessage += subcategoryId + " - " + name + "<br/>";
		 				});
     				} else {
     					data.deleteConfirmMessage = "Você tem certeza que deseja deletar a categoria?<br/> ";
     				}
     			}
        	 });
         },
         actions: {
        	 listAction: function (postData, jtParams) {
                 console.log("Recebendo lista do server...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                         url: '/EstagioEstoque/web/getPaginatedCategories?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize,
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                             $dfd.resolve(data);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             createAction: function (postData) {
                 console.log("Enviando produto para criação...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                         url: '/EstagioEstoque/web/saveCategory',
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 $dfd.resolve(data);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             updateAction: function (postData) {
                 console.log("Enviando produto para atualização...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                    	 url: '/EstagioEstoque/web/saveCategory',
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 if (data) {
                                 $dfd.resolve(data);
                        	 } else {
                        		 $dfd.reject();
                        	 }
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             },
             deleteAction: function (postData) {
                 console.log("Enviando produto para deleção...");
                 return $.Deferred(function ($dfd) {
                     $.ajax({
                    	 url: '/EstagioEstoque/web/deleteCategory',
                         type: 'POST',
                         dataType: 'json',
                         data: postData,
                         success: function (data) {
                        	 $dfd.resolve(data);
                         },
                         error: function () {
                             $dfd.reject();
                         }
                     });
                 });
             }
         },
         fields: {
             id: {
                 key: true,
                 list: false,
                 edit: true,
                 type: 'hidden'
             },
             subcategories: {
                 title: '',
                 width: '5%',
                 sorting: false,
                 edit: false,
                 create: false,
                 display: function (categoryData) {
                     //Create an image that will be used to open child table
                     var $img = $('<img src="/EstagioEstoque/css/jQuery/jTable/themes/metro/list_metro.png" title="Editar itens" />');
                     //Open child table when user clicks the image
                     $img.click(function () {
                    	 $('#categoryContainer').jtable('openChildTable',
                                 $img.closest('tr'), //Parent row
                                 {
                                 title: 'Itens de - ' + categoryData.record.name,
                                 paging: true,
                                 pageSize: 10,
                                 pageSizes: [10, 20, 35, 50],
                                 deleteConfirmation: function(data) {
                                 	
                                	 $.ajax({
                             			type : "GET",
                             			url : '/EstagioEstoque/web/checkSubcategoryBeforeDeleting?id=' + data.record.id,
                             			dataType : 'json',
                             			async: false,
                             			beforeSend : console.log("Enviando dados pro serv"),
                             			success : function(response) {
                             				if (response.length > 0 ) {
                             					data.deleteConfirmMessage = "Esta subcategoria tem os seguintes produtos atrelados a ela: <br/> ";
                        	 					$.each(response, function(index) {
                        	 						var name = response[index].name;
                        	 						var subcategoryId = response[index].id;
                        	 						data.deleteConfirmMessage += subcategoryId + " - " + name + "<br/>";
                        		 				});
                             				} else {
                             					data.deleteConfirmMessage = "Você tem certeza que deseja deletar a categoria?<br/> ";
                             				}
                             			}
                                	 });
                                 },
                                 actions: {
                                	 listAction: function (postData, jtParams) {
                                         console.log("Recebendo lista do server...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/getSubcategoriesByCategoryId?categoryId=' + categoryData.record.id + '&jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize,
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                     $dfd.resolve(data);
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                     createAction: function (postData) {
                                         console.log("Enviando item para criação...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/saveSubcategory',
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                     $dfd.resolve(data);
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                     updateAction: function (postData) {
                                         console.log("Enviando item para atualização...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/saveSubcategory',
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                     $dfd.resolve(data);
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                     deleteAction: function (postData) {
                                         console.log("Enviando item para deleção...");
                                         return $.Deferred(function ($dfd) {
                                             $.ajax({
                                                 url: '/EstagioEstoque/web/deleteSubcategory',
                                                 type: 'POST',
                                                 dataType: 'json',
                                                 data: postData,
                                                 success: function (data) {
                                                     $dfd.resolve(data);
                                                 },
                                                 error: function () {
                                                     $dfd.reject();
                                                 }
                                             });
                                         });
                                     },
                                 },
                                 fields: {
                                	 categoryId: {
                                         type: 'hidden',
                                         defaultValue: categoryData.record.id
                                     },
                                     id: {
                                         key: true,
                                         create: false,
                                         list: false,
                                         edit: true,
                                         type: 'hidden'
                                     },
                                     name: {
                                         title: 'Nome da subcategoria',
                                         width: '40%',
                                         input: function (data) {
                                             if (data.record) {
                                                 return '<input type="text" name="subcategory.name" class="subcategoryName" value="' + data.record.name + '" />';
                                             } else {
                                                 return '<input type="text" name="subcategory.name" class="subcategoryName" placeholder="Digite um nome" />';
                                             }
                                         }
                                     },
                                     active: {
                                    	 list: false,
                                    	 input: function (data) {
                                    		 if (data.record) {
                                    			 if (data.record.active)
                                    				 return '<input class="" type="checkbox" name="item.active" value="' + data.record.active + '" checked="checked"><span class="jtable-option-text-clickable">Ativo</span>';
                                    			 else
                                    				 return '<input class="" type="checkbox" name="item.active" value="' + data.record.active + '"><span class="jtable-option-text-clickable">Ativo</span>';
                                             } else {
                                            	 return '<input class="" type="checkbox" name="item.active" value="true" checked="checked" /><span class="jtable-option-text-clickable">Ativo</span>';
                                             }
                                         }
                                     }
                                 },
                                 //Initialize validation logic when a form is created
                                 formCreated: function (event, data) {
                                     data.form.find('input[name="subcategory.name"]').addClass('validate[required]');
                                     data.form.validationEngine();
                                 },
                                 //Validate form when it is being submitted
                                 formSubmitting: function (event, data) {
                                     return data.form.validationEngine('validate');
                                 },
                                 //Dispose validation logic when form is closed
                                 formClosed: function (event, data) {
                                     data.form.validationEngine('hide');
                                     data.form.validationEngine('detach');
                                 }
                             }, function (data) { //opened handler
                                 data.childTable.jtable('load');
                             });
                     });
                     //Return image to show on the category row
                     return $img;
                 }
             },
             name: {
                 title: 'Nome da categoria',
                 width: '40%',
                 input: function (data) {
                     if (data.record) {
                         return '<input type="text" name="category.name" class="categoryName" value="' + data.record.name + '" />';
                     } else {
                         return '<input type="text" name="category.name" class="categoryName" placeholder="Digite um nome" />';
                     }
                 }
             },
             active: {
            	 list: false,
            	 input: function (data) {
            		 if (data.record) {
            			 if (data.record.active)
            				 return '<input class="" type="checkbox" name="category.active" value="' + data.record.active + '" checked="checked"><span class="jtable-option-text-clickable">Ativo</span>';
            			 else
            				 return '<input class="" type="checkbox" name="category.active" value="' + data.record.active + '"><span class="jtable-option-text-clickable">Ativo</span>';
                     } else {
                    	 return '<input class="" type="checkbox" name="category.active" value="true" checked="checked" /><span class="jtable-option-text-clickable">Ativo</span>';
                     }
                 }
             }
         },
         //Initialize validation logic when a form is created
         formCreated: function (event, data) {
             data.form.find('input[name="category.name"]').addClass('validate[required]');
             data.form.validationEngine();
         },
         //Validate form when it is being submitted
         formSubmitting: function (event, data) {
             return data.form.validationEngine('validate');
         },
         //Dispose validation logic when form is closed
         formClosed: function (event, data) {
             data.form.validationEngine('hide');
             data.form.validationEngine('detach');
         }
     });
	 
	 //Re-load records when user click 'load records' button.
     $('.btn-search').click(function (e) {
         e.preventDefault();
         $('#categoryContainer').jtable('load', {
             query: $('.searchBar').val(),
             status: $('.statusSelector').val()
         });
     });

     //Load all records when page is first shown
     $('.btn-search').click();
 });