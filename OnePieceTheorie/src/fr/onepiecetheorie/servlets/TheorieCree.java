package fr.onepiecetheorie.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.onepiecetheorie.pojos.Theorie;
import fr.onepiecetheorie.services.TheorieService;


/**
 * Servlet implementation class TheorieCree
 */
@WebServlet(name="TheorieCree",urlPatterns= {"/TheorieCree"})
public class TheorieCree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheorieCree() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Theorie theorie = new Theorie();
		
		theorie =(Theorie) session.getAttribute("theorie");
		
		System.out.println(theorie);
		session.removeAttribute("theorie");
		request.setAttribute("theorie", theorie);

		this.getServletContext().getRequestDispatcher("/WEB-INF/TheorieCree.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
