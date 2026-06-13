import java.util.List;

public class Greedy {
    Solucion s;
    /*
     * El objetivo es minimizar la cantidad del peso noAsignado explorando todas las combinaciones posibles.
     * por ejemplo una lista de paquetes dentro de la clase camion. el map nos baja la complejidad de acceder a los paquetes de cada camion.
     * tambien como el enunciado nos decia que podria no asignarse un paquete tuvimos que hacer su propia rama de backtrack para explorar esa
     * opcion.
     * es una solucion con complejidad computacional de O((C+1)^p), donde c es la cantidad de camiones que tenemos para recorrer,
     * y p la cantidad de paquetes a asignar, el +1 es la posibilidad de que ese paquete no se asigne.
     * */
    public Solucion getSolucionGreedy(List<Camion> camiones, List<Paquete> paquetes){


    }
    //
    //    //Primero, ningún camión podrá superar su capacidad máxima de carga. La
    //    //capacidad de cada camión está definida en el archivo de entrada.
    //    //• Segundo, los paquetes que contienen alimentos sólo podrán ser asignados
    //    //a camiones refrigerados.
    //    //Solución obtenida: cada camión con los paquetes asignados.
    //    //Peso no asignado: <peso total de paquetes sin asignar> kg.
    //    //Métrica para analizar el costo de la solución (cantidad de
    //    //estados generados).
}

