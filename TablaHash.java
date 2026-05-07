/**
 * Plantilla de la tabla Hash, incluyendo las celdas y la tabla que las contiene
 */

public class TablaHash<E,K> {

    /**
     * Insertar un elemento con su clave en la tabla hash. Si ya existe un elemento
     * con dicha clave no se inserta y devuelve false. Si la tabla supera el
     * factor de carga límite, se amplía la tabla y se hace un rehash.
    * @param dato El dato que se quiere guardar
    * @param clave La clave del dato a guardar.
    * @return true si se ha insertado correctamente y false en caso contrario.
    */ public boolean insertar(E dato, K clave){

        if (dato==null || clave==null)
            return false;
        
        int hash = fHash(clave);
        int pos= hash;
        int posInicio = pos;                            // Necesito saber donde he empezado para poder terminar si está llena
        int colision= 0;
        while(tabla[pos]!=null) {

            if (tabla[pos].getBorrado()) {
                System.out.println("Insertando un objeto en la posición " + pos + " que estaba borrada");
                tabla[pos] = new CeldaHash<E,K>(dato, clave);
                return true;
            }

            if (tabla[pos].getClave().equals(clave))    // El objeto ya estaba en la tabla
                return false;           

            colision++;
            pos = (hash + colision)%tabla.length;
            if (pos==posInicio)                         // He vuelto al punto de partida: no puedo almacenar el objeto
                return false;
        }

        tabla[pos] = new CeldaHash<E,K>(dato, clave);
        System.out.println("Insertando un objeto en la posición " + pos);

        return true;
    }
    /**
     * Busca el dato que corresponde con la clave dada
     * @param clave La clave del dato que se quiere.
    * @return El elemento de la tabla que tiene la clave buscada. Si no existe * devuelve null.
    */ public E buscar(K clave){

        if (clave==null)
            return null;
    
        int hash = fHash(clave);
        int pos = hash;
        int posInicio = pos;
        int colision= 0;

        while (tabla[pos]!=null) {
            if (!tabla[pos].getBorrado())
                if (tabla[pos].getClave().equals(clave)) {
                    @SuppressWarnings("unchecked")
                    E dato = (E) tabla[pos].getDato();
                    return dato;
                }

            colision++;
            pos = (hash + colision)%tabla.length;
            if (pos==posInicio)                         // He vuelto al punto de partida: no puedo almacenar el objeto
                return null;

        }

        return null;
 
    }


    /**
     * Elimina de la tabla el dato que corresponde con la clave.
     * @param clave La clave del dato que se desea eliminar.
    * @return true si el elemento se encontró y borró y false en caso contrario.
    */
    public boolean borrar(K clave){ 

        if (clave==null)
            return false;

        int hash = fHash(clave);
        int pos = hash;
        int posInicio = pos;
        int colision= 0;

        while (tabla[pos]!=null) {
            if (!tabla[pos].getBorrado())
                if (tabla[pos].getClave().equals(clave)) {
                    System.out.println("Marcando como borrada la posición " + pos);
                    tabla[pos].setBorrado();
                    return true;
                }

            colision++;
            pos = (hash + colision)%tabla.length;
            if (pos==posInicio)                         // He vuelto al punto de partida: no puedo borrar el objeto
                return false;

        }

        return false;                                   // El objeto no estaba
    }


    private CeldaHash[] tabla;

    public TablaHash() {
        int numElem = 23;
        tabla = new CeldaHash[numElem]; 
        System.out.println("Construyendo una tabla hash de " + numElem + " elementos");
    }

    public TablaHash(int numElem) {
        tabla = new CeldaHash[numElem];
        System.out.println("Construyendo una tabla hash de " + numElem + " elementos");
    }



    private int fHash(int clave) {
        return clave%tabla.length;
    }

    private int fHash(K clave) {
        return fHash(clave.toString());
    }

     private int fHash(String clave) {
        
        int suma = 0;
        for (char c: clave.toCharArray()) {
            suma += c;
        }

        return suma%tabla.length;
    }



}







