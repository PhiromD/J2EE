<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="Header.jsp"%>
<body>
	
	<div id="global">
		<h1>Inscription</h1>
		<c:choose>
	<c:when test="${empty sessionScope.userforum}" >
		<div id="inscription">
			<form method="post" action="Inscription">
			
				<input id="nom" name="nom" type="text" class="inputChamp" placeholder="Votre nom *" value="${membre.nom}" ></textarea><br />
				   
					<input id="pseudo" name="pseudo" type="text" class="inputChamp" placeholder="Votre pseudo *" ></textarea><span style="color:#FF0000"> ${existant}</span><br /> 
					<input id="email" name="email" type="text" class="inputChamp" placeholder="Votre email *" /><br /> 
					
					<input id="mdp1" name="mdp" type="password" class="inputChamp" placeholder="Votre mot de passe *" /><br /> 
					<input id="mdp2" name="mdp_conf" type="password" class="inputChamp" placeholder="Confirmer le mot de passe *" /><br /> 
					<br /> 
					<input type="submit" value="Je 'm'inscris" class="submitBtn" />
			</form>
			${erreur}
		</div>
		</c:when>
		<c:when test="${!empty sessionScope.userforum }">
		<div id="creation user">
			
			<!-- CONTENT  -->
						<h4>Ton avatar</h4>
						
                    <!--   Ici le /Upload est ma servlet qui va gérer l'upload elle est bien sur définie dans le web.xml -->
						<form action="Inscription" enctype="multipart/form-data" method="POST">
						<p>
								<input type="file" name="file" /> <input type="submit"
									value="Envoyer" />
						</p>
						
						</form>
						
						</div>
		
		</c:when>
		</c:choose>
		
		
	</div>
	</body>
	<%@ include file="Footer.jsp"%>