package br.uefs.ecomp.brt.view;


import br.uefs.ecomp.brt.controller.Controller;
import br.uefs.ecomp.brt.model.exception.DadoInexistenteException;
import br.uefs.ecomp.brt.util.Vertice;

import java.awt.BorderLayout;
import static java.awt.Color.WHITE;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe (Java Principal) Interface, responsável pela única tela de interface
 * gráfica do programa BRT.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Interface {
    
    private static JFrame janela;
    private Container painelPrincipal;
    private JMenuBar barra;
    private JMenu arquivo, funcoes, desenho, ajuda;
    private JMenuItem carregar, adicionarV, removerV, adicionarA, removerA, modificar,
                      possiveis, menor, limpar, desenhar, sobre, sair;
    private Controller controller;
    private File file;
    private final JList lista = new JList();
    private final DefaultListModel model = new DefaultListModel();
    private final JScrollPane scroll = new JScrollPane(lista);

    /** Método inicial que chama o método programa.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        new Interface().programa();
    }

    /** Método que chama todos os outros métodos sequencialmente.
     */
    public void programa() {
       this.inicializa();
       this.preparaJanela();       
       this.preparaArquivo();
       this.preparaAdicionarV();
       this.preparaAdicionarA();
       this.preparaRemoverV();
       this.preparaRemoverA();
       this.preparaModificar();
       this.preparaPossiveisCaminhos();
       this.preparaMenorCaminho();
       this.preparaLimpar();
       this.preparaDesenho();
       this.preparaSobre();
       this.preparaSair();
    }
    
    /** Método que inicializa os atributos da tela principal, de login e cadastro.
     */
    private void inicializa() {
        janela = new JFrame("BRT©");
        painelPrincipal = new JPanel(new BorderLayout());
        barra = new JMenuBar();
        arquivo = new JMenu("Arquivo");
        funcoes = new JMenu("Funções");
        desenho = new JMenu("Desenho");
        ajuda = new JMenu("Ajuda");
        carregar = new JMenuItem("Carregar");
        adicionarV = new JMenuItem("Adicionar Vértice");
        removerV = new JMenuItem("Remover Vértice");
        adicionarA = new JMenuItem("Adicionar Aresta");
        removerA = new JMenuItem("Remover Aresta");
        modificar = new JMenuItem("Modificar Aresta");
        possiveis = new JMenuItem("Caminhos Possíveis");
        menor = new JMenuItem("Menor Caminho");
        desenhar = new JMenuItem("Desenhar Grafo");
        sobre = new JMenuItem("Sobre");
        sair = new JMenuItem("Sair");
        limpar = new JMenuItem("Limpar Texto");
        controller = new Controller();
    }
    
    /** Método que prepara a janela inicial, posicionando os objetos previamente
     * inicializados na tela.
     */
    private void preparaJanela() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(1000, 700);
        janela.setLocationRelativeTo(null);        
        painelPrincipal.add(scroll, BorderLayout.EAST);
        painelPrincipal.setBackground(WHITE);
        janela.setContentPane(painelPrincipal);
        janela.setVisible(true);
        barra.setBackground(WHITE.brighter());
        painelPrincipal.add(barra, BorderLayout.NORTH);
        arquivo.setBackground(WHITE.brighter());
        barra.add(arquivo);
        carregar.setBackground(WHITE.brighter());
        arquivo.add(carregar);
        funcoes.setBackground(WHITE.brighter());
        barra.add(funcoes);
        adicionarV.setBackground(WHITE.brighter());
        funcoes.add(adicionarV);
        adicionarA.setBackground(WHITE.brighter());
        funcoes.add(adicionarA);
        funcoes.addSeparator();
        removerV.setBackground(WHITE.brighter());
        funcoes.add(removerV);
        removerA.setBackground(WHITE.brighter());
        funcoes.add(removerA);
        funcoes.addSeparator();
        modificar.setBackground(WHITE.brighter());
        funcoes.add(modificar);
        funcoes.addSeparator();
        possiveis.setBackground(WHITE.brighter());
        funcoes.add(possiveis);
        menor.setBackground(WHITE.brighter());
        funcoes.add(menor);
        limpar.setBackground(WHITE.brighter());
        funcoes.add(limpar);
        desenho.setBackground(WHITE.brighter());
        barra.add(desenho);
        desenhar.setBackground(WHITE.brighter());
        desenho.add(desenhar);
        barra.add(ajuda);
        sobre.setBackground(WHITE.brighter());
        ajuda.add(sobre);
        sair.setBackground(WHITE.brighter());
        ajuda.add(sair);        
        janela.repaint();
        janela.validate();
    }

    /** Método que permite a seleção de arquivo de configuração na máquina do
     * usuário e a importação dos dados para o programa.
     */
    private void preparaArquivo() {
        carregar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser escolherArquivo = new JFileChooser();
                escolherArquivo.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
                int resultado = escolherArquivo.showOpenDialog(null);
                escolherArquivo.setMultiSelectionEnabled(false);
                
                if (resultado == JFileChooser.APPROVE_OPTION) {                    
                    file = escolherArquivo.getSelectedFile();
                    
                    try {
                        controller.importarArquivo(file);
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Arquivo selecionado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Arquivo não selecionado!");
                }
            }
        });
    }
    
    /** Método que desenha o grafo com configuração especificada na tela.
     */
    public void preparaDesenho() {
        desenhar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                painelPrincipal.add(new Graphics(controller), BorderLayout.CENTER);
                janela.repaint();
                janela.validate();
            }
        });
    }
    
    /** Método que adiciona um vértice ao grafo.
     */
    private void preparaAdicionarV() {
        adicionarV.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getBRT().getGrafo().inserirVertice(JOptionPane.showInputDialog(null, "Id:"));
            }
        });
    }
    
    /** Método que adiciona uma aresta ao grafo.
     */
    private void preparaAdicionarA() {
        adicionarA.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Vertice origem = new Vertice (JOptionPane.showInputDialog(null, "Id Origem:"));
                Vertice destino = new Vertice (JOptionPane.showInputDialog(null, "Id Destino:"));
                Double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Distância:"));
                controller.getBRT().getGrafo().inserirAresta(origem, destino, peso);
            }
        });
    }
    
    /** Método que remove um vértice do grafo.
     */
    private void preparaRemoverV() {
        removerV.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    controller.getBRT().getGrafo().removerVertice(JOptionPane.showInputDialog(null, "Id:"));
                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    
    /** Método que remove uma aresta do grafo.
     */
    private void preparaRemoverA() {
        removerA.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Vertice origem = new Vertice (JOptionPane.showInputDialog(null, "Id Origem:"));
                Vertice destino = new Vertice (JOptionPane.showInputDialog(null, "Id Destino:"));
                
                try {
                    controller.getBRT().getGrafo().removerAresta(origem, destino);
                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    
    /** Método que modifica a distância de uma aresta do grafo.
     */
    private void preparaModificar() {
        modificar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    Vertice origem = new Vertice (JOptionPane.showInputDialog(null, "Id Origem:"));
                    Vertice destino = new Vertice (JOptionPane.showInputDialog(null, "Id Destino:"));
                    Double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Distância:"));
                    controller.getBRT().getGrafo().modificarPesoAresta(origem, destino, peso);
                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    /** Método que, a partir da seleção de origem e destino, identifica as possíveis
     * rotas de acesso ao destino.
     */
    private void preparaPossiveisCaminhos() {
        possiveis.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.setModel(model);
                Iterator it = (controller.IdentificarPossiveisRotas(JOptionPane.showInputDialog(null, "Id Origem:"),
                               JOptionPane.showInputDialog(null, "Id Destino:")));
                model.addElement("Caminhos Possíveis:");
                model.addElement(" ");
                int i = 1;
                
                while (it.hasNext()) {
                    model.addElement(i++ + "º-" + (String) it.next());
                }
                model.addElement(" ");
                model.addElement(" ");
            }
        });
    }
    
    /** Método que, a partir das rotas entre origem e destino, calculadas as
     * distâncias das rotas, identifica a menor entre elas.
     */
    private void preparaMenorCaminho() {
        menor.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.setModel(model);
                String origem = JOptionPane.showInputDialog(null, "Origem:");
                String destino = JOptionPane.showInputDialog(null, "Destino:");
                Iterator<Vertice> it = (controller.calcularRotaMenorCaminho(Integer.parseInt(origem), Integer.parseInt(destino)).iterator());
                model.addElement("Menor Caminho:");
                model.addElement(" ");
                
                while (it.hasNext()) {
                    model.addElement(it.next().toString());
                }
                model.addElement(" ");
                model.addElement(" ");
            }
        });
    }
    
    /** Método que limpa área lateral de texto.
     */
    public void preparaLimpar() {
        limpar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeAllElements();
            }
        });
    }
    
    /** Método que exibe informações do programa.
     */
    public void preparaSobre() {
        sobre.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, " Criadores: Camille Jesus e Rodrigo Santos\n\n BRT©  2015\n Português - Br ");
            }
        });
    }
    
    /** Método que prepara finalização do programa.
     */
    public void preparaSair() {
        sair.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}