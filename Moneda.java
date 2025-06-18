
import java.util.Scanner;

/*
Author: Amangeldiuly Madi
 */
public class Moneda {

    final static Double USD = 1175.52, EUR = 1351.77, ARG = 1.0, YEN = 0.1235, ZWD = 0.273918;

    static void Monedas() {
        System.out.println("""
                            1.USD
                            2.EURO
                            3.Peso Argentino
                            4.YENE de JAPÓN
                            5.DÓLAR de ZIMBABWE
                            """);
    }

    static Double Eligo(int moneda, Double dinero) {
        switch (moneda) {
            case 1 -> {
                dinero = USD * dinero;
            }
            case 2 -> {
                dinero = EUR * dinero;
            }
            case 3 -> {
                dinero = ARG * dinero;
            }
            case 4 -> {
                dinero = YEN * dinero;
            }
            case 5 -> {
                dinero = ZWD * dinero;
            }
            default ->
                throw new AssertionError();
        }
        return dinero;
    }

    static Double Cambiar(int moneda, Double dinero) {
        switch (moneda) {
            case 1 -> {
                dinero = dinero / USD;
            }
            case 2 -> {
                dinero = dinero / EUR;
            }
            case 3 -> {
                dinero = dinero / ARG;
            }
            case 4 -> {
                dinero = dinero / YEN;
            }
            case 5 -> {
                dinero = dinero / ZWD;
            }
            default ->
                throw new AssertionError();
        }
        return dinero;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("************BIENVENIDA************");
            String resp = null;
            do {
                int moneda;
                Double dinero_actual, dinero_final;

                System.out.println("Elige la moneda que querés cambiar");
                Monedas();

                try {
                    moneda = (Integer.parseInt(sc.nextLine()));
                    if (moneda <= 0 || moneda >= 6) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("NO ES CORRECTO OPCIÓN");
                    continue;
                }

                System.out.println("Cantidad de dinero");

                try {
                    dinero_actual = (Double.valueOf(sc.nextLine()));
                    if (dinero_actual <= 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("NO ES CORRECTO OPCIÓN");
                    continue;
                }
                dinero_actual = Eligo(moneda, dinero_actual);

                System.out.println("Elige a que moneda querés cambiar");
                Monedas();

                do {
                    try {
                        moneda = (Integer.parseInt(sc.nextLine()));
                        if (moneda <= 0 || moneda >= 6) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("NO ES CORRECTO OPCIÓN");
                    }
                } while (moneda <= 0 || moneda >= 6);

                dinero_final = Cambiar(moneda, dinero_actual);
                System.out.printf("el resultado %.2f", dinero_final);
                System.out.println("");

                resp = Calculador.Bucle(sc, resp);

            } while (resp.equalsIgnoreCase("s"));
        }
    }
}
