package fr.onepiecetheorie.servlets;

import java.io.File;
import java.io.IOException;




import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.onepiecetheorie.pojos.Categorie;
import fr.onepiecetheorie.pojos.Image;
import fr.onepiecetheorie.pojos.UserForum;
import fr.onepiecetheorie.services.CategorieService;
import fr.onepiecetheorie.services.ImageService;
import fr.onepiecetheorie.services.UserForumService;




/**
 * Servlet implementation class Inscription
 */
@WebServlet(name="Inscription",urlPatterns= {"/Inscription"})
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="jpg";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String erreur="";
		HttpSession session = request.getSession();


		if((UserForum)session.getAttribute("userforum")==null) {
			String nom = request.getParameter("nom");
			if(nom.isEmpty()) {
				erreur +="Ton prénom <br>";
			}

			String pseudo = request.getParameter("pseudo");
			if(pseudo.isEmpty()) {
				erreur +="Ton pseudo moussaillon ! <br>";
			}
			String email = request.getParameter("email");
			if(email.isEmpty()) {
				erreur +="Ton email <br>";
			}


			String mdp = request.getParameter("mdp");
			String mdp_conf = request.getParameter("mdp_conf");
			if(mdp.isEmpty()) {
				erreur +="un bon mot de passe <br>";
			}



			// TODO: handle exception


			if(!mdp.contentEquals(mdp_conf)) {
				erreur += "La confirmation du mot de passe n'est pas bonne.";
			}



			if(erreur.isEmpty()) {
				String message = "Vous êtes inscrit";
				request.setAttribute("message", message);
				UserForum user = new UserForum(nom,pseudo,mdp,email,new Date());
				UserForum membrePseudoExistant = new UserForum();



				UserForumService membreservice = new UserForumService();
				try {
					membrePseudoExistant = membreservice.existPseudo(pseudo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(membrePseudoExistant==null) {

					
					session.setAttribute("userforum", user);
					response.sendRedirect("Inscription");

				}
				else {
					String existant= "Pseudo déjà pris... ";
					request.setAttribute("existant", existant);
					request.setAttribute("userforum", user);
					this.doGet(request, response);




				}

			}
			else {


				UserForum user = new UserForum(nom,pseudo,email,mdp,new Date());
				request.setAttribute("userforum", user);
				request.setAttribute("erreur", erreur);
				this.doGet(request, response);
			}
		}
		else if((UserForum)session.getAttribute("userforum")!=null){




			String savePath = "/Users/phirom/eclipse-workspace/OnePieceTheorie/WebContent/img" + File.separator ; //specify your path here
			File fileSaveDir=new File(savePath);
			if(!fileSaveDir.exists()){
				fileSaveDir.mkdir();
			}
			UserForum user = (UserForum)session.getAttribute("userforum");
			Part part=request.getPart("file");
			String fileName= getNomFichier(part);
			part.write(savePath + File.separator + fileName );
			String filePath= savePath + File.separator + fileName;
			UserForumService userService = new UserForumService();
			Image image = new Image(filePath,fileName);
			
			CategorieService categorieService = new CategorieService();
			Categorie categorie = new Categorie();
			try {
				categorie = categorieService.getCategorieFromIdUser();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			user.setCategorie(categorie);
			
			categorie.addUser(user);
			
			
			
			
			try {
				user = userService.createMembre(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            image.setUser(user);
            ImageService imageService = new ImageService();
            image = imageService.SaveImageUser(image);
			session.setAttribute("userforum",user);
			session.setAttribute("image", image);
			response.sendRedirect("Index");
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
