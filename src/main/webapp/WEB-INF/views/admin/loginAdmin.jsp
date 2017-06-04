<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>ADMIN LOGIN</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../../plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">
<div class="login-box">
	 <div class="login-logo">
	 	<b>Primeword 관리자 페이지</b>
	 </div>
  	<!-- /.login-logo -->
  	<div class="login-box-body">
    	<p class="login-box-msg">uStyle 관리자 로그인</p>
    	<c:if test="${not empty param.login_error}">		
			<font color="red">관리자 ID, PW가 올바르지 않습니다. 확인 후 다시 입력해주세요.</font>
		</c:if>
    	<form method="post" action="<c:url value='/admin/loginAuth' />">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="form-group has-feedback">
		  	<input type="text" name="username" class="form-control" placeholder="Admin Name">
		</div>
		<div class="form-group has-feedback">
		  	<input type="password" name="password" class="form-control" placeholder="Admin Password">
		</div>
		<div class="row">
		  	<div class="col-xs-8">
		  	</div>
		  	<!-- /.col -->
		  	<div class="col-xs-4">
		    	<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
		  	</div>
		  	<!-- /.col -->
		</div>
		</form>
		
	</div>
  	<!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<script src="../../plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../../bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="../../plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
