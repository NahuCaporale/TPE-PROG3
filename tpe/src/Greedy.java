import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Greedy {
    HashMap<Camion, List<Paquete>> s;
    /*

     * Estrategia greedy:
     Dividimos la unica lista que nos viene de paquetes en dos,con alimentos y sin, donde podemos justamente ser mas eficiente a la hora de
     asignar los que tienen alimentos. a su vez esas dos listas las ordenamos por peso descendente.
      tambien ordenamos la lista de camiones por peso y ponemos primero
     los que son refrigerados para que la primera iteracion de los alimentos sea mucho mas eficiente.
      peor caso: o(p^2) donde p es la cantidad de paquetes. si c es del mismo orden que p, esto equivale a O(p^2) y queda predominante sobre
      la complejidad del mejor caso que seria o(p log p)

     * */

    public Solucion getSolucionGreedy(List<Camion> camiones, List<Paquete> paquetes) {
        List<Paquete> pAlimentos = paquetes.stream().filter(p -> p.contieneAlimento()).collect(Collectors.toList());

        pAlimentos.sort((p1, p2) -> p2.getPeso() - p1.getPeso());
        List<Paquete> pNoAlimentos = paquetes.stream().filter(p -> !p.contieneAlimento()).collect(Collectors.toList());
        pNoAlimentos.sort((p1, p2) -> p2.getPeso() - p1.getPeso());
        camiones.sort((c1, c2) -> Boolean.compare(c2.isRefrigerado(), c1.isRefrigerado()));
        s = new HashMap<Camion, List<Paquete>>();

        for (Camion c : camiones) {
            s.put(c, new ArrayList<>());
        }

        int candidatos = 0;
        int pesoNoAsignado = 0;

        for (Paquete paquete : pAlimentos) {
            boolean asignado = false;

            for (Camion c : camiones) {

                candidatos++;
                if (c.isRefrigerado()) {
                    if (paquete.getPeso() + c.totalAsignado <= c.getCapacidad()) {
                        s.get(c).add(paquete);
                        c.addTotal(paquete.getPeso());
                        c.addPaquete(paquete);
                        asignado = true;
                        break;
                    }
                }
            }
            if (!asignado) pesoNoAsignado += paquete.getPeso();

        }
        for (Paquete paquete : pNoAlimentos) {
            boolean asignado = false;
            for (Camion c : camiones) {
                candidatos++;
                if (paquete.getPeso() + c.totalAsignado <= c.getCapacidad()) {
                    s.get(c).add(paquete);
                    c.addTotal(paquete.getPeso());
                    c.addPaquete(paquete);
                    asignado= true;
                    break;
                }
            }
            if (!asignado) pesoNoAsignado += paquete.getPeso();
        }

        Solucion solucion = new Solucion(s, pesoNoAsignado, 0, candidatos);

        return solucion;
    }

}

