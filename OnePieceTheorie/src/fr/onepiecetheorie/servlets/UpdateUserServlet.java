package fr.onepiecetheorie.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.onepiecetheorie.pojos.UserForum;
import fr.onepiecetheorie.services.UserForumService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet(name="UpdateUser",urlPatterns= {"/UpdateUser"})
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/UpdateUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserForum user = new UserForum();
		user =(UserForum) session.getAttribute("userforum");
		String erreur="";
		if((UserForum)session.getAttribute("userforum")!=null) {
			

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
				String message = "Edit";
				request.setAttribute("message", message);
				user.setPseudo(pseudo);
				user.setEmail(email);
				user.setMdp(mdp);
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
					request.setAttribute("user", user);
					this.doGet(request, response);




				}

			}
			else {


				UserForum userForum = new UserForum(user.getNom(),pseudo,email,mdp,new Date());
				request.setAttribute("user", userForum);
				request.setAttribute("erreur", erreur);
				this.doGet(request, response);
			}
		}
	}

}
