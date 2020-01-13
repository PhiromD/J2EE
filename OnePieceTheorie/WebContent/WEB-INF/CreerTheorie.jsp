<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="Header.jsp"%>
<div id="categorie"><%@ include file="Menu.jsp"%></div>
${erreur}


	<form action="CreerTheorie" enctype="multipart/form-data" method="POST">
		<textarea id="titre" name="titre" type="text" class="inputChamp"
			placeholder="Titre de ta théorie " value=""></textarea>
		<br>
		<h4>Image pour ta théorie (tu pourras en rajouter dérrière)</h4>

		<!--   Ici le /Upload est ma servlet qui va gérer l'upload elle est bien sur définie dans le web.xml -->

		<p>
			<input type="file" name="file" /> <br>
			<textarea id="description" name="description" rows="20" cols="120"
				type="text" class="inputTextArea" placeholder="Expliques nous..."></textarea>
			<span style="color: black"> </span><br /> <input type="submit"
				value="Envoyer la théorie" class="submitBtn1" />

		</p>
	</form>


<%@ include file="Footer.jsp"%>