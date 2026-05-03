package config;

public class imageSession {
    private static imageSession instance;
    private String imagePath;

    // Private constructor to prevent instantiation
    private imageSession() {}

    // Singleton pattern to ensure only one session exists
    public static synchronized imageSession getInstance() {
        if (instance == null) {
            instance = new imageSession();
        }
        return instance;
    }

    // Getter for the image path
    public String getImagePath() {
        return imagePath;
    }

    // Setter for the image path
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}