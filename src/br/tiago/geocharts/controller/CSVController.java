package br.tiago.geocharts.controller;

import br.tiago.geocharts.model.CSV;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class CSVController {
	private CSV csv;
    
    public CSVController (String name) {
    	csv = new CSV(name);
    }

    public Hashtable<String, Integer> agruparColuna(String nome) {
    	return csv.agruparColuna(nome);
    }

    public String[] getAtributes() {
    	return csv.getAtributes();
    }

    public ArrayList<ArrayList<String>> getLines() {
    	return csv.getLines();
    }

    public File barChartCreator (String nome) {
    	Hashtable<String, Integer> grupos = this.agruparColuna(nome);
    	Iterator<String> itr = grupos.keySet().iterator();
    	String str;

    	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	while (itr.hasNext()) {
    		str = itr.next();
    		dataset.addValue(grupos.get(str), str, nome);
    	}

    	JFreeChart barChart = ChartFactory.createBarChart(
    		"Relação de: " + nome,
    		nome,
    		"Quantidade",
    		dataset,
    		PlotOrientation.VERTICAL,
    		true, true, false
    		);

    	try {
			File BarChart = new File(nome+".jpg");
	    	ChartUtilities.saveChartAsJPEG(BarChart, barChart, 640, 480);
            return BarChart;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

        return null;

    }
}
