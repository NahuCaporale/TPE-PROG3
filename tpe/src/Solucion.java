import java.util.HashMap;
import java.util.List;

public class Solucion {
    HashMap<Camion, List<Paquete>> solucion;
    int pesoNoAsignado;
    int estadosGenerados;

    public Solucion(HashMap<Camion, List<Paquete>> solucion, int pesoNoAsignado, int estadosGenerados) {
        this.solucion = solucion;
        this.pesoNoAsignado = pesoNoAsignado;
        this.estadosGenerados = estadosGenerados;
    }

    public Solucion() {
        pesoNoAsignado= Integer.MAX_VALUE;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    @Override
    public String toString() {
        return "Solucion{" +
                "solucion=" + solucion +
                ", pesoNoAsignado=" + pesoNoAsignado +
                ", estadosGenerados=" + estadosGenerados +
                '}';
    }

    public void setEstadosGenerados(int estadosGenerados) {
        this.estadosGenerados = estadosGenerados;
    }

    public int getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public void setPesoNoAsignado(int pesoNoAsignado) {
        this.pesoNoAsignado = pesoNoAsignado;
    }

    public HashMap<Camion, List<Paquete>> getSolucion() {
        return solucion;
    }

    public void setSolucion(HashMap<Camion, List<Paquete>> solucion) {
        this.solucion = solucion;
    }
}
