package logins;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet("/ControladorLogin")
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModeloLogin ml = null;
	@Resource(name="jdbc/Productos")
	private DataSource pool;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			ml = new ModeloLogin(pool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Leer los datos que vienen del listado
		String usuario = request.getParameter("login");
		String password = request.getParameter("password");
		try {
			boolean resp = ml.getUsuario(usuario, password);
			if(resp) {
				request.getRequestDispatcher("ControladorRuta").forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
