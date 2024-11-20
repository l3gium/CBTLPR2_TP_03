public class Pessoa {
    private static int kp = 0;
    private String nome;
    private char sexo;
    private int idade;

    public Pessoa() {
        setKp();
    }

    public Pessoa(String nome, char sexo, int idade) {
        this.nome = nome;
        this.setSexo(sexo);
        this.idade = idade;
        setKp();
    }

    public static int getKp() {
        return kp;
    }

    public static void setKp() {
        kp++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        if (sexo == 'M' || sexo == 'F') {
            this.sexo = sexo;
        } else {
            throw new IllegalArgumentException("O campo 'Sexo' só pode ser 'M' ou 'F'.");
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}