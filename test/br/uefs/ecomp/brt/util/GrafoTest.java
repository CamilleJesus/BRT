package br.uefs.ecomp.brt.util;


import br.uefs.ecomp.brt.model.exception.DadoInexistenteException;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Camille Jesus e Rodrigo Santos
 */
public class GrafoTest {
    
    private String s1, s2, s3, s4, s5, s6;
    private Grafo grafo;
    
    @Before
    public void setUp() throws Exception {
        s1 = "1"; s2 = "2"; s3 = "3"; s4 = "4"; s5 = "5"; s6 = "6";
        grafo = new Grafo();
    }
        
    @Test
    public void testEstaVazio() throws DadoInexistenteException {   //Também testa inserirVertice() e removerVertice().
        assertTrue(grafo.estaVazio());
        grafo.inserirVertice(s1);
        assertFalse(grafo.estaVazio());
        grafo.inserirVertice(s2);
        assertFalse(grafo.estaVazio());
        grafo.inserirVertice(s3);
        assertFalse(grafo.estaVazio());
        grafo.removerVertice(s3);
        assertFalse(grafo.estaVazio());
        grafo.removerVertice(s2);
        assertFalse(grafo.estaVazio());
        grafo.removerVertice(s1);
        assertTrue(grafo.estaVazio());
    }
    
    @Test
    public void testObterTamanho() throws DadoInexistenteException {
        assertEquals(0, grafo.obterTamanho());
        grafo.inserirVertice(s1);
        assertEquals(1, grafo.obterTamanho());
        grafo.inserirVertice(s2);
        assertEquals(2, grafo.obterTamanho());
        grafo.inserirVertice(s3);
        assertEquals(3, grafo.obterTamanho());
        grafo.removerVertice(s3);
        assertEquals(2, grafo.obterTamanho());
        grafo.removerVertice(s2);
        assertEquals(1, grafo.obterTamanho());
        grafo.removerVertice(s1);
        assertEquals(0, grafo.obterTamanho());
    }
    
    @Test
    public void testListarVertices() throws DadoInexistenteException {   //Também testa buscarVertice().
        grafo.inserirVertice(s1);
        grafo.inserirVertice(s2);
        grafo.inserirVertice(s3);
        grafo.inserirVertice(s4);
        grafo.inserirVertice(s5);
        grafo.inserirVertice(s6);
        
        Iterator it = grafo.listarVertices();
        assertTrue(it.hasNext());
        assertSame(grafo.buscarVertice(s1), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarVertice(s2), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarVertice(s3), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarVertice(s4), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarVertice(s5), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarVertice(s6), it.next());
        assertFalse(it.hasNext());
    }
    
    @Test
    public void testQuantArestas() throws DadoInexistenteException {   //Também testa inserirAresta() e removerAresta().
        Vertice v1 = grafo.inserirVertice(s1);
        Vertice v2 = grafo.inserirVertice(s2);
        Vertice v3 = grafo.inserirVertice(s3);
        Vertice v4 = grafo.inserirVertice(s4);
        Vertice v6 = grafo.inserirVertice(s6);
        
        assertEquals(0, grafo.quantAresta());
        grafo.inserirAresta(v1, v2, 3);
        assertEquals(1, grafo.quantAresta());
        grafo.inserirAresta(v1, v3, 7);
        assertEquals(2, grafo.quantAresta());
        grafo.inserirAresta(v1, v4, 3);
        assertEquals(3, grafo.quantAresta());
        grafo.inserirAresta(v2, v1, 3);
        assertEquals(4, grafo.quantAresta());
        grafo.inserirAresta(v2, v3, 2);
        assertEquals(5, grafo.quantAresta());
        grafo.inserirAresta(v2, v6, 9);
        assertEquals(6, grafo.quantAresta());
        grafo.inserirAresta(v3, v1, 7);
        assertEquals(7, grafo.quantAresta());
        grafo.inserirAresta(v3, v2, 2);
        assertEquals(8, grafo.quantAresta());
        grafo.inserirAresta(v3, v4, 1);
        assertEquals(9, grafo.quantAresta());
        
        grafo.removerVertice(s1);
        assertEquals(4, grafo.quantAresta());
        grafo.removerVertice(s4);
        assertEquals(3, grafo.quantAresta());
        grafo.removerAresta(v2, v3);
        assertEquals(2, grafo.quantAresta());
        grafo.removerAresta(v2, v6);
        assertEquals(1, grafo.quantAresta());
        grafo.removerAresta(v3, v2);
        assertEquals(0, grafo.quantAresta());
    }
    
    @Test
    public void testListarArestas() throws DadoInexistenteException {   //Também testa buscarAresta().
        Vertice v1 = grafo.inserirVertice(s1);
        Vertice v2 = grafo.inserirVertice(s2);
        Vertice v3 = grafo.inserirVertice(s3);
        Vertice v4 = grafo.inserirVertice(s4);
        Vertice v5 = grafo.inserirVertice(s5);
        Vertice v6 = grafo.inserirVertice(s6);        
        grafo.inserirAresta(v1, v2, 3);
        grafo.inserirAresta(v2, v1, 3);
        grafo.inserirAresta(v3, v1, 7);
        grafo.inserirAresta(v4, v1, 3);
        grafo.inserirAresta(v5, v3, 3);
        grafo.inserirAresta(v6, v2, 9);
        
        Iterator it = grafo.listarArestas();
        assertTrue(it.hasNext());
        assertSame(grafo.buscarAresta(v1, v2), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarAresta(v2, v1), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarAresta(v3, v1), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarAresta(v4, v1), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarAresta(v5, v3), it.next());
        assertTrue(it.hasNext());
        assertSame(grafo.buscarAresta(v6, v2), it.next());
        assertFalse(it.hasNext());
    }
    
    @Test
    public  void testModificarPesoAresta() throws DadoInexistenteException {
        Vertice v1 = grafo.inserirVertice(s1);
        Vertice v2 = grafo.inserirVertice(s2);
        Vertice v3 = grafo.inserirVertice(s3);        
        grafo.inserirAresta(v1, v2, 3);
        grafo.inserirAresta(v2, v3, 2);
        grafo.inserirAresta(v3, v1, 7);
        
        assertEquals(3, grafo.buscarAresta(v1, v2).getPeso(), 0.01);
        assertEquals(2, grafo.buscarAresta(v2, v3).getPeso(), 0.01);
        assertEquals(7, grafo.buscarAresta(v3, v1).getPeso(), 0.01);
        grafo.modificarPesoAresta(v1, v2, 7.2);
        grafo.modificarPesoAresta(v2, v3, 900);
        grafo.modificarPesoAresta(v3, v1, 2797.27);
        assertEquals(7.2, grafo.buscarAresta(v1, v2).getPeso(), 0.01);
        assertEquals(900, grafo.buscarAresta(v2, v3).getPeso(), 0.01);
        assertEquals(2797.27, grafo.buscarAresta(v3, v1).getPeso(), 0.01);
    }
        
    @Test
    public void testMenorCaminho() {
        Vertice v1 = grafo.inserirVertice(s1);
        Vertice v2 = grafo.inserirVertice(s2);
        Vertice v3 = grafo.inserirVertice(s3);
        Vertice v4 = grafo.inserirVertice(s4);
        Vertice v5 = grafo.inserirVertice(s5);
        Vertice v6 = grafo.inserirVertice(s6);
        grafo.inserirAresta(v1, v2, 3);
        grafo.inserirAresta(v1, v3, 7);
        grafo.inserirAresta(v1, v4, 3);
        grafo.inserirAresta(v2, v1, 3);
        grafo.inserirAresta(v2, v3, 2);
        grafo.inserirAresta(v2, v6, 9);
        grafo.inserirAresta(v3, v1, 7);
        grafo.inserirAresta(v3, v2, 2);
        grafo.inserirAresta(v3, v4, 1);
        grafo.inserirAresta(v4, v1, 3);
        grafo.inserirAresta(v4, v3, 1);
        grafo.inserirAresta(v4, v5, 5);
        grafo.inserirAresta(v5, v3, 3);
        grafo.inserirAresta(v5, v4, 5);
        grafo.inserirAresta(v5, v6, 3);
        grafo.inserirAresta(v6, v2, 9);
        grafo.inserirAresta(v6, v3, 6);
        grafo.inserirAresta(v6, v5, 3);
        
        assertEquals(3.0, grafo.menorPeso(1, 2), 0.001);
        assertEquals(2.0, grafo.menorPeso(2, 3), 0.001);
        assertEquals(1.0, grafo.menorPeso(3, 4), 0.001);
        assertEquals(5.0, grafo.menorPeso(4, 5), 0.001);   //v4-v3-v5 seria 4.0, mas o grafo é orientado.
        assertEquals(3.0, grafo.menorPeso(5, 6), 0.001);
        assertEquals(4.0, grafo.menorPeso(1, 3), 0.001);
        assertEquals(3.0, grafo.menorPeso(1, 4), 0.001);
        assertEquals(8.0, grafo.menorPeso(1, 5), 0.001);
        assertEquals(11.0, grafo.menorPeso(1, 6), 0.001);
        
        List l = grafo.menorCaminho(1, 3, false);        
        Iterator it = l.iterator();
        assertTrue(it.hasNext());
        assertSame(v3, it.next());
        assertTrue(it.hasNext());
        assertSame(v4, it.next());
        assertTrue(it.hasNext());
        assertSame(v1, it.next());
        assertFalse(it.hasNext());
    }
}