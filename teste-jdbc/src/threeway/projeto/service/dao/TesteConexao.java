package threeway.projeto.service.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con;
		
		try {
			con = FabricaConexao.getConexao();
			if(con != null)
				System.out.println("Conexão Estabelecida!");
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
