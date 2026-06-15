import java.util.List;

public class Main {
    public static void main(String[] args) {
        Servicios s = new Servicios("src/files/camiones.csv", "src/files/paquetes.csv");

        System.out.println(s.servicio1("P001")); // deberia devolver el paquete con codigo P001
        System.out.println(s.servicio1("p")); // deberia devolver null

        System.out.println(s.servicio2(true)); // paquetes con alimento
        System.out.println(s.servicio2(false)); // paquetes sin alimento

        System.out.println(s.servicio3(2, 80)); // paquetes con urgencia entre 2 y 80
        System.out.println(s.servicio3(50, 100)); // paquetes con urgencia entre 50 y 100

        Backtracking bt = new Backtracking();
        Solucion sol = bt.getSolucion(s.getCamiones(), s.getPaquetes());
        System.out.println(sol);

        Greedy g = new Greedy();
        Solucion gSol = g.getSolucionGreedy(s.getCamiones(), s.getPaquetes());
        System.out.println(gSol);
    }


}
