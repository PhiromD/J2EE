package fr.onepiecetheorie.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.onepiecetheorie.pojos.Categorie;
import fr.onepiecetheorie.pojos.Theorie;
import fr.onepiecetheorie.pojos.UserForum;
import fr.onepiecetheorie.services.CategorieService;
import fr.onepiecetheorie.services.TheorieService;
import fr.onepiecetheorie.services.UserForumService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "User", urlPatterns= {"/User"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		UserForumService userForumService = new UserForumService();
		UserForum userForum = new UserForum();
		try {
			userForum = userForumService.getUserFromId(Integer.parseInt(request.getParameter("idUser")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", userForum);
		TheorieService theorieService = new TheorieService();
		List<Theorie> theories = new ArrayList<Theorie>();
		try {
			theories = theorieService.getAllByMembre(userForum.getIdUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("theories", theories);
		for (Theorie theorie : theories) {
			System.out.println(theorie);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/User.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
