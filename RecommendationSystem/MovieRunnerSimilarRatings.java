
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + "movies");
        System.out.println("number of raters are : " + RaterDatabase.size());
        
        ArrayList<Rating> avgList = fr.getAverageRatings(35);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            //System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("number of raters are : " + RaterDatabase.size());
        
        Filter f1 = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        
        ArrayList<Rating> avgList = fr.getAverageRatingsByFilter(8,f);
        System.out.println("found : " + avgList.size());
        Collections.sort(avgList);
        
        for(Rating r : avgList){
            //System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
            //System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings();
        //MovieFilters fr = new MovieFilters();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> list = fr.getSimilarRatings("337",10,3);
        int i = 0;
        for(Rating r : list){
            String movieID = r.getItem();
            Double weight = r.getValue();
            if(i<10)
            System.out.println(i + " " + String.format("%.2f", weight) + " " + MovieDatabase.getTitle(movieID));
            i++;
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings();
        //MovieFilters fr = new MovieFilters();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f1 = new GenreFilter("Mystery");
        //AllFilters f = new AllFilters();
        //f.addFilter(f1);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("964",20,5,f1);
        int i = 0;
        for(Rating r : list){
            String movieID = r.getItem();
            Double weight = r.getValue();
            if(i<10)
            System.out.println(i + " " + String.format("%.2f", weight) + " " + MovieDatabase.getTitle(movieID));
            i++;
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings();
        //MovieFilters fr = new MovieFilters();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f1 = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("120",10,2,f);
        int i = 0;
        for(Rating r : list){
            String movieID = r.getItem();
            Double weight = r.getValue();
            if(i<10)
            System.out.println(i + " " + String.format("%.2f", weight) + " " + MovieDatabase.getTitle(movieID));
            i++;
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes (){
        FourthRatings fr = new FourthRatings();
        //MovieFilters fr = new MovieFilters();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f1 = new GenreFilter("Drama");
        Filter f2 = new MinutesFilter(80,160);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("168",10,3,f);
        int i = 0;
        for(Rating r : list){
            String movieID = r.getItem();
            Double weight = r.getValue();
            if(i<10)
            System.out.println(i + " " + String.format("%.2f", weight) + " " + MovieDatabase.getTitle(movieID));
            i++;
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings();
        //MovieFilters fr = new MovieFilters();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new MinutesFilter(70,200);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("314",10,5,f);
        int i = 0;
        for(Rating r : list){
            String movieID = r.getItem();
            Double weight = r.getValue();
            if(i<10)
            System.out.println(i + " " + String.format("%.2f", weight) + " " + MovieDatabase.getTitle(movieID));
            i++;
        }
    }
}
