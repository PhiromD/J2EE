<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
<body>
<div id="global">
	<div id="categorie"><%@ include file="Menu.jsp"%>


			<h1 id="titreBlog"><a>${theorie.titre}</a></h1>
			<br> 
			${theorie.description}
			<br>
			<c:forEach var="ImageTheorie" items="${theorie.getImageTheorie()}" varStatus="loop">
			
			
			<img class="imgImage" src="img/${ImageTheorie.nameImage}"  />
			</c:forEach>
			

</div>
</div>
<%@ include file="Footer.jsp"%> 