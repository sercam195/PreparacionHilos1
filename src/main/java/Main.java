import java.util.ArrayList;
import java.util.Random;

public class Main {


    static ArrayList<Hilo> listaHilosOrdenada = new ArrayList<>();


    public static void main(String[] args) {

        ArrayList<Hilo> listaHilos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Hilo h = new Hilo(i);
            h.start();
            listaHilos.add(h);
        }

        for (Hilo h : listaHilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mostraListado();
    }

    private static void mostraListado() {
        for (Hilo hilo : listaHilosOrdenada) {
            System.out.println("El hilo " + hilo.numero + " ha finalizado en el milisegundo " + hilo.tiempoFinalizacion);
        }
    }

}

class Hilo extends Thread {

    Random r = new Random();

    public final int numero;

    public long tiempoFinalizacion;


    public Hilo(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        long comienzo = System.currentTimeMillis();
        try {
            Thread.sleep(r.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tiempoFinalizacion = System.currentTimeMillis();
        Main.listaHilosOrdenada.add(this);
        long tiempoDormido = tiempoFinalizacion - comienzo;
        System.out.println("Soy el hilo nº " + numero + " y he dormido por " + tiempoDormido + " milisegundos");
    }


}