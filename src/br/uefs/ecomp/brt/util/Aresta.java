package br.uefs.ecomp.brt.util;


/**
 * Classe Aresta, representa o caminho/a ligação entre vértices no grafo e a rota
 * real entre transbordos.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class Aresta {
        
    private final Vertice origem;
    private final Vertice destino;
    private double peso;

    /** Construtor da classe, os campos origem, destino e peso da aresta são inicializados.
     * 
     * @param origem
     * @param destino
     * @param peso
     */
    public Aresta(Vertice origem, Vertice destino, double peso){
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    /** Método que retorna o vértice origem da aresta.
     *
     * @return origem Vertice
     */
    public Vertice getOrigem() {
        return origem;
    }

    /** Método que retorna o vértice destino da aresta.
     *
     * @return destino Vertice
     */
    public Vertice getDestino() {
        return destino;
    }

    /** Método que retorna o peso da aresta.
     *
     * @return peso double
     */
    public double getPeso() {
        return peso;
    }

    /** Método que altera o peso da aresta.
     *
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /** Método que compara duas arestas, critério: os dois vértices de origem e de
     * destino devem ser iguais, somente nesta ordem, pois a aresta tem um sentido.
     *
     * @param o
     * @return boolean (true ou false)
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Aresta) {
            return ((origem.equals(((Aresta) o).getOrigem())) && (destino.equals(((Aresta) o).getDestino())));
        }
        return false;
    }
}