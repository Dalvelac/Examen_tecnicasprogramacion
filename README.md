# Ejercicios 02, 03 y 04

## Ejercicio 02: anotaciones

### `@SuppressWarnings("deprecation")`

Esta anotación se utiliza principalmente durante el desarrollo y la compilación. Sirve para indicar al compilador que no muestre un aviso concreto; en este caso, el relacionado con el uso de elementos obsoletos.

Significa que el programador sabe que está usando una clase, método o atributo marcado como `@Deprecated`, pero decide ocultar ese aviso porque el uso es intencionado. Puede ser útil cuando se mantiene compatibilidad con versiones antiguas o cuando no es posible cambiar inmediatamente a una alternativa.

Ejemplo:

```java
@SuppressWarnings("deprecation")
public void usarMetodoAntiguo() {
    objeto.metodoAntiguo();
}
```

### `@Deprecated`

Esta anotación se utiliza durante el desarrollo, la compilación y el mantenimiento del software. Indica que un elemento del programa (como una clase, método o atributo) está obsoleto y no se recomienda su uso.

Sirve para avisar a otros programadores de que ese elemento puede desaparecer en versiones futuras o de que existe una alternativa mejor. El compilador puede mostrar advertencias cuando se usa un elemento marcado como `@Deprecated`.

Ejemplo:

```java
@Deprecated
public void metodoAntiguo() {
    // Método mantenido solo por compatibilidad.
}
```

### `@Override`

Esta anotación se usa durante el desarrollo y la compilación. Indica que un método está sobrescribiendo un método heredado de una superclase o definido en una interfaz.

Sirve para hacer el código más claro y para que el compilador compruebe que realmente se está sobrescribiendo un método existente. Si el nombre o los parámetros no coinciden, el compilador dará error, evitando fallos difíciles de detectar.

Ejemplo:

```java
@Override
public String toString() {
    return "Persona";
}
```

### `@Test`

Esta anotación se utiliza en la fase de pruebas. En JUnit, marca un método como método de prueba, de forma que el framework pueda ejecutarlo automáticamente.

Sirve para separar el código de prueba del código normal de la aplicación y para comprobar si una parte del programa se comporta como se espera.

Ejemplo:

```java
@Test
public void insertar_debeGuardarPersona() {
    // Prueba automatizada.
}
```

## Ejercicio 03: algoritmo ávido para elegir personas para el coche

Un algoritmo ávido es una técnica que, en cada paso, toma la decisión que parece mejor en ese momento, sin volver atrás. En este contexto, la decisión más conveniente es elegir primero a las personas por su nivel de cariño.

La primera fase sería preparar la lista de candidatos, asociando a cada persona su nivel de cariño. La lista sería: Esposa 10, Hijo 9, Hija 8, Padre 7, Sobrina 6, Sobrino 5 y Amigo 4.

La segunda fase sería ordenar los candidatos de mayor a menor nivel de cariño. Así, el algoritmo revisa primero a las personas más valoradas y deja para el final a las de menor prioridad.

La tercera fase sería seleccionar personas: se recorre la lista ordenada y, para cada persona, se comprueba si queda alguna plaza libre. Si queda plaza, esa persona entra en el coche. Si no queda plaza, se pasa a la siguiente.

El algoritmo termina cuando se han ocupado las P plazas disponibles o cuando ya no quedan más personas por revisar. Como todas las personas ocupan una plaza, no hace falta comparar tamaños ni pesos.

Si P = 4, se elegirían las cuatro primeras personas de la lista ordenada: Esposa, Hijo, Hija y Padre. El nivel total de cariño sería 10 + 9 + 8 + 7 = 34. Sobrina, Sobrino y Amigo quedarían fuera porque no hay plazas disponibles.

## Ejercicio 04: factorial dinámico

El código recursivo dado tiene un problema lógico:

```java
return n * factorial(n + 1);
```

Para calcular el factorial, debería avanzar hacia el caso base reduciendo `n`, es decir, usando `factorial(n - 1)`. Al usar `n + 1`, cada llamada se aleja de los casos base `0` y `1`, por lo que la recursión nunca termina.

Una implementación correcta con programación dinámica consiste en construir una tabla con los factoriales desde `0` hasta `n`. Primero se guardan los casos base y después cada posición se calcula usando la anterior.

```java
public static long factorialDin(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("El factorial no está definido para números negativos");
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
```

Con esta solución, `factorialDin(0)` y `factorialDin(1)` devuelven `1`. Para valores mayores, la tabla evita la recursión y calcula el resultado de forma iterativa.
