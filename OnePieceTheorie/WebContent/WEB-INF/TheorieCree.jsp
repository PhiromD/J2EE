<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>


<h1 id="titreBlog">Théorie ajoutée !</h1>

<li class="selected"><center><a href="Theorie?idTheorie=${theorie.idTheorie}"
	style="color: gold;">${theorie.titre}</center></a></li>
	<%@ include file="Footer.jsp"%>