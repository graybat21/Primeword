<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="_csrf_header" content="X-CSRF-TOKEN" />
<meta name="${_csrf.parameterName}" content="${_csrf.token}" />

<script type="text/javascript" src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function isExistUser()		// use keyup event -> To check id
{
	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;	
	
	$("#idCheck").bind("click",function() {
		$.ajax({
		  url : "/Primeword/duplicationCheck.prime",
		  type : "post",
		  contentType : 'application/json; charset=utf-8',
		  //data : JSON.stringify({ username : $("#username").val() }),
		  data : $("#username").val(),
		  //dataType: "json",
		  success : function(data) {
		    if (data) {
		    	alert("이름이 같은 회원이 있습니다");
		    } 
		    else {
		    	alert("가입 가능합니다.");
		    } 
		  },
		  error : function(error) {
		    alert("error");  
		  }
		});
	  	return false;
	});
}
	
</script>
</head>
<body>
<div id="wrap">
	
	<div id="main_bg">
		<div class="join_wrap">
			<div class="login_content">
				<div class="login_title">회원가입</div>
				<!-- <form method="post" id="join-form" name="join-form"> -->
				<form:form method="post" id="join-form" name="join-form" onsubmit="valid_check(this)">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<!-- commandName="user"  -->
				<div class="input_area">
					<div class="left">
						<div class="top">
							<div class="text">이름</div>
							<div class="input"><input type="text" id="username" name="username"></div>
						</div>
						<div>
							<div class="text">비밀번호</div>
							<div class="input"><input type="password" name="passwd"></div>
						</div>
						<div>
							<div class="text">비밀번호확인</div>
							<div class="input"><input type="password" name="passwdCheck"></div>
						</div>
						<div class="bottom">
							<div class="text">생년월일</div>
							<div class="input">
							<%-- <select name="grade">
								<c:forEach begin="1" end="12" var="item">
									<option value="${item }">${item }학년</option>
								</c:forEach>
							</select> --%>
							<input type="text" id="birth" name="birth">
							</div>
						</div>
					</div>
					<div class="right">
						<div class="join-form-idcheck">
						<input type="button" name="idCheck" id="idCheck" value="중복확인" onclick="isExistUser()"/>
						<!-- 이름, 생년월일로 중복확인 -->
						</div>
						<div class="join-form-submit">
						<input type="submit" value="가입"/>
						</div>
						<!-- <img src="images/login_btn.png" alt="조인" onclick="javascript:join-form.submit();" > -->
					</div>
				</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<!-- <script type="text/javascript">
function verifynotify(field1, field2, result_id, match_html, nomatch_html) 
{
  this.field1 = field1;
  this.field2 = field2;
  this.result_id = result_id;
  this.match_html = match_html;
  this.nomatch_html = nomatch_html;

  this.check = function() {
    // Make sure we don't cause an error
    // for browsers that do not support getElementById
    if (!this.result_id) { return false; }
    if (!document.getElementById){ return false; }
    r = document.getElementById(this.result_id);
    if (!r){ return false; }

    if (this.field1.value != "" && this.field1.value == this.field2.value) {
      r.innerHTML = this.match_html;
    } else {
      r.innerHTML = this.nomatch_html;
    }
  }
}
function verifyInput() 
{
	verify = new verifynotify();
	verify.field1 = document.join-form.passwd;
	verify.field2 = document.join-form.passwdCheck;
	verify.result_id = "password_result";
	verify.match_html = "<span style=\"color:blue\">패스워드가 일치합니다!<\/span>";
	verify.nomatch_html = "<span style=\"color:red\">귀하의 비밀번호가 일치하는지 확인하십시오!<\/span>";
	
	// Update the result message
	verify.check();
}

function addLoadEvent(func) 
{
	var oldonload = window.onload;
	if (typeof window.onload != 'function') {
	  window.onload = func;
	} else {
	  window.onload = function() {
	    if (oldonload) {
	      oldonload();
	    }
	    func();
	  }
	}
}

addLoadEvent(function() {
  verifyInput();
});

function verifyOK()
{
	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;		
	var pwReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/g;

	if ( document.join-form.passwd.value != document.join-form.passwdCheck.value )
	{
		alert("비밀번호와 비밀번호 확인이 서로 일치하지 않습니다. 다시 확인해주세요.");
		return false;	
	}
	else if ( !idReg.test(document.join-form.username.value) ) 
	{
		alert("아이디는 영문 소문자로 시작하는 6~20자의 영문자 또는 숫자이어야 합니다.");
		return false;
	}
	else if ( $("#duplicateResult").text() != '사용 가능한 아이디입니다.' && $("#duplicateResult").text() != '') 
	{
		alert("이미 해당 아이디로 가입된 회원이 있습니다. 다른 아이디를 입력해주세요.");
		return false;
	}
	else if ( !pwReg.test(document.join-form.passwd.value) ) 
	{
		alert("패스워드는 6~20자의 영문 대소문자, 숫자, 특수문자로 이루어져야 하며, 최소 한개의 숫자 혹은 특수문자가 포함되어야 합니다.");
		return false;
	}
	else
		return true;
}
function isExistUser()
{
	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;	
	
	$("#username").keyup(function() {
		if ( $("#username").val().length >= 6 )
		{	
			$.ajax({
			  url : "/duplicationCheck.prime",
			  type : "post",
			  contentType : 'application/json; charset=utf-8',
			  //data : JSON.stringify({ username : $("#username").val() }),
			  data : $("#username").val(),
			  //dataType: "json",
			  success : function(data) {
			    if (data) {
			    	$("#duplicateResult").text("이미 해당 아이디로 가입된 회원이 있습니다. 다른 아이디를 입력해주세요."); 
			    } 
			    else {
			    	$("#duplicateResult").text(""); 
			    } 
			  },
			  error : function(error) {
			    alert(error.statusText);  
			  }
			});
		}
	  	return false;
	});
}
</script> -->