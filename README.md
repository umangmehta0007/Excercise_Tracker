
---
# Title: Exercise Tracker

### Author: Umang Mehta (7885176)

#### Date: October 30, 2025

# Overview

Exercise Tracker is an implementation of real apps like Starva and Apple Fitness for COMP 2450 in FALL 2025.
However, due to accessibility issues and knowledge limitations, we're using a grid system instead of a real 
Map and GPS to record and track our data. The program begins as just you've finished a workout and came on
your desk to enter all details about it.

* As you begin, it records your information as in Starva/Apple fitness( Here it asks for name and weight).
* There is MAP which creates a Grid with Rows and Coloumns defined by the user along with user defined obstacles.   
* Track workouts including the gears used and the route taken. 
* The goal is to have your data stored and print them when you need details. (Example: Viewing the data within user specified date range)

**I separated IMapDataType (which marks objects that can be placed on the grid)
from IObjectsWithCoordinates (which adds coordinate behavior).**

This avoids forcing Empty to implement meaningless methods, while still allowing
consistent polymorphism in the map grid.

[Starva]: https://en.wikipedia.org/wiki/Strava
[Apple Fitness]: https://en.wikipedia.org/wiki/Fitness_(Apple)

## Running

This project was developed using IntelliJ IDEA and uses Maven, so there are two
ways to run it:

1. Open the class called `Main.java` and click the green play button on the
   `main`method, or
2. Run Maven on the command line:
   
```
mvn compile exec:java -Dexec.mainClass="comp2450.Main"
```

# Domain model

## Resources
* I learnt some important concepts of domain modeling at: 
<https://www.thoughtworks.com/en-ca/insights/blog/agile-project-management/domain-modeling-what-you-need-to-know-before-coding>

* Making records was not an easy task and direct help was used from :
<https://dev.java/learn/records/>

* Discussion forums were a BIG part of learning, along with discussion with classmates which continuously led me implement the 
changes and learning new stuff. (luckily there's no e-Link for friends).

* Mermaid's Syntax for class diagrams was not a piece of cake, used this as s guide: 
<https://mermaid.js.org/syntax/classDiagram.html>

* To learn about new classes and frameworks used like: Calendar, TreeSet, Lists :
 <https://docs.oracle.com/javase/8/docs/api/allclasses-noframe.html>

* To be honest chatGPT helped me study a lot about topics and definitions by being concise to the topic and in easy and understandable english words.
* I explored multiple approaches for creating the grid. Initially I had one idea, but after discussion with classmates and reviewing suggestions from Professor and examples from ChatGPT.
* I implemented an interface-based solution. ( No snippet was taken from anywhere, totally own work after learning implementations.)
```mermaid

classDiagram
        
     note for Person"Invariant Properties
    <ul>
    <li>name != null</li>
    <li>name.length() >= 1</li>
    <li>weight > 0</li>
    <li>myActivityList != null</li>
    <li>gearsEquipped != null</li>
    <li>loop: myActivityList has no null elements</li>
    <li>loop: gearsEquipped has no null elements</li>
    </ul>"
    class Person{

        -String name
        -double weight

        -list~Activity~activitiesDone
        -list~Gear~gearsEquipped

        +addActivity(Activity activity) void
        +removeActivity(int index) void

        +addGear(Gears gear) void
        +removeGear(Gears gear) void

        +getName() String
        +getMyActivityList() List~Activity~
        +getGearsEquipped() Set~Gears~
        +getWeight() double
        +setWeight(double weight) void
    }
    note for Activity "Invariant Properties
    <ul>
    <li>name != null</li>
    <li>name.length() >= 1</li>
    <li>gearsUsed != null</li>
    <li>routeTaken != null</li>
    <li>routeTaken.size() >= 1</li>
    <li>date != null</li>
    <li>startingCoordinate!=null</li>
    <li>distance > 0</li>
    <li>currWeight > 0</li>
    <li>caloriesBurnt >= 0</li>
    <li>loop: check if coordinates of Route are assigned a null value<li>
    </ul>
     "
    class Activity{

        -double CALORIES_CONSTANT <<final>>
        -String name
        -Gears gearsUsed
        -List~Coordinates~ routeTaken
        -Calendar date
        -Coordinates startingCoordinate
        -double distance
        -double currWeight
        -double caloriesBurnt

        +getGears() ~Gear~
        +getCalendar() ~Calendar~
        +getDistance() double
        +getName() String
        +getcaloriesBurnt() double
        +addCoordiantes()
        +getMappingObject() List~Coordinates~
    }

    note for Map" Invariant Properties
       <ul>
    <li>name != null</li>
    <li>name.length() >= 1</li>
    <li>obstacles != null</li>
    <li>activities != null</li>
    <li>dimensions != null</li>
    <li>myGird!=null</li>
    <li>myGrid.length>=1</li> //checking for valid coloumns
    <li>myGrid[0].length>=1 //checking for valid rows
    
    <li>loop: no obstacles added here are null</li>
    <li>loop: no routes added inside each activity here are null</li>
    <li>loop: no Coordinate inside obstacles lie outside map</li>
    <li>loop: no Coordinate inside Route inside each activity lie outside map</li>
    </ul>
        "
    class Map{

        -String name
        -list~Obstacles~obsctacles
        -list~Activity~activities
        -Dimensions dimensions
        -Imapping[][] myGrid

        +addObstacles(Obstacle obs) void
        +removeObstacles(int index) void
        +addActivity(Activity activity) void
        +removeActivity(int index) void

        +createGrid(List~Obstacle~ obstacles, list~Activity~route )
        -addObstacleToGrid(List~Obstacle~ obstacles)
        -addObs(Obstacle obs)
        -addRouteToGrid(list~Activity~activities)
        -addRoute(Activity act)

        +getObsInMap() List~Obstacle~
        +getActivities() List~Activity~
        +getDimensions() Dimension
        +getGrid() IMapping[][]
        +getName() String

    }
    note for Obstacle" Invariant Properties
    <ul>
    <li>name != null</li>
    <li>name.length() >= 1</li>
    <li>coordinatesCovered != null</li>
    <li>loop: no coordinates added are null</li>
    </ul>"

    class Obstacle{

        -String name
        -List~Coordinates~ coordinatesCovered

        +addCoordinates(Coordinates coordinates) void
        +getMappingObject() List~Coordinates~
        +getName() String
    }
        
        
        note for Empty "Invariant Properties
        <ul> 
        <li>Always represents an empty grid cell (no internal state).
        <ul>"
        
        class Empty{
            
        }

        note for Coordinates"Invariant Properties
        <ul>
        <li> xCoordinate >=0 </li>
        <li> yCoordinate>=0 </li>
        <ul>"
    class Coordinates{
        <<Record>>
        -int xCoordinate
        -int yCoordinate
    }

    note for Gears "Invariant Properties
         <ul>
         <li>name != null</li>
         <li>name.length() >= 1</li>
         <li>uses != null</li>
         <li>uses.length() >= 1</li> 
         
         Method Preconditions
            <li>compareTo(Gears gear): gear != null</li>
        </ul>
        "
        
    class Gears{
        -String name
        -String summary

        +getName() String
        +getUses() String
        +compareTo(Gear gear) int
    }

    note for IObjectsWithCoordinates "Invariant Properties
    <ul>
    <li>Interfaces have no direct invariants as they hold no data.</li>
    </ul>"

    class IObjectsWithCoordinates{
            <<Interface>>
            +getMappingObject() List~Coordinates~
            +addCoordinates(Coordinates coordinates) void
    }

    note for IMapDataType "Invariant Properties
    <ul>
    <li>Interfaces have no direct invariants as they hold no data.</li>
    </ul>"
    
    class IMapDataType{
        <<Interface>>
    }
    note for Dimensions"Invariant Properties
            <ul>
            <li> nRows >=1 </li>
            <li> nCols>=1 </li>
            <ul>
     "
            
    class Dimensions{
            <<Record>>
            -int nRows
            -int nCols
    }
    Person *-- Gears
    Person *-- Activity
    Person o-- Map
    
    Obstacle o-- Coordinates
    
    Map *-- Obstacle
    Map o-- Activity
    Map *-- Dimensions
    Map *--Empty

    Activity o-- Gears

    Activity ..|> IObjectsWithCoordinates
    Obstacle ..|> IObjectsWithCoordinates
    
    Activity ..|> IMapDataType
    Obstacle ..|> IMapDataType
    Empty ..|> IMapDataType
```














