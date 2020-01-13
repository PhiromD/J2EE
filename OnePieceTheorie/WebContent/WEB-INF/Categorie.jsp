<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>

<body>


	<div id="global">
		<div id="categorie">
			<%@ include file="Menu.jsp"%></div>

		
			<div id="contenucategorie">
				<c:forEach var="Theorie" items="${theories}" varStatus="loop">
					<li class="select"><a
						href="Theorie?idTheorie=${Theorie.idTheorie}"style="color:gold;">${Theorie.titre}</a>

					</li>
					<br>
					<br>

					

				</c:forEach>
				<c:forEach var="Mugiwara" items="${mugiwaras}" varStatus="loop">
					<a
						href="Mugiwara?idMugiwara=${Mugiwara.idMugiwara}"style="color:gold;">${Mugiwara.name}</a>

					
					<br>

					<br>
					

				</c:forEach>
				<c:forEach var="UserForum" items="${users}" varStatus="loop">
					<img src="img/${UserForum.image.getNameImage()}" alt="Avatar" class="avatar" /> 
					<a href="User?idUser=${UserForum.idUser}"style="color:gold;">${UserForum.pseudo}</a>

				
					
					
					<br>
					
				</c:forEach>
			</div>
			
	
	<c:choose>
		<c:when test="${!empty sessionScope.userforum}">
			<h2 id="titreAjout">
				<a href="CreerTheorie" style="color:brown;">Ajouter une
						Theorie</a>
			</h2>
		</c:when>
		<c:otherwise>
			<div style="width: 400px; margin: 10px auto;">Pour ajouter une
				théorie il faut être connecté...</div>
		</c:otherwise>
	</c:choose>
	
	<hr />



	<%@ include file="Footer.jsp"%>