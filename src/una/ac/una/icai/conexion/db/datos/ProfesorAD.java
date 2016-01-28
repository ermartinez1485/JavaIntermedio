/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.ac.una.icai.conexion.db.datos;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import una.ac.una.icai.conexion.db.clases.Profesor;

/**
 *
 * @author Tek
 */
public class ProfesorAD {
    
    private Conexion con;
    private PreparedStatement sentencia;

    public ProfesorAD() {
        this.con = new Conexion();
        this.sentencia = null;
    }

    
    public Integer insertar(Profesor profe){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("insert into biblioteca.profesor values (?,?,?,?)");
                this.sentencia.setInt(1, profe.getCedula());
                this.sentencia.setString(2, profe.getNombre());
                this.sentencia.setDouble(3, profe.getSalario());
                this.sentencia.setString(4, profe.getDireccion());
                this.sentencia.executeUpdate();
                this.con.desconectarse();
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien
            
            }else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }
        
        } catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")){
                return 3;
            }else{
                    Logger.getLogger(ProfesorAD.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
                }
            }
    }
    
    public Integer eliminar(Profesor profe){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("delete from biblioteca.profesor where cedula = ?");
                this.sentencia.setInt(1, profe.getCedula());
                this.sentencia.executeUpdate();
                int i = this.sentencia.executeUpdate();
                this.con.desconectarse();
                if (i==0) {
                    return 3;
                }
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien
            
            }else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ProfesorAD.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
            }
    }
    
        public Integer actualizar(Profesor profe){
        try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("update biblioteca.profesor set nombre =?, salario =?, direccion=? where cedula =?");
                this.sentencia.setString(1, profe.getNombre());
                this.sentencia.setDouble(2, profe.getSalario());
                this.sentencia.setString(3, profe.getDireccion());
                this.sentencia.setInt(4, profe.getCedula());
                this.sentencia.executeUpdate();
                int i = this.sentencia.executeUpdate();
                this.con.desconectarse();
                if (i==0) {
                    return 3;
                    
                }
                return 0; //retorna 0 cuando se conecta a la DB y todo esta bien
            
            }else {
                return 1; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ProfesorAD.class.getName()).log(Level.SEVERE, null, ex);
                        return 2;  //retorna 2 cuando se cae la conexion
            }
    }
        
        public Profesor consultaXCedula(Integer cedula){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from biblioteca.profesor where cedula =?");
                this.sentencia.setInt(1, cedula);
                ResultSet resultado = this.sentencia.executeQuery();
                
                
                Profesor profe = new Profesor();
                while (resultado.next()){
                    profe.setCedula(resultado.getInt(1));
                    profe.setNombre(resultado.getString("nombre"));
                    profe.setSalario(resultado.getDouble(3));
                    profe.setDireccion(resultado.getString("direccion"));
                }
                this.con.desconectarse();
                return profe; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ProfesorAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
        
        //si se devuelve mas de un valor se crear una lista
        public List<Profesor> consultaXNombre(String nombre){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from biblioteca.profesor where nombre =?");
                this.sentencia.setString(1, nombre);
                ResultSet resultado = this.sentencia.executeQuery();
                
                
                List<Profesor> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Profesor profe = new Profesor();
                    profe.setCedula(resultado.getInt(1));
                    profe.setNombre(resultado.getString("nombre"));
                    profe.setSalario(resultado.getDouble(3));
                    profe.setDireccion(resultado.getString("direccion"));
                    lista.add(profe);
                }
                this.con.desconectarse();
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ProfesorAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
        
        public List<Profesor> consultaTodos(){
            
            try {
            if(this.con.conectarse()){
            
                this.sentencia = this.con.getCon().prepareStatement
                ("select * from biblioteca.profesor");
                ResultSet resultado = this.sentencia.executeQuery();
                
                
                List<Profesor> lista = new ArrayList<>();
                
                while (resultado.next()){
                    Profesor profe = new Profesor();
                    profe.setCedula(resultado.getInt(1));
                    profe.setNombre(resultado.getString("nombre"));
                    profe.setSalario(resultado.getDouble(3));
                    profe.setDireccion(resultado.getString("direccion"));
                    lista.add(profe);
                }
                
                this.con.desconectarse();
                
                return lista; //retorna 0 cuando se conecta a la DB y todo esta bien
            }else {
                return null; //retorna 1 cuando no se conecta a la DB
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ProfesorAD.class.getName()).log(Level.SEVERE, null, ex);
                return null;  //retorna 2 cuando se cae la conexion
            }
        }
}
