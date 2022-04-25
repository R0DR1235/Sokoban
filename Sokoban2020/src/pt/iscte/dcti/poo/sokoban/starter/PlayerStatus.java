package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

public class PlayerStatus {
    
    private String playerName;
    private ArrayList<Integer> scoresTop= new ArrayList<Integer>();
    private ArrayList<Integer> scores= new ArrayList<Integer>();
    
    public PlayerStatus(String playerName){
        this.playerName=playerName;
    }
    
    public String getPlayerName(){
        return playerName;
    }
    
    public void setScores(int score){
        scores.add(score);
        scoresTop.add(score); 
    }
    
    public ArrayList<Integer> sendTopScores(){
        return scoresTop;
    }
    
    public ArrayList<Integer> sendScores(){
        return scores;
    }
    
    public void reset(){
        scoresTop.removeAll(scoresTop);
        scores.removeAll(scores);
    }
    
    public String toString(){
        String s= playerName+": "+"\n";
        int level=1;
        for(int i=0;i!=scores.size();i++){
            s+="level "+ level +": "+scores.get(i)+" movements"+"\n";
            level++;
        }
        return s;
    }

}

