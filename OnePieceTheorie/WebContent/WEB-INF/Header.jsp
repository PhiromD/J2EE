<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link href="Styles/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<title>One Piece et ses Théories</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<div style="text-align: right" ><font
						color="#FFD700">One Piece</font></div>
<header id="header">
	<a href="Index"><h1 id="titreBlog"><font color="#FFD700">One Piece Theorie</font></h1> </a>
	
	<div id="loginBar">

		<c:choose>
			<c:when test="${empty sessionScope.userforum}">
				<div class="login">
					<a class="primaryBtn login" href="Connexion">Connexion</a> <a
						class="primaryBtn login" href="Inscription">Inscription</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="login">
					<a class="primaryBtn login" href="Deconnexion">Deconnexion</a>
				</div>


				<div>
					<img src="img/${sessionScope.image.nameImage}" alt="Avatar" class="avatar" /> 
						${sessionScope.userforum.pseudo} <div class="login">
					<a class="primaryBtn login" href="UpdateUser">Edit</a> 
				</div><br>
					${sessionScope.userforum.bounty} <strong><font
						color="#FFD700">Berrys</font></strong>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
</header>




</head>