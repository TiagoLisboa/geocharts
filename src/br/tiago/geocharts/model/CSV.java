package br.tiago.geocharts.model;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

import java.io.IOException;
import java.io.FileNotFoundException;

public class CSV {
	private String csvFile;
	private String atributes[];
	private ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();

    public CSV(String csvFile) {
    	this.csvFile = csvFile;
    	atributes = new String[1];

    	BufferedReader br = null;
    	
    	String line = "";

    	boolean l = false;

    	try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (l) {
					ArrayList<String> parsedLine = parseLine(line);
					lines.add(parsedLine);
				} else {
					atributes = new String[line.split(",").length];
					atributes = line.split(",");
					l = !l;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static ArrayList<String> parseLine (String csvLine) {
    	char line[] = csvLine.toCharArray();
    	char separator = ',';
    	char blockQuote = '\"';
    	boolean inQuotes = false;
    	ArrayList<String> parsedLine = new ArrayList<String>();
    	String current = "";

    	for (int i = 0; i < line.length; i++) {
    		if (!inQuotes && line[i] != blockQuote) {
    			if (line[i] == separator) {
	    			parsedLine.add(current);
	    			current = "";
	    		} else {
	    			current += line[i];
	    		}
    		} else {
    			if (line[i] == blockQuote) {
    				inQuotes = !inQuotes;
    			} else { // remove else to include " in the string
    				current += line[i];
    			}
    		}
    	}

    	return parsedLine;
    }

    public Hashtable<String, Integer> agruparColuna (String coluna) {
        Hashtable<String, Integer> lista = new Hashtable<String, Integer>();
        ArrayList<String> valores = new ArrayList<String>();
        ArrayList<Integer> valoresQtds = new ArrayList<Integer>();

        int idx = java.util.Arrays.asList(atributes).indexOf(coluna);

        for (List<String> i : lines) {
            String v = i.get(idx);
            if (valores.indexOf(v) >= 0) {
                valoresQtds.set(valores.indexOf(v), valoresQtds.get(valores.indexOf(v)) + 1);
            } else {
                valores.add(v);
                valoresQtds.add(1);
            }
        }

        for (int i = 0; i < valores.size(); i++) {
            lista.put(valores.get(i), valoresQtds.get(i));
            // valores.set(i, valores.get(i) + ": " + valoresQtds.get(i));
        }

        return lista;
    }

    public String[] getAtributes () {
        return this.atributes;
    }

    public ArrayList<ArrayList<String>> getLines () {
        return this.lines;
    }
}
