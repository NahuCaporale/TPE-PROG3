import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solucion {
    HashMap<Camion, List<Paquete>> solucion;
    int pesoNoAsignado;
    int estadosGenerados;


    int candidatosConsiderados;

    public Solucion(HashMap<Camion, List<Paquete>> solucion, int pesoNoAsignado, int estadosGenerados, int candidatosConsiderados) {
        this.solucion = solucion;
        this.pesoNoAsignado = pesoNoAsignado;
        this.estadosGenerados = estadosGenerados;
        this.candidatosConsiderados = candidatosConsiderados;
    }

    public Solucion() {
        pesoNoAsignado = Integer.MAX_VALUE;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solucion {\n");
        if (solucion != null) {
            for (Map.Entry<Camion, List<Paquete>> entry : solucion.entrySet()) {
                Camion c = entry.getKey();
                sb.append("  Camion ").append(c.getId())
                        .append(" (").append(c.getPatente()).append(")")
                        .append(" - refrigerado=").append(c.isRefrigerado())
                        .append(", capacidad=").append(c.getCapacidad())
                        .append(", asignado=").append(c.getTotalAsignados())
                        .append("\n");
                for (Paquete p : entry.getValue()) {
                    sb.append("    - ").append(p).append("\n");
                }
            }
        }
        sb.append("  Peso no asignado: ").append(pesoNoAsignado).append(" kg\n");
        sb.append("  Estados generados: ").append(estadosGenerados).append("\n");
        sb.append("  Candidatos considerados: ").append(candidatosConsiderados).append("\n");
        sb.append("}");
        return sb.toString();
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

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public void setCandidatosConsiderados(int candidatosConsiderados) {
        this.candidatosConsiderados = candidatosConsiderados;
    }

}
