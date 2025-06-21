import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData instance = null;

    private List<BarProduct> barProducts = new ArrayList<>();
    private final String FILE_NAME_PRODUCTS = "products.dat";

    private List<Movie> movies = new ArrayList<>();
    private final String FILE_NAME_MOVIES = "movies.dat";

    private List<Session> sessions = new ArrayList<>();
    private final String FILE_NAME_SESSIONS = "sessions.dat";

    private List<Sala> salas  = new ArrayList<>();
    private final String FILE_NAME_SALAS = "salas.dat";

    private AppData() {
        // Tenta carregar de ficheiros
        loadDataProducts();
        loadDataMovies();
        loadDataSessions();
        loadDataSalas();

        // Se listas estiverem vazias, cria dados de exemplo
        if (barProducts.isEmpty()) loadDefaultBarProducts();
        if (movies.isEmpty()) loadDefaultMovies();
        if (sessions.isEmpty()) loadDefaultSessions();
        if (salas.isEmpty()) loadDefaultSalas();
    }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    private void loadDefaultBarProducts() {
        barProducts.add(new BarProduct("Coca-cola", 2.5, 10,1,0.6));
        barProducts.add(new BarProduct("Pepsi", 2.3, 15,2,0.3));
        barProducts.add(new BarProduct("Fanta", 2.4, 12,1,1.2));
        barProducts.add(new BarProduct("Sprite", 2.2, 15,3, 1));
    }

    private void loadDefaultMovies() {
        movies.add(new Movie(1, "Sinner", "Horror", "EUA", "Ryan Coogler", "Warner Bros.", "English", "Português", 16, 137, 2025, 35.2));
        movies.add(new Movie(2, "Conclave", "Drama", "EUA", "Edward Berger", "Warner Bros.", "English", "Português", 12, 120, 2024, 30.5));
    }

    private void loadDefaultSessions() {
        sessions.add(new Session(movies.get(0),"A1","17:00","21/06/2025"));
        sessions.add(new Session(movies.get(1),"A1","19:00","21/06/2025"));
    }

    private void loadDefaultSalas() {
        salas.add(new Sala("A1", 5, 8, "Dolby", "Normal", "Standard", "Nenhuma"));
    }

    // ------------------------- SAVE METHODS -------------------------

    public void saveDataProducts() {
        saveToFile(FILE_NAME_PRODUCTS, barProducts);
    }

    public void saveDataMovies() {
        saveToFile(FILE_NAME_MOVIES, movies);
    }

    public void saveDataSessions() {
        saveToFile(FILE_NAME_SESSIONS, sessions);
    }

    public void saveDataSalas() {saveToFile(FILE_NAME_SALAS, salas);}

    private void saveToFile(String filename, Object data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------------- LOAD METHODS -------------------------

    @SuppressWarnings("unchecked")
    private void loadDataProducts() {
        barProducts = (List<BarProduct>) loadFromFile(FILE_NAME_PRODUCTS);
    }

    @SuppressWarnings("unchecked")
    private void loadDataMovies() {
        movies = (List<Movie>) loadFromFile(FILE_NAME_MOVIES);
    }

    @SuppressWarnings("unchecked")
    private void loadDataSessions() {
        sessions = (List<Session>) loadFromFile(FILE_NAME_SESSIONS);
    }

    @SuppressWarnings("unchecked")
    private void loadDataSalas() {salas = (List<Sala>) loadFromFile(FILE_NAME_SALAS);}

    private Object loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                return obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    // ------------------------- GETTERS -------------------------

    public List<BarProduct> getBarProducts() {
        return barProducts;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public List<Sala> getSalas() {return salas;}

    public List<Ticket> getTicketType() {
        return getTicketType();
    }
}
