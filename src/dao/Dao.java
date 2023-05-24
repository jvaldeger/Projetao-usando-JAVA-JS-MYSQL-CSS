package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Dao {  
    protected Connection c;
    protected PreparedStatement sql;
    protected ResultSet r;
    
    public Dao() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempresab","root","root");
        sql = null;
        r = null;
        
    } // fim do Dao
    
    public java.sql.Date getSqlDate(Date data){
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        return sqlDate;
    }
     
} // fim da classe