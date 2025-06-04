import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData instance = null;

    private List<Product> products = new ArrayList<>();

    private List<Movie> movies = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();

    public AppData() {
        this.products.add(new Product("Coca-cola",2.5));
        //add remaining products

        this.movies.add();
        //needs class Movie implemented

        this.sessions.add();
        //needs class Session implemented
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
            loadData();
        }
        return instance;
    }

    private static void loadData() {}

    private static void saveData() {}

    public List<Product> getProducts() {
        return products;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
