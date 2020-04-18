
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + "movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        ArrayList<Rating> avgList = tr.getAverageRatings(35);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            //System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        Filter f = new YearAfterFilter(2000);
        
        ArrayList<Rating> avgList = tr.getAverageRatingsByFilter(20,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        Filter f = new GenreFilter("Comedy");
        
        ArrayList<Rating> avgList = tr.getAverageRatingsByFilter(20,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            //System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
            //System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        Filter f = new MinutesFilter(105, 135);
        
        ArrayList<Rating> avgList = tr.getAverageRatingsByFilter(5,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            //System.out.println(r.getValue() + "\tTime : " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        ArrayList<Rating> avgList = tr.getAverageRatingsByFilter(4,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        Filter f1 = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        
        ArrayList<Rating> avgList = tr.getAverageRatingsByFilter(8,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            //System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
            //System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + tr.getRaterSize());
        
        Filter f1 = new MinutesFilter(90,180);
        Filter f2 = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        
        ArrayList<Rating> avgList = tr.getAverageRatingsByFilter(3,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            System.out.println(r.getValue() + "\tTime: " + MovieDatabase.getMinutes(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }
}
