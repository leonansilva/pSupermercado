package dao;
import factory.ConnectionFactory;
import model.Fornecedor;
import dao.FornecedorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO {
    public void salvarFornecedor(Fornecedor f)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO fornecedor (nome_fornecedor) VALUES(?)";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, f.getNome_for());
        stmt.execute();
        stmt.close();
        
    }   
    public void alterarFornecedor (Fornecedor f) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE fornecedor SET nome_fornecedor = ? WHERE id_fornecedor = ?";
        
         Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, f.getId_for());
        stmt.execute();
        stmt.close();
    }       
    public void excluirFornecedor(Fornecedor f)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM fornecedor WHERE Id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, f.getId_for());
        stmt.execute();
        stmt.close();
    }

    public Fornecedor pesquisar(Fornecedor f)throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM fornecedor WHERE Id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, f.getId_for());
        
        ResultSet rs = stmt.executeQuery();
        Fornecedor  retorno = null;
           if(rs.next()){
            retorno = new Fornecedor();
            retorno.setId_for(rs.getInt("Id_fornecedor"));
            retorno.setNome_for(rs.getString("nome"));
        }
        return retorno;

}
    public ArrayList<Fornecedor> listaFornecedor()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM fornecedor";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
        
        while(rs.next()){
            Fornecedor f = new Fornecedor();
            f.setId_for(rs.getInt("id_fornecedor"));
            f.setNome_for(rs.getString("nome_fornecedor"));            
            lista.add(f);
        }
        return lista;
    }
}