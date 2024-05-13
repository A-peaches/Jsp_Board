<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="net.basket.db.BasketBean" %>
<% request.setCharacterEncoding("UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<meta charset="UTF-8">

<title>CookieList</title>
<style>
	.img {
		height : 100px;
		width : 100px
	}
	
	td, tr {
		padding : 5px 5px 5px 5px;
	}
</style>
		<script>
				// 수량 증가
				function increase(cookie) {
				    let cnt = document.getElementById("quantity" + cookie);
				    let quantity = parseInt(cnt.value);
				    cnt.value = quantity + 1;
				}
				
				// 수량 감소
				function decrease(cookie) {
				    let cnt = document.getElementById("quantity" + cookie);
				    let quantity = parseInt(cnt.value);
				    if (quantity > 0) {
				        cnt.value = quantity - 1;
				    }
				}
				

				function isEmpty() {
					let cookies = ["Ame","Double","Otmeal"];
					let allEmpty = true;
					let exists = false;
					
					//3개 다 수량 입력안했을경우를 체크한다.
					for(let i =0; i<3; i++){
						let cnt = document.getElementById("quantity" + cookies[i]).value;
						if(parseInt(cnt)>0) {
							allEmpty = false;
							break;
						}
					}
					
					if(allEmpty) {
						alert("1개 이상의 상품을 선택해주세요!");
						return false;
					}
					
					
					return true;
				}
				
		</script>
</head>	
<body>
	<jsp:include page="../login/loginState.jsp"></jsp:include>
	<h3 class="my-4 fw-bold" >🍪 CookieList</h3>
	
	<form name="List" method ="post" action="BasketAddAction.ba" onsubmit="return isEmpty()">
	<table class="table table-hover w-50">
		<tr>
			<td class="fw-bold">제품 사진</td>
			<td class="fw-bold">제품명</td>
			<td class="fw-bold">제품가격</td>
			<td class="fw-bold">수량</td>
		</tr>
		<tr>
			<td><img src="./img/cookie1.png" class="img"/></td>
			<td>AmericanCookie</td>
			<td>3,000원</td>
			<td>
				<input type="button" value="-" name="decreaseAme"
				class= "btn btn-secondary" onclick="decrease('Ame')"/>
				<input type="number" class="form-control" name="AmeCnt" id="quantityAme" 
				value="0" style="width:50px; display : inline-block"/>
				<input type="button" value="+"  class= "btn btn-secondary"
				name="increaseAme" onclick="increase('Ame')"/>
			</td>
		</tr>
		<tr>
			<td><img src="./img/cookie2.png" class="img"/></td>
			<td>DoubleChocoCookie</td>
			<td>2,800원</td>
			<td>
				<input type="button" class= "btn btn-secondary"  value="-" 
				name="decreaseDouble" onclick="decrease('Double')"/>
				<input type="number" class="form-control" name="DoubleCnt" id="quantityDouble" 
				value="0" style="width:50px; display : inline-block"/>
				<input type="button" class= "btn btn-secondary" value="+" 
				name="increaseDouble" onclick="increase('Double')"/>
			</td>
		</tr>
		<tr>
			<td><img src="./img/cookie3.png" class="img"/></td>
			<td>OtmealCookie</td>
			<td>2,600원</td>
			<td>
				<input type="button" value="-" name="decreaseOtmeal" 
				class= "btn btn-secondary" onclick="decrease('Otmeal')"/>
				<input type="number" name="OtmealCnt" class="form-control"
				id="quantityOtmeal" value="0" style="width:50px; display : inline-block"/>
				<input type="button" value="+" name="increaseOtmeal"
				class= "btn btn-secondary" onclick="increase('Otmeal')"/>
			</td>
		</tr>
	</table>
		<input type=submit name=intoCart value="장바구니 담기 및 이동" 
		class="btn btn-success" style="margin-top:20px"/><br>
		<input type=button name=intoCart onclick="window.location.href='./BasketViewAction.ba'" value="장바구니 이동"
		class="btn btn-warning" style="margin-top:20px"/><br>

	</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
	
</body>

</html>