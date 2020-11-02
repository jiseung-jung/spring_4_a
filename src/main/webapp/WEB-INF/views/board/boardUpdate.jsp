<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/bootStrap.jsp"></c:import>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style type="text/css">
	#f {
		display:none;
	}
	
	.del {
        color: red;
        font-weight: bold;
    }
    
</style>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
  <h2>${board} Update Form</h2>
  <form id="frm" action="./${board}Update" method="post" enctype="multipart/form-data">
  
    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" value="${dto.title}" name="title">
    </div>
    
    <div class="form-group">
      <label for="writer">Writer:</label>
      <input type="text" class="form-control" value="${member.id}" readonly="readonly" id="writer" name="writer">
    </div> 
    
     <div class="form-group">
      <label for="contents">Contents:</label>
      <textarea class="form-control" rows="10" id="contents" name="contents"></textarea>
    </div>	
    
	<input type="button" value="FileAdd" id="fileAdd" class="btn btn-info">
	<div id ="files">
		
	</div>
	<hr>
 	
 	  <div class="form-group">
 	  	<label></label>
    	<input type="button" class="btn btn-primary" value="Write" id="btn">
    	<button type="submit" class="btn btn-default">Write</button>
    </div>
  </form>
</div>

<div id ="f">
	<div class="input-group">
       <input id="files" type="file" class="form-control" name="files">
       <span class="input-group-addon del">DEL</span>
    </div>
</div>

<script type="text/javascript">
	var count =0;
	$('#contents').summernote({
		height:300,
	});
	
	$('#contents').summernote('code', ${dto.contents});
	
	
</script>
</body>
</html>