package br.uefs.ecomp.brt.controller;


import br.uefs.ecomp.brt.model.BRT;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;

/**
 * Classe Controller, controlador do sistema BRT.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class Controller {
    
    private final BRT brt;
    
    /** Construtor da classe, um atributo do tipo BRT é instanciado.
     */
    public Controller(){
        brt = new BRT();
    }
    
    /** Chamada que retorna o brt.
     * 
     * @return brt BRT
     */
    public BRT getBRT() {
       return brt;
    }
    
    /** Chamada que importa o arquivo desejado.
     * 
     * @param file
     * @throws java.io.IOException
     */
    public void importarArquivo(File file) throws IOException {
        brt.importarArquivo(file);
    }
    
    /** Chamada que retorna as possíveis rotas.
     * 
     * @param origem
     * @param destino
     * @return Iterator
     */
    public Iterator IdentificarPossiveisRotas(String origem, String destino) {
        return (brt.IdentificarPossiveisRotas(origem, destino));
    }
    
    /** Método que retorna a rota de menor caminho.
     * 
     * @param origem
     * @param destino
     * @return List
     */
    public List calcularRotaMenorCaminho(int origem, int destino) { 
        return (brt.calcularRotaMenorCaminho(origem, destino));
    }
    
    /** Chamada que retorna a distância da rota de menor caminho.
     * 
     * @param origem
     * @param destino
     * @return double
     */
    public double calcularDistanciaMenorCaminho(int origem, int destino) {
        return (brt.calcularDistanciaMenorCaminho(origem, destino));
    }
}