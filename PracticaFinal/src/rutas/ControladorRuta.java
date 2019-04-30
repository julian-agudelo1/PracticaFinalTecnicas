package rutas;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import rutas.ModeloRuta;

/**
 * Servlet implementation class ControladorRuta
 */
@WebServlet("/ControladorRuta")
public class ControladorRuta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModeloRuta mr;
	@Resource(name="jdbc/Productos")
	private DataSource pool;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			mr = new ModeloRuta(pool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Leer parámetro del formulario
		String comando = request.getParameter("instruccion");
		//si no se envía parámetro, listar
		if(comando == null) comando = "listar";
		//Redirigir el flujo de ejeción al método adecuado
		switch(comando) {
			case "listar":
				obtenerRutas(request, response);
				break;
			case "insertarBBDD":
				agregarRutas(request, response);
				break;
			case "cargar":
				try {
					cargaRutas(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "actualizarBBDD":
				try {
					actualizaRutas(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "eliminar":
				try {
					eliminaRutas(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "buscarEmpresas":
				obtenerEmpresas(request, response);
				break;
			case "buscarRutasG":
				obtenerOrigenDestinos(request, response);
				break;
			case "buscarRutaE":
				obtenerRutasE(request, response);
				break;
			case "buscarRutaOd":
				obtenerRutasOd(request, response);
				break;
			case "calcular":
				obtenerCalculos(request, response);
				break;
			}
	}

	private void obtenerCalculos(HttpServletRequest request, HttpServletResponse response) {
		
		//Capturar campos clave
		String oD = request.getParameter("origenDestino");
		String em = request.getParameter("empresa");
		//Obtener lista de rutasOdCalc del modelo
		Ruta rutaOdCalc;
		try {
			rutaOdCalc = mr.getRutaCalc(oD, em);
			//--Agregar lista al request--
			request.setAttribute("RutaOdCalc", rutaOdCalc);
			//Mostrar origen-destino y empresa en request
			request.setAttribute("origenDestino", oD);
			request.setAttribute("empresa", em);
			//Enviar request a jsp--
			RequestDispatcher rd = request.getRequestDispatcher("/RutaOdCalc.jsp"); 
			rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private void obtenerRutasOd(HttpServletRequest request, HttpServletResponse response) {
		//Capturar campo clave
		String oD = request.getParameter("origenDestino");
		//Obtener lista de rutasE del modelo
		List<Ruta> rutasOd;
		try {
			rutasOd = mr.getRutasOd(oD);
			//--Agregar lista al request--
			request.setAttribute("listaRutasOd", rutasOd);
			//Mostrar origen-destino en request
			request.setAttribute("origenDestino", oD);
			//Enviar request a jsp--
			RequestDispatcher rd = request.getRequestDispatcher("/listaRutasOd.jsp"); 
			rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private void obtenerRutasE(HttpServletRequest request, HttpServletResponse response) {
		//Capturar campo clave
		String em = request.getParameter("empresa");
		//Obtener lista de rutasE del modelo
		List<Ruta> rutasE;
		try {
			rutasE = mr.getRutasE(em);
			//--Agregar lista al request--
			request.setAttribute("listaRutasE", rutasE);
			//Mostrar empresa en request
			request.setAttribute("empresa", em);
			//Enviar request a jsp--
			RequestDispatcher rd = request.getRequestDispatcher("/listaRutasE.jsp"); 
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void obtenerOrigenDestinos(HttpServletRequest request, HttpServletResponse response) {
		//--Obtener lista de empresas del modelo--
		List<String> origenDestinos;
		try {
			origenDestinos = mr.getOrigenDestinos();
			//--Agregar lista al request--
			request.setAttribute("listaOrigenDestinos", origenDestinos);
			//Enviar request a jsp--
			RequestDispatcher rd = request.getRequestDispatcher("/listaOrigenDestinos.jsp"); 
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void obtenerEmpresas(HttpServletRequest request, HttpServletResponse response) {
		//--Obtener lista de empresas del modelo--
		List<String> empresas;
		try {
			empresas = mr.getEmpresas();
			//--Agregar lista al request--
			request.setAttribute("listaEmpresas", empresas);
			//Enviar request a jsp--
			RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas.jsp"); 
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void eliminaRutas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Capturar los parámetros clave
		String oD = request.getParameter("origenDestino");
		String em = request.getParameter("empresa");
		//Borrar producto en BBDD
		try {
			mr.eliminarRuta(oD, em);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Volver al listado
		obtenerRutas(request, response);
	}

	private void actualizaRutas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Leer los datos del formulario actualizar
		String oD = request.getParameter("origenDestino");
		String em = request.getParameter("empresa");
		double pB = Double.parseDouble(request.getParameter("precioB"));
		double pPr = Double.parseDouble(request.getParameter("precioPr"));
		double pPl = Double.parseDouble(request.getParameter("precioPl"));
		//Crear objeto Ruta con la info del formulario
		Ruta rutaActualizada = new Ruta(oD, em, pB, pPr, pPl);
		//Actualizar la BBDD
		try {
			mr.actualizarRuta(rutaActualizada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Listar con BBDD Actualizada
		obtenerRutas(request, response);
	}

	private void cargaRutas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Leer los datos que vienen del listado
		String oD = request.getParameter("origenDestino");
		String em = request.getParameter("empresa");
		//Enviarselos modelo
		Ruta r = mr.getRuta(oD, em);
		//Mostrar atributo correspondiente al los datos
		request.setAttribute("RutaActualizar", r);
		//Enviar ruta al formulario actualizar
		RequestDispatcher rd = request.getRequestDispatcher("/actualizarRuta.jsp");
		rd.forward(request, response);
	}

	private void agregarRutas(HttpServletRequest request, HttpServletResponse response) {
		//Leer información de la ruta
		String oD = request.getParameter("origenDestino");
		String em = request.getParameter("empresa");
		double pB = Double.parseDouble(request.getParameter("precioB"));
		double pPr = Double.parseDouble(request.getParameter("precioPr"));
		double pPl = Double.parseDouble(request.getParameter("precioPl"));
		//Crear objeto Ruta con la info del formulario
		Ruta rutaNueva = new Ruta(oD, em, pB, pPr, pPl);
		//Enviar al modelo e insertar en BBDD
		try {
			mr.agregarNueva(rutaNueva);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Volver al listado
		obtenerRutas(request, response);
	}

	private void obtenerRutas(HttpServletRequest request, HttpServletResponse response) {
		//--Obtener lista de rutas del modelo--
		List<Ruta> rutas;
		try {
			rutas = mr.getRutas();
			//--Agregar lista al request--
			request.setAttribute("listaRutas", rutas);
			//Enviar request a jsp--
			RequestDispatcher rd = request.getRequestDispatcher("/listaRutas.jsp"); 
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
