/*
 * Autores: Camille de L. Jesus e Rodrigo B. Santos
 * Componente Curricular: EXA863 - MI Programação
 * Data: 23/10/15
 * Declaro que este código foi elaborado por mim de forma individual e não contém
 * algum trecho de código de outro autor, tais como provindos de livros ou apostilas,
 * e páginas ou documentos eletrônicos da internet. Qualquer trecho de código de
 * outra autoria, que não minha, poderá estar destacado com uma citação para o
 * autor ou a fonte do código e estou ciente que estes trechos não serão considerados
 * para fins de avaliação.
 */

package br.uefs.ecomp.brt.model;


import br.uefs.ecomp.brt.util.AuxGrafo;
import br.uefs.ecomp.brt.util.Grafo;
import br.uefs.ecomp.brt.util.Vertice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe BRT, responsável pela criação de um sistema brt, faz todos os requisitos
 * do programa.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class BRT {
    
    private final Grafo grafo;
    private final AuxGrafo aux;
    
    /** Construtor da classe, um atributo do tipo Grafo e um AuxGrafo são instanciados.
     */
    public BRT() {
        grafo = new Grafo();
        aux = new AuxGrafo();
    }
    
    /** Método que retorna um grafo.
     * 
     * @return grafo Grafo
     */
    public Grafo getGrafo() {
        return grafo;
    }
    
    /** Método que retorna um auxGrafo.
     * 
     * @return aux AuxGrafo
     */
    public AuxGrafo getAuxGrafo() {
        return aux;
    }
    
    /** Método que importa o arquivo desejado.
     * 
     * @param file
     * @throws java.io.IOException
     */
    public void importarArquivo(File file) throws IOException {
        FileReader arquivo = new FileReader(file);
        BufferedReader ler = new BufferedReader(arquivo);
        String linha;
        
        while((linha = ler.readLine()) !=  null) {
            
            if (linha.trim().length() > 0) {
                StringTokenizer linha2 = new StringTokenizer(linha);
                String origem = linha2.nextToken();
                Vertice v1 = grafo.inserirVertice(origem);
                String destino = linha2.nextToken();
                Vertice v2 = grafo.inserirVertice(destino);
                String distancia = linha2.nextToken();
                grafo.inserirAresta(v1, v2, (Double.parseDouble(distancia) * 100));
                aux.adicionar(v1.getNome(), v2.getNome());
            }
        }
    }
    
    /** Método que retorna as possíveis rotas.
     * 
     * @param origem
     * @param destino
     * @return Iterator
     */
    public Iterator IdentificarPossiveisRotas(String origem, String destino) {
        return (grafo.possiveisRotas(origem, destino, aux));
    }
    
    /** Método que retorna a rota de menor caminho.
     * 
     * @param origem
     * @param destino
     * @return List
     */
    public List calcularRotaMenorCaminho(int origem, int destino) { 
        return (grafo.menorCaminho(origem, destino, false));
    }
    
    /** Método que retorna a distância da rota de menor caminho.
     * 
     * @param origem
     * @param destino
     * @return double
     */
    public double calcularDistanciaMenorCaminho(int origem, int destino) {
        return (grafo.menorPeso(origem, destino));
    }
}