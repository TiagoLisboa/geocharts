package br.tiago.geocharts.view;

import br.tiago.geocharts.controller.FBController;
import br.tiago.geocharts.controller.GUIController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Share {
    public static void openShareDialog (JFrame janela, FBController fbc, JPanel graph) {
    	JPanel mainPanel = new JPanel();

    	JPanel textPanel = new JPanel();
    	JPanel graphPanel = new JPanel();
    	JPanel btnsPanel = new JPanel();

    	// graphPanel.setPreferredSize(new Dimension(1280/2, 540));
    	graphPanel.add(graph);
    	graphPanel.revalidate();
    	graphPanel.repaint();

    	textPanel.setLayout(new BorderLayout());

    	JLabel descricao = new JLabel("Escreva uma mensagem para o amiguinho");
    	textPanel.add(descricao, BorderLayout.NORTH);


		final JTextArea msg = new JTextArea(12, 20);
		textPanel.add(msg);



		final JTextField id = new JTextField("Id do facebook do amiguinho");
		JButton send = new JButton("Enviar");
		send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                GUIController.publish(id.getText(), msg.getText());
            }
        });
		JButton back = new JButton("Voltar");
		back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                GUIController.callGraph ();
            }
        });
		btnsPanel.add(id);
		btnsPanel.add(send);
		btnsPanel.add(back);


    	mainPanel.setLayout(new BorderLayout());
    	// mainPanel.add(labelPanel, BorderLayout.NORTH);
    	mainPanel.add(graphPanel);
    	mainPanel.add(textPanel, BorderLayout.EAST);
    	mainPanel.add(btnsPanel, BorderLayout.SOUTH);

    	janela.add(mainPanel);
    	
    	// mainPanel.updateUI();

    	janela.revalidate();
        janela.repaint();
    }
}
