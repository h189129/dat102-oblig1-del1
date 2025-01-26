package no.hvl.data102.filmarkiv.impl;

public class LinearNode<T> {
    
    private T element;                 // Data som lagres i noden
    private LinearNode<T> neste;       // Pekeren til neste node i kjeden
    
    /**
     * Standardkonstruktør som initialiserer 
     * element og neste til null.
     */
    public LinearNode() {
        this.element = null;
        this.neste = null;
    }
    
    /**
     * Konstruktør som tar inn et element.
     * Neste-peker blir satt til null.
     *
     * @param elem Elementet som noden skal holde på
     */
    public LinearNode(T elem) {
        this.element = elem;
        this.neste = null;
    }
    
    /**
     * Henter ut elementet som ligger i noden.
     *
     * @return Elementet av typen T
     */
    public T getElement() {
        return this.element;
    }
    
    /**
     * Setter nytt element i noden.
     *
     * @param elem Elementet av typen T
     */
    public void setElement(T elem) {
        this.element = elem;
    }
    
    /**
     * Henter noden som kommer etter denne.
     *
     * @return Neste node
     */
    public LinearNode<T> getNeste() {
        return this.neste;
    }
    
    /**
     * Oppdaterer pekeren til neste node i kjeden.
     *
     * @param node En referanse til en annen LinearNode
     */
    public void setNeste(LinearNode<T> node) {
        this.neste = node;
    }
}
