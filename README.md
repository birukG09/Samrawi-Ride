# ሳምራዊ Samrawi Rode

> "Free rides. Rooted in faith. Built for Ethiopia."
>
>

An Ethiopian community carpooling platform connecting drivers with empty seats to passengers who need a free ride, matched by shared route and location. Built as a Java console application demonstrating core OOP concepts.

---

 ## OOP Concepts Used

* **Classes & Objects** → `persons/Person.java`
* **Encapsulation**

  * `persons/User.java`
  * `persons/Person.java`
  * `persons/NewDriver.java`
* **Inheritance**

  * `Driver → User → Person`
  * `VerifiedDriver → Driver → User → Person`
  * `PriorityPassenger → Passenger → User → Person`
* **Method Overriding**

  * `displayProfile()`
  * `requestRide()`
  * `confirmRide()`
* **Method Overloading**

  * `system/ReportGenerator.java`
  * `generateReport()` with multiple versions
* **Polymorphism / Superclass Reference**

  * `User[] users = { ... }` in `Main.java`


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
