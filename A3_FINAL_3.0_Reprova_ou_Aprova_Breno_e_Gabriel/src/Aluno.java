import java.io.FileWriter;
import java.io.IOException;

public class Aluno {
    private String nome;
    double[] notasProvas;
    double[] notasTrabalho;
    private int faltas;
    private int totalAulas;

    public Aluno(String nome, int totalAulas) {  
        this.nome = nome;
        this.notasProvas = new double[4]; // Duas provas de cada matéria
        this.notasTrabalho = new double[2]; // Dois trabalhos, um para cada matéria
        this.totalAulas = totalAulas;
    }

    public String getNome() { 
        return nome;
    }

    public void setarNotas(double notaProva1Mat1, double notaProva2Mat1, double notaTrabalho1Mat1, double notaProva1Mat2, double notaProva2Mat2, double notaTrabalho1Mat2) {
        notasProvas[0] = notaProva1Mat1;
        notasProvas[1] = notaProva2Mat1;
        notasProvas[2] = notaProva1Mat2;
        notasProvas[3] = notaProva2Mat2;
        notasTrabalho[0] = notaTrabalho1Mat1;
        notasTrabalho[1] = notaTrabalho1Mat2;
    }

    public void setarFaltas(int faltas) {
        this.faltas = faltas;
    }

    double calcularSoma(double[] notas) {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma;
    }

    public boolean aprovado() {
        double somaMat1 = calcularSoma(new double[]{notasProvas[0], notasProvas[1], notasTrabalho[0]});
        double somaMat2 = calcularSoma(new double[]{notasProvas[2], notasProvas[3], notasTrabalho[1]});
        return somaMat1 >= 21 && somaMat2 >= 21; // 70% de 30 pontos possíveis
    }

    public boolean comDireitoARecuperacao() {
        double somaMat1 = calcularSoma(new double[]{notasProvas[0], notasProvas[1], notasTrabalho[0]});
        double somaMat2 = calcularSoma(new double[]{notasProvas[2], notasProvas[3], notasTrabalho[1]});
        return (somaMat1 < 21 || somaMat2 < 21);
    }

    public boolean aprovadoPorPresenca() {
        double porcentagemPresenca = ((double) (totalAulas - faltas) / totalAulas) * 100;
        return porcentagemPresenca >= 70;
    }

    public void realizarRecuperacaoMat1(double notaRecuperacao) {
        notasProvas[0] = notaRecuperacao;
        notasProvas[1] = 0;
        notasTrabalho[0] = 0;
    }

    public void realizarRecuperacaoMat2(double notaRecuperacao) {
        notasProvas[2] = notaRecuperacao;
        notasProvas[3] = 0;
        notasTrabalho[1] = 0;
    }

    public void salvarDados(String caminhoDoArquivo) throws IOException {
        FileWriter escrevedorNoArquivo = new FileWriter(caminhoDoArquivo, true);
        escrevedorNoArquivo.write("Nome: " + nome + "\n");
        escrevedorNoArquivo.write("Notas Provas: " + notasProvas[0] + "," + notasProvas[1] + ";" + notasProvas[2] + "," + notasProvas[3] + "\n");
        escrevedorNoArquivo.write("Notas Trabalhos: " + notasTrabalho[0] + ";" + notasTrabalho[1] + "\n");
        escrevedorNoArquivo.write("Faltas: " + faltas + "\n");
        escrevedorNoArquivo.write("Total de Aulas: " + totalAulas + "\n");
        escrevedorNoArquivo.close();
    }
}
