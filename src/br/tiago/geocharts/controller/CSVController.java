package br.tiago.geocharts.controller;

import br.tiago.geocharts.model.CSV;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class CSVController {
	private CSV csv;
    
    public CSVController (FileReader file) {
    	csv = new CSV(file);
    }

    public CSVController (String file) {
        try {
            csv = new CSV(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // FileReader reader = ;
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

    public File pieChartCreator (String nome) {
        Hashtable<String, Integer> grupos = this.agruparColuna(nome);
        Iterator<String> itr = grupos.keySet().iterator();
        String str;

        final DefaultPieDataset dataset = new DefaultPieDataset();
        while (itr.hasNext()) {
            str = itr.next();
            dataset.setValue(str, grupos.get(str));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Relação de: " + nome,
            dataset, true, true, false
            );

        try {
            File PieChart = new File("pie_"+nome+".jpg");
            ChartUtilities.saveChartAsJPEG(PieChart, pieChart, 1280, 800);
            return PieChart;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ChartPanel pieChartCreatorPanel (String nome) {
        Hashtable<String, Integer> grupos = this.agruparColuna(nome);
        Iterator<String> itr = grupos.keySet().iterator();
        String str;

        final DefaultPieDataset dataset = new DefaultPieDataset();
        while (itr.hasNext()) {
            str = itr.next();
            dataset.setValue(str, grupos.get(str));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Relação de: " + nome,
            dataset, true, true, false
            );

        try {
            // File PieChart = new File("pie_"+nome+".jpg");
            // ChartUtilities.saveChartAsJPEG(PieChart, pieChart, 1280, 800);
            ChartPanel pieChartP = new ChartPanel(pieChart);
            return pieChartP;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
			File BarChart = new File("bar_"+nome+".jpg");
	    	ChartUtilities.saveChartAsJPEG(BarChart, barChart, 1280, 800);
            return BarChart;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

        return null;
    }

    public ChartPanel barChartCreatorPanel (String nome) {
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
            // File BarChart = new File("bar_"+nome+".jpg");
            ChartPanel BarChart = new ChartPanel(barChart);
            return BarChart;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public File triBarChartCreator (String nome) {
        Hashtable<String, Integer> grupos = this.agruparColuna(nome);
        Iterator<String> itr = grupos.keySet().iterator();
        String str;

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (itr.hasNext()) {
            str = itr.next();
            dataset.addValue(grupos.get(str), str, nome);
        }

        JFreeChart barChart = ChartFactory.createBarChart3D(
            "Relação de: " + nome,
            nome,
            "Quantidade",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
            );

        try {
            File BarChart = new File("tridbar_"+nome+".jpg");
            ChartUtilities.saveChartAsJPEG(BarChart, barChart, 1280, 800);
            return BarChart;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ChartPanel triBarChartCreatorPanel (String nome) {
        Hashtable<String, Integer> grupos = this.agruparColuna(nome);
        Iterator<String> itr = grupos.keySet().iterator();
        String str;

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (itr.hasNext()) {
            str = itr.next();
            dataset.addValue(grupos.get(str), str, nome);
        }

        JFreeChart barChart = ChartFactory.createBarChart3D(
            "Relação de: " + nome,
            nome,
            "Quantidade",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
            );

        try {
            // File BarChart = new File("bar_"+nome+".jpg");
            ChartPanel BarChart = new ChartPanel(barChart);
            return BarChart;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
