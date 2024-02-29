package socialgames;

public class principal {
    public static void main(String[] args) {
        CamisaTimeFutebol camisa = new CamisaTimeFutebol(1, "Modelo 1", "Azul", "M", "Nike");
        System.out.println(camisa.toString());
    }
}

class CamisaTimeFutebol {
    private int id;
    private String modelo;
    private String cor;
    private String tamanho;
    private String fabricante;

    public CamisaTimeFutebol() {
        this.id = -1;
        this.modelo = "";
        this.cor = "";
        this.tamanho = "";
        this.fabricante = "";
    }

    public CamisaTimeFutebol(int id, String modelo, String cor, String tamanho, String fabricante) {
        this.id = id;
        this.modelo = modelo;
        this.cor = cor;
        this.tamanho = tamanho;
        this.fabricante = fabricante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "CamisaTimeFutebol [id=" + id + ", modelo=" + modelo + ", cor=" + cor + ", tamanho=" + tamanho
                + ", fabricante=" + fabricante + "]";
    }    
}
