package fr.onepiecetheorie.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import fr.onepiecetheorie.pojos.*;
import fr.onepiecetheorie.services.CategorieService;
import fr.onepiecetheorie.services.ImageService;
import fr.onepiecetheorie.services.TheorieService;
import fr.onepiecetheorie.services.UserForumService;

/**
 * Servlet implementation class CreerTheorie
 */
@WebServlet(name="CreerTheorie",urlPatterns= {"/CreerTheorie"})
public class CreerTheorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="jpg";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreerTheorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategorieService categorieservice = new CategorieService();
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			categories = categorieservice.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categories", categories);

		this.getServletContext().getRequestDispatcher("/WEB-INF/CreerTheorie.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String erreur="";

		HttpSession session = request.getSession();
		UserForum userforum = new UserForum();
		userforum =(UserForum) session.getAttribute("userforum");
		Categorie categorie = new Categorie();
		CategorieService categorieService = new CategorieService();
		try {
			categorie = categorieService.getCategorieFromIdTheorie();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String titre = request.getParameter("titre");
		if(titre.isEmpty()) {
			erreur +="Saisis un titre pour ta théorie frère ! <br>";
		}

		String description = request.getParameter("description");
		if(description.isEmpty()) {
			erreur +="Du contenu voyons ! <br>";
		}




		if(erreur.isEmpty()) {
			Theorie theorie = new Theorie(titre, description, new Date());
			UserForumService userForumService = new UserForumService();
			UserForum userTheorie = new UserForum();
			try {
				userTheorie = userForumService.existJoinUser(userforum.getIdUser());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			categorie.addTheorie(theorie);
			userTheorie.addTheorie(theorie);
			theorie.setCategorie(categorie);
			theorie.setMembre(userTheorie);


			TheorieService theorieService = new TheorieService();
			try {
				theorie = theorieService.createTheorie(theorie);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String savePath = "/Users/phirom/eclipse-workspace/OnePieceTheorie/WebContent/img" + File.separator ; //specify your path here
			File fileSaveDir=new File(savePath);
			if(!fileSaveDir.exists()){
				fileSaveDir.mkdir();
			}

			Part part=request.getPart("file");
			String fileName=getNomFichier(part);
			part.write(savePath + File.separator + fileName);
			String filePath= savePath + File.separator + fileName;
			ImageService recettePhotoService = new ImageService();
			ImageTheorie imageTheorie = new ImageTheorie(filePath,fileName);
			imageTheorie.setTheorie(theorie);
			try {
				imageTheorie = recettePhotoService.saveImageTheorie(imageTheorie);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


            
            session.setAttribute("theorie", theorie);
			response.sendRedirect("TheorieCree");

		}
		else{
			request.setAttribute("erreur", erreur);
			this.doGet(request, response);
		}




	}

	private String getNomFichier(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
}
