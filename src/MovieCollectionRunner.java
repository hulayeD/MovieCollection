import java.util.ArrayList;

public class MovieCollectionRunner
{
  public static void main(String[] args)
  {
   MovieCollection collection = new MovieCollection("src\\movie.csv");
   myCollection.menu();
    ArrayList<Movie> movieList = collection.getMovies();
    for (Movie movie : movieList)
    {
      System.out.println(movie);
    }


  }
}