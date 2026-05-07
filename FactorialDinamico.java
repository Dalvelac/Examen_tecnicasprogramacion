public class FactorialDinamico {

    public static long factorialDin(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no esta definido para numeros negativos");
        }

        long[] tabla = new long[n + 1];
        tabla[0] = 1;

        if (n >= 1) {
            tabla[1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            tabla[i] = i * tabla[i - 1];
        }

        return tabla[n];
    }
}
