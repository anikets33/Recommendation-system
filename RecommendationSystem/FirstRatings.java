
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec : parser){
            
            String id = rec.get(0);
            String title = rec.get(1);
            String year = rec.get(2);
            String genres = rec.get(4);
            String director = rec.get(5);
            String country = rec.get(3);
            String poster = rec.get(7);
            int minutes = Integer.parseInt(rec.get(6));
            
            Movie m = new Movie(id, title, year, genres, director, country, poster, minutes);
            list.add(m);
        }
        return list;
    }
    
    public int comedyMovies(ArrayList<Movie> list){
        int count = 0;
        for(Movie m : list){
            String genre = m.getGenres();
            if(genre.contains("Comedy"))    count++;
        }
        return count;
    }
    
    public int greaterMovies(ArrayList<Movie> list){
        int count = 0;
        for(Movie m : list){
            int length = m.getMinutes();
            if(length>150)    count++;
        }
        return count;
    }
    
    public void directMovies(ArrayList<Movie> list){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(Movie m : list){
            String str = m.getDirector();
            str = str.replaceAll(", ", ",");
            String[] s = str.split(",");
            for(String name : s){
                if(map.containsKey(name)){
                    map.put(name,map.get(name) + 1);
                }
                else{
                    map.put(name,1);
                }
            }
        }
        int max = 0;
        String dr = "";
        for(String name : map.keySet()){
            if(max < map.get(name)){
                dr = name;
                max = map.get(name);
            }
        }
        System.out.println(map);
        System.out.println(max);
        System.out.println(dr);
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec : parser){
            String id = rec.get(0);
            int i = 0;
            for(i=0; i<list.size(); i++){
                Rater rater = list.get(i);
                if(rater.getID().equals(id)){
                    rater.addRating(rec.get(1), Double.parseDouble(rec.get(2)));
                    break;
                }
            }
            if(list.size() == i){
                Rater r = new EfficientRater(id);
                r.addRating(rec.get(1), Double.parseDouble(rec.get(2)));
                list.add(r);                        
            }        
        }
        return list;
    }
    
    public HashMap<String, Integer> numberOfRaters(ArrayList<Rater> list){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(Rater r : list){
            if(map.containsKey(r.getID())){
                map.put(r.getID(), map.get( r.getID() ) + 1);
            }else{
                map.put(r.getID(),1);
            }
        }
        return map;
    }
    
    public int numberOfRatingsByRater(HashMap<String, Integer> map, String item){
        for(String name : map.keySet()){
            if(name.equals(item))
                return map.get(name);
        }
        return -1;
    }
    
    public void maxRater(HashMap<String, Integer> map){
        int max = Collections.max(map.values());
        int count = 0;
        for(String name : map.keySet()){
            if(map.get(name) == max){
                System.out.println(name + " has max rating with " + max + " rating");
                count++;
            }
        }
        System.out.println(count + " no of raters rated maximum");
    }
    
    public int numberOfRatingsOfMovie(String id, ArrayList<Rater> list){
        int count = 0;
        for(Rater r : list){
            ArrayList<String> item = r.getItemsRated();
            if(item.contains(id))   count++;
        }
        return count;
    }
    
    public int uniqueMoviesRated(ArrayList<Rater> list){
        HashSet<String> set = new HashSet<String>();
        for(Rater r : list){
            ArrayList<String> item = r.getItemsRated();
            for(String movie : item){
                set.add(movie);
            }
        }
        return set.size();
    }

    public void testLoadMovies(){
        //ArrayList<Movie> list = loadMovies("/data/ratedmovies_short.csv");
        //ArrayList<Movie> list = loadMovies("/data/ratedmoviesfull.csv");
        //for(Movie m : list){
        //    System.out.println(m);
        //}
        //System.out.println(list.size());
        //System.out.println(comedyMovies(list));
        //System.out.println(greaterMovies(list));
        //directMovies(list);
        
        //ArrayList<Rater> listRater = loadRaters("/data/ratings_short.csv");
        ArrayList<Rater> listRater = loadRaters("/data/ratings.csv");
        
        System.out.println(listRater.size());
        //System.out.println(listRater);
        //HashMap<String,Integer> map = numberOfRaters(listRater);
        //System.out.println(map.size());
        //for(String name : map.keySet()){
        //    System.out.println(name + " has done " + map.get(name) + " ratings.");
        //    ratingsByARater(listRater, name);
        //}
        //System.out.println(numberOfRatingsByRater(map, "193"));
        //maxRater(map);
        //System.out.println(numberOfRatingsOfMovie("1798709", listRater));
        //System.out.println(uniqueMoviesRated(listRater));
    }
}
