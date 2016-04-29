package dao;
 import factory.ConnectionFactory;
import model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO {
    public void salvar(Fornecedor f)
            throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO fornecedor ( nome ) VALUES(?)";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, f.getNome_for());
        stmt.execute();
        stmt.close();
        
    }
                    
   /*  public static void main(String [] args)
        throws ClassNotFoundException, SQLException{
        Fornecedor f1 = new Fornecedor();
        f1.setNome_for("Descrição 1");
        Fornecedor f2 = new Fornecedor();
        f2.setNome_for("Descrição 2");
        
        
        FornecedorDAO fdao = new FornecedorDAO();
        try{
           fdao.salvar(f1);
           fdao.salvar(f2);
            System.out.println("Fornecedor cadastrado com sucesso!");
            }catch(SQLException e){
                System.out.println("Erro ao tentar salvar o fornecedor");
                e.printStackTrace();
            }
     }*/


                   
    public void excluir(Fornecedor f)
            throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM fornecedor WHERE Id_fornecedor = ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, f.getId_for());
        stmt.execute();
        stmt.close();
    }

    /*public static void main(String [] args)throws ClassNotFoundException, SQLException{
        Fornecedor f1 = new Fornecedor();
        f1.setId_for(7);
        
        FornecedorDAO fdao = new FornecedoaDAO();
        try{
            fdao.excluir(f1);
            }catch(SQLException e){
                System.out.println("Erro ao excluir o fabricante.");
                e.printStackTrace();
            }
        
    }*/
    public void editar(Fornecedor f)throws ClassNotFoundException,SQLException{
        String sql= "UPDATE fornecedor SET getNome_fornecedor = ? WHERE Id_for= ?";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString (1, f.getNome_for());
        stmt.setInt(2, f.getId_for());    
        stmt.execute();
        stmt.close();
}
                            
        /*public static void main(String [] args)throws ClassNotFoundException, SQLException{
        Fornecedor f1 = new Fornecedor();
        f1.setId_for(10);
        f1.setNome_for("Nome 10");
       
      FornecedorDAO fdao = new FornecedorDAO();
        
        try{
            fdao.editar(f1);
            System.out.println("Fornecedor alterado com sucesso!");
        }catch(SQLException e){
            System.out.println("Erro ao editar o fornecedor!");
            e.printStackTrace();
        }
    }*/
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
    public ArrayList<Fornecedor> lista()throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM fornecedor";
        
        Connection connection = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
        
        while(rs.next()){
            Fornecedor f = new Fornecedor();
            f.setId_for(rs.getInt("id_fornecedor"));
            f.setNome_for(rs.getString("nome"));            
            lista.add(f);
        }
        return lista;
    }
}


    
   /* public static void main(String [] args)throws ClassNotFoundException, SQLException{
       Fornecedor f1 = new Fornecedor();
        f1.setId_for(8);
 
        
       FornecedoDAO fdao = new FornecedorDAO();
        
        try{
            Fornecedor f3 = fdao.pesquisar(f1);
            
            System.out.println("Resultado 1: "+f3);
            
            
        }catch(SQLException e){
            System.out.println("Erro ao pesquisar o fornecedor.");
            e.printStackTrace();
        }
    }*/
