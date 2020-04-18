
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        double avg = 0.0;
        FirstRatings fr = new FirstRatings();
        int num = fr.numberOfRatingsOfMovie(id, myRaters);
        if(minimalRaters <= num){
            for(Rater r : myRaters){
                if(r.hasRating(id)){
                    avg += r.getRating(id);
                }
            }
            avg = avg/num;
        }
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgList = new ArrayList<Rating>();
        for(Movie m : myMovies){
            double avg = getAverageByID(m.getID(), minimalRaters);
            //System.out.println(m.getID() + " has " + avg + " average rating.");
            Rating r = new Rating(m.getID(), avg);
            if(avg != 0.0)
                avgList.add(r);
        }
        return avgList;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id))
                return m.getTitle();
        }
        return "ID Not Found";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().equals(title))
                return m.getID();
        }
        return "NO SUCH TITLE";
    }
}