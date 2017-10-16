package br.tiago.geocharts.view;

import br.tiago.geocharts.controller.CSVController;
import br.tiago.geocharts.controller.FBController;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.File;

public class MainUI {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        CSVController csvc = new CSVController("csv/listingsAthens.csv");
    	// CSVController csvc = new CSVController("csv/listings.csv");
        CSVController info = new CSVController(".env.csv");
        FBController fbc = null;

        for (ArrayList<String> s : info.getLines()) {
            fbc = new FBController(s.get(0), s.get(1), s.get(2));
        }

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
    				csvc = new CSVController("csv/" + nome + ".csv");
    				break;
    			case 2:
	    			imprimirAtributos(csvc);
	    			break;
    			case 3:
    				imprimirDataset(csvc);
    				break;
                case 4:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite o nome da coluna: (neighbourhood)");
                    nome = sc.next();
                    System.out.println("
                        --------------------------------------------------");
                    imprimirAtributoPorGrupos(csvc, nome);
                    break;
                case 5:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite o nome da coluna: (neighbourhood)");
                    nome = sc.next();
                    System.out.println("--------------------------------------------------");
                    imprimirGrafico(csvc, nome);
                    break;
                case 6:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite o nome da coluna: (neighbourhood)");
                    nome = sc.next();
                    System.out.println("Digite o id do usuario: (100001383083662 | lucas: 100001523343046)");
                    String usr = sc.next();
                    System.out.println("Digite uma mensagem: ");
                    sc.nextLine();
                    String msg = sc.nextLine();
                    System.out.println("--------------------------------------------------");
                    publicarGrafico(csvc, fbc, nome, usr, msg);
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
        System.out.println("5 - Criar Grafico");
        System.out.println("6 - Postar Grafico");
    }

    private static void imprimirAtributoPorGrupos (CSVController csvc, String nome) {
        Hashtable<String, Integer> grupos = csvc.agruparColuna(nome);
        Iterator<String> itr = grupos.keySet().iterator();
        String str;
        while (itr.hasNext()) {
            str = itr.next();
            System.out.println(str + ": " + grupos.get(str));
        }
    }

    private static void imprimirAtributos (CSVController csvc) {
    	String atributes[] = csvc.getAtributes();
    	System.out.println("--------------------------------------------------");
    	for (int i = 0; i < atributes.length; i++) {
    		System.out.print(atributes[i] + " ");
    	}
    	System.out.println();
    }

    private static void imprimirDataset (CSVController csvc) {
    	System.out.println("--------------------------------------------------");

    	ArrayList<ArrayList<String>> lines = csvc.getLines();
        String atributes[] = csvc.getAtributes();

    	for (int i = 0; i < lines.size(); i++) {
    		for (int j = 0; j < lines.get(i).size(); j++) {
	    		System.out.print(atributes[j] + ": " + lines.get(i).get(j) + " | ");
	    	}
	    	System.out.println("");
    	}
    }

    private static void imprimirGrafico (CSVController csvc, String nome) {
        csvc.barChartCreator(nome);
    }

    private static void publicarGrafico (CSVController csvc, FBController fbc, String nome, String usr, String msg) {
        File chart = csvc.barChartCreator(nome);
        fbc.publish(msg, chart, usr);
    }
}
