import java.util.Scanner;

public class Main {

    public static void inserirProdutos(String[][] matriz, int linhas, int colunas, boolean limiteDefinido, int quantidadeLimite) {
        Scanner scanner = new Scanner(System.in);
        int linha;

        mostrarProdutos(matriz, linhas, colunas);
        do {
            System.out.println("Informe onde você deseja inserir o produto com valores de 1 à " + linhas);
            linha = scanner.nextInt();
        } while (linha < 1 || linha > linhas);

        System.out.println("Insira o nome do produto: ");
        matriz[linha - 1][0] = scanner.next();
        System.out.println("Insira a quantidade de produtos: ");
        matriz[linha - 1][1] = scanner.next();
        if (limiteDefinido) {
            verificaSeTodosEstaoDentroDoLimite(matriz, linhas, quantidadeLimite, limiteDefinido);
        }
        System.out.print("Insira o valor do produto: ");
        matriz[linha - 1][2] = scanner.next();
        System.out.println("Produto adicionado.");
        matriz[linha - 1][3] = Float.toString(Float.parseFloat(matriz[linha - 1][1]) * Float.parseFloat(matriz[linha - 1][2])); // Calculo da coluna total
    }

    public static void mostrarProdutos(String[][] matriz, int linhas, int colunas) {
        System.out.println("Produto | Quantidade | Valor | Total");

        for (int i = 0; i < linhas; i++)
         {
            System.out.print((i + 1) + " - ");
            for (int j = 0; j < colunas; j++) 
            {
                System.out.print(matriz[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void calcularValor(String[][] matriz, int linhas) {
        Float valorFinal = 0.0f;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null) {
                valorFinal = valorFinal + Float.parseFloat(matriz[i][3]);
            }
        }
        System.out.println("Valor final da lista de compras: " + valorFinal + " R$");
    }

    public static void buscarProduto(String[][] lista, int produtos, String nomeProduto, int quantidadeLimite, boolean limiteDefinido) {
        Scanner scanner = new Scanner(System.in);
        boolean encontrado = false;
        int opcaoBusca;

        for (int i = 0; i < produtos; i++) {
            if (lista[i][0] != null && lista[i][0].equals(nomeProduto)) {
                encontrado = true;
            }
        }

        if (encontrado) { // if (encontrado == true) {
        System.out.println("Produto encontrado.");
            
            
        System.out.println("Escolha uma opção: \n 1 - Atualizar produto. \n 2 - Remover produto \n 0 - Sair.");

        opcaoBusca = scanner.nextInt();
        switch (opcaoBusca) {
            case 0:
                break;
            case 1:
                atualizarProduto(lista, produtos, nomeProduto, quantidadeLimite, limiteDefinido);
                break;
            case 2:
                removerProduto(lista, produtos, nomeProduto);
                break;
            default:
                System.out.println("Opção Inválida!");
        }
        } 
        
        else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void atualizarProduto(String[][] matriz, int linhas, String nomeProduto, int quantidadeLimite, boolean limiteDefinido) {
        int i;

        for (i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equals(nomeProduto)) {
                atualizarNomeDoProduto(matriz, i);

                atualizarQuantidadeDoProduto(matriz, i, quantidadeLimite, limiteDefinido);

                atualizarValorDoProduto(matriz, i);
            }
        }
    }

    public static void atualizarNomeDoProduto(String[][] matriz, int i) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o nome do produto atualizado: ");
        matriz[i][0] = scanner.next();
    }

    public static void atualizarQuantidadeDoProduto(String[][] matriz, int i, int quantidadeLimite, boolean limiteDefinido) {
        Scanner scanner = new Scanner(System.in);

        if (limiteDefinido) {
            do {
                System.out.print("Insira a nova quantidade de produtos, ela deve ser menor do que ou igual a " + quantidadeLimite +": ");
                matriz[i][1] = scanner.next();
            } while (Integer.parseInt(matriz[i][1]) > quantidadeLimite);
        }
        else {
            System.out.print("Insira a nova quantidade de produtos: ");
                matriz[i][1] = scanner.next();
        }

        if (matriz[i][3] != null) {
            matriz[i][3] = Float.toString(Float.parseFloat(matriz[i][1]) * Float.parseFloat(matriz[i][2]));
        }
    }

    public static void atualizarValorDoProduto(String[][] matriz, int i) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o valor atualizado do produto: ");
        matriz[i][2] = scanner.next();

        matriz[i][3] = Float.toString(Float.parseFloat(matriz[i][1]) * Float.parseFloat(matriz[i][2]));
    }

    public static void removerProduto(String[][] matriz, int linhas, String nomeProduto) {
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equals(nomeProduto)) {
                matriz[i][0] = null;
                matriz[i][1] = null;
                matriz[i][2] = null;
                matriz[i][3] = null;
            }
        }
        
        System.out.println("Produto removido.");
    }

    public static int definirQuantidadeLimite() {
        Scanner scanner = new Scanner(System.in);
        int quantidadeLimite;

        System.out.print("Insira a quantidade limite para cada produto: ");
        quantidadeLimite = scanner.nextInt();

        return quantidadeLimite;
    }

    public static void verificaSeTodosEstaoDentroDoLimite(String[][] matriz, int linhas, int quantidadeLimite, boolean limiteDefinido) {
        int nomeDoProduto = 0;
        int quantidadeDeProdutos = 1; // O índice da quantidade de um produto é matriz[linhaDoProduto][1]

        for (int i = 0; i < linhas; i++) {
            if (matriz[i][quantidadeDeProdutos] != null && Integer.parseInt(matriz[i][quantidadeDeProdutos]) > quantidadeLimite) {
                System.out.println("O produto '" + matriz[i][nomeDoProduto] + "' ultrapassou a quantidade limite.");
                atualizarQuantidadeDoProduto(matriz, i, quantidadeLimite, limiteDefinido);
            }
        }
    }

    public static void calcularValorMedio(String[][] matriz, int linhas) {
        Float valorTotal = 0.0f;
        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null) {
                valorTotal = valorTotal + Float.parseFloat(matriz[i][3]);
            }
        }
        System.out.println("Valor médio por item da lista de compras: " + (valorTotal/linhas) + " R$");
    }

    public static void main(String[] args) {
        String[][] lista;
        int produtos, opcao;
        int colunas = 4;
        Scanner scanner = new Scanner(System.in);
        String nomeProduto;
        int quantidadeLimite = 0;
        boolean limiteDefinido = false;

        System.out.print("Insira a quantidade de produtos que serão inseridos na sua lista de compras: ");
        produtos = scanner.nextInt();
        lista = new String[produtos][colunas];

        do {
            System.out.println("Escolha uma opção: \n 1 - Mostrar lista de compras. \n 2 - Inserir produtos na lista de compras. \n 3 - Calcular valor dos produtos da lista de compras. \n 4 - Calcular valor médio por item da lista. \n 5 - Buscar produto da lista de compras. \n 6 - Definir quantidade limite. \n 0 - Sair.");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;

                case 1:
                    mostrarProdutos(lista, produtos, colunas);
                    break;

                case 2:
                    inserirProdutos(lista, produtos, colunas, limiteDefinido, quantidadeLimite);
                    break;
                    
                case 3:
                    calcularValor(lista, produtos);
                    break;

                case 4:
                    calcularValorMedio(lista, produtos);
                    break;

                case 5:
                    System.out.print("Insira o produto a ser buscado: ");
                    nomeProduto = scanner.next();
                    buscarProduto(lista, produtos, nomeProduto, quantidadeLimite, limiteDefinido);
                    break;

                case 6:
                    quantidadeLimite = definirQuantidadeLimite();
                    limiteDefinido = true;
                    verificaSeTodosEstaoDentroDoLimite(lista, produtos, quantidadeLimite, limiteDefinido);
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }
}
