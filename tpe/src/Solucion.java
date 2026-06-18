import java.util.ArrayList;
import java.util.List;

public class Solucion {
    private List<Camion> camiones;
    private int pesoNoAsignado;
    private int estadosGenerados;
    private int candidatosConsiderados;

    public Solucion(List<Camion> camiones, int pesoNoAsignado, int estadosGenerados, int candidatosConsiderados) {
        this.camiones = camiones;
        this.pesoNoAsignado = pesoNoAsignado;
        this.estadosGenerados = estadosGenerados;
        this.candidatosConsiderados = candidatosConsiderados;
    }

    public Solucion() {
        this.pesoNoAsignado = Integer.MAX_VALUE;
        this.camiones = new ArrayList<>();
    }

    // Getters y Setters
    public List<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }

    public int getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public void setPesoNoAsignado(int pesoNoAsignado) {
        this.pesoNoAsignado = pesoNoAsignado;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public void setEstadosGenerados(int estadosGenerados) {
        this.estadosGenerados = estadosGenerados;
    }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public void setCandidatosConsiderados(int candidatosConsiderados) {
        this.candidatosConsiderados = candidatosConsiderados;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solucion {\n");
        if (camiones != null && !camiones.isEmpty()) {
            for (Camion c : camiones) {
                sb.append("  Camion ").append(c.getId())
                        .append(" (").append(c.getPatente()).append(")")
                        .append(" - refrigerado=").append(c.isRefrigerado())
                        .append(", capacidad=").append(c.getCapacidad())
                        .append(", asignado=").append(c.getTotalAsignados())
                        .append("\n");
                for (Paquete p : c.getPaquetesAsignados()) {
                    sb.append("    - ").append(p).append("\n");
                }
            }
        }
        sb.append("  Peso no asignado: ").append(pesoNoAsignado).append(" kg\n");
        sb.append("  Estados generados (Backtracking): ").append(estadosGenerados).append("\n");
        sb.append("  Candidatos considerados (Greedy): ").append(candidatosConsiderados).append("\n");
        sb.append("}");
        return sb.toString();
    }
}