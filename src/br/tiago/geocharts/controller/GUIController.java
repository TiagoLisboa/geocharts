package br.tiago.geocharts.controller;

import br.tiago.geocharts.view.Graph;
import br.tiago.geocharts.view.Hello;
import br.tiago.geocharts.view.Share;
import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
// import org.jfree.chart.ChartUtilities;
// import org.jfree.chart.JFreeChart;

public class GUIController {
	public static JFrame janela;
    private static CSVController csvc;
    public static FBController fbc;
    public static String attr = "neighbourhood";
    public static String tipo = "setores";
    public static String fileName;
    public static File chartJPG;
	// public static JPanel painel;

	public GUIController () {
        janela = new JFrame("GeoCharts");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        // janela.setSize(540, 540);
        janela.setVisible(true);
    }

    public static void callHello () {
        janela.getContentPane().removeAll();
    	Hello.openHelloDialog(janela);
    }

    public static void callGraph () {
        FileReader file = null;

        try {
            file = new FileReader("csv/"+fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        janela.getContentPane().removeAll();
        csvc = new CSVController(file);
        Graph.openGraphDialog(janela, csvc);
    }

    public static void callGraph (FileReader file) {
        janela.getContentPane().removeAll();
    	csvc = new CSVController(file);
    	Graph.openGraphDialog(janela, csvc);
    }

    public static void callShare (JPanel graphP) {
        janela.getContentPane().removeAll();
        CSVController info = new CSVController(".env.csv");

        for (ArrayList<String> s : info.getLines()) {
            fbc = new FBController(s.get(0), s.get(1), s.get(2));
        }

        Share.openShareDialog(janela, fbc, graphP);
    }

    public static void updateGraph () {
        Graph.graphPanel.removeAll();
        JPanel x = null;
        if (tipo == "colunas") {
            x = csvc.barChartCreatorPanel(attr);
            chartJPG = csvc.barChartCreator(attr);
        } else if (tipo == "setores") {
            x = csvc.pieChartCreatorPanel(attr);
            chartJPG = csvc.pieChartCreator(attr);
        } else if (tipo == "trid") {
            x = csvc.triBarChartCreatorPanel(attr);
            chartJPG = csvc.triBarChartCreator(attr);
        }
        x.setPreferredSize(new Dimension(Graph.graphPanel.getWidth(), Graph.graphPanel.getHeight()));
        Graph.graphPanel.add(x);
        Graph.graphPanel.revalidate();
        Graph.graphPanel.repaint();
    }

    public static void publish (String id, String msg) {
        fbc.publish(msg, chartJPG, id);
    }
}
