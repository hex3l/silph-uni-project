		let cartRemove = function (id) {
            $.ajax({
                type: "POST",
                url: "/cart/remove/" + id,
                success: function () {
                	$('#cart-' + id).remove();
                },
                error: function (xhr, ajaxOptions, thrownError) {
					//TODO: error
                }
            });
        }
		
		let cartAdd = function (id) {
            $.ajax({
                type: "POST",
                url: "/cart/add/" + id,
                success: function (data) {
                	var response = jQuery.parseJSON(data);
                	console.log(data)
                	$('#cart tbody').append("<tr id='cart-"+ id +"'><td>"+ id +"</td><td>"+ response.name +"</td><td><i class='fas fa-times' onclick='cartRemove(" + id + ");'></i></td></tr>");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                	//TODO: error
                }
            });
        }
		
		let hideShowCart = function () {
			if($('#cart-list').css('display') == 'none'){
				$('#cart-list').show();
				if(!$('#cart-sh').hasClass('fa-rotate-180')) $('#cart-sh').addClass('fa-rotate-180');
			} else {
				$('#cart-list').hide();
				$('#cart-sh').removeClass('fa-rotate-180');
			}
			
		}