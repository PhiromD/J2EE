<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
<body>
<div id="global">
	<div id="categorie"><%@ include file="Menu.jsp"%>


			<h1 id="titreBlog"><a>${mugiwara.lastName}    ${mugiwara.name}  </a>
			<br> 
			<font color="#FFD700">${mugiwara.bounty}   Berrys</font> </h1>
			<br>
			<img  auto class="img" src="img/${mugiwara.name }.jpg"
					width="500px" height="400px" alt="Mugiwaras" /> 
					<h2>${mugiwara.description }</h2>

</div>
</div>
<%@ include file="Footer.jsp"%>