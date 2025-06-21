import java.io.Serializable;

public class Sala implements Serializable {
    private String nome;
    private int largura;
    private int comprimento;
    private String tipoSom;
    private String tipoSala;
    private String tipoLugares;
    private String caracEspecial;
    private boolean atividade;

    public Sala(String nome, int largura, int comprimento, String tipoSom, String tipoSala, String tipoLugares, String caracEspecial) {
        this.nome = nome;
        this.largura = largura;
        this.comprimento = comprimento;
        this.tipoSom = tipoSom;
        this.tipoSala = tipoSala;
        this.tipoLugares = tipoLugares;
        this.caracEspecial = caracEspecial;
        this.atividade = true;
    }

    //GETs
    public String getNome() {return nome;}
    public int getLargura() {return largura;}
    public int getComprimento() {return comprimento;}
    public String getTipoSom() {return tipoSom;}
    public String getTipoSala() {return tipoSala;}
    public String getTipoLugares() {return tipoLugares;}
    public String getCaracEspecial() {return caracEspecial;}
    public boolean isAtiva() {return atividade;}

    //SETs
    public void setNome(String nome) {this.nome = nome;}
    public void setLargura(int largura) {this.largura = largura;}
    public void setComprimento(int comprimento) {this.comprimento = comprimento;}
    public void setTipoSom(String tipoSom) {this.tipoSom = tipoSom;}
    public void setTipoSala(String tipoSala) {this.tipoSala = tipoSala;}
    public void setTipoLugares(String tipoLugares) {this.tipoLugares = tipoLugares;}
    public void setCaracEspecial(String caracEspecial) {this.caracEspecial = caracEspecial;}
    public void setAtiva(boolean atividade) {this.atividade = atividade;}

    @Override
    public String toString() {
        return nome;
    }
}
