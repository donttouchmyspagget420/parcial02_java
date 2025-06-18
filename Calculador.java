
import java.util.Scanner;
import java.util.Stack;

/*
Author: Amangeldiuly Madi
 */
public class Calculador {

    final static String OPERACIONES = "+-*/";

    public static String Bucle(Scanner sc, String resp) {
        do {
            System.out.println("¿Querés hacerlo una vez más?(s/n)");
            resp = sc.nextLine().trim();
        } while (!resp.equalsIgnoreCase("s") && !resp.equalsIgnoreCase("n"));
        return resp;
    }

    static boolean Chequear_format(Stack<Double> st, String calc) {
        int cant_operaciones = 0;
        boolean válido;
        String num = "";
        if (calc.length() < 3) {
            return false;
        }
        for (int i = 0; i < calc.length(); i++) {
            char ch = calc.charAt(i);
            if (OPERACIONES.indexOf(ch) >= 0) {
                if (!num.isEmpty()) {
                    st.push((Double.valueOf(num)));
                    num = "";
                }
                cant_operaciones++;
            } else if (Character.isDigit(ch) || ch == '.') {
                num += ch;
            } else {
                break;
            }

        }
        if (!num.isEmpty()) {
            st.push((Double.valueOf(num)));
        }
        válido = cant_operaciones == 1 && st.size() == 2;
        return válido;
    }

    static char Cambiar_operación(char operación, String calc) {
        for (int i = 0; i < calc.length(); i++) {
            if (OPERACIONES.indexOf(calc.charAt(i)) >= 0) {
                operación = calc.charAt(i);
            }
        }
        return operación;
    }

    static Double Calcular(char operación, Stack<Double> st) {
        Double total;
        switch (operación) {
            case '+' ->
                total = st.firstElement() + st.lastElement();
            case '-' ->
                total = st.firstElement() - st.lastElement();
            case '*' ->
                total = st.firstElement() * st.lastElement();
            case '/' ->
                total = st.firstElement() / st.lastElement();
            default ->
                throw new AssertionError();
        }
        return total;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String resp = null;
            do {
                String calc;
                char operación = 0;
                Stack<Double> st = new Stack<>();

                System.out.print("Ingrese una expresión(p.e: 2+3): ");

                calc = (sc.nextLine().trim());

                if (Chequear_format(st, calc)) {
                    operación = Cambiar_operación(operación, calc);
                    if (st.lastElement() == 0 && operación == '/') {
                        System.out.println("NO SE PUEDE DIVIDIR A ZERO");
                        continue;
                    }
                    System.out.printf("El resultado es: %.3f", Calcular(operación, st));
                    System.out.println();
                } else {
                    System.out.println("Expresión inválida");
                }

                resp = Bucle(sc, resp);
            } while (resp.equalsIgnoreCase("s"));
        }
    }
}
