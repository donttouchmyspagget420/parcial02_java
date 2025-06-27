
import java.util.HashSet;
import java.util.Scanner;


/*
Author: Amangeldiuly Madi
 */
// DISCLAIMER: Todos las adivinanzas fueron generadas con IA
public class Adivinanza {

    static int Index(HashSet<Integer> set, int longtitud) {
        int index;
        do {
            index = (int) (Math.random() * longtitud);
        } while (set.contains(index));
        return index;
    }

    static boolean Set_check(Scanner sc, HashSet set, int longtitud) {
        if (set.size() == longtitud) {
            String resp = null;
            System.out.println("se quedó sin adivinanzas.");
            resp = Calculador.Bucle(sc, resp);
            if (resp.equalsIgnoreCase("s")) {
                set.clear();
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int correctas = 0, incorrectas = 0, longtitud = Content.ADIVINANZAS.length;
            String jugar = "";
            HashSet<Integer> ya_fue = new HashSet<>();
            System.out.println("**************BIENVENIDA A LA*******************");
            System.out.println("**************JUEGO DE ADIVINANZA***************");
            do {
                if (correctas + incorrectas == longtitud) {
                    correctas = 0;
                    incorrectas = 0;
                }
                int index = Index(ya_fue, longtitud);
                String respuesta;

                System.out.println(Content.ADIVINANZAS[index]);
                System.out.print("RESPUESTA: ");

                respuesta = sc.nextLine().trim();

                if (respuesta.equalsIgnoreCase(Content.RESPUESTAS[index])) {
                    correctas++;
                    System.out.println("¡Correcto!");
                } else {
                    incorrectas++;
                    System.out.println("¡Incorrecto!");
                    System.out.println("La respuesta correcto es: " + Content.RESPUESTAS[index]);
                }
                ya_fue.add(index);

                System.out.println("Cantidad de respuestas correctas: " + correctas);
                System.out.println("Cantidad de respuestas incorrectas: " + incorrectas);

                if (ya_fue.size() < longtitud) {
                    jugar = Calculador.Bucle(sc, jugar);
                } else if (Set_check(sc, ya_fue, longtitud)) {
                    break;
                }
            } while (jugar.equalsIgnoreCase("s"));
        }
    }
}
