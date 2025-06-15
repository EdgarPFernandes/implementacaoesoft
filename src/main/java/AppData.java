import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData instance = null;

    private List<Product> products = new ArrayList<>();
    private final String FILE_NAME = "products.dat";

    private List<Movie> movies = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();



    public AppData() {
        this.products.add(new Product("Coca-cola",2.5));
        this.products.add(new Product("Pepsi", 2.3));
        this.products.add(new Product("Fanta", 2.4));
        this.products.add(new Product("Sprite", 2.2));

        this.products.add(new Product("Coca-cola",2.5));
        this.products.add(new Product("Pepsi", 2.3));
        this.products.add(new Product("Fanta", 2.4));
        this.products.add(new Product("Sprite", 2.2));
        this.products.add(new Product("Coca-cola",2.5));
        this.products.add(new Product("Pepsi", 2.3));
        this.products.add(new Product("Fanta", 2.4));
        this.products.add(new Product("Sprite", 2.2));
        this.products.add(new Product("Coca-cola",2.5));
        this.products.add(new Product("Pepsi", 2.3));
        this.products.add(new Product("Fanta", 2.4));
        this.products.add(new Product("Sprite", 2.2));
        this.products.add(new Product("Coca-cola",2.5));
        this.products.add(new Product("Pepsi", 2.3));
        this.products.add(new Product("Fanta", 2.4));
        this.products.add(new Product("Sprite", 2.2));

        //add remaining products

        //this.movies.add();
        //needs class Movie implemented

        //this.sessions.add();
        //needs class Session implemented
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }



    public void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                products = (List<Product>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
