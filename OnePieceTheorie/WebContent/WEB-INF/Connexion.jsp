<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>

	${erreur }
	${inconnu} 
	<c:choose>
		<c:when test="${!empty sessionScope.userforum}">
			<h1 id="titreBlog">Vous êtes maintenant connectés.</h1>
			<a href="Index"><h1 id="titreBlog">Retour à l'acceuil</h1></a>
		</c:when>
		<c:otherwise>
			<body>

				<div id="global">
					<h1>Connexion</h1>
					<div id="inscription">
						<form method="post" action="Connexion">

							<input id="pseudo" name="pseudo" type="text" class="inputChamp"
								placeholder="Ton pseudo *" ><br /> 
								<input id="mdp1" name="mdp" type="password" class="inputChamp"
								placeholder="Ton mot de passe *" ><br /> 
								<input type="submit" value="Connexion" class="submitBtn" >
						</form>

					</div>


				</div>
			</body>
		</c:otherwise>
	</c:choose>