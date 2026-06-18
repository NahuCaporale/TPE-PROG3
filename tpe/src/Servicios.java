import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servicios {
    private int camionesTotales = 0;
    private List<Camion> camiones;
    private int paquetesTotales;
    private List<Paquete> paquetes;
    //para servicio 1
    private HashMap<String, Paquete> paqueteMap;
    //servicio 2
    private final List<Paquete> conAlimento;
    private final List<Paquete> sinAlimento;
    //servicio 3
    private List<Paquete>[] urgencias;

    /*
     * o(n) donde n es la cantidad de elementos que contienen los archivos .csv
     */
    public Servicios(String pathCamiones, String pathPaquetes) {
        utils utils = new utils();
        this.paqueteMap = new HashMap<>();
        this.conAlimento = new ArrayList<>();
        this.sinAlimento = new ArrayList<>();
        this.urgencias = new ArrayList[101];
        //CAMIONES si no funciona con este path poner absoluth,
        // o agregar $MODULE_WORKING_DIR$ en el working directory dentro de la config del run
        List<List<String>> listaCamion = utils.reader(pathCamiones);
        this.camionesTotales = utils.getTotal(listaCamion);
        this.camiones = utils.getCamiones(listaCamion);


        //PAQUETES,  si no funciona con este path poner absoluth,
        // o agregar $MODULE_WORKING_DIR$ en el working directory dentro de la config del run
        List<List<String>> paquetesRaw = utils.reader(pathPaquetes);
        this.paquetesTotales = utils.getTotal(paquetesRaw);
        this.paquetes = utils.getPaquetes(paquetesRaw);
        for (int i = 0; i < 101; i++) {
            this.urgencias[i] = new ArrayList<>();
        }
        for (Paquete p : paquetes) {
            paqueteMap.put(p.getCodigo().toUpperCase(), p);
            if (p.contieneAlimento) conAlimento.add(p);
            else {
                sinAlimento.add(p);
            }
            this.urgencias[p.getUrgencia()].add(p);

        }

    }


    /*
     * Expresar la complejidad temporal del servicio 1.
     * o(1), ya que utilizamos un map cargado en el constructor, donde tenemos el codigo + el paquete.
     * * dado un código de paquete (String), retornar toda la información
        del paquete asociado. En caso de no existir, retornar null.

     */
    public Paquete servicio1(String codigoPaquete) {
        if (codigoPaquete == null) return null;
        return paqueteMap.get(codigoPaquete.toUpperCase());
    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     * o(m) donde m es la cantidad de elementos de la lista que necesitamos retornar
     * nos evitamos el tener que iterar sobre las listas.
     *

     */
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return contieneAlimentos ? new ArrayList<>(conAlimento) : new ArrayList<>(sinAlimento);
    }

    /*
     * Complejidad temporal del servicio 3: O(K)
     * Donde K es la cantidad total de paquetes que caen dentro del rango solicitado.
     * Justificación: Como la urgencia está acotada entre 1 y 100, iterar el rango de índices
     * toma un máximo de 100 operaciones (O(1)). El tiempo lo determina únicamente la acción
     * de agregar los elementos encontrados a la lista de resultado (O(K)).
     */
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();

        // para no pasarnos del limite del arreglo
        int min = Math.max(1, urgenciaMinima);
        int max = Math.min(100, urgenciaMaxima);

        for (int i = min; i <= max; i++) {
            resultado.addAll(this.urgencias[i]);
        }

        return resultado;
    }


    public int getCamionesTotales() {
        return camionesTotales;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public int getPaquetesTotales() {
        return paquetesTotales;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }
}
