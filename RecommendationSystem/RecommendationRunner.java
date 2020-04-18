
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> list = new ArrayList<String>();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        list.add("68646");list.add("264464");list.add("120338");list.add("120755");list.add("435761");
        list.add("119217");list.add("55928");list.add("1872181");list.add("1436562");list.add("119654");
        list.add("107290");list.add("2139555");list.add("3538766");list.add("1673434");list.add("2226417");
        list.add("93773");list.add("1392190");list.add("3678782");list.add("2267998");list.add("103776");
        
        return list;
    }
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> list = fr.getSimilarRatings(webRaterID,50,6);
        Rater rater = RaterDatabase.getRater(webRaterID);
        if(list.size() == 0)
            System.out.println("SORRY!!! Couldn't find any recommendation.");
        else{
            String start = "<style>table,th,td,tr{border: 1px solid black; padding: 3px}</style><table><tr><th>Poster</th><th>Movie</th></tr>";
            System.out.println(start);
            int i = 0;
            for(Rating r : list){
                String movieID = r.getItem();
                if(!rater.hasRating(movieID)){
                    if(i<20){
                        String poster = MovieDatabase.getPoster(movieID).substring(7);
                        String middle = "<tr><td><a href=\"data/" + poster + "\">";
                        middle += "<img src=\"data/" + poster + "\" height=\"100\" align=\"center\">" + "</a></td><td>";
                        middle += MovieDatabase.getYear(movieID) + " &nbsp;&nbsp; <a href=\"http://www.imdb.com/title/tt" + movieID + "/\">" + MovieDatabase.getTitle(movieID) + "</a></td></tr>";
                        System.out.println(middle);
                    }
                    i++;
                }
            }
            String end = "<table>";
            System.out.println(end);
        }
    }
}
