package buscaminas.strategy;

import java.util.Random;

public class EstrategiaFactory {
	private static final int nombreEstrategies = 2;
	
		public EstrategiaFactory(){}
	
		public static Estrategia getEstrategiaRandom() {
			Random rand = new Random();
			int r = rand.nextInt(nombreEstrategies-1);
			
			if(r == 1) {
				return new PuntuacioPerTemps();
			} else {
				return new PuntuacioPerNombreDeTirades();
			}
		}
		
		
		public Estrategia getEstrategia(String est) {
	    	if(est.equals("PuntuacioPerNombreDeTirades")) return new PuntuacioPerNombreDeTirades();
	    	else return new PuntuacioPerTemps();
	    }
		
}	
