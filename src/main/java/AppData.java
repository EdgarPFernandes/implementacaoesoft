import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData instance = null;

    private List<BarProduct> barProducts = new ArrayList<>();
    private final String FILE_NAME = "products.dat";

    private List<Movie> movies = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();




    public AppData() {
        this.barProducts.add(new BarProduct("Coca-cola",2.5,10));
        this.barProducts.add(new BarProduct("Pepsi", 2.3, 15));
        this.barProducts.add(new BarProduct("Fanta", 2.4,12));
        this.barProducts.add(new BarProduct("Sprite", 2.2,15));

        this.barProducts.add(new BarProduct("Coca-cola",2.5,10));
        this.barProducts.add(new BarProduct("Pepsi", 2.3, 15));
        this.barProducts.add(new BarProduct("Fanta", 2.4,12));
        this.barProducts.add(new BarProduct("Sprite", 2.2,15));
        this.barProducts.add(new BarProduct("Coca-cola",2.5,10));
        this.barProducts.add(new BarProduct("Pepsi", 2.3, 15));
        this.barProducts.add(new BarProduct("Fanta", 2.4,12));
        this.barProducts.add(new BarProduct("Sprite", 2.2,15));
        this.barProducts.add(new BarProduct("Coca-cola",2.5,10));
        this.barProducts.add(new BarProduct("Pepsi", 2.3, 15));
        this.barProducts.add(new BarProduct("Fanta", 2.4,12));
        this.barProducts.add(new BarProduct("Sprite", 2.2,15));
        this.barProducts.add(new BarProduct("Coca-cola",2.5,10));
        this.barProducts.add(new BarProduct("Pepsi", 2.3, 15));
        this.barProducts.add(new BarProduct("Fanta", 2.4,12));
        this.barProducts.add(new BarProduct("Sprite", 2.2,15));
        this.barProducts.add(new BarProduct("Coca-cola",2.5,10));
        this.barProducts.add(new BarProduct("Pepsi", 2.3, 15));
        this.barProducts.add(new BarProduct("Fanta", 2.4,12));
        this.barProducts.add(new BarProduct("Sprite", 2.2,15));

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
            out.writeObject(barProducts);
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
                barProducts = (List<BarProduct>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<BarProduct> getBarProducts() {
        return barProducts;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
