package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rating {

    private int score;
    private String comment;
    private String ratedBy;
    private LocalDateTime timestamp;

    public Rating(int score, String comment, String ratedBy) {
        this.score = (score >= 1 && score <= 5) ? score : 3;
        this.comment = comment;
        this.ratedBy = ratedBy;
        this.timestamp = LocalDateTime.now();
    }

    public int getScore() { return score; }
    public String getComment() { return comment; }
    public String getRatedBy() { return ratedBy; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        String stars = "★".repeat(score) + "☆".repeat(5 - score);
        return stars + " by " + ratedBy + " — \"" + comment + "\" ["
                + timestamp.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + "]";
    }
}
