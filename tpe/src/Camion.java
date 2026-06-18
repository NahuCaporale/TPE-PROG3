import java.util.ArrayList;
import java.util.List;

public class Camion {
    private int id;
    private String patente;
    private boolean refrigerado;
    private int capacidad;
    private List<Paquete> paquetesAsignados;
    private int totalAsignado;

    public Camion(int id, String patente, boolean refrigerado, int capacidad) {
        this.id = id;
        this.patente = patente;
        this.refrigerado = refrigerado;
        this.capacidad = capacidad;
        this.paquetesAsignados = new ArrayList<>();
        this.totalAsignado = 0;
    }

    public Camion() {
        this.paquetesAsignados = new ArrayList<>();
        this.totalAsignado = 0;
    }

    public Camion clonarEstado() {
        Camion copia = new Camion(this.id, this.patente, this.refrigerado, this.capacidad);
        copia.totalAsignado = this.totalAsignado;
        copia.paquetesAsignados.addAll(this.paquetesAsignados);
        return copia;
    }

    public boolean puedeLlevar(Paquete paquete) {
        if (paquete.contieneAlimento() && !this.refrigerado) {
            return false;
        }
        return this.totalAsignado + paquete.getPeso() <= this.capacidad;
    }

    public void addPaquete(Paquete paquete) {
        this.paquetesAsignados.add(paquete);
        this.totalAsignado += paquete.getPeso();
    }

    public void removePaquete(Paquete paquete) {
        this.paquetesAsignados.remove(paquete);
        this.totalAsignado -= paquete.getPeso();
    }

    public List<Paquete> getPaquetesAsignados() {
        return paquetesAsignados;
    }

    public int getTotalAsignados() {
        return totalAsignado;
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
        return "Camion{id=" + id + ", patente='" + patente + "', refrigerado=" + refrigerado + ", capacidad=" + capacidad + '}';
    }
}