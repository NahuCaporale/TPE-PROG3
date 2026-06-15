import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class utils {
    public List<List<String>> reader(String path) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                List<String> lineData = Arrays.asList(values);
                data.add(lineData);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }


    public int getTotal(List<List<String>> data) {
        List<String> first = data.getFirst();
        data.removeFirst();
        return Integer.parseInt(first.getFirst());
    }

    public List<Camion> getCamiones(List<List<String>> data) {
        List<Camion> camiones = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<String> row = data.get(i);
            Camion camion = new Camion();
            camion.setId(Integer.parseInt(row.getFirst()));
            camion.setPatente(row.get(1));
            camion.setRefrigerado(row.get(2).equals("1"));//si viene 1 es true osea refrigerado, sino es 0 osea falso que es refirgerado
            camion.setCapacidad(Integer.parseInt(row.get(3)));
            camiones.add(camion);
        }
        return camiones;
    }

    public List<Paquete> getPaquetes(List<List<String>> data) {
        List<Paquete> paquetes = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<String> row = data.get(i);
            Paquete paquete = new Paquete();
            paquete.setId(Integer.parseInt(row.getFirst()));
            paquete.setCodigo(row.get(1));
            paquete.setPeso(Integer.parseInt(row.get(2)));//si viene 1 es true osea refrigerado, sino es 0 osea falso que es refirgerado
            paquete.setContieneAlimento(row.get(3).equals("1"));
            paquete.setUrgencia(Integer.parseInt(row.get(4)));
            paquetes.add(paquete);
        }
        return paquetes;
    }
}
