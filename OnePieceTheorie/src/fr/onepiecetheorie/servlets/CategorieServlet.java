package fr.onepiecetheorie.servlets;

import java.io.IOException;



import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.group.interceptors.FragmentationInterceptor.FragCollection;

import fr.onepiecetheorie.hibernate.utils.HibernateConnect;
import fr.onepiecetheorie.services.*;
import fr.onepiecetheorie.pojos.*;



/**
 * Servlet implementation class Categorie
 */
@WebServlet(name="Categorie",urlPatterns= {"/Categorie"})
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategorieServlet() {
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

		//HttpSession session = request.getSession();
		//session.removeAttribute("theorie");
		if(!request.getParameter("idCategorie").isEmpty()) {
			int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));

			if(idCategorie==3) {
				TheorieService theorieService = new TheorieService();
				List<Theorie> theories = new ArrayList<Theorie>();
				
					try {
						theories = theorieService.getAll();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				request.setAttribute("theories", theories);
			}
			if(idCategorie==1) {
			MugiwaraService mugiwaraService = new MugiwaraService();
			List<Mugiwara> mugiwaras = new ArrayList<Mugiwara>();
			try {
				mugiwaras = mugiwaraService.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("mugiwaras", mugiwaras);
			}
			if(idCategorie==2) {
				UserForumService userForumService = new UserForumService();
				List<UserForum> users = new ArrayList<UserForum>();
				try {
					users = userForumService.getAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (UserForum userForum : users) {
					ImageService imageService= new ImageService();
					Image image = null;
					
					try {
						image = imageService.getImageUser(userForum.getIdUser());
						userForum.setImage(image);
						
					} finally {
						// TODO: handle finally clause
					}
					
				}
				request.setAttribute("users", users);
			}
				
		}
			



		







		this.getServletContext().getRequestDispatcher("/WEB-INF/Categorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
