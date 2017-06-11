<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">


<style>
a {
    -webkit-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
    -moz-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
    -ms-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
    -o-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
    transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);

}

a.button {
    color: rgba(30, 22, 54, 0.6);
    box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;
}

a.button:hover {
    color: rgba(255, 255, 255, 0.85);
    box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;
}
</style>


<script type="text/javascript"
   src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
   function isExistUser() {
      $.ajax({
         url : "/Primeword/duplicationCheck.prime",
         type : "post",
         data : $("#username").val(),
         success : function(data) {
            if (data) {
               alert("id가 같은 회원이 있습니다");
               $('#IDcodeCheck').val("0");
            } else {
               alert("가입 가능합니다.");
               $('#IDcodeCheck').val("1");
            }
         },
         error : function(error) {
            alert("error");
         }
      });
      return false;
   }

   function checksubmit() {
      if ($('#IDcodeCheck').val() == "1") {
    	  alert('a');
         $('#join-form').submit();
      } else {
         alert("중복체크 확인하세요.")
      }
   }
   function selectChange(){
		$('#sbelong option:selected').each(function () {
           if($("#sbelong").val() == '1'){ //직접입력일 경우
                $("#belong").val('');                        //값 초기화
                $("#belong").attr("disabled",false); //활성화
           }else{ //직접입력이 아닐경우
                $("#belong").val($("#sbelong").val());      //선택값 입력
                $("#belong").attr("disabled",true); //비활성화
           }
      	});
   }
</script>
</head>
<body>
   <div id="wrap">

      <div id="main_bg" style="padding-top:50px;">
         <div class="join_wrap" style="height:650px;">
            <div class="login_content">
               <div class="login_title">회원가입</div>
               <form method="post" id="join-form" name="join-form" >
               <%-- <form:form method="post" id="join-form" name="join-form"
                  onsubmit="valid_check(this)"> --%>
                  <!-- commandName="user"  -->
                  <input type="hidden" id="IDcodeCheck" value="0">
                  <div class="input_area">
                     <div class="left">
                        <div class="top">
                           <div class="text">ID</div>
                           <input type="text" class="form-control col-lg-4" id="username" name="username"
                  style="width:338px; height:46px; background:#ffffff;">
                        </div>
                        <div>
                           <div class="text">비밀번호</div>
                           <div class="input">
                              <input type="password" name="passwd" style="width:338px; height:46px; background:#ffffff;">
                           </div>
                        </div>
                        <div>
                           <div class="text">비밀번호확인</div>
                           <div class="input">
                              <input type="password" name="passwdCheck" style="width:338px; height:46px; background:#ffffff;">
                           </div>
                        </div>

                        <div>
                           <div class="text">REALNAME</div>
                           <div class="input">
                              <input type="text" name="realname" style="width:338px; height:46px; background:#ffffff;">
                           </div>
                        </div>
                        <div>
                           <div class="text">PHONE</div>
                           <div class="input">
                              <input type="text" name="phone" style="width:338px; height:46px; background:#ffffff;" placeholder="-없이 입력해 주세요.">
                           </div>
                        </div>
                        <div>
                           <div class="text">BIRTH</div>
                           <div class="input">
                              <input type="text" name="birth" style="width:338px; height:46px; background:#ffffff;" placeholder="-없이 입력해 주세요.">
                           </div>
                        </div>
                        <div>
                           <div class="text">BELONG</div>
                           <div class="input">
                              <!-- <input type="text" name="belong"> -->
                              <select id="sbelong" style="width:338px; height:46px; background:#ffffff;" onchange="javascript:selectChange();">
                                 <option value->소속 학원을 선택하세요</option>
                                 <c:forEach var="item" items="${belongList }">
                                    <c:if test="${item != null}">
                                       <option value="${item }">${item }</option>
                                    </c:if>
                                 </c:forEach>
                                 <option value="1">직접 입력</option>
                              </select>
                           </div>
                        </div>
                        <div>
                           <div class="text">&nbsp;</div>
                           <div class="input">
                              <input type="text" id="belong" name="belong" disabled>
                           </div>
                        </div>


                        <div>
                           <div class="text" >SCHOOL</div>
                           <div class="input">
                              <input type="text" name="school" style="width:338px; height:46px; background:#ffffff;">
                           </div>
                        </div>

                        <%-- <div class="bottom">
                     <div class="text">생년월일</div>
                     <div class="input">
                     <select name="grade">
                        <c:forEach begin="1" end="12" var="item">
                           <option value="${item }">${item }학년</option>
                        </c:forEach>
                     </select>
                     <input type="text" id="birth" name="birth">
                     </div>
                  </div> --%>
                     </div>
                     <div class="right">
                        <div class="join-form-idcheck" style="margin-bottom: 420px; margin-top: 15px;">
                           <a href="#" class="button" style="padding:20px;" onclick="isExistUser()">아이디 중복확인</a>
                        
                         <!--  <input type="button" name="idCheck" id="idCheck" value="중복확인"
                             onclick="isExistUser()" /> -->
                           <!-- 이름, 생년월일로 중복확인 --> 
                        </div>
                        <div class="join-form-submit">
                        <a href="#" class="button" style="padding:30px;" onclick="checksubmit()">가입</a>
                        </div>
                        <!-- <img src="images/login_btn.png" alt="조인" onclick="javascript:join-form.submit();" > -->
                     </div>
                  </div>
                  </form>
            </div>
         </div>
      </div>
   </div>
</body>
</html>

<%-- <%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	function isExistUser() {
		$.ajax({
			url : "/Primeword/duplicationCheck.prime",
			type : "post",
			data : $("#username").val(),
			success : function(data) {
				if (data) {
					alert("id가 같은 회원이 있습니다");
					$('#IDcodeCheck').val("0");
				} else {
					alert("가입 가능합니다.");
					$('#IDcodeCheck').val("1");
				}
			},
			error : function(error) {
				alert("error");
			}
		});
		return false;
	}

	function checksubmit() {
		if ($('#IDcodeCheck').val() == "1") {
			$('#join-form').submit();
		} else {
			alert("중복체크 확인하세요.")
		}
	}
</script>
<script>
	$('#sbelong').change(function(){
	   $("#sbelong option:selected").each(function () {
	        
	        if($(this).val()== '1'){ //직접입력일 경우
	             $("#belong").val('');                        //값 초기화
	             $("#belong").attr("disabled",false); //활성화
	        }else{ //직접입력이 아닐경우
	             $("#belong").val($(this).text());      //선택값 입력
	             $("#belong").attr("disabled",true); //비활성화
	        }
	   });
	});
</script>
</head>
<body>
	<div id="wrap">

		<div id="main_bg">
			<div class="join_wrap">
				<div class="login_content">
					<div class="login_title">회원가입</div>
					<!-- <form method="post" id="join-form" name="join-form"> -->
					<form:form method="post" id="join-form" name="join-form"
						onsubmit="valid_check(this)">
						<!-- commandName="user"  -->
						<!-- <input type="hidden" id="IDcodeCheck" value="0"> -->
						<div class="input_area">
							<div class="left">
								<div class="top">
									<div class="text">USERNAME</div>
									<div class="input">
										<input type="text" id="username" name="username">
									</div>
								</div>
								<div>
									<div class="text">비밀번호</div>
									<div class="input">
										<input type="password" name="passwd">
									</div>
								</div>
								<div>
									<div class="text">비밀번호확인</div>
									<div class="input">
										<input type="password" name="passwdCheck">
									</div>
								</div>

								<div>
									<div class="text">REALNAME</div>
									<div class="input">
										<input type="text" name="realname">
									</div>
								</div>
								<div>
									<div class="text">PHONE</div>
									<div class="input">
										<input type="text" name="phone">
									</div>
								</div>
								<div>
									<div class="text">BIRTH</div>
									<div class="input">
										<input type="text" name="birth">
									</div>
								</div>
								<div>
									<div class="text">BELONG</div>
									<div class="input">
										<!-- <input type="text" name="belong"> -->
										<select id="sbelong">
											<option selected="selected" disabled="disabled">소속 학원을 선택하세요</option>
											<c:forEach var="item" items="${belongList }">
												<c:if test="${item != null}">
													<option value="${item }">${item }</option>
												</c:if>
											</c:forEach>
											<option value="1">직접 입력</option>
										</select>
									</div>
								</div>
								<div>
									<div class="text">&nbsp;</div>
									<div class="input">
										<input type="text" id="belong" name="belong" disabled="disabled">
									</div>
								</div>


								<div>
									<div class="text">SCHOOL</div>
									<div class="input">
										<input type="text" name="school">
									</div>
								</div>

								<div class="bottom">
							<div class="text">생년월일</div>
							<div class="input">
							<select name="grade">
								<c:forEach begin="1" end="12" var="item">
									<option value="${item }">${item }학년</option>
								</c:forEach>
							</select>
							<input type="text" id="birth" name="birth">
							</div>
						</div>
							</div>
							<div class="right">
								<div class="join-form-idcheck">
									<input type="button" name="idCheck" id="idCheck" value="중복확인"
										onclick="isExistUser()" />
									<!-- 이름, 생년월일로 중복확인 -->
								</div>
								<div class="join-form-submit">
									<input type="button" value="가입" onclick="checksubmit()" />
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
 --%>