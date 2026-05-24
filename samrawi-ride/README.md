# ሳምራዊ Samrawi Ride

> "Free rides. Rooted in faith. Built for Ethiopia."
>
> Inspired by the Parable of the Good Samaritan — Luke 10:25-37

An Ethiopian community carpooling platform connecting drivers with empty seats to passengers who need a free ride, matched by shared route and location. Built as a Java console application demonstrating core OOP concepts.

---

## OOP Concept Reference Table

| Concept | Material | File | Lines | Note |
|---|---|---|---|---|
| Classes & Objects | V1.0, V2.0 | `persons/Person.java` | 1–45 | Base class modeling a real person |
| Encapsulation | V1.0, V2.0 | `persons/User.java` | 10–60 | `private` fields with validated getters/setters; `password` hashed and never exposed |
| Encapsulation | V2.0 | `persons/Person.java` | 12 | `nationalID` is private — stored for verification, never printed |
| Encapsulation | V2.0 | `persons/NewDriver.java` | 30 | `safetyScore` setter validates range 0.0–5.0 |
| Inheritance (3-level) | V3.0 | `persons/Driver.java` | 1 | `Driver extends User extends Person` |
| Inheritance (4-level) | V3.0 | `persons/VerifiedDriver.java` | 1 | `VerifiedDriver extends Driver extends User extends Person` |
| Inheritance (4-level) | V3.0 | `persons/PriorityPassenger.java` | 1 | `PriorityPassenger extends Passenger extends User extends Person` |
| Method Overriding | V4.0 | `persons/Driver.java` | ~55 | `@Override displayProfile()` — shows vehicle + routes |
| Method Overriding | V4.0 | `persons/VerifiedDriver.java` | ~25 | `@Override displayProfile()` — shows verification badge |
| Method Overriding | V4.0 | `persons/PriorityPassenger.java` | ~20 | `@Override requestRide()` — jumps to front of queue |
| Method Overriding | V4.0 | `rides/ScheduledRide.java` | ~30 | `@Override confirmRide()` — sends scheduled reminder |
| Method Overriding | V4.0 | `rides/InstantRide.java` | ~20 | `@Override confirmRide()` — immediate dispatch |
| Method Overloading | V4.0 | `system/ReportGenerator.java` | 10–60 | 3 overloaded `generateReport()` — full / by area / by month |
| Superclass Reference | V4.0 | `Main.java` | ~55 | `User[] users = { new VerifiedDriver(...), new RegularPassenger(...), ... }` |

---

## Inheritance Hierarchy

```
Person
└── User
    ├── Driver
    │   ├── VerifiedDriver   ← Background-checked, unlimited rides
    │   └── NewDriver        ← Probationary, max 3 rides/day
    └── Passenger
        ├── RegularPassenger ← Standard queue order
        └── PriorityPassenger← Elderly/disabled — jumps to front of queue

Ride
├── ScheduledRide  ← Booked in advance, sends reminder on confirm
└── InstantRide    ← Immediate dispatch
```

---

## File Structure

```
samrawi-ride/src/
├── Main.java                   — Entry point, console menu, superclass reference demo
├── persons/
│   ├── Person.java             — Superclass: name, age, nationalID (private), phone, email
│   ├── User.java               — Extends Person: username, password (private), safetyScore
│   ├── Driver.java             — Extends User: vehicle, licenseNumber, routeHistory
│   ├── VerifiedDriver.java     — Extends Driver: verificationDate, trustBadge
│   ├── NewDriver.java          — Extends Driver: dailyRideCount, MAX_RIDES_PER_DAY=3
│   ├── Passenger.java          — Extends User: rideHistory, preferredArea
│   ├── RegularPassenger.java   — Extends Passenger: standard queue
│   └── PriorityPassenger.java  — Extends Passenger: overrides requestRide() → front of queue
├── rides/
│   ├── Ride.java               — Superclass: rideID, route, driver, passenger, status, date
│   ├── ScheduledRide.java      — Extends Ride: overrides confirmRide() → sends reminder
│   └── InstantRide.java        — Extends Ride: overrides confirmRide() → immediate dispatch
├── models/
│   ├── Vehicle.java            — plateNumber, model, color, availableSeats
│   ├── Route.java              — origin, destination, estimatedDistanceKm
│   ├── Rating.java             — score (1–5), comment, ratedBy, timestamp
│   └── SafetyFlag.java         — reason, flaggedBy, severity (LOW/MEDIUM/HIGH)
└── system/
    ├── SamrawiSystem.java      — Core matching engine: routes drivers to passengers
    ├── SafetyManager.java      — Tracks flags, auto-blocks at 3 flags
    └── ReportGenerator.java    — 3 overloaded generateReport() methods
```

---

## Compile & Run

```bash
# From the samrawi-ride/ directory
cd samrawi-ride

# Compile all source files into out/
javac -d out src/persons/*.java src/rides/*.java src/models/*.java src/system/*.java src/Main.java

# Run
java -cp out Main
```

Or use the included script:

```bash
./run.sh
```

**Requirements:** Java 8 or above · IntelliJ IDEA / VS Code / Eclipse all work

---

## Console Menu

```
[1]  Register as Driver
[2]  Register as Passenger (Regular / Priority)
[3]  Offer a Ride (Driver menu)
[4]  Request a Ride (Passenger menu)
[5]  Match Passengers to Available Drivers
[6]  Confirm or Decline a Ride Request
[7]  Complete Ride and Rate Each Other
[8]  View User Profile and Safety Score
[9]  View Flagged and Blocked Users
[R]  Generate Report (full / by area / by month)
[0]  Exit
```

---

## Safety Features

| Feature | Implementation |
|---|---|
| Private National ID | `private String nationalID` in `Person.java` — never printed publicly |
| Safety Score | `private double safetyScore` in `User.java` — setter validates 0.0–5.0 range |
| Hidden Phone | Phone only revealed after ride status is `CONFIRMED` |
| Flag System | `SafetyFlag` objects per user — 3 flags triggers auto-block via `SafetyManager` |
| Driver Tiers | `VerifiedDriver` (unlimited) vs `NewDriver` (max 3/day) |
| Priority Queue | `PriorityPassenger.requestRide()` places elderly/disabled at front of queue |

---

## Evaluation Checklist

- [x] Classes defined with attributes and methods
- [x] Objects instantiated in `Main.java`
- [x] `private` fields with getters/setters and validation
- [x] 4-level inheritance hierarchy (`VerifiedDriver` and `PriorityPassenger`)
- [x] `@Override` on 5+ methods (`displayProfile`, `confirmRide`, `requestRide`)
- [x] 3 overloaded `generateReport()` methods
- [x] `User[]` superclass reference array at top of `main()`
- [x] Java naming conventions followed throughout
- [x] `// V1.0`, `// V2.0`, `// V3.0`, `// V4.0` comments on every OOP concept
- [x] `README.md` with concept reference table
