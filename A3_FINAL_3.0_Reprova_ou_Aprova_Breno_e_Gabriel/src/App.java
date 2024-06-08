import java.util.Scanner;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        final int totalAulas = 100;

        System.out.println("Nome do Aluno 1: ");
        String nome1 = teclado.nextLine();
        Aluno aluno1 = new Aluno(nome1, totalAulas);

        System.out.println("Nome do Aluno 2: ");
        String nome2 = teclado.nextLine();
        Aluno aluno2 = new Aluno(nome2, totalAulas);

        System.out.println("Digite as notas do Aluno 1 (A1, matemática; A2, matemática; trabalho, matemática; A1, Português; A2, Português; trabalho, Português): ");
        aluno1.setarNotas(teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble());
        System.out.print("Digite as FALTAS do aluno 1: ");
        aluno1.setarFaltas(teclado.nextInt());

        System.out.println("Digite as notas do Aluno 2 (A1, matemática; A2, matemática; trabalho, matemática; A1, Português; A2, Português; trabalho, Português): ");
        aluno2.setarNotas(teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble(), teclado.nextDouble());
        System.out.print("Digite as FALTAS do aluno 2: ");
        aluno2.setarFaltas(teclado.nextInt());

        try {
            aluno1.salvarDados("dados_aluno1.txt");
            aluno2.salvarDados("dados_aluno2.txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }

        avaliarAluno(aluno1, teclado);
        avaliarAluno(aluno2, teclado);
        teclado.close(); // Fecha o scanner no final
    }

    private static void avaliarAluno(Aluno aluno, Scanner teclado) {
        if (!aluno.aprovadoPorPresenca()) {
            System.out.println(aluno.getNome() + " está reprovado por falta.");
        } else if (aluno.aprovado()) {
            System.out.println(aluno.getNome() + " está aprovado.");
        } else if (aluno.comDireitoARecuperacao()) {
            System.out.println(aluno.getNome() + " está de recuperação.");
            boolean passouRecuperacao = true;

            if (aluno.calcularSoma(new double[]{aluno.notasProvas[0], aluno.notasProvas[1], aluno.notasTrabalho[0]}) < 21) {
                System.out.print("Nota da recuperação (Matemática): ");
                double notaRecuperacaoMat1 = teclado.nextDouble();
                aluno.realizarRecuperacaoMat1(notaRecuperacaoMat1);
                if (notaRecuperacaoMat1 < 7) {
                    passouRecuperacao = false;
                }
            }

            if (aluno.calcularSoma(new double[]{aluno.notasProvas[2], aluno.notasProvas[3], aluno.notasTrabalho[1]}) < 21) {
                System.out.print("Nota da recuperação (Português): ");
                double notaRecuperacaoMat2 = teclado.nextDouble();
                aluno.realizarRecuperacaoMat2(notaRecuperacaoMat2);
                if (notaRecuperacaoMat2 < 7) {
                    passouRecuperacao = false;
                }
            }

            if (passouRecuperacao) {
                System.out.println(aluno.getNome() + " está aprovado na recuperação.");
            } else {
                System.out.println(aluno.getNome() + " não passou na recuperação. " + aluno.getNome() + " está reprovado.");
            }
        } else {
            System.out.println(aluno.getNome() + " está reprovado.");
        }
    }
}
