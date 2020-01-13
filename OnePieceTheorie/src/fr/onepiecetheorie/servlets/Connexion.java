package fr.onepiecetheorie.servlets;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.onepiecetheorie.pojos.Image;
import fr.onepiecetheorie.pojos.UserForum;
import fr.onepiecetheorie.services.ImageService;
import fr.onepiecetheorie.services.UserForumService;


/**
 * Servlet implementation class Connexion
 */
@WebServlet(name="Connexion",urlPatterns= {"/Connexion"})
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String erreur ="";
		String pseudo = request.getParameter("pseudo");
		if(pseudo.isEmpty()) {
			erreur +="Veuillez saisir un pseudo <br>";
		}
		String mdp = request.getParameter("mdp");
		if(mdp.isEmpty()) {
			erreur +="Veuillez saisir un mot de passe <br>";
		}
		if(erreur.isEmpty()) {
			
					
			
				
				UserForumService membreService = new UserForumService();
				boolean result = false;
				try {
					result = membreService.authenticateUser(pseudo, mdp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(result){
					UserForum user = new UserForum();
					try {
						user = membreService.existUser(pseudo, mdp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.setAttribute("userforum", user);
					ImageService imageService = new ImageService();
					Image image = new Image();
					image = imageService.getImageUser(user.getIdUser());
					session.setAttribute("image", image);
					response.sendRedirect("Connexion");
					
				}
				else {
					String inconnu ="Utilisateur inexsistant et/ou mauvais mot de passe...";
					request.setAttribute("inconnu", inconnu);
					this.doGet(request, response);
				}


			
	    
		     }
		
		else {
			request.setAttribute("erreur", erreur);
			this.doGet(request, response);
		}
	}

}
