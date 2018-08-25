package threeway.projeto.service.persistencia;
import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory.Result;

import threeway.projeto.service.dao.FabricaConexao;
import threeway.projeto.service.modelo.Livro;

public class LivroDAO {
	
	private static final String CONSULTAR_SQL = "SELECT * FROM LIVRO WHERE TITULO LIKE ?";
	private static final String CONSULTAR =  "SELECT * FROM LIVRO WHERE COD_LIVRO = ?";
	private static final String CONSULTAR_DELET = "DELETE FROM LIVRO WHERE COD_LIVRO = ?";
	private static final String CONSULTAR_UPDATE = "UPDATE LIVRO SET TITULO = ?, ALTOR = ? WHERE COD_LIVRO = ?";
	
	
	public List<Livro> consultarTitulo(String titulo){
		
		ArrayList<Livro> lista = new ArrayList<Livro>();
		
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(CONSULTAR_SQL);
			){
				consulta.setString(1,"%" + titulo.toUpperCase() + "%");
			
				ResultSet resultado = consulta.executeQuery();
				
				while(resultado.next()) {
					Livro livro = new Livro();
					
					livro.setAltor(resultado.getString("ALTOR"));
					livro.setCodigo(resultado.getInt("COD_LIVRO"));
					livro.setImagem(resultado.getString("IMAGEM"));
					livro.setPreco(resultado.getDouble("PRECO"));
					livro.setTitulo(resultado.getString("TITULO"));
					livro.setDescricao(resultado.getString("DESCRICAO"));
					lista.add(livro);
					
				}
				resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lista;
	}
	
	public Livro consultar2(int codigo) {
		Livro livro = null;
		
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consultar2 = conexao.prepareStatement(CONSULTAR)){
			
			consultar2.setInt(1, codigo);
			
			ResultSet resultado = consultar2.executeQuery();
			
			if(resultado.next()) {
				livro = new Livro();
				
				livro.setAltor(resultado.getString("ALTOR"));
				livro.setCodigo(resultado.getInt("COD_LIVRO"));
				livro.setImagem(resultado.getString("IMAGEM"));
				livro.setPreco(resultado.getDouble("PRECO"));
				livro.setTitulo(resultado.getString("TITULO"));
				livro.setDescricao(resultado.getString("DESCRICAO"));
			}
			resultado.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "Deu algum problema");
		}
		return livro;
	}
	public void deletar(int codigo) {
		
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement cons = conexao.prepareStatement(CONSULTAR_DELET)){
			
			cons.setInt(1, codigo);
			cons.execute();
			System.out.println("excluido com sucesso" + codigo);
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void alterar(Livro livro) {
		
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consultar3 = conexao.prepareStatement(CONSULTAR_UPDATE)){
			
			consultar3.setString(1, livro.getTitulo());
			consultar3.setString(2, livro.getAltor());
			consultar3.setInt(3, livro.getCodigo());
			
			consultar3.execute();
			System.out.println( "      Alteração feita" 
								+ "\nTitulo : " + livro.getTitulo() 
								+ "\nAltor  : " + livro.getAltor() 
								+ "\nCodigo : " + livro.getCodigo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
