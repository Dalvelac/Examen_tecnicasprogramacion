# Ejercicios 02, 03 y 04

## Ejercicio 02: anotaciones

### `@SuppressWarnings("deprecation")`

Esta anotacion se utiliza principalmente durante el desarrollo y la compilacion. Sirve para indicar al compilador que no muestre un aviso concreto, en este caso el aviso relacionado con el uso de elementos obsoletos o deprecados.

Significa que el programador sabe que esta usando una clase, metodo o atributo marcado como `@Deprecated`, pero decide ocultar ese aviso porque el uso es intencionado. Puede ser util cuando se mantiene codigo antiguo o cuando todavia no existe una alternativa inmediata.

Ejemplo:

```java
@SuppressWarnings("deprecation")
public void usarMetodoAntiguo() {
    objeto.metodoAntiguo();
}
```

### `@Deprecated`

Esta anotacion se utiliza durante el desarrollo, la compilacion y el mantenimiento del software. Indica que un elemento del programa, como una clase, metodo o atributo, esta obsoleto y no se recomienda seguir utilizandolo.

Sirve para avisar a otros programadores de que ese elemento puede desaparecer en versiones futuras o que existe una alternativa mejor. El compilador puede mostrar advertencias cuando se usa un elemento deprecado.

Ejemplo:

```java
@Deprecated
public void metodoAntiguo() {
    // Metodo mantenido solo por compatibilidad.
}
```


### `@Override`

Esta anotacion se usa durante el desarrollo y la compilacion. Indica que un metodo esta sobrescribiendo un metodo heredado de una superclase o definido en una interfaz.

Sirve para hacer el codigo mas claro y para que el compilador compruebe que realmente se esta sobrescribiendo un metodo existente. Si el nombre o los parametros no coinciden, el compilador dara error, lo que ayuda a detectar fallos.

Ejemplo:

```java
@Override
public String toString() {
    return "Persona";
}
```

### `@Test`

Esta anotacion se utiliza en la fase de pruebas. En JUnit, marca un metodo como metodo de prueba, de forma que el framework pueda ejecutarlo automaticamente.

Sirve para separar el codigo de prueba del codigo normal de la aplicacion y para comprobar si una parte del programa se comporta como se espera.

Ejemplo:

```java
@Test
public void insertar_debeGuardarPersona() {
    // Prueba automatizada.
}
```

## Ejercicio 03: algoritmo avid para elegir personas para el coche

Un algoritmo avid es una tecnica que toma en cada paso la decision que parece mejor en ese momento, sin volver atras. En este contexto, la decision mas conveniente es elegir primero a las personas por las que se tiene mayor nivel de carino, siempre que queden plazas libres en el coche.

La primera fase seria preparar la lista de candidatos, asociando a cada persona su nivel de carino. La lista seria: Esposa 10, Hijo 9, Hija 8, Padre 7, Sobrina 6, Sobrino 5 y Amigo 4.

La segunda fase seria ordenar los candidatos de mayor a menor nivel de carino. Asi, el algoritmo revisa primero a las personas mas valoradas y deja para el final a las de menor prioridad.

La tercera fase seria seleccionar personas. Se recorre la lista ordenada y, para cada persona, se comprueba si queda alguna plaza libre. Si queda plaza, esa persona entra en el coche. Si no queda plaza, ya no se puede anadir a nadie mas.

El algoritmo termina cuando se han ocupado las P plazas disponibles o cuando ya no quedan mas personas por revisar. Como todas las personas ocupan una plaza, no hace falta comparar tamanos ni pesos: basta con elegir las de mayor carino hasta llenar el coche.

Si P = 4, se elegirian las cuatro primeras personas de la lista ordenada: Esposa, Hijo, Hija y Padre. El nivel total de carino seria 10 + 9 + 8 + 7 = 34. Sobrina, Sobrino y Amigo quedarian fuera porque ya no habria plazas disponibles.

## Ejercicio 04: factorial dinamico

El codigo recursivo dado tiene un problema logico:

```java
return n * factorial(n + 1);
```

Para calcular el factorial deberia avanzar hacia el caso base reduciendo `n`, es decir, usando `factorial(n - 1)`. Al usar `n + 1`, cada llamada se aleja de los casos base `0` y `1`, por lo que la recursion no termina correctamente para valores mayores que 1.

Una implementacion correcta con programacion dinamica consiste en construir una tabla con los factoriales desde `0` hasta `n`. Primero se guardan los casos base y despues cada posicion se calcula usando la anterior:

```java
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
```

Con esta solucion, `factorialDin(0)` y `factorialDin(1)` devuelven `1`. Para valores mayores, la tabla evita la recursion y calcula el resultado de forma iterativa.
