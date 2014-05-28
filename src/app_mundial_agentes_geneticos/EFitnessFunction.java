package app_mundial_agentes_geneticos;

import java.util.ArrayList;
import java.util.List;

import app_mundial_agentes_geneticos.Inicio;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

public class EFitnessFunction extends FitnessFunction {

	    List equipos = new ArrayList();

	    public EFitnessFunction(List equipos) {
	        this.equipos = equipos;
	    }

	    //determinar la instancia del mejor cromosoma
	    //controla que 
	    protected double evaluate(IChromosome chromosome) {
	        double score = 0;
	        	        
	        List dups = new ArrayList();
	        int badSolution = 1;

	        for (int i = 0; i < chromosome.size(); i++) {

	            //IntegerGene agene = (IntegerGene) chromosome.getGene(i);
	            //System.out.println(agene);
	            int index = (Integer) chromosome.getGene(i).getAllele();
	            
	            if (dups.contains(index)) {
	                badSolution = 0;
	            } else {
	                dups.add(index);
	            }

	            Equipo equipo = (Equipo) equipos.get(index);
	            double genreScore = getGenreScore(equipo);
	            if (genreScore == 0) {
	                badSolution = 0;
	            }
	            score = (score+genreScore);

	        }
	        
	        return (score * badSolution);
	    }

	    private int getGenreScore(Equipo equipo) {
	        int copas = 0;
	        int contRanking = 0;
	        int partidos = 0;
	        int total  = 0;
	        
	        if ((Integer.parseInt(equipo.getCopas())) == 0) {
	            copas = Integer.parseInt(equipo.getCopas() + 1);
	        }else if ((Integer.parseInt(equipo.getCopas())) >= 1 && (Integer.parseInt(equipo.getCopas())) <= 2) {
	            copas = Integer.parseInt(equipo.getCopas()) + 10;
	        }else if ((Integer.parseInt(equipo.getCopas())) >= 3 && (Integer.parseInt(equipo.getCopas())) <= 4) {
	            copas = Integer.parseInt(equipo.getCopas()) + 15;
	        }else if ((Integer.parseInt(equipo.getCopas())) >= 5 && (Integer.parseInt(equipo.getCopas())) <= 6) {
	            copas = Integer.parseInt(equipo.getCopas()) + 20;
	        }else if ((Integer.parseInt(equipo.getCopas())) >= 7 && (Integer.parseInt(equipo.getCopas())) <= 8) {
	            copas = Integer.parseInt(equipo.getCopas()) + 25;
	        }else if ((Integer.parseInt(equipo.getCopas())) >= 9 && (Integer.parseInt(equipo.getCopas())) <= 10) {
	            copas = Integer.parseInt(equipo.getCopas()) + 30;
	        }
	        
	        //
	        // ranking
	        //
	        
	        if (((Integer.parseInt(equipo.getRanking())) >= 1) && ((Integer.parseInt(equipo.getRanking()))<=5)){
	            contRanking = 15;
	        }
	        
	        if (((Integer.parseInt(equipo.getRanking())) >= 5) && ((Integer.parseInt(equipo.getRanking()))<=10)){
	            contRanking = 10;
	        }
	        
	        if (((Integer.parseInt(equipo.getRanking()))>= 11) && ((Integer.parseInt(equipo.getRanking())<=20))){
	            contRanking = 8;
	        }
	        
	        if (((Integer.parseInt(equipo.getRanking()))>= 21) && ((Integer.parseInt(equipo.getRanking())<=30))){
	            contRanking = 6;
	        }
	        
	        if (((Integer.parseInt(equipo.getRanking()))>= 31) && ((Integer.parseInt(equipo.getRanking())<=40))){
	            contRanking = 4;
	        }
	        
	        if (((Integer.parseInt(equipo.getRanking()))>= 41) && ((Integer.parseInt(equipo.getRanking())<=50))){
	            contRanking = 2;
	        }
	        
	        if (((Integer.parseInt(equipo.getRanking()))>= 51) && ((Integer.parseInt(equipo.getRanking())<=60))){
	            contRanking = 1;
	        }
	        
	        
	        if(Integer.parseInt(equipo.getPartido1()) == -1){
	        	partidos += 0;
	        }else if(Integer.parseInt(equipo.getPartido1()) == 0){
	        	partidos += 1;
	        }else if(Integer.parseInt(equipo.getPartido1()) == 1){
	        	partidos += 3;
	        }
	        
	        if(Integer.parseInt(equipo.getPartido2()) == -1){
	        	partidos += 0;
	        }else if(Integer.parseInt(equipo.getPartido2()) == 0){
	        	partidos += 1;
	        }else if(Integer.parseInt(equipo.getPartido2()) == 1){
	        	partidos += 3;
	        }
	        
	        if(Integer.parseInt(equipo.getPartido3()) == -1){
	        	partidos += 0;
	        }else if(Integer.parseInt(equipo.getPartido3()) == 0){
	        	partidos += 1;
	        }else if(Integer.parseInt(equipo.getPartido3()) == 1){
	        	partidos += 3;
	        }
	        
	        total = copas + contRanking + partidos;
	        //System.out.println(copas+"-"+contRanking+"-"+partidos+"\t"+total);
	        //cont++;
	        //System.out.println(cont);
	        return total;
	    }
	}
