<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
<br><strong><h1 style="color: crimson">${userforum.pseudo }</h1></strong>
<div id="inscription">
			<form method="post" action="Inscription">
			
				Prénom : ${userforum.nom} <br> 
				   
					Pseudo : <input id="pseudo" name="pseudo" type="text" class="inputChamp" value="${userforum.pseudo}" ></textarea><span style="color:#FF0000"> ${existant}</span><br /> 
					email : <input id="email" name="email" type="text" class="inputChamp" value= "${userforum.email}" /><br /> 
					
					<input id="mdp1" name="mdp" type="password" class="inputChamp" placeholder="nouveau mot de passe *" /><br /> 
					<input id="mdp2" name="mdp_conf" type="password" class="inputChamp" placeholder="Confirmer le nouveau mot de passe *" /><br /> 
					<br /> 
					<input type="submit" value="Je Valide" class="submitBtn" />
			</form>
			${erreur}
		</div>


<%@ include file="Footer.jsp"%>