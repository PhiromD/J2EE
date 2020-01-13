<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
<div id="categorie"><%@ include file="Menu.jsp"%> </div>

<br><strong><h1 style="color: crimson">${user.pseudo }</h1></strong>
<h2 style="color: coral">Ses théories :</h2> <br>
<c:choose>
<c:when test="${!empty theories}">
<c:forEach var="Theorie" items="${theories}" varStatus="loop">
	<li class="selected"><a
		href="Theorie?idTheorie=${Theorie.idTheorie}" style="color: gold;">${Theorie.titre}</a>

	</li>
	<br>
	<br>

</c:forEach>
</c:when>
<c:otherwise>
Cette personne n'a pas de théorie apparement...
</c:otherwise>
</c:choose>

<%@ include file="Footer.jsp"%>