package factory;
import java.sql.*;
public class TesteConexao {
    public static void main(String [] args) throws SQLException, ClassNotFoundException{
        Connection connection = new ConnectionFactory().getConnection();
        System.out.println("Conexão aberta!");
        connection.close();
    }
}