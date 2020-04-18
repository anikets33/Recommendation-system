
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("/data/ratedmoviesfull.csv", "/data/ratings.csv");
        System.out.println("number of movies are : " + sr.getMovieSize());
        System.out.println("number of raters are : " + sr.getRaterSize());
        
        ArrayList<Rating> avgList = sr.getAverageRatings(12);
        Collections.sort(avgList);
        System.out.println(avgList.size());
        for(Rating r : avgList){
            System.out.println(r.getValue() + "\t" + sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("/data/ratedmoviesfull.csv", "/data/ratings.csv");
        System.out.println("number of movies are : " + sr.getMovieSize());
        System.out.println("number of raters are : " + sr.getRaterSize());
        
        String id = sr.getID("Vacation");
        double d = sr.getAverageByID(id,3);
        System.out.println("movie has " + d + " avg rating");
    }
}
