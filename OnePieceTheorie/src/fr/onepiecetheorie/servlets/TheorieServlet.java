package fr.onepiecetheorie.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.onepiecetheorie.services.*;
import fr.onepiecetheorie.pojos.*;

/**
 * Servlet implementation class TheorieServlet
 */
@WebServlet(name = "Theorie",urlPatterns= {"/Theorie"})
public class TheorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheorieServlet() {
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
		TheorieService theorieService = new TheorieService();
		Theorie theorie = new Theorie();
		try {
			theorie = theorieService.getTheorieFromId(Integer.parseInt(request.getParameter("idTheorie")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageService imageService = new ImageService();
		Collection<ImageTheorie> imageTheorie = new ArrayList<ImageTheorie>();
		imageTheorie = imageService.getImageTheorie(Integer.parseInt(request.getParameter("idTheorie")));
		for (ImageTheorie imageTheorie2 : imageTheorie) {
			theorie.addImageTheorie(imageTheorie2);
		
		}
		
		request.setAttribute("theorie", theorie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Theorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
