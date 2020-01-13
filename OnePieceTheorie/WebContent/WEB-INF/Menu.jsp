<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="global">
	
       <ul>
			<div style="width: 300px; margin: 20px auto;">


			<c:forEach var="Categorie" items="${categories}" varStatus="loop">
				

				<li class="selected"><a
					href="Categorie?idCategorie=${Categorie.idCategorie}">${Categorie.nom}</a>

				</li>
			</c:forEach>
		</ul>
	
	</div>