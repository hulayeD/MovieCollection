import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }

  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    /* TASK 4: IMPLEMENT ME! */
  }

  private void searchKeywords()
  {
    System.out.println("Enter a keyword");
    String Searchkeywords= scanner.nextLine();

    Searchkeywords = Searchkeywords.toLowerCase();

    ArrayList<Movie> words = new ArrayList<Movie>();

    for (int i = 0; i < movies.size(); i++)
    {
      String movieKeyword = movies.get(i).getKeywords();
      movieKeyword = movieKeyword.toLowerCase();

      if (movieKeyword.indexOf(Searchkeywords) != -1)
      {
        //add the Movie objest to the results list
        words.add(movies.get(i));
      }
    }
    // sort the results by title
//    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < words.size(); i++)
    {
      String keyword = words.get(i).getKeywords();

      // this will print index 0 as choice 1 in the results list; better for user!
      int num = i + 1;

      System.out.println("" + num + ". " + keyword);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = words.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();

  }
  
  private void listGenres()
  {

  }
  
  private void listHighestRated()
  {
    /* TASK 6: IMPLEMENT ME! */
  }
  
  private void listHighestRevenue()
  {
    /* TASK 6: IMPLEMENT ME! */
  }

  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader=new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      movies= new ArrayList<Movie>();

      while((line= bufferedReader.readLine() != null))
      {
        String[] movieFromCSV=line.split(", ");

        String title=movieFromCSV[0];
        String cast=movieFromCSV[1];
        String director=movieFromCSV[2];
        String tagline=movieFromCSV[3];
        String keywords=movieFromCSV[4];
        String overview= movieFromCSV[5];
        int runtime= Integer.parseInt(movieFromCSV[6]);
        String genres=movieFromCSV[7];
        double userRating=Integer.parseInt(movieFromCSV[8]);
        int year= Integer.parseInt(movieFromCSV[9]);
        int revenue= Integer.parseInt(movieFromCSV[10]);

        Movie nextMovie= new Movie(title,cast,director,tagline,keywords,overview,runtime,genres,userRating,year,revenue);
        movies.add(nextMovie);


      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {

      System.out.println("Unable to access " + exception.getMessage());
    }
  }
  
  // ADD ANY ADDITIONAL PRIVATE HELPER METHODS you deem necessary
//  private void searchCast()
//  {
//    ArrayList<String> cast = new ArrayList<>();
//    for (int i = 0; i < movies.size(); i++)
//    {
//      String fullCast = movies.get(i).getCast();
//      String[] castList = fullCast.split("\\|");
//      for (String actor : castList)
//      {
//        for (String actors : cast)
//        {
//          if (actor.equals(actors) == false)
//          {
//            cast.add(actor);
//          }
//        }
//      }
//      // get search term
//      System.out.print("Enter a person to search for (first or last name): ");
//      String name = scanner.nextLine();
//      ArrayList<String> results = new ArrayList<>();
//      for (String actor : cast)
//      {
//        if (actor.indexOf(name) != -1)
//        {
//          results.add(actor);
//        }
//      }
//      //display results
//      for (int j = 0; j < results.size(); j++)
//      {
//        String actor = results.get(j);
//        int choiceNum = j + 1;
//        System.out.println("" + choiceNum + ". " + actor);
//      }
//
//      System.out.print("Which actor's movies do you want to explore?: ");
//      int choice = scanner.nextInt();
//      scanner.nextLine();
//
//
//    }
//  }
//
//  private void searchKeywords()
//  {
//    System.out.print("Enter a keyword search term: ");
//    String keyword = scanner.nextLine();
//    keyword = keyword.toLowerCase();
//
//    ArrayList<Movie> results = new ArrayList<Movie>();
//    for (int i = 0; i < movies.size(); i++)
//    {
//      String keywords = movies.get(i).getKeywords();
//      keywords = keywords.toLowerCase();
//      if (keywords.indexOf(keyword) != -1)
//      {
//        results.add(movies.get(i));
//      }
//    }
//    sortResults(results);
//    for (int i = 0; i < results.size(); i++)
//    {
//      String currentTitle = results.get(i).getTitle();
//      int choiceNum = i + 1;
//
//      System.out.println("" + choiceNum + ". " + currentTitle);
//    }
//
//    System.out.print("Which movie would you like to learn more about?\nEnter number: ");
//    int choice = scanner.nextInt();
//    scanner.nextLine();
//    Movie selectedMovie = results.get(choice - 1);
//
//    displayMovieInfo(selectedMovie);
//    System.out.println("\n ** Press Enter to Return to Main Menu **");
//    scanner.nextLine();
//  }

}