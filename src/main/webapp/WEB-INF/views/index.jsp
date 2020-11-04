<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
<c:import url="./template/bootStrap.jsp"></c:import>	
</head>
<body>

<c:import url="./template/header.jsp"></c:import>


<div class="container">
  <h3>Right Aligned Navbar</h3>
  <p>The .navbar-right class is used to right-align navigation bar buttons.</p>
  <div>
  	<c:if test="${not empty member}">
  		<h1>Login Success</h1>
  	</c:if>
  	
  	<c:if test="${empty member}">
  		<h1>Login Fail</h1>
  	</c:if>
  	
  	<img alt="main Image" src="./resources/images/index/3.jpg">
  </div>
  
  	<a href="./cookie/makeCookie">Make Cookie</a>
  
</div>

</body>
</html>
