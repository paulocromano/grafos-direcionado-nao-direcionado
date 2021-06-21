import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int tamanhoMatriz = 0;
		String resposta;
		boolean isGrafoDirecionado = false;
		
		System.out.printf("Informe o tamanho da matriz: ");
		tamanhoMatriz = teclado.nextInt();
		
		System.out.printf("Grafo direcionado (S/N)?: ");
		resposta = teclado.next();
		isGrafoDirecionado = verificarResposta(resposta);
		
		
		int[][] matrizGrafo = gerarMatrizDoGrafo(teclado, tamanhoMatriz, isGrafoDirecionado);
		
		imprimirMatriz(matrizGrafo, tamanhoMatriz);
		imprimirGrauDosVerticesDoGrafo(matrizGrafo, tamanhoMatriz, isGrafoDirecionado);
		imprimirListaAdjacenciaDoGrafo(matrizGrafo, tamanhoMatriz, isGrafoDirecionado);
	}
	
	
	public static boolean verificarResposta(String resposta) {
		if (resposta.toLowerCase().equals("s")) {
			return true;
		}
		else if (resposta.toLowerCase().equals("n")) {
			return false;
		}
		
		throw new IllegalArgumentException("Valor inválido");
	}
	
	
	public static int[][] gerarMatrizDoGrafo(Scanner teclado, int tamanhoMatriz, boolean isGrafoDirecionado) {
		int[][] matrizGrafo = zerarValoresDaMatrizDoGrafo(tamanhoMatriz);
		boolean continuarLendoValores = true;
		
		System.out.println("\n\t\t Informe os vértices de origem e destino (x-y)");
		while (continuarLendoValores) {
			System.out.printf("\n  Vértices: ");
			String vertices = teclado.next();
			
			validarVertices(vertices, tamanhoMatriz);
			setarVerticesNaMatriz(matrizGrafo, vertices, isGrafoDirecionado);
			
			System.out.printf("Inserir novos vértices (S/N)?: ");
			String resposta = teclado.next();
			continuarLendoValores = verificarResposta(resposta); 
		}
		
		return matrizGrafo;
	}
	
	
	public static void setarVerticesNaMatriz(int[][] matrizGrafo, String vertices, boolean isGrafoDirecionado) {
		String[] verticesSeparados = vertices.split("-");
		int x = Integer.parseInt(verticesSeparados[0]) - 1;
		int y = Integer.parseInt(verticesSeparados[1]) - 1;
		
		matrizGrafo[x][y]++;
		
		if (!isGrafoDirecionado) {
				matrizGrafo[y][x]++;
			
			if (matrizGrafo[y][x] > 1 && matrizGrafo[y][x] < 2) {
				matrizGrafo[y][x]++;
			}
		}
	}
	
	
	public static void validarVertices(String vertices, int tamanhoMatriz) {
		if (!vertices.contains("-")) 
			throw new IllegalArgumentException("Formato inválido do valor dos vértices!");
		
		String[] verticesSeparados = vertices.split("-");
		int x = Integer.parseInt(verticesSeparados[0]);
		int y = Integer.parseInt(verticesSeparados[1]);
		
		if (x > tamanhoMatriz || y > tamanhoMatriz) 
			throw new IllegalArgumentException("O vértice não pode ter uma valor maior do que o definido pelo tamanho da Matriz!");
	}
	
	
	public static int[][] zerarValoresDaMatrizDoGrafo(int tamanhoMatriz) {
		int[][] matrizGrafo = new int[tamanhoMatriz][tamanhoMatriz];
		
		for (int i = 0; i < tamanhoMatriz; i++) {
			for (int j = 0; j < tamanhoMatriz; j++) {
				matrizGrafo[i][j] = 0;
			}
		}
		
		return matrizGrafo;
	}
	
	
	public static void imprimirMatriz(int[][] matrizGrafo, int tamanhoMatriz) {
		System.out.println("\n  Matriz do Grafo \n");
		
		String colunasMatriz = "    ";
		for (int i = 0; i < tamanhoMatriz; i++) {
			colunasMatriz += i + 1 + " ";
		}
		System.out.println(colunasMatriz);
		
		for (int i = 0; i < tamanhoMatriz; i++) {
			String linhaMatriz = "";
			linhaMatriz = i + 1 + " [ ";
			
			for (int j = 0; j < tamanhoMatriz; j++) {
				linhaMatriz += matrizGrafo[i][j] + " ";
			}
			
			linhaMatriz += "]";
			System.out.println(linhaMatriz);
		}
	}
	
	
	public static void imprimirGrauDosVerticesDoGrafo(int[][] matrizGrafo, int tamanhoMatriz, boolean isGrafoDirecionado) {
		if (isGrafoDirecionado) {
			System.out.println("\n  Grau dos vértices do grafo direcionado \n");
			imprimirGrauDosVerticesDeGrafoDirecionado(matrizGrafo, tamanhoMatriz);
		}
		else {
			System.out.println("\n  Grau dos vértices do grafo não direcionado \n");
			imprimirGrauDosVerticesDeGrafoNaoDirecionado(matrizGrafo, tamanhoMatriz);
		}
	}
	
	
	private static void imprimirGrauDosVerticesDeGrafoDirecionado(int[][] matrizGrafo, int tamanhoMatriz) {
		System.out.println("Vértice  -  Grau Entrada  -  Grau Saída");
		
		for (int i = 0; i < tamanhoMatriz; i++) {
			int somaGrauVerticeEntrada = 0;
			int somaGrauVerticeSaida = 0;
			
			for (int j = 0; j < tamanhoMatriz; j++) {
				somaGrauVerticeEntrada += matrizGrafo[j][i];
				somaGrauVerticeSaida += matrizGrafo[i][j];
			}
			
			System.out.println("   " + (i + 1) + "\t      " + somaGrauVerticeEntrada + "\t       " + somaGrauVerticeSaida);
		}
	}
	
	
	private static void imprimirGrauDosVerticesDeGrafoNaoDirecionado(int[][] matrizGrafo, int tamanhoMatriz) {
		System.out.println("Vértice  -  Grau");
		
		for (int i = 0; i < tamanhoMatriz; i++) {
			int somaGrauVertice = 0;
			
			for (int j = 0; j < tamanhoMatriz; j++) {
				somaGrauVertice += matrizGrafo[i][j];
			}
			 
			System.out.println("   " + (i + 1) + "\t  " + somaGrauVertice);
		}
	}
	
	
	public static void imprimirListaAdjacenciaDoGrafo(int[][] matrizGrafo, int tamanhoMatriz, boolean isGrafoDirecionado) {
		if (isGrafoDirecionado) {
			System.out.println("\n  Lista adjacência do grafo direcionado \n");
			imprimirListaAdjacenciaGrafoDirecionado(matrizGrafo, tamanhoMatriz);
		}
		else {
			System.out.println("\n  Lista adjacência do grafo não direcionado \n");
			imprimirListaAdjacenciaGrafoNaoDirecionado(matrizGrafo, tamanhoMatriz);
		}
	}
	
	
	private static void imprimirListaAdjacenciaGrafoDirecionado(int[][] matrizGrafo, int tamanhoMatriz) {
		for (int i = 0; i < tamanhoMatriz; i++) {
			String vertices = "";
			
			for (int j = 0; j < tamanhoMatriz; j++) {
				if (matrizGrafo[i][j] != 0) {
					vertices += "-> " + (j + 1);
				}
			}
			
			System.out.println((i + 1) + " " + (vertices.isEmpty() ? "•" : vertices + " •"));
		}
	}
	
	
	private static void imprimirListaAdjacenciaGrafoNaoDirecionado(int[][] matrizGrafo, int tamanhoMatriz) {
		for (int i = 0; i < tamanhoMatriz; i++) {
			String vertices = "";
			
			for (int j = 0; j < tamanhoMatriz; j++) {
				if (matrizGrafo[i][j] != 0) {
					if (matrizGrafo[i][j] == 2 && i != j) {
						vertices += "-> " + (j + 1);
					}
					
					vertices += "-> " + (j + 1);
				}
			}
			
			System.out.println((i + 1) + " " + (vertices.isEmpty() ? "•" : vertices + " •"));
		}
	}
}
