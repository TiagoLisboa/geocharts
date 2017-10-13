package br.tiago.geocharts.view;

import br.tiago.GeoCharts.model.CSV;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MainUI {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	CSV csv = new CSV("csv/listings.csv");
        String nome;
    	boolean loop = true;

    	while (loop) {
			imprimirMenu();
			System.out.println("--------------------------------------------------");
    		switch (sc.nextInt()) {
    			case 0:
    				loop = false;
    				break;
    			case 1:
    				System.out.println("--------------------------------------------------");
    				System.out.println("Digite o nome do csv: (listings)");
    				nome = sc.next();
                    System.out.println("--------------------------------------------------");
    				csv = new CSV("csv/" + nome + ".csv");
    				break;
    			case 2:
	    			imprimirAtributos(csv);
	    			break;
    			case 3:
    				imprimirDataset(csv);
    				break;
                case 4:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite o nome da coluna: (neighbourhood)");
                    nome = sc.next();
                    System.out.println("--------------------------------------------------");
                    imprimirAtributoPorGrupos(csv, nome);
                    break;
    		}
    	}
    	
    }

    private static void imprimirMenu () {
    	System.out.println("--------------------------------------------------");
    	System.out.println("1 - Escolher Dataset");
    	System.out.println("2 - Listar Atributos");
    	System.out.println("3 - Listar Dataset");
        System.out.println("4 - Examinar Coluna");
    }

    private static void imprimirAtributoPorGrupos (CSV csv, String nome) {
        ArrayList<String> grupos = csv.agruparColuna(nome);
        for (String s : grupos) {
            System.out.println(s);
        }
    }

    private static void imprimirAtributos (CSV csv) {
    	String atributes[] = csv.getAtributes();
    	System.out.println("--------------------------------------------------");
    	for (int i = 0; i < atributes.length; i++) {
    		System.out.print(atributes[i] + " ");
    	}
    	System.out.println();
    }

    private static void imprimirDataset (CSV csv) {
    	System.out.println("--------------------------------------------------");

    	ArrayList<ArrayList<String>> lines = csv.getLines();

    	for (int i = 0; i < lines.size(); i++) {
    		for (int j = 0; j < lines.get(i).size(); j++) {
	    		System.out.print(csv.getAtributes()[j] + ": " + lines.get(i).get(j) + " | ");
	    	}
	    	System.out.println("");
    	}
    }
}
