package resources;

import dao.DateDao;
import dao.EmployeeDao;
import dao.LoginDao;
import model.Date;
import model.Employee;
import model.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AddCustomerController
 */
public class AddNewDateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewDateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		Get Data from request in this way
//		String email = request.getParameter("employeeEmail");


		Date date = new Date();
		date.setDateID(request.getParameter(""));
		date.setUser1ID(request.getParameter("user1ID"));
		date.setUser2ID(request.getParameter("user2ID"));
		date.setDate(request.getParameter("date"));
		date.setGeolocation(request.getParameter("Location"));
		date.setBookingfee(Integer.parseInt(request.getParameter("fee")));
		date.setCustRepresentative(request.getParameter("custRep"));
		date.setComments(request.getParameter("Comments"));
		date.setUser1Rating(Integer.parseInt(request.getParameter("user1Rating")));
		date.setUser2Rating(Integer.parseInt(request.getParameter("user2Rating")));

		DateDao dao = new DateDao();
		String result = dao.addDate(date);

		if(result.equals("success")) {
			Login login = new Login();
			login.setUsername("email");
			login.setPassword("password");
			login.setRole("customerRepresentative");
			LoginDao loginDao = new LoginDao();
			String loginResult = loginDao.addUser(login);
			if(loginResult.equals("success")) {
				response.sendRedirect("managerHome.jsp?status=addDateSuccess");
			}
			else {
				// Create addDate.jsp page to add this date to the database
				response.sendRedirect("addDate.jsp");
			}
		}
		else {
			response.sendRedirect("addDate.jsp");
		}

		
	}

}
