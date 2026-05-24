package system;

import models.SafetyFlag;
import persons.User;

import java.util.List;

public class SafetyManager {

    private static final int MAX_FLAGS_BEFORE_BLOCK = 3;
    private static final double AUTO_FLAG_THRESHOLD = 2.5;

    public void flagUser(User user, String reason, String reportedBy, SafetyFlag.Severity severity) {
        SafetyFlag flag = new SafetyFlag(reason, reportedBy, severity);
        user.addFlag(flag);

        System.out.println("  [Safety] " + user.getUsername() + " flagged: " + flag);

        if (user.getFlags().size() >= MAX_FLAGS_BEFORE_BLOCK) {
            user.block();
            System.out.println("  [Safety] !! " + user.getUsername()
                    + " AUTO-BLOCKED after " + MAX_FLAGS_BEFORE_BLOCK + " flags.");
        }
    }

    public void updateSafetyScore(User user, double newScore) {
        user.setSafetyScore(newScore);
        if (newScore < AUTO_FLAG_THRESHOLD && !user.isBlocked()) {
            System.out.println("  [Safety] WARNING: " + user.getUsername()
                    + "'s safety score dropped below " + AUTO_FLAG_THRESHOLD);
        }
    }

    public void printUserFlags(User user) {
        List<SafetyFlag> flags = user.getFlags();
        if (flags.isEmpty()) {
            System.out.println("  No safety flags for " + user.getUsername());
            return;
        }
        System.out.println("  Flags for " + user.getUsername() + " (" + flags.size() + "):");
        for (SafetyFlag f : flags) {
            System.out.println("    - " + f);
        }
    }

    public void printFlaggedUsers(List<User> users) {
        boolean found = false;
        for (User u : users) {
            if (!u.getFlags().isEmpty() || u.isBlocked()) {
                found = true;
                System.out.println("  " + u.getUsername()
                        + (u.isBlocked() ? " [BLOCKED]" : " [flagged]")
                        + " — " + u.getFlags().size() + " flag(s)");
                for (SafetyFlag f : u.getFlags()) {
                    System.out.println("      " + f);
                }
            }
        }
        if (!found) System.out.println("  No flagged or blocked users.");
    }
}
