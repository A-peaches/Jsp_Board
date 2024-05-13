<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Join</title>
<style>
	td{
		text-align : left;
	}
</style>
<script>
	function Check(){
        var pw = document.forms["joinForm"]["user_pw"].value;
        var pw2 = document.forms["joinForm"]["user_pw2"].value;
        var id = document.forms["joinForm"]["user_id"].value;
        
        if(id == "" || pw == "" || pw2 == ""){
        	alert("ID와 PW는 필수 입력사항입니다!");
        	return false;
        }
        
        if (pw != pw2) {
            alert("비밀번호가 잘못 입력되었습니다!");
            return false; 
        }
        
        if (id.length < 4 || id.length > 12) {
            alert("ID는 4~12자 사이로 입력해주세요!");
            return false;
        } else {

            for (var i = 0; i < id.length; i++) {
                var charId = id.charCodeAt(i);
                if (!((charId > 47 && charId < 59) ||
                    (charId > 64 && charId < 91) ||
                    (charId > 96 && charId < 123))) {
                    alert("ID는 영문 대소문자와 숫자로만 입력해주세요!");
                    return false;
                }
            }
        }
        
        if (pw.length < 4 || pw.length > 12) {
             alert("PW는 4~12자 사이로 입력해주세요!");
             return false;
           } else {
               for (var i = 0; i < pw.length; i++) {
                   var charPw = pw.charCodeAt(i);
                   if (!((charPw > 47 && charPw < 59) ||
                       (charPw > 64 && charPw < 91) ||
                       (charPw > 96 && charPw < 123))) {
                       alert("pw는 영문 대소문자와 숫자로만 입력해주세요!");
                       return false;
                    }
                }
            }
            
        
        var email = document.getElementById("user_email").value;


        var idx = email.indexOf("@");
        if (idx == -1 || idx == 0 || idx == email.length - 1) {
            alert("email은 id@domain.com 형식으로 입력해주세요!");
            return false;
        }

        return true;
    }
</script>
</head>
<body>
    <div>
        <h3 class="fw-bold my-3"> 👥 회원 가입 </h3>
        <hr><br>
        <div align='center' >
			<form name="joinForm" method=post action="MemberJoinAction.lo" onsubmit="return Check()">
				<table class="table table-hover" style="width: 55%">
					<tr>
						<td class="subtitle" colspan='2'>
							<h5 class="subtitle fw-bold">회원 기본 정보</h5>
						</td>
					</tr>
					<tr>
						<td>아이디 :</td>
						<td><input type='text' name="user_id" class='form-control w-50' id="user_id"
						placeholder="4~12자의 영문 대소문자와 숫자로만 입력"></input>
						</td>
					</tr>
					<tr>
						<td>비밀번호 :</td>
						<td><input type='password' name="user_pw" class='form-control w-50' id="user_pw"
						placeholder="4~12자의 영문 대소문자와 숫자로만 입력"></input>
						</td>
					</tr>
					<tr>
						<td>비밀번호 확인:</td>
						<td><input type='password' name="user_pw2" class='form-control w-50' id="user_pw2"
						placeholder="4~12자의 영문 대소문자와 숫자로만 입력"></input>
						</td>
					</tr>
					<tr>
						<td>메일 주소:</td>
						<td><input type="text" name="user_email" class='form-control w-50' id="user_email"
						placeholder="예) id@domain.com"></input>
							
					</tr>
					<tr>
						<td>이름:</td>
						<td><input type='text' name="user_name" class='form-control w-50'
						></input>
					</tr>
					<tr>
						<td colspan='2' class="subtitle">
							<h5 class="subtitle fw-bold">개인 신상 정보</h5>
						</td>
					</tr>
					<tr>
						<td>생일 :</td>
						<td>
						 <div class="row g-3 align-items-center">
           				 <div class="col-auto">
						<label for="year" class="col-form-label">연도</label>
               			 <select class="form-select" name="year" id="year">년
                			<option value='1990'>1990</option>
							<option value='1991'>1991</option>
							<option value='1992'>1992</option>
							<option value='1993'>1993</option>
							<option value='1994'>1994</option>
							<option value='1995'>1995</option>
							<option value='1996'>1996</option>
							<option value='1997'>1997</option>
							<option value='1998'>1998</option>
							<option value='1999'>1999</option>
							<option value='2000'>2000</option>
							<option value='2001'>2001</option>
							<option value='2002'>2002</option>
							<option value='2003'>2003</option>
							<option value='2004'>2004</option>
							<option value='2005'>2005</option>
							<option value='2006'>2006</option>
							<option value='2007'>2007</option>
							<option value='2008'>2008</option>
							<option value='2009'>2009</option>
							<option value='2009'>2009</option>
							<option value='2010'>2010</option>
							<option value='2011'>2011</option>
							<option value='2012'>2012</option>
							<option value='2013'>2013</option>
							<option value='2014'>2014</option>
							<option value='2015'>2015</option>
							<option value='2016'>2016</option>
						</select>
						</div>
						<div class="col-auto">
         			       <label for="month" class="col-form-label">월</label>
             			   <select class="form-select" name="month" id="month">
								<option value='1'>1</option>
								<option value='2'>2</option>
								<option value='3'>3</option>
								<option value='4'>4</option>
								<option value='5'>5</option>
								<option value='6'>6</option>
								<option value='7'>7</option>
								<option value='8'>8</option>
								<option value='9'>9</option>
								<option value='10'>10</option>
								<option value='11'>11</option>
								<option value='12'>12</option>
						</select>
						</div>
						 <div class="col-auto">
			                <label for="day" class="col-form-label">일</label>
			                <select class="form-select" name="day" id="day">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
						</select>
						</div>
						</div>
						</td>
					</tr>
					<tr style='height: 45px'>
						<td>관심분야 :</td>
						<td><input type='radio' name='hobby' value='게임' class="form-check-input mx-3 " style="border : 1px solid gray;"> 게임
							<input type='radio' name='hobby' value='맛집탐방' class="form-check-input mx-3 " style="border : 1px solid gray;">맛집탐방
							<input type='radio' name='hobby' value='여행' class="form-check-input mx-3 " style="border : 1px solid gray;">여행
							<input type='radio' name='hobby' value='운동' class="form-check-input mx-3 " style="border : 1px solid gray;">운동
							<input type='radio' name='hobby' value='음악감상' class="form-check-input mx-3 " style="border : 1px solid gray;">음악감상
							</td>
					</tr>
					<tr style='height: 65px'>
						<td>자기 소개 :</td>
						<td><textarea name="info" cols='105' rows='6' class="form-control"
						placeholder="30자이상 작성해주세요."></textarea> 
						
						</td>
					</tr>
				</table>
				<div style="margin: 20px;">
				
					<input type="submit" class="btn btn-success" value="회원 가입">
					<input type="reset"  class="btn btn-danger" value="다시 입력">
					<a href="javascript:history.back()" class="btn btn-secondary">이전</a>
				</div>
			</form>
		</div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
	
</body>
</html>