# ShapePhotoAlbum
An album consisting of various snapshots of shapes in Java with a clear hierarchy of abstract and concrete classes (e.g., Album, Snapshot...etc. and the corresponding interfaces), following the Model-View-Controller architecture.
## Instructions
run the .jar file with command line arguments, formatted as follows:
    -in "name-of-command-file" -view "type-of-view (web or graphical)" [-out "where-output-should-go"] [xmax] [ymax]

To use this application, put text command file into the resources/ if you want to use it with the jar file. To use it with inteliji or other IDEs, you should put the text files into the root directory of the project.

## Design Notes

### The Model
The model itself is a class called "Album", implementing the IAlbum interface. An instance of IAlbum is the only thing the future controller will need to interact with. To keep the model pure, it currently has no methods to reader user message or to display its state, other than some toString implementation to make it easier to look at for future development.

An album instance is aggregated by one String-to-IShape hashmap, serving as the canvas, and a list of Snapshot instances. A client can enter String input get to the shape they want to deal with. Functionality of the album includes to create, change, and remove IShapes from the canvas, take snapshots, and get or clear canvas and snapshots. 

An abstract class "AbstractShape" is implementing the IShape interface, and is extended by concrete shapes like Rectangle and Oval, for maximized code reuse. IShape has all the method to change the shapes, these methods will be called by Album to realize all the functions. An IShape is composed of a Point class and a Color Enum with other data like name and dimensions, in order to make the data more organized.

To create IShape instances, the album uses a ShapeFactory implementing the IFactory interface for future expandability. To add more concrete shapes in the future, just create new classes implementing IShape and extending AbstractShape, and then add constructors and type String representation like "triangle" into ShapeFactory.

A Snapshot simply stores all the IShape at a given timestamp. User can enter description for it or not while creating, and it will get timestamp from the system, generate an ID and store them upon creation and store current shapes into a list of IShape. Creation and display of snapshots are also taken care of by the Album (aka the model). This interface is implemented by the SnapshotImpl Class.

This implementation uses a lot of safe copying to avoid unwanted mutation to the data. When passing an object to another, this implementation copies the object or simply use the spec to create a new one.

On its own, the Album can add shapes, take snapshots, show snapshots, show current canvas, clean snapshots or canvas or both. 

Necessary methods to get and change colors, position, and dimensions are implemented in all the shape-related classes and are used by the Album to implement its own methods on these.

### The Controller and the View
First of all, GraphicView and WebView are classes that represent each view and implement the IView interface. Both views can take in snapshots from the controller to update their information. To make actions more flexible, the controller feeds snapshots one by one to the views. The views have shared a method (implemented differently) to display the view. While the GraphicView starts an interactive user interface, the WebView writes an html file. Also, Graphic view stores a the list of shapes to be drawn to better use the paintComponent() method of Swing, as it doesn't take other parameters.

Since it will be very different to control WebView and GraphicView, I first wrote an IController interface to specify their shared method, hide implementation, and decouple controllers and views. 
However, both of them have to read from the text file to the model the information about changes, consequently having shared instance variables and functions. So, I created an AbstractAlbumController implementing the IController interface and then let WebViewController and GraphicViewController extend it, for maximum code reuse. 

A FileCommandReader implementing ICommandReader takes all the command line arguments and read files and calls controller accordingly. It will pass the configurations to the controller. This is done mainly to separate this part of "concern" and keep the Main compact.

Web controller just reads from text, converts the instructions for the model, and feeds the snapshots one by one to the view to create the html file.

Besides reading from text and converting the instructions for the model, Graphical controller lets the user use some buttons to go to other snapshots. Thus, Graphical controller has an extra instance variable to store which snapshot the view should be showing. All buttons sends action commands which will be recognized by the action listener in the controller, which then tells the view to change to the corresponding snapshot.
