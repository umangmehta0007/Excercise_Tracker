
---
# Title: Exercise Tracker

### Author: Umang Mehta (7885176)

#### Date: November 24, 2025

# Overview

Exercise Tracker is an implementation of real apps like Starva and Apple Fitness for COMP 2450 in FALL 2025.
However, due to accessibility issues and knowledge limitations, we're using a grid system instead of a real 
Map and GPS to record and track our data. The program begins as just you've finished a workout and came on
your desk to enter all details about it.

* In this updated phase, As you being you're asked either to Create Profile or sign in if you have existing file.
* After signing in successfully you're able to see feed of the people you follow, add/remove follower, manage your current profile.
* Under managing your profile you'll be able to add activites, add/remove obstacles, add/remove gears.
* The new thing about adding activity here would be you can now choose they you want to find the Route. 
* Either you can enter manually, choose from your existing activites or enter starting and ending points for your route system will find a route for you from either of your existing one's or the follower's list. 

# Flows of interaction

## Diagrams

### Login Page

```mermaid

flowchart
    homescreen[[Home Screen]]

    homescreen ==Create Profile==> enterDetails

    enterDetails[Create Profile: Name and Weight]
    processplayer{Create Person}
    
    enterDetails==Enter Name and Weight==>processplayer
    processplayer -.Can't create valid person.->enterDetails
    processplayer -.Successfully Created a player.->homescreen


    existing[Select a Profile]
    loader{Load Profile}
    
    successLogin[Profile Screen]

    homescreen ==Sign in using existing==> existing
    
    existing==Selected Profile==>loader
    
    loader -.Invalid Selections.->existing
    loader -.SuccessLogin.->successLogin
    
    viewact[[View your activites]]
    viewfol[[View followers activities]]
    addfol[[Add Follower]]
    removefol[[Remove Follower]]
    manager[[Profile Management]]
    
    Logout{Logout Profile}

    successLogin==FeedSelection==>viewact
    successLogin==FeedSelection==>viewfol
    successLogin==FeedSelection==>addfol
    successLogin==FeedSelection==>removefol
    successLogin==ProfileManagement==>manager
    successLogin==Logout==>Logout




    viewact -..-> successLogin
    viewfol -..-> successLogin
    addfol -..-> successLogin
    removefol -..-> successLogin
    manager -..-> successLogin

    Logout -.Succesfully Logout to Home Screen.-> homescreen



```

### View your activities

```mermaid
flowchart

    viewact[[View Your Activities]]
    
    printMap{Loads Activity}

    viewact ==Select Activity to View==>printMap

    printMap -.Invalid Selection.-> viewact

    printMap -.Prints the Map Including activity.-> success

    success[[Feed Success View]]

```

### View Followers Activities
```mermaid
flowchart

    viewact[[View followers Activities]]
    
    printMap{Loads Activity of Follower}

    viewact ==Select Activity to View==>printMap

    printMap -.Invalid Selection.-> viewact

    printMap -.Prints the Map Including activity.-> success

    success[[Following feed Success View]]
```
### Add Follower

```mermaid
flowchart

    viewact[[Add Follower]]
    
    printMap{Add Person}

    viewact ==Select Person to add as Following==>printMap

    printMap -.Invalid Selection/Empty List of people.-> viewact

    printMap -.Adds Person to your following list.-> success

    success[[Added Successfully]]
```
### Remove Follower

```mermaid
flowchart

    viewact[[Remove Follower]]
    
    printMap{Remove Person}

    viewact ==Select Person to remove from Following==>printMap

    printMap -.Invalid Selection/Empty List of following.-> viewact

    printMap -.Removes Person to your following list.-> success

    success[[Added Successfully]]
```

### Profile Management

```mermaid
flowchart


    viewact[[Profile Management]]

    addAct[[Add Activity]]
    addGear[[Add Gear]]
    removeGear[[Remove Gear]]
    addObs[[Add Obstacle]]
    removeObs[[Remove Obstacle]]
    returnFeed{Return to Feed}

    viewact ==Add Activity==> addAct
    viewact ==Add Gear==> addGear
    viewact ==Remove Gear==> removeGear
    viewact ==Add Obstacle==> addObs
    viewact ==Remove Obstacle==> removeObs
    viewact ==Return to Feed==> returnFeed


    returnFeed-.Moves to Profile Screen.->viewact
```
### Add Gears

```mermaid
flowchart

    addgears[[Add Gears]]

    gearCreation{Create Gear}
    success[[successfully Added]]

    addgears ==nameInput and usesInput==>gearCreation
    %%addgears ==usesInput==>gearCreation

    gearCreation -.Invalid Entry.-> addgears
    gearCreation -.Adding Gears to Person Profile.-> success
    
   
```

### Remove Gears

```mermaid
flowchart

    addgears[[Remove Gears]]

    gearCreation{Remove Gear}
    success[[successfully Removed]]

    addgears ==Choose Gear to Remove==>gearCreation
    gearCreation -.Invalid Entry.-> addgears
    gearCreation -.Removing Gears from Person Profile.-> success
    
   
```
### Add Obstacle
```mermaid
flowchart

    addgears[[Add Obstacle]]

    gearCreation{Create Obstacle}
    success[[successfully Removed]]

    addgears ==Enter Name,Choose Coordinates for Obstacle==>gearCreation
    %%addgears ==Choose Coordinates for Obstacle==>gearCreation

    gearCreation -.Invalid Entry/Invalid Coordinates.-> addgears
    gearCreation -.Adds Obstacle to Map.-> success
   
```

### Remove Obstacle

```mermaid
flowchart

    addgears[[Remove Obstacle]]

    gearCreation{Remove Obstacle}
    success[[successfully Removed]]

    addgears ==Choose Obstacle==>gearCreation

    gearCreation -.Invalid Entry.-> addgears
    gearCreation -.Removing Obstacle from Map.-> success
   
```
### Creating Actvity

```mermaid
flowchart

    addAct[[Add Activity]]

    chooseRoute{Activity Processing}
    success[[Activity Added Successfully]]

    addAct ==nameInput,Date, Distance and Select Gear==>chooseRoute

%%addAct ==DateInput==>chooseGear
%%addAct==selectGear==>chooseGear


    chooseRoute -.Adds Gear to Activity.-> createRoute
    chooseRoute -.Invalid Entry.-> addAct


        
        
        
    createRoute[Create Route]
    manual[Choose Route Manually]
    
    existing[Choose Route from Existing Routes]
    exist{Getting Route from Activity}
    
    path[Find Path Using starting and ending point]
    pathf{Finding Path}
    
    success{Creating Activity}
    
    manCod{Adding Coordinates for Route}


    createRoute ==Manual Selection==> manual
    createRoute ==Existing Route==> existing
    createRoute ==Path Finding==> path
        
    
    manual==Enter The Coordinates==>manCod
    manCod-.continue adding.->manual
    manCod-.Invalid Entry.->manual
    manCod-.AddedRoute to Activity.->success
    
    existing==Choosing Activity you want the Route from==>exist
    exist-.Invalid Entry.->existing
    exist-.NoRouteAvailable.->createRoute

    exist-.AddedRoute to Activity.->success

    path==Entering Coordinates & choosing source for route==>pathf
    %%chooseFromOwnActivity====>pathf
    %%chooseFromFollowers====>pathf

    pathf-.InvalidEntry.->path

    %%chooseSource ==Own Activity==> pathf
    %%chooseSource ==Followers' Activity==> pathf
    
    pathf-.NoRouteAvailable.->createRoute
    pathf-.AddedRoute to Activity.->success
    
    success-..->

    created[[Activity created Success]]

    








```

## Resources
* Franklin's Repository - Literally The biggest help I could have used. 
* Stack interface was taken from 
<https://code.cs.umanitoba.ca/comp2450-fall2025/2450-stack/-/blob/main/src/main/java/ca/umanitoba/cs/umbrist1/generics/stacks/Stack.java>


Some notable components include:

* [Subgraph (for entire tasks)](https://mermaid.js.org/syntax/flowchart.html#subgraphs)
* [Double rectangle (start/end subtasks)](https://mermaid.js.org/syntax/flowchart.html#a-node-in-a-subroutine-shape)
* [Diamond (for processing)](https://mermaid.js.org/syntax/flowchart.html#decision-diamond)
* [Styling lines (scroll down, there's a table! thick lines for input, dashed lines for outputs)](https://mermaid.js.org/syntax/flowchart.html#minimum-length-of-a-link)




## Phase 2 Changes

- Implementation of UI and Logic layers was done, in which UI was reponsible for Validation and Logic Layer was used for processing and returning Domain Model Objects.
- Logic Layers updated/changed the state of my Domain model objects.
- All Validations eg: If it's a valid selection was done inside the UI
- A lot of methods were taken off form the domain model object and were directly put inside the Logic class
- Map here has became the basic example of Single Responsibility Principle and list of activates were taken and were processed by Logic now.
- Direct instantiation of the domain model object were removed and were made through Builders
- Coordinate which was a record before was changed to a class for the same building reason.
- We now had a thing where like in real life we can follow people and look there feed which includes exercise they did.
- A person now can remove/add followers with all powers same as phase 1
- Map has now been hard-coded so that it can be used globally by anyone using the software, we have a common map
- For adding activities now we have three different ways, which were choosing manually, choose from User's existing, and Path finding.
- For path finding we created algorithm that would ask for first and last coordinates you want and choose the way you want to exlore the route.
- Path could be chose either from the followers list or the self's list of activities done.
- Lastly, for the route finding algorithm Stack and linked list(self-created) was used to implement alg.


[Starva]: https://en.wikipedia.org/wiki/Strava
[Apple Fitness]: https://en.wikipedia.org/wiki/Fitness_(Apple)

## Running

This project was developed using IntelliJ IDEA and uses Maven, so there are two
ways to run it:

1. Open the class called `Main` and click the green play button on the
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

* This Phase 2 has been quite intense, not a lot of questions but a lot of thinking and working on real stuff
* AI Disclaimer: All implementations and logic were self created, all logic layer to UI were created, however some syntax's were asked form ChatGPT like the new Switch learnt, 
* I also tend to learn some understanding of Logic and UI but never shared the code, some technical and basic questions were asked that helped building some stuff but never asked for any code.
* Questions like: can we have multiple logic layers, inside logic layers, how would that affect the design and many more. But NO CODE/ LOGIC was taken. 
* Some design patterns were learnt too. 

* For learning More about Layers a Youtube channel was used :
<https://www.youtube.com/@ApnaCollegeOfficial>

* Mermaid's Syntax for class diagrams was not a piece of cake, used this as s guide: 
<https://mermaid.js.org/syntax/classDiagram.html>

* To learn about new classes and frameworks used like: LocalDateTime, TreeSet, Lists :
 <https://docs.oracle.com/javase/8/docs/api/allclasses-noframe.html>

* Computer Science help centre people have been a great help as well as my those classmates who did clear a lot of questions. 

```mermaid

classDiagram
        
     note for Person"Invariant Properties
    <ul>
    <li>name != null</li>
    <li>name.length() >= 1</li>
    <li>weight > 0</li>
    <li>myActivityList != null</li>
    <li>gearsEquipped != null</li>
    <li>following != null</li>
    <li>loop: myActivityList has no null elements</li>
    <li>loop: gearsEquipped has no null elements</li>
    <li>loop: following has no null elements</li>
 
    </ul>"
    class Person{

        -String name
        -double weight

        -list~Activity~activitiesDone
        -list~Gear~gearsEquipped
        -list~Person~following

        -activities(LocalDateTime startDate, LocalDateTime endDate) List~Activity~
        +totalCalories(LocalDateTime startDate, LocalDateTime endDate) double
        +totalDistance(LocalDateTime startDate, LocalDateTime endDate) double
        +compareTo(Person other) int
        +equals(Object o) boolean
        
        +addActivity(Activity activity) void
        +removeActivity(int index) void

        +addGear(Gears gear) void
        +removeGear(Gears gear) void
        
        +addFollower(Person person)void
        +removeFollower(Person person) void

        +getName() String
        +getMyActivityList() List~Activity~
        +getGearsEquipped() Set~Gears~
        +getWeight() double
        +getFollowing() Set~Person~
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
    <li>loop: check if coordinates of Route are assigned a null value<li>
    </ul>
     "
    class Activity{

        -double CALORIES_CONSTANT <<final>>
        -String name
        -Gears gearsUsed
        -List~Coordinates~ routeTaken
        -LocalDateTime date
        -double distance
        
        
        +getGears() ~Gear~
        +getCalendar() ~LocalDateTime~
        +getDistance() double
        +getName() String
        +caloriesBurnt(Person p) double
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
    <li>myGrid.length>=1</li> //checking for valid columns
    <li>myGrid[0].length>=1 //checking for valid rows
    
    <li>loop: no obstacles added here are null</li>
    <li> loop: each no coordinate of my Grid should be null </li>
    </ul>
        "
    class Map{

        -String name
        -list~Obstacles~obsctacles
        -Dimensions dimensions
        -IMapDataType[][] myGrid

        +addObstacles(Obstacle obs) void
        +removeObstacles(int index) void

        +getObsInMap() List~Obstacle~
        +getDimensions() Dimension
        +getGrid() IMapDataType[][]
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
        <li>myEmptyCoordinates!= null </li>
        <li>myEmptyCoordinates.size() == 1 </li>
        <ul>"
        
        class Empty{
                -List~Coordinates~myEmptyCoordinates
                +getMappingObject() List~Coordinates~
            
        }

        note for Coordinates"Invariant Properties
        <ul>
        <li> xCoordinate >=0 </li>
        <li> yCoordinate>=0 </li>
        <ul>"
    class Coordinates{
        -int xCoordinate
        -int yCoordinate
        
        +xCoordinates() int
        +yCoordinates() int
        +equals(Object obj) boolean
        
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

    note for IMapDataType "Invariant Properties
    <ul>
    <li>Interfaces have no direct invariants as they hold no data.</li>
    </ul>"
    
    class IMapDataType{
        <<Interface>>
        +getMappingObject() List~Coordinates~
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


    note for ExerciseTracker "Invariant Properties
<ul>
<li>myList != null</li>
<li>loop: no People inside list are null</li>
</ul>
"
    class ExerciseTracker {
        -List~Person~ myList
        +add(Person person) void
        +getList() List~Person~
        +remove(index) void
    }



    class Stack~T~ {
        <<Interface>>
        +push(T item) void
        +pop() T
        +size() int
        +isEmpty() boolean
    }

    note for LinkedListStack "Invariant Properties
    <ul>
    <li> size >=0
    <li> ((size == 0 && top == null) || (size > 0 && top!= null))
    <ul>
    "
    class LinkedListStack~T~ {
        -Node~T~ top
        -int size
        +push(T item) void
        +pop() T
        +size() int
        +isEmpty() boolean
    }
    class Node~T~ {
        -T data
        -Node~T~ next
    }

    Stack <|.. LinkedListStack
    LinkedListStack *-- Node~T~
    
    ExerciseTracker*--Person
    Person *-- Gears
    Person *-- Activity
    Person o-- Map
    
    Obstacle o-- Coordinates
    
    Map *-- Obstacle
    Map o-- Activity
    Map *-- Dimensions
    Map *--Empty

    Activity o-- Gears
    
    Activity ..|> IMapDataType
    Obstacle ..|> IMapDataType
    Empty ..|> IMapDataType
```














