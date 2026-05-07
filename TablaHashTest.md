# Justificacion de las pruebas de TablaHash

## Objetivo

El objetivo de las pruebas es comprobar el comportamiento principal de la clase `TablaHash` cuando almacena objetos de tipo `Persona` usando el campo `DNI` como clave. Se prueban las operaciones pedidas en la practica: `insertar`, `buscar` y `borrar`.

Las pruebas se han planteado con JUnit 4 y comprueban tanto casos normales como casos limite. En la clase `Persona` no existe un metodo `equals`, por lo que en los tests se usa `assertSame` para verificar que la tabla devuelve exactamente el mismo objeto que se habia insertado.

## Criterios utilizados

Se han seleccionado pruebas de caja negra basadas en la especificacion de los metodos:

- Casos correctos, donde la operacion debe realizarse con exito.
- Casos incorrectos, donde la operacion debe fallar sin modificar la tabla.
- Casos limite con valores `null`.
- Un caso de reutilizacion de una celda marcada como borrada, ya que la implementacion usa borrado logico.

## Pruebas de insertar

### Insertar una persona correctamente

Se crea una tabla vacia, se inserta una `Persona` usando su `DNI` como clave y se espera que `insertar` devuelva `true`. Despues se busca el mismo DNI para comprobar que el objeto ha quedado almacenado en la tabla.

Resultado esperado: la insercion se realiza correctamente y la busqueda devuelve la persona insertada.

### No insertar una clave duplicada

Se inserta una persona y despues se intenta insertar otra persona distinta con el mismo DNI. La clave ya existe en la tabla, por lo que la segunda insercion debe devolver `false`.

Resultado esperado: no se inserta la segunda persona y la tabla conserva la persona original asociada a ese DNI.

### Insertar con dato null

Se intenta insertar un dato `null` con una clave valida. La implementacion indica que si el dato es `null`, el metodo debe devolver `false`.

Resultado esperado: no se inserta ningun elemento y una busqueda posterior de esa clave devuelve `null`.

### Insertar con clave null

Se intenta insertar una `Persona` valida, pero usando una clave `null` en lugar del DNI. Una tabla hash necesita una clave valida para calcular la posicion del elemento.

Resultado esperado: `insertar` devuelve `false` y la persona no queda almacenada en la tabla.

### Reutilizar una celda marcada como borrada

Se usa una tabla de tamano 1 para forzar que la unica posicion disponible sea reutilizada. Primero se inserta una persona, despues se borra y finalmente se inserta otra persona con otro DNI. Como el borrado es logico, la celda queda marcada como borrada y puede usarse de nuevo.

Resultado esperado: la segunda insercion devuelve `true`, la persona borrada ya no se encuentra y la nueva persona si puede buscarse por su DNI.

## Pruebas de buscar

### Buscar una persona existente

Se inserta una persona y se busca por su DNI.

Resultado esperado: el metodo `buscar` devuelve la misma instancia de `Persona` que fue insertada.

### Buscar una persona inexistente

Se busca un DNI que no ha sido insertado en la tabla.

Resultado esperado: el metodo devuelve `null`, indicando que no existe ningun dato asociado a esa clave.

### Buscar con clave null

Se llama a `buscar` pasando `null` como clave.

Resultado esperado: el metodo devuelve `null`, ya que una clave nula no puede indexar ningun elemento.

## Pruebas de borrar

### Borrar una persona existente

Se inserta una persona y despues se borra usando su DNI.

Resultado esperado: `borrar` devuelve `true` y una busqueda posterior del mismo DNI devuelve `null`.

### Borrar una persona inexistente

Se intenta borrar un DNI que no existe en la tabla.

Resultado esperado: `borrar` devuelve `false` y los elementos ya insertados permanecen disponibles.

### Borrar con clave null

Se llama a `borrar` pasando `null` como clave.

Resultado esperado: el metodo devuelve `false`, porque no se puede eliminar un elemento sin una clave valida.

## Resumen

Estas pruebas cubren las operaciones principales de la tabla hash en situaciones habituales y en condiciones limite. Tambien verifican que la tabla no acepte entradas invalidas y que el borrado logico permita reutilizar espacio posteriormente.
