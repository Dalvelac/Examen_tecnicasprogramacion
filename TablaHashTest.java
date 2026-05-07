import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TablaHashTest {

    private Persona persona(String nombre, String apellidos, String dni) {
        return new Persona(nombre, apellidos, dni);
    }

    @Test
    public void insertar_debeGuardarPersonaCuandoDniNoExiste() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona persona = persona("Ana", "Garcia Lopez", "11111111A");

        boolean insertada = tabla.insertar(persona, persona.DNI);

        assertTrue(insertada);
        assertSame(persona, tabla.buscar(persona.DNI));
    }

    @Test
    public void insertar_noDebePermitirDniDuplicado() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona original = persona("Luis", "Perez Ruiz", "22222222B");
        Persona duplicada = persona("Marta", "Sanchez Diaz", "22222222B");

        assertTrue(tabla.insertar(original, original.DNI));
        boolean insertada = tabla.insertar(duplicada, duplicada.DNI);

        assertFalse(insertada);
        assertSame(original, tabla.buscar(original.DNI));
    }

    @Test
    public void buscar_debeDevolverPersonaExistentePorDni() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona persona = persona("Elena", "Martin Castro", "33333333C");
        tabla.insertar(persona, persona.DNI);

        Persona encontrada = tabla.buscar(persona.DNI);

        assertSame(persona, encontrada);
    }

    @Test
    public void buscar_debeDevolverNullCuandoDniNoExiste() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona persona = persona("Pablo", "Navarro Gil", "44444444D");
        tabla.insertar(persona, persona.DNI);

        Persona encontrada = tabla.buscar("99999999Z");

        assertNull(encontrada);
    }

    @Test
    public void borrar_debeEliminarPersonaExistentePorDni() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona persona = persona("Sara", "Ortega Mora", "55555555E");
        tabla.insertar(persona, persona.DNI);

        boolean borrada = tabla.borrar(persona.DNI);

        assertTrue(borrada);
        assertNull(tabla.buscar(persona.DNI));
    }

    @Test
    public void borrar_debeDevolverFalseCuandoDniNoExiste() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona persona = persona("Ivan", "Romero Vidal", "66666666F");
        tabla.insertar(persona, persona.DNI);

        boolean borrada = tabla.borrar("00000000X");

        assertFalse(borrada);
        assertSame(persona, tabla.buscar(persona.DNI));
    }

    @Test
    public void insertar_debeDevolverFalseCuandoDatoEsNull() {
        TablaHash<Persona, String> tabla = new TablaHash<>();

        boolean insertada = tabla.insertar(null, "77777777G");

        assertFalse(insertada);
        assertNull(tabla.buscar("77777777G"));
    }

    @Test
    public void insertar_debeDevolverFalseCuandoClaveEsNull() {
        TablaHash<Persona, String> tabla = new TablaHash<>();
        Persona persona = persona("Carmen", "Vega Ramos", "10101010K");

        boolean insertada = tabla.insertar(persona, null);

        assertFalse(insertada);
        assertNull(tabla.buscar(persona.DNI));
    }

    @Test
    public void buscar_debeDevolverNullCuandoClaveEsNull() {
        TablaHash<Persona, String> tabla = new TablaHash<>();

        Persona encontrada = tabla.buscar(null);

        assertNull(encontrada);
    }

    @Test
    public void borrar_debeDevolverFalseCuandoClaveEsNull() {
        TablaHash<Persona, String> tabla = new TablaHash<>();

        boolean borrada = tabla.borrar(null);

        assertFalse(borrada);
    }

    @Test
    public void insertar_debeReutilizarCeldaMarcadaComoBorrada() {
        TablaHash<Persona, String> tabla = new TablaHash<>(1);
        Persona primera = persona("Laura", "Santos Molina", "88888888H");
        Persona segunda = persona("Diego", "Blanco Torres", "99999999J");

        assertTrue(tabla.insertar(primera, primera.DNI));
        assertTrue(tabla.borrar(primera.DNI));

        boolean insertada = tabla.insertar(segunda, segunda.DNI);

        assertTrue(insertada);
        assertNull(tabla.buscar(primera.DNI));
        assertSame(segunda, tabla.buscar(segunda.DNI));
    }
}
