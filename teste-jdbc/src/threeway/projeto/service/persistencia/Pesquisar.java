package threeway.projeto.service.persistencia;

import threeway.projeto.service.modelo.Livro;

public class Pesquisar {
	
	static LivroDAO dao = new LivroDAO();
	
	public static void main(String[] args) {
		
		//testeConsulta();
		//testarConsulta2();
		
		//tem que passar o parametro
		//testarDelete(1);
		
		testarAlterar();

		//dao.consultar(titulo).forEach(p -> System.out.println("Desc: " + p.getTitulo() + " Preço:" + p.getPreco()));
		
		// deixar usar essa implementação e corrigir o erro
		// dao.consultar(titulo).forEach(p -> System.out.println("Desc: " + p.getDescricao() + "Preço:" + p.getPreco()));

		// implementar usando o for each
		// dao.consultar(titulo).forEach(p -> System.out.println("Desc: " + p.getDescricao() + "Preço:" + p.getPreco()));
	}
	public static void testeConsulta(String titulo) {
		
		for(Livro livro : dao.consultarTitulo(titulo)) {
			System.out.println("cod : " + livro.getCodigo() 
								+ "\nDescrição: " + livro.getCodigo()
								+ "\nAltor: " + livro.getAltor());
		}
		
	}
	public static void ConsultarAltor() {
		//instancia
		Livro livro = new Livro();
		// receber o resultado;
		livro = dao.consultar2(1);
			
		System.out.println("Altor: " + livro.getAltor() 
							+ "\nCodigo: " +livro.getCodigo());
	}
	public static void testarDelete(int codigo) {
		dao.deletar(codigo);
	}
	public static void testarAlterar() {
		Livro livro = new Livro();
		
		livro.setTitulo("LOUCOS_MAIS TOP");
		livro.setAltor("Leonidas");
		livro.setCodigo(3);
		
		dao.alterar(livro);
	}

}