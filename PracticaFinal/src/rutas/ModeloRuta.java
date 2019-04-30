package rutas;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class ModeloRuta {
	
	private DataSource origenDatos;
	
	public ModeloRuta(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}
	
	public List<Ruta> getRutas() throws Exception{
		List<Ruta> rutas = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		//--Establecer conexión--
		con = origenDatos.getConnection();
		//--Crear sentencia--
		String i = "select * from rutas";
		st = con.createStatement();
		//--Ejecutar instrucción SQL--
		rs = st.executeQuery(i);
		//--Recorrer el rs--
		while(rs.next()) {
			String oD = rs.getString("OrigenDestino");
			String e = rs.getString("Empresa");
			double pB = rs.getInt("PrecioBasico");
			double pPr = rs.getInt("PrecioPremium");
			double pPl = rs.getInt("PrecioPlatino");
			Ruta temp = new Ruta(oD, e, pB, pPr, pPl);
			rutas.add(temp);
		}
		return rutas;
	}
	
	public List<String> getEmpresas() throws Exception{
		List<String> empresas = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		//--Establecer conexión--
		con = origenDatos.getConnection();
		//--Crear sentencia--
		String i = "select Empresa from rutas";
		st = con.createStatement();
		//--Ejecutar instrucción SQL--
		rs = st.executeQuery(i);
		//--Recorrer el rs--
		while(rs.next()) {
			String e = rs.getString("Empresa");
			empresas.add(e);
		}
		return empresas;
	}
	
	public List<String> getOrigenDestinos() throws Exception{
		List<String> origenDestinos = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		//--Establecer conexión--
		con = origenDatos.getConnection();
		//--Crear sentencia--
		String i = "select OrigenDestino from rutas";
		st = con.createStatement();
		//--Ejecutar instrucción SQL--
		rs = st.executeQuery(i);
		//--Recorrer el rs--
		while(rs.next()) {
			String oD = rs.getString("OrigenDestino");
			origenDestinos.add(oD);
		}
		return origenDestinos;
	}
	
	public List<Ruta> getRutasE(String empresa) throws Exception{
		List<Ruta> rutasE = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//--Establecer conexión--
		con = origenDatos.getConnection();
		//--Crear sentencia--
		String i = "select * from rutas where Empresa=?";
		ps = con.prepareStatement(i);
		//Establecer parámetro
		ps.setString(1, empresa);
		//--Ejecutar instrucción SQL--
		rs = ps.executeQuery();
		//--Recorrer el rs--
		while(rs.next()) {
			String oD = rs.getString("OrigenDestino");
			String e = rs.getString("Empresa");
			double pB = rs.getInt("PrecioBasico");
			double pPr = rs.getInt("PrecioPremium");
			double pPl = rs.getInt("PrecioPlatino");
			Ruta temp = new Ruta(oD, e, pB, pPr, pPl);
			rutasE.add(temp);
		}
		return rutasE;
	}
	
	public List<Ruta> getRutasOd(String origenDestino) throws Exception{
		List<Ruta> rutasOd = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//--Establecer conexión--
		con = origenDatos.getConnection();
		//--Crear sentencia--
		String i = "select * from rutas where OrigenDestino=?";
		ps = con.prepareStatement(i);
		//Establecer parámetro
		ps.setString(1, origenDestino);
		//--Ejecutar instrucción SQL--
		rs = ps.executeQuery();
		//--Recorrer el rs--
		while(rs.next()) {
			String oD = rs.getString("OrigenDestino");
			String e = rs.getString("Empresa");
			double pB = rs.getInt("PrecioBasico");
			double pPr = rs.getInt("PrecioPremium");
			double pPl = rs.getInt("PrecioPlatino");
			Ruta temp = new Ruta(oD, e, pB, pPr, pPl);
			rutasOd.add(temp);
		}
		return rutasOd;
	}
	
	public Ruta getRutaCalc(String origenDestino, String empresa) throws Exception{
		Ruta rutaOdCalc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int dcto = 0;
		//--Establecer conexión--
		con = origenDatos.getConnection();
		//--Crear sentencias--
		String i = "select * from rutas where OrigenDestino=? and Empresa=?";
		String i2 = "select * from empresas where Nombre=?";
		ps = con.prepareStatement(i);
		ps2 = con.prepareStatement(i2);
		//Establecer parámetros
		ps.setString(1, origenDestino);
		ps.setString(2, empresa);
		ps2.setString(1, empresa);
		//--Ejecutar instrucciones SQL--
		rs = ps.executeQuery();
		rs2 = ps2.executeQuery();
		//--Obtener dtos de los rs--
		if(rs.next() && rs2.next()) {
			dcto = rs2.getInt("Descuento");
			String oD = rs.getString("OrigenDestino");
			String e = rs.getString("Empresa");
			int subtotal1 = rs.getInt("PrecioBasico");
			System.out.println(subtotal1);
			System.out.println(dcto);
			double dctoF1 = ((dcto/100)*subtotal1);
			System.out.println(dctoF1);
			double pB = 40*(subtotal1-dctoF1);
			int subtotal2 = rs.getInt("PrecioPremium");
			double dctoF2 = ((dcto/100)*subtotal2);
			double pPr = 40*(subtotal2-dctoF2);
			int subtotal3 = rs.getInt("PrecioPlatino");
			double dctoF3 = ((dcto/100)*subtotal3);
			double pPl = 40*(subtotal3-dctoF3);
			rutaOdCalc = new Ruta(oD, e, pB, pPr, pPl);
		}
		return rutaOdCalc;
	}
	
	public void agregarNueva(Ruta ruta) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		//Obtener conexión
		try {
			con = origenDatos.getConnection();
			//Intrucción query
			String i = "insert into rutas (OrigenDestino,Empresa,PrecioBasico,PrecioPremium,PrecioPlatino)"
					+"values (?,?,?,?,?)";
			ps = con.prepareStatement(i);
			//Establecer parámetros
			ps.setString(1, ruta.getOrigenDestino());
			ps.setString(2, ruta.getEmpresa());
			ps.setInt(3, (int) ruta.getPrecioB());
			ps.setInt(4, (int) ruta.getPrecioPr());
			ps.setInt(5, (int) ruta.getPrecioPl());
			//Ejecutar instruccion
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}
	
	public Ruta getRuta (String oD, String em) {
		Ruta r = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String origenDestino = oD;
		String empresa = em;
		//Establecer Conexión
		try {
			con = origenDatos.getConnection();
			//Crar instrucción para buscar el producto
			String instruccion = "select * from rutas where OrigenDestino=? and Empresa=?";
			//Crear consulta preparada
			ps = con.prepareStatement(instruccion);
		    //Establecer parametros de la consulta
			ps.setString(1, origenDestino);
			ps.setString(2, empresa);
			//Ejecucuón de la consulta
			rs = ps.executeQuery();
		    //Obtener datos de respuesta
			if(rs.next()) {
				String oriDe = rs.getString("OrigenDestino");
				String emp = rs.getString("Empresa");
				double pB = rs.getInt("PrecioBasico");
				double pPr = rs.getInt("PrecioPremium");
				double pPl = rs.getInt("PrecioPlatino");
				r = new Ruta(oriDe, emp, pB, pPr, pPl);
			} else {
				throw new Exception("No se encontró ruta con empresa dada");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public void actualizarRuta(Ruta rutaActualizada) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		//Establecer la conexión
		try {
			con = origenDatos.getConnection();
			//Crear instrucción 
			String i = "update rutas set PrecioBasico=?, PrecioPremium=?, PrecioPlatino=? where OrigenDestino=? and Empresa=?";
			//Crear consulta preparada
			ps = con.prepareStatement(i);
			//Establecer parámetros
			ps.setInt(1, (int)rutaActualizada.getPrecioB());
			ps.setInt(2, (int)rutaActualizada.getPrecioPr());
			ps.setInt(3, (int)rutaActualizada.getPrecioPl());
			ps.setString(4, rutaActualizada.getOrigenDestino());
			ps.setString(5, rutaActualizada.getEmpresa());
			//Ejecutar instrucción SQL
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}
	
	public void eliminarRuta(String oD, String em) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		//Establecer conexion
		try {
			con = origenDatos.getConnection();
			//Crear instrucción de eliminación
			String i = "delete from rutas where OrigenDestino=? and Empresa=?";
			//Preparar la consulta
			ps = con.prepareStatement(i);
			//Establecer Parametros de consulta
			ps.setString(1, oD);
			ps.setString(2, em);
			//Ejecutar instruccion SQL
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}
	}
}
