<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Shop Homepage - Start Bootstrap Template</title>
<!-- Bootstrap core CSS --> 
<link
	href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet"> 
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>   
<!-- Custom styles for this template -->   
<link
	href="${pageContext.servletContext.contextPath }/assets/css/shop-order.css" 
	rel="stylesheet">   
<!------ Include the above in your HEAD tag ----------> 
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<sec:authorize access="isAuthenticated()"> 
	<sec:authentication property="principal.no" var="userNo"/>
</sec:authorize> 
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="cart" />
	</c:import> 
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<div class="col-lg-3"> 
				<h1 class="my-4">JEMall</h1>    
				<div class="list-group">
					<c:forEach items='${categoryList }' var='vo' varStatus='status'>
						<c:choose>
							<c:when test="${vo.depth == 1 }"> 
								<a href="#" class="list-group-item">${vo.name }</a>
							</c:when>
							<c:otherwise>
								<a href="#" class="list-group-item">
								<img style="width: 20px; height: 20px;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAlvidkOcAC0HIFOB9vrrixTdq4GrJ2EtskdHJ9vw7qfKez8Eq2g">
									${vo.name }
								</a> 
							</c:otherwise> 
						</c:choose>
					</c:forEach> 
				</div>
			</div>  
			<!-- /.col-lg-3 -->  
 			<br><br><br><br><br> 
			<div class="shopping-cart" style="width: 75%;padding-bottom: 50px;">

				<!-- Nav pills -->
				<ul class="nav nav-pills justify-content-center nav-justified"
					role="tablist" style="padding-left: 30px;"> 
					<li class="nav-item"><a
						class="nav-link active btn btn-primary" data-toggle="pill"
						href="#organizer-details">?????? ?????? ??????</a></li>
					<li class="nav-item"><a class="nav-link btn btn-primary"
						data-toggle="pill" href="#event-details">??????</a></li>
					<li class="nav-item"><a class="nav-link btn btn-primary"
						data-toggle="pill" href="#confirm-details">????????????</a></li>
				</ul>
				<div class="connected-line"></div>
				<br> 
				 
				<h4 class="order-title">?????? ?????? ??????</h4>   
				<div class="column-labels"> 
					<label class="product-image">Image</label> 
					<label class="product-details">??????</label> 
					<label class="product-price">??????</label> 
					<label class="product-quantity">??????</label>  
					<label class="product-removal">?????????</label>   
					<label class="product-line-price">?????? ??????</label> 
				</div>			  
				<form class="seminor-login-form"
					action="${pageContext.servletContext.contextPath}/user/order" method="post">
				<c:forEach items='${cartList }' var='vo' varStatus='status'>  
					<div class="product"> 
					<input type="hidden" value="${vo.mainImg }" name="orderProductDto[${status.index}].mainImg"> 
					<input type="hidden" value="${vo.productNo }" name="orderProductDto[${status.index}].productNo">
					<input type="hidden" value="${vo.productOptionNo }" name="orderProductDto[${status.index}].productOptionNo">
					<input type="hidden" value="${vo.productName }" name="orderProductDto[${status.index}].productName">
					<input type="hidden" value="${vo.optionName }" name="orderProductDto[${status.index}].optionName">
					<input type="hidden" value="${vo.quantity }" name="orderProductDto[${status.index}].quantity">
					<input type="hidden" value="${vo.price }" name="orderProductDto[${status.index}].price">
						<div class="product-image">  
						<a href="${pageContext.servletContext.contextPath }/product/${vo.productNo }"> 
							<img src="${pageContext.servletContext.contextPath }/assets/${vo.mainImg }">
						</a>
						</div>
						<div class="product-details">
							<div class="product-title">
								<a href="${pageContext.servletContext.contextPath }/product/${vo.productNo }">
									${vo.productName } 
								</a>
							</div>
							<p class="product-description" style="min-width: 30%;">
							?????? : ${vo.optionName }</p>
						</div> 
						<div class="product-price">${vo.price }???</div>   
						<div class="product-quantity">${vo.quantity }</div>  
						<div class="product-removal">${vo.shippingFee }</div>  
						<div class="product-line-price total_price" >${vo.sumPrice }</div>
					</div> 
				</c:forEach>  
				<small class="modify-des">????????? ?????? ??? ?????? ????????? ???????????? ?????? ?????????????????? ???????????????.</small>
				<br><br><hr>   
				
				<div class="container-fluid">
					<div class="row"> 
						<div class="col-sm-12">
							<div class="pos-rel">  
								<!-- Tab panes -->
								<div class="tab-content">
									<div id="organizer-details" class="container tab-pane active"> 
											<!-- ????????? ?????? ?????? -->  
											<h4>????????? ?????? ??????</h4>
											<table class="table" style="width:100%;"> 
											  <thead>  
											    <tr> 
											    </tr> 
											  </thead>
											  <tbody>
											    <tr>
											      <th scope="row">????????? ??????</th> 
											      <td><input type="text" name="userName" class="form-control" required autocomplete="off"> </td> 
											    </tr> 
											    <tr>
											      <th scope="row">??????</th>
											      <td> 
											      	<input type="text" name="postcode" id="postcode" class="mini form-control" placeholder="????????????" readonly="readonly">
										        
											        <input type="button" onclick="execDaumPostcode(1)" value="???????????? ??????" class="btn-danger" readonly="readonly"><br>
											        
											        <input type="text" name="roadAddress" id="roadAddress" class="std form-control" placeholder="???????????????" readonly="readonly">  
											       
											        <input type="text" name="jibunAddress" id="jibunAddress" class="std form-control" placeholder="????????????" readonly="readonly">
											         
											        <span id="guide" style="color:#999;display:none"></span> 
											        
											        <input type="text" name="extraAddress" id="extraAddress" class="form-control" placeholder="????????????" readonly="readonly"> 
											       
											        <input type="text" name="detailAddress" id="detailAddress" class="form-control" placeholder="????????????"> 
											      </td> 
											    </tr> 
											    <tr>
											      <th scope="row">??????</th>
											      <td>  
													<select id="txtMobile1" name="txtMobile1" > 
														<option value="">::??????::</option>
														<option value="011">011</option>
														<option value="016">016</option>
														<option value="017">017</option>
														<option value="019">019</option>
														<option value="010">010</option>
													</select> -
													<input type="text" id="txtMobile2" name="txtMobile2" size="4" onkeypress="onlyNumber();" /> -  
													<input type="text" id="txtMobile3" name="txtMobile3" size="4" /> 
												  </td>   
											    </tr> 
											    <tr> 
											      <th scope="row">?????????</th> 
											      <td>
											      	<input type="text" name="userEmail" class="form-control" required autocomplete="off"> 
											      	<small class="modify-des">- ???????????? ?????? ????????????????????? ??????????????????.</small> <br> 
													<small class="modify-des">- ????????? ??????????????? ????????? ??????????????? ?????????????????? ????????? ?????????</small> 
											      </td>  
											      
											    </tr> 
											    <tr> 
											      <th scope="row">???????????????</th> 
											      <td>
											      	<input type="text" class="form-control" required autocomplete="off" 
											      	placeholder="?????????????????? ??????????????????." id="shipping_message" name="shippingMessage">  
											      </td>     
											    </tr>     
											  </tbody>
											</table> 
											<hr> 
											<!-- ?????? ?????? ?????? -->
											<!-- <h4>?????? ?????? ??????</h4>
											<table class="table" style="width:100%;"> 
											  <thead>  
											    <tr>  
											    </tr> 
											  </thead>
											  <tbody> 
											    <tr>
											      <th scope="row">?????? ????????? ??? ??????</th> 
											      <td><input type="text" class="form-control" required autocomplete="off"> </td> 
											    </tr> 
											    <tr>
											      <th scope="row">??????</th>
											      <td> 
											      	<input type="text" id="postcode2" class="mini form-control" placeholder="????????????" readonly="readonly">
										        
											        <input type="button" onclick="execDaumPostcode(2)" value="???????????? ??????" class="btn-danger" readonly="readonly"><br>
											        
											        <input type="text" id="roadAddress2" class="std form-control" placeholder="???????????????" readonly="readonly" > 
											       
											        <input type="text" id="jibunAddress2" class="std form-control" placeholder="????????????" readonly="readonly">
											        
											        <span id="guide" style="color:#999;display:none"></span> 
											        
											        <input type="text" id="extraAddress2" class="form-control" placeholder="????????????" readonly="readonly">
											       
											        <input type="text" id="detailAddress2" class="form-control" placeholder="????????????"> 
											      </td> 
											    </tr> 
											    <tr>
											      <th scope="row">??????</th>
											      <td> 
													<select id="txtMobile1">
														<option value="">::??????::</option>
														<option value="011">011</option>
														<option value="016">016</option>
														<option value="017">017</option>
														<option value="019">019</option>
														<option value="010">010</option>
													</select> -
													<input type="text" id="txtMobile2" size="4" onkeypress="onlyNumber();" /> -  
													<input type="text" id="txtMobile3" size="4" /> 
												  </td>   
											    </tr> 
											    <tr> 
											      <th scope="row">???????????????</th> 
											      <td>
											      	<textarea class="form-control" required autocomplete="off" placeholder="?????????????????? ??????????????????." id="shipping_message"></textarea>  
											      </td>     
											    </tr>    
											  </tbody> 
											</table> --> 
														
											<br><br> 
											<div class="form-group">
												<label class="container-checkbox"> ?????? ?????? 1 <input
													type="checkbox" checked="checked" required> <span 
													class="checkmark-box"></span>
												</label>
												<div>????????????</div> 
											</div>
											<div class="form-group">
												<label class="container-checkbox"> ?????? ?????? 2 <input type="checkbox"
													checked="checked" required> <span
													class="checkmark-box"></span> 
												</label>
												<div>????????????</div>  
											</div>
											<br><br><br> 
											<div class="totals">
												<div class="totals-item">
													<label>?????? ??? ??????</label> 
													<input type="text" class="totals-value" style="width: 104px;"
														id="total_price_sum" value="0" readonly /> 
												</div>
												<div class="totals-item">
													<label>????????? <br> 
													<small style="color: red;">50,000??? ?????? ???????????? !</small>
													</label> <input type="text" class="totals-value"
														style="width: 104px;" id="shopping_fee"
														value="${cartList[0].shippingFee }" readonly />
												</div>
												<div class="totals-item totals-item-total"> 
													<label>??? ?????? ??????</label> <input type="text"
														class="totals-value" style="width: 104px;"
														id="final_price" value="0" readonly name="totalPrice"/> 
												</div>  
											</div>  


											<div class="btn-check-log">
												<button type="submit" class="btn-check-login">????????????</button>
											</div>
										</form>
									</div>
									<div id="confirm-details" class="container tab-pane fade">
										<br>
										<h3>Menu 2</h3>
										<p>Sed ut perspiciatis unde omnis iste natus error sit
											voluptatem accusantium doloremque laudantium, totam rem 
											aperiam.</p>
									</div>
								</div>
							</div>
				</div>

				<br>
				<br>
			</div>
			<!-- row  -->

		</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//-- Click on detail
		$("ul.menu-items > li").on("click", function() {
			$("ul.menu-items > li").removeClass("active");
			$(this).addClass("active");
		})

		$(".attr,.attr2").on("click", function() {
			var clase = $(this).attr("class");

			$("." + clase).removeClass("active");
			$(this).addClass("active");
		})

		//-- Click on QUANTITY
		$(".btn-minus").on("click", function() {
			var now = $(".section > div > input").val();
			if ($.isNumeric(now)) {
				if (parseInt(now) - 1 > 0) {
					now--;
				}
				$(".section > div > input").val(now);
			} else {
				$(".section > div > input").val("1");
			}
		})
		$(".btn-plus").on("click", function() {
			var now = $(".section > div > input").val();
			if ($.isNumeric(now)) {
				$(".section > div > input").val(parseInt(now) + 1);
			} else {
				$(".section > div > input").val("1");
			}
		})
		 
		// ?????? ??? ???
		var sum = 0;
		$('.total_price').each(function(){   
		    sum += parseFloat($(this).text());
		});    
		
	    $("#total_price_sum").val(sum); 
	    
	    if(sum>=50000){
		    $("#shopping_fee").val("0");
	    } 
	     
	    var sFee = $("#shopping_fee").val();
	    if($("#shopping_fee").val()==''){
	    	sFee = 0;  
	    	$("#shopping_fee").val("0");  
	    }  
	    var finalPrice =  parseFloat(sum)+ parseFloat(sFee);
	    
	    $("#final_price").val(finalPrice);  
	    
	    // ?????? ??????
	    $('.remove-product').click(function(){
	    	if(!confirm("?????? ?????????????????????????")){ 
	    		return;
	    	}
	    	var cartNo = $(this).attr("cart-no"); 
	    	
			if(cartNo == ''){
				return; 
			} 
			 
			
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/nonuser/api/cart/" + cartNo,
				type : "delete",
				dataType : "json",
				data : "",
				success: function(response){
					console.log(response.data); 
					if(response.data ==true){ 
						alert("?????????????????????.");   
						window.location.reload();  
					}else{
						alert("????????? ??????????????????. ?????? ??????????????????");  												
					}
				}, 
				error : function(xhr, error){ 
					console.error("error : " + error);
				}
			}); 
		});
	    
	    
	}); 
	
	// ???????????? Valid
	// 1. ????????? ???????????? ?????? ??????
	function onlyNumber() {
		if ((event.keyCode < 48)
				|| (event.keyCode > 57))
			event.returnValue = false;
	}

	function CheckForm() {
		if (document
				.getElementById("txtMobile1").value == "") {
			window
					.alert("????????? ????????? ???????????????.");
			return false;
		}
		if (document
				.getElementById("txtMobile2").value.length != 4) {
			window
					.alert("????????? ????????? 4????????? ???????????????");
		}
	} 
														
														
	/* ?????? ?????? api */
    function execDaumPostcode(a) {
        if(a==1){
        	var postcode = $('#postcode')
            var roadAddress = $("#roadAddress")
            var jibunAddress = $("#jibunAddress") 
           	var extraAddress = $("#extraAddress")
        }else{
        	var postcode = $('#postcode2')
            var roadAddress = $("#roadAddress2")
            var jibunAddress = $("#jibunAddress2") 
           	var extraAddress = $("#extraAddress2")
        }         
        new daum.Postcode({ 
            oncomplete: function(data) {
                // ???????????? ???????????? ????????? ??????????????? ????????? ????????? ???????????? ??????.

                // ????????? ????????? ?????? ????????? ?????? ????????? ????????????.
                // ???????????? ????????? ?????? ?????? ????????? ??????('')?????? ????????????, ?????? ???????????? ?????? ??????.
                var roadAddr = data.roadAddress; // ????????? ?????? ??????
                var extraRoadAddr = ''; // ?????? ?????? ??????

                // ??????????????? ?????? ?????? ????????????. (???????????? ??????)
                // ???????????? ?????? ????????? ????????? "???/???/???"??? ?????????.
                if(data.bname !== '' && /[???|???|???]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // ???????????? ??????, ??????????????? ?????? ????????????.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // ????????? ??????????????? ?????? ??????, ???????????? ????????? ?????? ???????????? ?????????.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                } 

                // ??????????????? ?????? ????????? ?????? ????????? ?????????.
                postcode.val(data.zonecode);
                roadAddress.val(roadAddr);
                jibunAddress.val(data.jibunAddress); 
                
                // ???????????? ???????????? ?????? ?????? ?????? ????????? ?????????.
                if(roadAddr !== ''){
                    extraAddress.val(extraRoadAddr);
                } else { 
                    extraAddress.val('');   
                }

                var guideTextBox = document.getElementById("guide");
                // ???????????? '?????? ??????'??? ????????? ??????, ?????? ???????????? ????????? ?????????.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(?????? ????????? ?????? : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(?????? ?????? ?????? : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none'; 
                }
            } 
        }).open();
    }
</script>   
</html>