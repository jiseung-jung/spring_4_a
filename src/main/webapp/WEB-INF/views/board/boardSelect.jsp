<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="../template/bootStrap.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
  
<div class="container">
  <h3>Notice Select Page</h3>
  <hr>
  <h3 id="num">${dto.num}</h3>
  <hr>
  <h3>Title : ${dto.title}</h3>
  <hr>
  <h3>Writer : ${dto.writer}</h3>
  <hr>
  <h3>Contents : ${dto.contents}</h3>
  <hr>
  
  <div>
  <c:forEach items="${dto.boardFileDTOs}" var="dto">
  	<a href="./fileDown?fileName=${dto.fileName}&oriName=${dto.oriName}">${dto.oriName}</a>
  	<hr>
  </c:forEach>
  </div>
  
  
  
  <input type="button" title="${dto.num}" value="Delete" class="btn btn-default" id="del">
  <input type="button" class="btn btn-default" value="Update" id="update">
  <c:if test="${board ne 'notice'}">
  <a href="./${board}Reply?num=${dto.num}" class="btn btn-info">Reply</a>
  </c:if>
</div>

<script type="text/javascript">
	//$("css선택자").action();
	$("#update").click(function() {
		location.href="./noticeUpdate?num=${dto.num}";
	});
	
	$("#del").click(function() {
		//var num = $("#num").html();
		var num = $(this).attr("title");
		alert(num);
		location.href="./noticeDelete?num="+num;
	});

</script>


</body>
</html>