
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsFile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgList = new ArrayList<Rating>();
        for(String id : movies){
            double avg = getAverageByID(id, minimalRaters);
            //System.out.println(m.getID() + " has " + avg + " average rating.");
            Rating r = new Rating(id, avg);
            if(avg != 0.0)
                avgList.add(r);
        }
        return avgList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        for(String id : movieIDs){
            double avg = getAverageByID(id, minimalRaters);
            Rating r = new Rating(id, avg);
            if(avg != 0.0)
                list.add(r);
        }
        return list;
    } 
}
