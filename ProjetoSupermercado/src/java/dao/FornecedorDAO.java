package dao;
import factory.ConnectionFactory;
import dao.FornecedorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.fornecedor;

public class FornecedorDAO {
    public void salvarFornecedor(fornecedor f)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO fornecedor (nome_fornecedor) VALUES(?)";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, f.getNome_for());
        stmt.execute();
        stmt.close();
        
    }   
    public void alterarFornecedor (fornecedor f) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE fornecedor SET nome_fornecedor = ? WHERE id_fornecedor = ?";
        
         Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, f.getNome_for());
        stmt.setInt(2, f.getId_for());
        stmt.execute();
        stmt.close();
    }       
    public void excluirFornecedor(fornecedor f)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM fornecedor WHERE Id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, f.getId_for());
        stmt.execute();
        stmt.close();
    }

    public fornecedor pesquisar(fornecedor f)throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM fornecedor WHERE Id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, f.getId_for());
        
        ResultSet rs = stmt.executeQuery();
        fornecedor  retorno = null;
           if(rs.next()){
            retorno = new fornecedor();
            retorno.setId_for(rs.getInt("Id_fornecedor"));
            retorno.setNome_for(rs.getString("nome"));
        }
        return retorno;

}
    public ArrayList<fornecedor> listaFornecedor()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM fornecedor";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<fornecedor> lista = new ArrayList<fornecedor>();
        
        while(rs.next()){
            fornecedor f = new fornecedor();
            f.setId_for(rs.getInt("id_fornecedor"));
            f.setNome_for(rs.getString("nome_fornecedor"));            
            lista.add(f);
        }
        return lista;
    }
}