public class Paquete {
    int id;
    String codigo;
    int peso;
    boolean contiene_alimento;
    int urgencia;


    public Paquete(int id, String codigo, int peso, boolean contiene_alimento, int urgencia) {
        this.id = id;
        this.codigo = codigo;
        this.peso = peso;
        this.contiene_alimento = contiene_alimento;
        this.urgencia = urgencia;
    }

    public Paquete() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isContiene_alimento() {
        return contiene_alimento;
    }

    public void setContiene_alimento(boolean contiene_alimento) {
        this.contiene_alimento = contiene_alimento;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", peso=" + peso +
                ", contiene_alimento=" + contiene_alimento +
                ", urgencia=" + urgencia +
                '}';
    }
}
