package br.tiago.geocharts.view;

import br.tiago.geocharts.controller.GUIController;
import br.tiago.geocharts.controller.CSVController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph {
	public static JPanel graphPanel = new JPanel();
	public static JPanel mainPanel = new JPanel();

    public static void openGraphDialog(JFrame janela, CSVController csvc) {
    	JPanel attrPanel = new JPanel();
    	JPanel typePanel = new JPanel();
    	JPanel bottonPanel = new JPanel();
    	graphPanel.setPreferredSize(new Dimension(1280-300, 720));

    	JButton colunas = new JButton("Colunas");
    	colunas.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent a) {
	                GUIController.tipo = "colunas";
	                GUIController.updateGraph();
	            }
	        });
    	typePanel.add(colunas);
    	
    	JButton setores = new JButton("Pizza");
    	setores.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent a) {
	                GUIController.tipo = "setores";
	                GUIController.updateGraph();
	            }
	        });
    	typePanel.add(setores);

    	JButton trid = new JButton("3D");
    	trid.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent a) {
	                GUIController.tipo = "trid";
	                GUIController.updateGraph();
	            }
	        });
    	typePanel.add(trid);

    	JButton atributo;
    	for (String s : csvc.getAtributes()) {
    		final String teste = s;
    		atributo = new JButton(s);
    		atributo.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent a) {
	                GUIController.attr = teste;
	                GUIController.updateGraph();
	            }
	        });

    		attrPanel.add(atributo);
    	}

    	JButton fb = new JButton("Compartilhar no facebook");
    	fb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                GUIController.callShare(graphPanel);
            }
        });
    	bottonPanel.add(fb);

        attrPanel.setLayout(new GridLayout(csvc.getAtributes().length, 1));
    	attrPanel.setPreferredSize(new Dimension(300, 720));

    	mainPanel.setLayout(new BorderLayout());

    	mainPanel.add(attrPanel, BorderLayout.WEST);
    	mainPanel.add(graphPanel);
    	mainPanel.add(typePanel, BorderLayout.NORTH);
    	mainPanel.add(bottonPanel, BorderLayout.SOUTH);

    	janela.add(mainPanel);

    	mainPanel.updateUI();
        janela.setSize(1280, 540);
        janela.repaint();

    }
}
