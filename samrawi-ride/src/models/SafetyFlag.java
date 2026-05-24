package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SafetyFlag {

    public enum Severity { LOW, MEDIUM, HIGH }

    private String reason;
    private String flaggedBy;
    private Severity severity;
    private LocalDateTime timestamp;

    public SafetyFlag(String reason, String flaggedBy, Severity severity) {
        this.reason = reason;
        this.flaggedBy = flaggedBy;
        this.severity = severity;
        this.timestamp = LocalDateTime.now();
    }

    public String getReason() { return reason; }
    public String getFlaggedBy() { return flaggedBy; }
    public Severity getSeverity() { return severity; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + severity + "] " + reason + " — reported by " + flaggedBy
                + " on " + timestamp.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }
}
