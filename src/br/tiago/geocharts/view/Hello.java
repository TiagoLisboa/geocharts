package br.tiago.geocharts.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.tiago.geocharts.controller.GUIController;


public class Hello {
    public static void openHelloDialog (JFrame janela) {
        JPanel painel = new JPanel();
        // Container c = janela.getContentPane();
        janela.add(painel);
        // janela.invalidate();
        // janela.revalidate();
        // janela.updateUI();

        JButton botaoCarregar = new JButton("Carregar CSV");
        botaoCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    int retorno = chooser.showOpenDialog(null);

                    if (retorno == JFileChooser.APPROVE_OPTION) {
                        GUIController.fileName = chooser.getSelectedFile().getName();
                        FileReader reader = new FileReader(chooser.getSelectedFile());
                        GUIController.callGraph(reader);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        
        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.add(botaoCarregar);
        painel.add(botaoSair);

        janela.setSize(200, 100);


        // painel.updateUI();
        // janela.repaint();
    }
}
