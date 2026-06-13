import java.util.ArrayList;
import java.util.List;

public class Camion {
    int id;
    String patente;
    boolean refrigerado;

    int capacidad;
    List<Paquete> paquetesAsignados;
    int totalAsignado;

    public Camion(int id, String patente, boolean refrigerado, int capacidad) {
        this.id = id;
        this.patente = patente;
        this.refrigerado = refrigerado;
        this.capacidad = capacidad;
        paquetesAsignados = new ArrayList<>();
    }

    public Camion() {
        paquetesAsignados = new ArrayList<Paquete>();
    }


    public List<Paquete> getPaquetesAsignados() {
        return paquetesAsignados;
    }

    public void setPaquetesAsignados(List<Paquete> paquetesAsignados) {
        this.paquetesAsignados = paquetesAsignados;
    }

    public int getTotalAsignados() {
       return totalAsignado;
    }

    public void addTotal(int peso) {
        totalAsignado += peso;
    }

    public void restarPeso(int peso) {
        totalAsignado -= peso;
    }

    public void addPaquete(Paquete paquete) {
        paquetesAsignados.add(paquete);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", refrigerado=" + refrigerado +
                ", capacidad=" + capacidad +
                '}';
    }
}
