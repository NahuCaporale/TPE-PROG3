import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking {
    Solucion mejorSolucion;
    int parcialNoAsignado;
    int estadosGenerados;
    int mejorPesoNoAsignado;

    public Backtracking() {
    }

    /*
     * El objetivo es minimizar la cantidad del peso noAsignado explorando todas las combinaciones posibles.
     * Utilizamos un hashmap para la solucion porque en terminos de acceso se hacia mas efectivo que tener
     * por ejemplo una lista de paquetes dentro de la clase camion. el map nos baja la complejidad de acceder a los paquetes de cada camion.
     * tambien como el enunciado nos decia que podria no asignarse un paquete tuvimos que hacer su propia rama de backtrack para explorar esa
     * opcion.
     *
     * es una solucion con complejidad computacional de O((C+1)^p) en el peor caso, donde c es la cantidad de camiones que tenemos para recorrer,
     * y p la cantidad de paquetes a asignar, el +1 represnta la rama donde ese paquete no sa asigna.
     *
     * Poda:la unica poda que hay es si nos pasamos del mejor pesoNoAsignado que ya tenemos registrado.
     * nos sirve para reducir lacantidad de estados generados.
     * */
    public Solucion getSolucion(List<Camion> camiones, List<Paquete> paquetes) {
        if (camiones.isEmpty() || paquetes.isEmpty()) return null;
        this.parcialNoAsignado = 0;
        this.estadosGenerados = 0;
        this.mejorPesoNoAsignado = Integer.MAX_VALUE;
        this.mejorSolucion = new Solucion();

        List<Camion> estadoCamiones = new ArrayList<>();
        for (Camion c : camiones) {
            estadoCamiones.add(new Camion(c.getId(), c.getPatente(), c.isRefrigerado(), c.getCapacidad()));
        }
        backtrack(camiones, paquetes, 0);
        this.mejorSolucion.setEstadosGenerados(this.estadosGenerados);
        return this.mejorSolucion;
    }

    //Backtracking
    //Primero, ningún camión podrá superar su capacidad máxima de carga. La
    //capacidad de cada camión está definida en el archivo de entrada.
    //• Segundo, los paquetes que contienen alimentos sólo podrán ser asignados
    //a camiones refrigerados.
    //Solución obtenida: cada camión con los paquetes asignados.
    //Peso no asignado: <peso total de paquetes sin asignar> kg.
    //Métrica para analizar el costo de la solución (cantidad de
    //estados generados).
    private void backtrack(List<Camion> camiones, List<Paquete> paquetes, int iPaquete) {
        this.estadosGenerados++;

        if (this.parcialNoAsignado >= this.mejorPesoNoAsignado) return;

        if (iPaquete >= paquetes.size()) {
            if (this.parcialNoAsignado < this.mejorPesoNoAsignado) {
                this.mejorPesoNoAsignado = this.parcialNoAsignado;

                List<Camion> copiaCamiones = new ArrayList<>();
                for (Camion c : camiones) {
                    copiaCamiones.add(c.clonarEstado());
                }

                this.mejorSolucion.setCamiones(copiaCamiones);
                this.mejorSolucion.setPesoNoAsignado(this.mejorPesoNoAsignado);
            }
            return;
        }

        Paquete paquete = paquetes.get(iPaquete);

        for (Camion c : camiones) {
            if (c.puedeLlevar(paquete)) {
                c.addPaquete(paquete);
                backtrack(camiones, paquetes, iPaquete + 1);
                c.removePaquete(paquete);
            }
        }

        this.parcialNoAsignado += paquete.getPeso();
        backtrack(camiones, paquetes, iPaquete + 1);
        this.parcialNoAsignado -= paquete.getPeso();
    }
}