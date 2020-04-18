
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    String[] directorsList;
    public DirectorsFilter(String directors){
        directorsList = directors.split(",");
    }
    
    @Override
	public boolean satisfies(String id) {
		for(String name : directorsList){
		    if(MovieDatabase.getDirector(id).contains(name))
		      return true;
		  }
		  return false;
	}
}
