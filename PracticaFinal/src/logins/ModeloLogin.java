package logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class ModeloLogin {
	
private DataSource origenDatos;
	
	public ModeloLogin(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}
	
	public boolean getUsuario (String usuario, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String usu = usuario;
		String pass = password;
		//Establecer Conexión
		try {
			con = origenDatos.getConnection();
			//Crar instrucción para buscar el producto
			String instruccion = "select * from usuarios where Usuario=? and Contrasena=?";
			//Crear consulta preparada
			ps = con.prepareStatement(instruccion);
		    //Establecer parametros de la consulta
			ps.setString(1, usu);
			ps.setString(2, pass);
			//Ejecucuón de la consulta
			rs = ps.executeQuery();
		    //Obtener datos de respuesta
			if(rs.next()) {
				return true;
			} else {
				throw new Exception("Usuario no existe");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
