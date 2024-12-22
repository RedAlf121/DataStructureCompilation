import empresa.Empresa;
import trabajadores.Trabajador;

public class App {
    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa("Stonks", "./registro.dat");
        Trabajador pepito = new Trabajador("Pepito", "21654654", "asdasd", 1265);
        empresa.add(pepito);
        Trabajador trab = new Trabajador("Pedrito", "2132654", "asdasd", 1265);
        empresa.add(trab);
        empresa.readAll();
        Trabajador josefa = new Trabajador("Josefina", "2165465122", "asdasd", 1265);
        empresa.add(josefa);
        empresa.add(new Trabajador("Jacinto", "324324234", "asdasd", 1265));
        empresa.delete(trab);
        empresa.deleteLast();        
        empresa.readAll();
        empresa.update(pepito, new Trabajador("Pepitin", "2132654", "asdasd", 1265));
        empresa.update(josefa, new Trabajador("Josefa", "2165465122", "asdasd", 1265));
        empresa.readAll();
    }
}
