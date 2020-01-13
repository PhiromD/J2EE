package fr.onepiecetheorie.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.onepiecetheorie.pojos.Categorie;
import fr.onepiecetheorie.pojos.Mugiwara;

import fr.onepiecetheorie.services.CategorieService;
import fr.onepiecetheorie.services.MugiwaraService;

/**
 * Servlet implementation class MugiwaraServlet
 */
@WebServlet(name = "Mugiwara",urlPatterns= {"/Mugiwara"})
public class MugiwaraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MugiwaraServlet() {
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
		MugiwaraService mugiwaraService = new MugiwaraService();
		Mugiwara mugiwara = new Mugiwara();
		try {
			mugiwara = mugiwaraService.getMugiwaraFromId(Integer.parseInt(request.getParameter("idMugiwara")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("mugiwara", mugiwara);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Mugiwara.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
