import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking {
    Map<Camion, List<Paquete>> solucionParcial;
    Solucion s;
    int parcialNoAsignado;

    /*
     * El objetivo es minimizar la cantidad del peso noAsignado explorando todas las combinaciones posibles.
     * Utilizamos un hashmap para la solucion porque en terminos de acceso se hacia mas efectivo que tener
     * por ejemplo una lista de paquetes dentro de la clase camion. el map nos baja la complejidad de acceder a los paquetes de cada camion.
     * tambien como el enunciado nos decia que podria no asignarse un paquete tuvimos que hacer su propia rama de backtrack para explorar esa
     * opcion.
     * es una solucion con complejidad computacional de O((C+1)^p), donde c es la cantidad de camiones que tenemos para recorrer,
     * y p la cantidad de paquetes a asignar, el +1 es la posibilidad de que ese paquete no se asigne.
     * */
    public Solucion getSolucion(List<Camion> camiones, List<Paquete> paquetes) {
        if (camiones.isEmpty() || paquetes.isEmpty()) return null;
        s = new Solucion();
        solucionParcial = new HashMap<>();
        for (Camion c : camiones) {
            solucionParcial.put(c, new ArrayList<>());
        }
        backtrack(camiones, paquetes, 0);
        return s;
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
    public void backtrack(List<Camion> camiones, List<Paquete> paquetes, int iPaquete) {
        s.estadosGenerados++;
        if (paquetes.size() <= iPaquete) {
            if (s.getPesoNoAsignado() > parcialNoAsignado) {
                if (s.getSolucion() != null) s.getSolucion().clear();
                HashMap<Camion, List<Paquete>> copia = new HashMap<>();
                for (Map.Entry<Camion, List<Paquete>> camActual : solucionParcial.entrySet()) {
                    //genero una entrada nueva en copia con los datos del camion actual del map.
                    //si asignaba directamente la solucionparcial de la clase no se guardaban los elementos
                    //ya que estaba referenciando a una solucion que se limpia cada vez que termina el algoritmo.
                    copia.put(camActual.getKey(), new ArrayList<>(camActual.getValue()));

                }
                s.setSolucion(copia);
                s.setPesoNoAsignado(parcialNoAsignado);
            }
            return;
        }


        Paquete paquete = paquetes.get(iPaquete);
        for (int i = 0; i <= camiones.size() - 1; i++) {
            Camion c = camiones.get(i);
            if (sePuedeAsignar(c, paquete)) {
                solucionParcial.get(c).add(paquete);
                c.addTotal(paquete.getPeso());
                backtrack(camiones, paquetes, iPaquete + 1);
                solucionParcial.get(c).remove(paquete);
                c.restarPeso(paquete.getPeso());
            }
        }

        parcialNoAsignado += paquete.getPeso();//no se pudo asignar el paquete, exploramos la opcion. +1 en complejidad.
        backtrack(camiones, paquetes, iPaquete + 1);
        parcialNoAsignado -= paquete.getPeso();

    }

    public boolean sePuedeAsignar(Camion c, Paquete paquete) {
        if (paquete.isContiene_alimento() && !c.isRefrigerado()) return false;//solo si contiene alimentos y no es refrigerado es false.
        if (c.getTotalAsignados() + paquete.getPeso() > c.getCapacidad()) return false;
        return true;
    }

}
