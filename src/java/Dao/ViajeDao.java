
package Dao;
import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ViajeVo;

public class ViajeDao {
    
    // OBTENER LISTA
    public List<ViajeVo> listarViajes() {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<ViajeVo> lista = new ArrayList<>();

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_mostrar_viaje()");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new ViajeVo(
                        rs.getInt("id_vi"),
                        rs.getInt("id_dest"),
                        rs.getDate("salida"),
                        rs.getDate("llegada")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }
    
    // OBTENER POR ID
    public ViajeVo obtenerViajePorCodigo(int codigo) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        ViajeVo viaje = null;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("{call sp_viaje_by_codigo(?)}");
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                viaje = new ViajeVo(
                        rs.getInt("id_vi"),
                        rs.getInt("id_dest"),
                        rs.getDate("salida"),
                        rs.getDate("llegada")
                );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return viaje;
    }
    
    // REGISTRAR VIAJE
    public void registrarViaje(int idDestino, String salida, String llegada) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_registrar_viaje(?, ?, ?)");
            // Agregamos 
            pstm.setInt(1, idDestino);
            pstm.setString(2, salida);
            pstm.setString(3, llegada);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ACTUALIZAR DATOOOOSSS
    public void actualizarViaje(int idViaje, int idDestino, String salida, String llegada) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_actualizar_Viaje(?, ?, ?, ?)");
            // Agregamos 
            pstm.setInt(1, idViaje);
            pstm.setInt(2, idDestino);
            pstm.setString(3, salida);
            pstm.setString(4, llegada);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ELIMINAR POR ID
    public void eliminarViaje(int idViaje) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_eliminar_viaje(?)");
            // Agregamos
            pstm.setInt(1, idViaje);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
}
