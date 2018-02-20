package br.uefs.ecomp.brt.util;


import br.uefs.ecomp.brt.model.exception.DadoInexistenteException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Camille Jesus e Rodrigo Santos
 */
public interface IGrafo {
    
    public boolean estaVazio();
    
    public int obterTamanho();
    
    public Vertice inserirVertice(String nome) ;
    
    public void removerVertice(String nome)throws DadoInexistenteException;
    
    public Vertice buscarVertice(String nome)throws DadoInexistenteException;
    
    public Iterator listarVertices();
    
    public void inserirAresta(Vertice origem, Vertice destino, double peso)throws DadoInexistenteException;
    
    public void removerAresta(Vertice origem, Vertice destino)throws DadoInexistenteException;
    
    public Aresta buscarAresta(Vertice origem, Vertice destino)throws DadoInexistenteException;
    
    public Iterator listarArestas();
    
    public void modificarPesoAresta(Vertice origem, Vertice destino, double peso)throws DadoInexistenteException;
    
    public double menorPeso(int origem, int destino);
    
    public List menorCaminho(int origem, int destino, boolean menor);
        
    public Iterator possiveisRotas(String origem, String destino, AuxGrafo aux);
}