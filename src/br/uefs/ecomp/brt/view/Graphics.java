package br.uefs.ecomp.brt.view;


import javax.swing.JPanel;

import br.uefs.ecomp.brt.controller.Controller;
import br.uefs.ecomp.brt.util.Aresta;
import br.uefs.ecomp.brt.util.Vertice;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import java.util.Iterator;

/**
 * Classe Graphics, responsável pelo representação gráfica do grafo no programa.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Graphics extends JPanel {
   
    private final Controller controller;
    
    public Graphics(Controller controller){
        this.controller = controller;
    }
    
    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double teta = 0, angulo = (6.28/(controller.getBRT().getGrafo().obterTamanho()));
        Iterator<Vertice> it = controller.getBRT().getGrafo().listarVertices();        
        Vertice maiorG = controller.getBRT().getGrafo().maiorGrau();
           
        while (it.hasNext()) {
            Vertice v = it.next();
            
            //Localização central para vértice de maior grau:
            if (v.equals(maiorG)) {
                v.setX(300);
                v.setY(300);
            } else {
                teta += angulo;           
                v.setX((int) (300 + Math.sin(teta) * 300));
                v.setY((int) (300 + Math.cos(teta) * 300));
            }
        }
        it = controller.getBRT().getGrafo().listarVertices();
        
        while (it.hasNext()) {
            Vertice v = it.next();
            Iterator<Aresta> it2 = controller.getBRT().getGrafo().listarArestas();
            
            while (it2.hasNext()) {
                g2.setColor(Color.BLACK);
                Aresta a = it2.next();
                g2.drawLine(a.getOrigem().getX() + 20, a.getOrigem().getY() + 20,
                        a.getDestino().getX() + 20, a.getDestino().getY() + 20);
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(a.getPeso()), Math.abs((a.getOrigem().getX() + a.getDestino().getX()) / 2) + 20,
                              Math.abs((a.getOrigem().getY() + a.getDestino().getY()) / 2) + 20);
            }
            g2.setColor(Color.GRAY);
            g2.fill(new Ellipse2D.Double(v.getX(), v.getY(), 40, 40));
            g2.setColor(Color.BLACK);
            g2.drawString(v.toString(), v.getX() + 17, v.getY() + 22);
        }
    }
}