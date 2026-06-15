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
    private HashMap<Integer, List<Paquete>> paquetesByUrgencia;

    /*
     * o(n) donde n es la cantidad de elementos que contienen los archivos .csv
     */
    public Servicios(String pathCamiones, String pathPaquetes) {
        utils utils = new utils();
        this.paqueteMap = new HashMap<>();
        this.conAlimento = new ArrayList<>();
        this.sinAlimento = new ArrayList<>();
        this.paquetesByUrgencia = new HashMap<>();
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
        for (Paquete p : paquetes) {
            paqueteMap.put(p.getCodigo(), p);
            if (this.paquetesByUrgencia.containsKey(p.getUrgencia())) {
                this.paquetesByUrgencia.get(p.getUrgencia()).add(p);
            } else {
                List<Paquete> paqueteList = new ArrayList<>();
                paqueteList.add(p);
                this.paquetesByUrgencia.put(p.getUrgencia(), paqueteList);
            }
            if (p.contieneAlimento) conAlimento.add(p);
            else {
                sinAlimento.add(p);
            }
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
     * o(1) ya que al utilizar un hashmap y tener dividos entre los que contienen o no alimentos,
     * nos evitamos el tener que iterar sobre las listas y que la complejidad se dispare a o(n) donde n sea la cantidad de paquetes
     * Dado un booleano que indica si se buscan paquetes que
    contienen alimentos (true) o que no contienen alimentos (false), retornar el
    listado de paquetes correspondiente.

     */
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return contieneAlimentos ? new ArrayList<>(conAlimento) : new ArrayList<>(sinAlimento);
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     * o(r + p) donde r es la cantidad a recorrer del rango especificado y p cantidad de paquetes encontrados en ese rango.
     * si el nivel de urgencia fuera mas amplio es decir mas alto que el 100 que tenemos como maximo, deberiamos usar un
     * arbol binario para guardar los datos, pero como es un rango acotado no nos conviene. porque si bien la complejidad que tenemos ahora
     * o(r + p) es un poco mas amplia. en el caso optimo podria ser o(p) dependiendo el nivel de urgencia que nos venga.
     * Dados dos valores enteros que representan un nivel de urgencia
    mínimo y máximo, retornar todos los paquetes cuyo nivel de urgencia se
    encuentre dentro de ese rango (inclusive).
     */
    public List<Paquete> servicio3(int urgenciaMinima, int
            urgenciaMaxima) {
        List<Paquete> paquetes = new ArrayList<>();
        if (urgenciaMinima <= 0 || urgenciaMaxima <= 0 || urgenciaMaxima > 100 || urgenciaMinima > urgenciaMaxima)
            return null;
        for (int i = urgenciaMinima; i <= urgenciaMaxima; i++) {
            if (this.paquetesByUrgencia.containsKey(i)) {
                paquetes.addAll(this.paquetesByUrgencia.get(i));
            }
        }
        return paquetes;
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
