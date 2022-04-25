package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Pontuacao{
    
    
    /***********************MELHOR SCORE POR NIVEL E QUEM FEZ, SÓ ESCREVE QUANDO O JOGO É COMPLETADO*************************/
    
    public static void writeScore(ArrayList<Integer> scores, String playerName){
        ArrayList<Integer> bestScores=readScore("BestScores");
        ArrayList<String> names=readPlayerName("BestScores");
        try {
            PrintWriter writer= new PrintWriter(new File("Scores/"+"BestScores"+".txt"));
            writer.println("BEST SCORES (When the game is completed)");
            for(int i=0,level=1;i!=scores.size();i++,level++){
                if(scores.get(i)<bestScores.get(i))
                writer.println("level "+level+": "+scores.get(i)+" movements "+"(by "+playerName+")");
                else
                writer.println("level "+level+": "+bestScores.get(i)+" movements "+"(by "+names.get(i)+")");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO");
        }
    }
    
    public static ArrayList<Integer> readScore(String fileName){
        ArrayList<Integer> scores=new ArrayList<Integer>();
        try {
            Scanner s= new Scanner(new File("Scores/"+fileName+".txt"));
            s.nextLine();
            while(s.hasNextLine()){
                String line=s.nextLine();
                String info=line.substring(line.indexOf(":")+2,line.indexOf("m")-1);
                int movements= Integer.parseInt(info);
                scores.add(movements);
            }
            s.close();
            return scores;
                
        } catch (FileNotFoundException e) {
            for(int i=0;i!=SokobanGame.getInstance().numberOfLevels();i++)
                scores.add(i,200);
            return scores;
        }
       
    }
    
    public static ArrayList<String> readPlayerName(String fileName){
        ArrayList<String> names=new ArrayList<String>();
        try {
            Scanner s= new Scanner(new File("Scores/"+fileName+".txt"));
            s.nextLine();
            while(s.hasNextLine()){
                String line=s.nextLine();
                String name=line.substring(line.indexOf("y")+2,line.indexOf(")"));
                names.add(name);
            }
            s.close();
            return names;
                
        } catch (FileNotFoundException e) {
            for(int i=0;i!=SokobanGame.getInstance().numberOfLevels();i++)
                names.add(i,"");
            return names;
        }
       
    }
    
    /*************************************TOP 3 SCORES POR NIVEL, ESCREVE ASSIM QUE PASSA DE NIVEL*****************************************/
    
    
    public static ArrayList<ArrayList<Integer>> readScoresTop3(){
        ArrayList<ArrayList<Integer>> scores=new ArrayList<ArrayList<Integer>>();
        try {
            Scanner s= new Scanner(new File("Scores/"+"TopScores"+".txt"));
            for(int i=1;i!=SokobanGame.getInstance().numberOfLevels()+1;i++){
                ArrayList<Integer> lista= new ArrayList<Integer>();
                s.nextLine();
                for(int j=1;j!=4;j++){
                    String line=s.nextLine();
                    String info=line.substring(line.indexOf(":")+2,line.indexOf("m")-1);
                    int movements= Integer.parseInt(info);
                    lista.add(movements);
                }
                scores.add(lista);
            }
            s.close();
            return scores;
                
        } catch (FileNotFoundException e) {
            for(int i=1;i!=SokobanGame.getInstance().numberOfLevels()+1;i++){
                ArrayList<Integer> lista= new ArrayList<Integer>();
                for(int j=1;j!=4;j++)
                    lista.add(0);
                scores.add(lista);
            }
                return scores;
        }
        
    }
    
    public static void writeScoresTop3(ArrayList<Integer> scores){
        ArrayList<ArrayList<Integer>> bestScores=readScoresTop3();
        completeArray(scores);
        ArrayList<ArrayList<Integer>> newBestScores=arrayOrganize(scores,bestScores);
        try {
            PrintWriter writer= new PrintWriter(new File("Scores/"+"TopScores"+".txt"));
            for(int i=1;i!=SokobanGame.getInstance().numberOfLevels()+1;i++){
                ArrayList<Integer> levelScores= newBestScores.get(i-1);  
                writer.println("level "+i+":");
                for(int j=1;j!=4;j++){
                    writer.println(j+"º: "+levelScores.get(j-1)+" movements ");
                }
            }
            remakeArray(scores);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO");
        
        }
    }
    
    public static ArrayList<ArrayList<Integer>> arrayOrganize(ArrayList<Integer> scores, ArrayList<ArrayList<Integer>> bestScores){
        for(int i=0;i!=bestScores.size();i++){   
            bestScores.get(i).add(scores.get(i));
            Collections.sort(bestScores.get(i),new Comparador());
            bestScores.get(i).remove(bestScores.get(i).size()-1);
        }
        return bestScores;
    }
    
    public static void completeArray(ArrayList<Integer> scores){
        while(scores.size()<SokobanGame.getInstance().numberOfLevels())
            scores.add(0);
    }
    
    public static void remakeArray(ArrayList<Integer> scores){
        scores.removeAll(scores);
        int i=0;
        while(i<SokobanGame.getInstance().level()){
            scores.add(0);
            i++;
        }
        
    }
    
    private static class Comparador implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1==0) return 1;
            else if(o2==0) return -1;
            else if(o1 == 0 && o2 == 0) return 0;
            else return o1.compareTo(o2); 
        }

    }
    

            
        
        
    
        
        

}
