
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    /*public double getAverageByID(String id, int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    */
    private double dotProduct(Rater me, Rater r){
        double product = 0.0;
        ArrayList<String> movies = me.getItemsRated();
        for(String m : movies){
            if(r.getItemsRated().contains(m)){  //r.hasRating(m)
                product += ((me.getRating(m) - 5) * (r.getRating(m) - 5));
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(id)){
                double product = dotProduct(me, r);
                if(product >= 0)
                    list.add(new Rating(r.getID(), product));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID : movies){
            double sum = 0.0;
            int count = 0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = list.get(i);
                String rid = r.getItem();
                double sim = r.getValue();
                
                //System.out.println(sim);
                
                Rater rater = RaterDatabase.getRater(rid);
                if(rater.hasRating(movieID)){
                    count++;
                    double weight = rater.getRating(movieID) * sim;
                    sum += weight;
                }
            }
            if(count >= minimalRaters){
                ret.add(new Rating(movieID, sum/count));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieID : movies){
            double sum = 0.0;
            int count = 0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = list.get(i);
                String rid = r.getItem();
                double sim = r.getValue();
                Rater rater = RaterDatabase.getRater(rid);
                if(rater.hasRating(movieID)){
                    count++;
                    double weight = rater.getRating(movieID) * sim;
                    sum += weight;
                }
            }
            if(count >= minimalRaters){
                ret.add(new Rating(movieID, sum/count));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
}
