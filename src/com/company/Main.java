package com.company;

import java.util.Scanner;
public class Main {

    public static void mostrarAlunos(String [][] matriz, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe os dados com base no modelo a seguir: \n") ;
        System.out.println("Nome do aluno | Data da falta (DD-MM-AAAA) | Numero de faltas ");
        for(int i = 0; i < linhas; i++) {
            System.out.print((i+1) + " - ");
            for(int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j] + " | ");

            }
            System.out.println();
        }
    }

    public static void inserirAlunos(String[][] matriz, int linhas, int colunas) {
        Scanner scanner = new Scanner(System.in);
        int linha;

        mostrarAlunos(matriz, linhas, colunas);
        do {
            System.out.println();
            System.out.println("Para começarmos, informe em qual linha você deseja inserir o aluno de 1 à " + linhas);
            linha = scanner.nextInt();
        } while (linha < 1 || linha > linhas);

        System.out.println("Ótimo! Agora insira o nome do aluno: ");
        matriz[linha - 1][0] = scanner.next();

        String dataFalta;
        boolean entradaValida = false;
        do {
            System.out.println("Insira a data em que o aluno faltou: ");
            dataFalta = scanner.next();
            if (!dataFalta.matches("\\d{2}-\\d{2}-\\d{4}")) { //Verifica se a data condiz com o modelo citado
                System.out.println("Data inválida. Por favor, insira uma data no formato exigido (DD-MM-AAAA).");
            } else {
                entradaValida = true;
            }
        } while (!entradaValida);

        matriz[linha - 1][1] = dataFalta;

        String numeroFaltas;
        entradaValida = false;
        do {
            System.out.println("Insira o número de faltas do determinado aluno: ");
            numeroFaltas = scanner.next();
            if (!numeroFaltas.matches("\\d+")) { // Verifica se as faltas estão apresentadas somente em numeros
                System.out.println("Valor inválido. Por favor, insira um valor numérico.");
            } else {
                entradaValida = true;
            }
        } while (!entradaValida);

        matriz[linha - 1][2] = numeroFaltas;
        System.out.println("Aluno Cadastrado.");
    }

    public static void calcularFaltas(String[][] matriz, int linhas) { //Aqui caso você adicione o mesmo aluno em diferentes dias ele calculará o total de faltas desse mesmo aluno
        Scanner scanner = new Scanner(System.in);
        int totalFaltas = 0;

        System.out.println("Insira o nome do aluno para calcular o total de faltas:");
        String nomeAluno = scanner.next();

        for (int i = 0; i < linhas; i++) {
            if (matriz[i][0] != null && matriz[i][0].equalsIgnoreCase(nomeAluno)) {
                totalFaltas += Integer.parseInt(matriz[i][2]);
                System.out.println("Data de falta: " + matriz[i][1]);
            }
        }

        System.out.println("Total de faltas do aluno " + nomeAluno + ": " + totalFaltas);
    }
    public static void removerAluno(String [][] matriz, int linhas, String nomeAluno) {
        boolean encontrado = false;
        for(int i = 0; i < linhas; i++) {
            if(matriz[i][0] != null && matriz[i][0].equals(nomeAluno)) {
                encontrado = true;
                matriz[i][0] = null;
                matriz[i][1] = null;
                matriz[i][2] = null;
            }
        }
        if(encontrado) { // if (encontrado == true) {
            System.out.println("Aluno removido.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public static void main(String[] args) {
        String [][] lista;
        int alunos, opcao;
        int colunas = 3;
        Scanner scanner = new Scanner(System.in);
        String nomeAluno;

        System.out.println();
        System.out.println("Seja bem vindo ao sistema de chamada!!");
        System.out.println();
        System.out.println("----------------------------------------------------------");
        System.out.println("Insira a quantidade total de alunos presentes em sua turma: ");
        System.out.println("----------------------------------------------------------");
        while (!scanner.hasNextInt()) {
            System.out.println("Valor inválido, por favor, insira um valor numérico.");
            scanner.next(); // Limpa o valor inválido inserido caso o mesmo não seja numérico
        }
        alunos = scanner.nextInt();
        lista = new String[alunos][colunas];
        System.out.println("Quantidade registrada. Muito obrigado!");

        do {
            System.out.println();
            System.out.println("Agora, você pode escolher entre as seguintes opções do menu abaixo: \n");
            System.out.println("------------- MENU -------------");
            System.out.println(" 1 - Inserir alunos na chamada. \n 2 - Mostrar lista de alunos. \n 3 - Calcular total de faltas de determinado aluno. \n 4 - Remover aluno da chamada. \n 0 - Fechar chamada.");
            System.out.println("--------------------------------");
            opcao = scanner.nextInt();
            switch(opcao) {
                case 0:
                    break;
                case 1:
                    inserirAlunos(lista, alunos, colunas);
                    break;
                case 2:
                    mostrarAlunos(lista, alunos, colunas);
                    break;
                case 3:
                    calcularFaltas(lista, alunos);
                    break;
                case 4:
                    System.out.println("Insira o nome do aluno que você deseja remover da chamada.");
                    nomeAluno = scanner.next();
                    removerAluno(lista, alunos, nomeAluno);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}