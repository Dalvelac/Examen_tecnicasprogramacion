public class CeldaHash<E,K> {
    private E dato;
    private K clave;
    private boolean borrado;
    public CeldaHash(E dato, K clave) {
        this.dato = dato;
        this.clave = clave;
        this.borrado = false;
    }

    /**
     * Dato de la CeldaHash
     * @return El dato que contiene
    */ 
    public E getDato() { 
        return dato;
    }

    /**
     * Clave de la CeldaHash
     * @return La clave que contiene
    */
    public K getClave() { 
        return clave;
    }

     /**
     * Indicador de borrado de la celda
     * @return el indicador de borrado
    */
    public boolean getBorrado() { 
        return borrado;
    }

     /**
     * marca la celda como borrada
     * @return el indicador de borrado
    */
    public void setBorrado() { 
        borrado= true;
    }

}