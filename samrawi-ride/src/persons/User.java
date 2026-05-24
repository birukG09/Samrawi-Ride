package persons;

import models.Rating;
import models.SafetyFlag;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {

    protected String username;

    private String password;

    private double safetyScore;

    protected double ratingAverage;
    protected boolean isActive;
    protected boolean isBlocked;

    protected List<Rating> ratings;
    protected List<SafetyFlag> flags;

    public User(String name, int age, String nationalID, String phone, String email,
                String username, String password) {
        super(name, age, nationalID, phone, email);
        this.username = username;
        this.password = hashPassword(password);
        this.safetyScore = 5.0;
        this.ratingAverage = 0.0;
        this.isActive = true;
        this.isBlocked = false;
        this.ratings = new ArrayList<>();
        this.flags = new ArrayList<>();
    }

    private String hashPassword(String raw) {
        return "hashed#" + raw.hashCode();
    }

    public void setSafetyScore(double score) {
        if (score >= 0.0 && score <= 5.0) {
            this.safetyScore = score;
        } else {
            System.out.println("  [Warning] Safety score must be between 0.0 and 5.0");
        }
    }

    public double getSafetyScore() { return safetyScore; }
    public String getUsername() { return username; }
    public boolean isActive() { return isActive; }
    public boolean isBlocked() { return isBlocked; }
    public List<SafetyFlag> getFlags() { return flags; }
    public List<Rating> getRatings() { return ratings; }

    public boolean checkPassword(String input) {
        return this.password.equals(hashPassword(input));
    }

    public void block() {
        this.isBlocked = true;
        this.isActive = false;
    }

    public void addRating(Rating r) {
        ratings.add(r);
        double total = ratings.stream().mapToInt(Rating::getScore).sum();
        this.ratingAverage = total / ratings.size();
    }

    public void addFlag(SafetyFlag flag) {
        flags.add(flag);
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("  Username     : " + username);
        System.out.printf("  Safety Score : %.1f / 5.0%n", safetyScore);
        System.out.printf("  Avg Rating   : %.1f / 5.0%n", ratingAverage);
        System.out.println("  Status       : " + (isBlocked ? "BLOCKED" : isActive ? "Active" : "Inactive"));
        System.out.println("  Flags        : " + flags.size());
    }

    @Override
    public String toString() {
        return username + " (" + name + ")";
    }
}
