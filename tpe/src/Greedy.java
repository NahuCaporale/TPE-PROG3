import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Greedy {

    /*

     * Estrategia greedy:
     Dividimos la unica lista que nos viene de paquetes en dos, con alimentos y sin,
     donde podemos justamente ser mas eficiente a la hora de
     asignar los que tienen alimentos. a su vez esas dos listas las ordenamos por peso descendente.
     tambien ordenamos la lista de camiones por peso y ponemos primero
     los que son refrigerados para que la primera iteracion de los alimentos sea mucho mas eficiente.
     peor caso: o(p^2) donde p es la cantidad de paquetes. si c es del mismo orden que p,
     esto equivale a O(p^2) y queda predominante sobre la complejidad del mejor caso que seria o(p log p)

     * */
    public Solucion getSolucionGreedy(List<Camion> camionesBase, List<Paquete> paquetes) {
        List<Paquete> pAlimentos = paquetes.stream()
                .filter(Paquete::contieneAlimento)
                .collect(Collectors.toList());
        pAlimentos.sort((p1, p2) -> p2.getPeso() - p1.getPeso());

        List<Paquete> pNoAlimentos = paquetes.stream()
                .filter(p -> !p.contieneAlimento())
                .collect(Collectors.toList());
        pNoAlimentos.sort((p1, p2) -> p2.getPeso() - p1.getPeso());

        List<Camion> camiones = new ArrayList<>();
        //para no alterar/ensuciar la lista si hay que volver a ejecutar el algoritmo.
        for (Camion c : camionesBase) {
            camiones.add(new Camion(c.getId(), c.getPatente(), c.isRefrigerado(), c.getCapacidad()));
        }
        camiones.sort((c1, c2) -> Boolean.compare(c2.isRefrigerado(), c1.isRefrigerado()));

        int candidatosConsiderados = 0;
        int pesoNoAsignado = 0;

        for (Paquete paquete : pAlimentos) {
            boolean asignado = false;
            for (Camion c : camiones) {
                candidatosConsiderados++;
                if (c.puedeLlevar(paquete)) {
                    c.addPaquete(paquete);
                    asignado = true;
                    break;
                }
            }
            if (!asignado) pesoNoAsignado += paquete.getPeso();
        }

        for (Paquete paquete : pNoAlimentos) {
            boolean asignado = false;
            for (Camion c : camiones) {
                candidatosConsiderados++;
                if (c.puedeLlevar(paquete)) {
                    c.addPaquete(paquete);
                    asignado = true;
                    break;
                }
            }
            if (!asignado) pesoNoAsignado += paquete.getPeso();
        }

        return new Solucion(camiones, pesoNoAsignado, 0, candidatosConsiderados);
    }
}
