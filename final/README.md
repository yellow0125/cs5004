# Easy_Animator

## Overview:

In this project, we will build an application that helps to create simple but effective 2D animations from shapes. One
of our main goals will be to create a separation between how an algorithm or phenomenon is described and how it is drawn
on the screen. This separation will allow us to create an application that will *draw an animation*

## Structure:

### Model Design:

The model consists of:

* **Interfaces**
    * Shape
    * Animation
    * AnimationModel represents for all operations offered by easy animator

* **Classes**
    * AbstractShape (abstract class implements Shape)
    * ShapeType (enum class)
    * Point2D
    * Rectangle (extends Abstract Shape)
    * Oval (extends Abstract Shape)

    * AbstractAnimation (abstract class implements Animation)
    * AnimationType (enum class)
    * ChangeColor (extends Abstract Animation)
    * Move (extends Abstract Animation)
    * Scale (extends Abstract Animation)

    * AnimationModelImpl (implements AnimationModel)
    * FormatString represents for formatting all strings

#### shape classes

##### Shape:

This is an interface defines the methods that are going to be implemented on different 2D shapes.

* Getter parameters of shape
    * `getNameShape().` `getColor().` `getShapeType().` `getWidth().` `getHeight().` `getTimeAppear().`
      `getTimeDisappear().` `getPoint2D().`
* Getter strings
    * `getHeightTag().` `getWidthTa().` `getSizeString().` `getPosTag().` `getPosString().`
* Calculates
    * `area().` `perimeter().` `resize().` `setNewPos().` `setNewColor().` `cloneShape().`
* Description
    * `getShapeDescription().` `getCreatDescription().` `getAppearDescription().`

##### AbstractShape:

This is an abstract class Implements Shape, It overrides most of the methods that would be inherited by other 2D shape
classes and create a constructor initializing one shape object with given parameters:
name, type, double x y, color, width, height, timeAppear,timeDisappear

##### ShapeType:

This is an Enum class represents the various kinds of 2D shapes.

##### Point2D:

This is a class represents a 2D point of a shape object. It has these methods:
distToOrigin, getX, getY.

##### Rectangle:

This class represents the Rectangle shape. It extends the class AbstractShape because a lot of its methods and fields
are common to all shapes. Rectangle only overrides `copyShape().` method.

##### Oval:

This class represents the Oval shape. It extends the class AbstractShape because a lot of its methods and fields are
common to all shapes. Oval only overrides
`copyShape().` `getWidthTag().` `getHeightTag().` `getPosTag().` `area().` `perimeter().`method.

#### animation classes

##### Animation:

This is an interface class defines the methods that are going to be implemented on different animations of the shape
object.

* Getter parameters of animation
    * `getShape().` `getStartTime().` `getEndTime().` `getAnimationType().`
* Getter strings
    * `getOldStatus().` `getNewStatus().` `getAnimationTag().`
* Calculates
    * `change(double time).`  `cloneAnimation(Shape s).`
* Description
    * `getMotionDescription().` `getAnimationSVG(double speed, boolean isLoop).`

##### AbstractAnimation:

This is an abstract class Implements Animation, It overrides most of the methods that would be inherited by other
different types of animations classes and create a constructor initializing one shape object with given parameters:
shape, start, end, type

except method getOldStatus, getNewStatus, getAnimationTag, change(double time), which are implement in Move,
ChangeColor, Scale Classes.

##### AnimationType:

This is an Enum class represents the various kinds of animations for shape object.

##### ChangeColor:

This class represents the Changing color Animation to shape object. It extends the class AbstractAnimation. It also
contains a constructor with new color, It overrides  `getOldStatus().`  `getNewStatus().`  `getAnimationTag().`  
`change(double time).` `cloneAnimation(Shape s).` and `getAnimationSVG(double speed, boolean isLoop).`method.

##### Move:

This class represents the Moving Animation to shape object. It extends the class AbstractAnimation. It also contains a
constructor with new position, It overrides  `getOldStatus().`  `getNewStatus().`  `getAnimationTag().`  
`change(double time).` `cloneAnimation(Shape s).` and `getAnimationSVG(double speed, boolean isLoop).`method.

##### Scale:

This class represents the Scaling Animation to shape object. It extends the class AbstractAnimation. It also contains a
constructor with new width and height, It overrides  `getOldStatus().`  `getNewStatus().`  `getAnimationTag().`  
`change(double time).` `cloneAnimation(Shape s).` and `getAnimationSVG(double speed, boolean isLoop).`method.

#### model class

##### AnimatorModel:

This is an interface class represents for all operations offered by the easy animator model of shapes and animations.
And the shapes and animations are stored in array lists during the animation model.

* Operation
    * `addShape().` `removeShape().` `addAnimation().` `getAnimationType().`
* Get lists
    * `getShapes().` `getAnimations().`
* Get time bounds
    * `getMinTick().`  `getMaxTick().` `getMinAnimationTime().` `getMaxAnimationTime().`
* Canvas
    * `setCanvas.`  `getCanvasX().` `getCanvasY().` `getCanvasWidth().` `getCanvasHeight().`
* Description
    * `getModelDescription().` `showShapeLog(String name).`  `showShapeInfo(String name).`

##### AnimatorModelImpl:

This is a class represents the Model class of our project, implements AnimationModel. The class is responsible for
implementing the logic that updates the state of objects to eventually pass that information to the controller. The
inputs from the controller will call methods in this class to update/mutate our shape objects. The class also allows for
the addition and removal of shapes from the animation.

* Two helper function:
    * return if the shape is in the shapes list or not.
    * compare two animation if they work on the same shape in the overlapping time with the same animation type.

##### Format String:

This class represents a bunch of string format methods of animation model

### Util Design:

This is the package that is provided as a starter code. It contains a class that helps to read animation data and
construct an animation from it, and an interface that represents a model builder, which is implemented in the
AnimatorModelImpl class.

#### AnimationBuilder

#### AnimationReader:

### View Design:

The view consists of:

* **Interfaces**
    * AnimationView
* **Classes**
    * ViewType (enum class)
    * ViewFactory

    * TextualView (implements View)
    * SvgView (implements View)
    * VisualView (implements View, has a JFrame and JPanel)
        * VisualJFrame (extends JFrame)
        * VisualPanel (extends JPanel)
    * EditorView (implements View)

#### AnimationView:

* For all types view
    * `setModel(AnimationModel m) .`  `setViewSpeed(double speed).`   `getViewSpeed().` `getViewType().`
* Description
    * `showViewDescription(boolean isLoop).`  `OutputOfView(String file,boolean isLoop).`
* For visible or editor
    * `makeVisible().` `setFeature(String feature).` `setActionListener(ActionListener listener) .`
      `refresh().`

#### ViewType:

This is an Enum class represents the various kinds of view.

#### ViewFactory:

This class represents a factory of view. It initializes different view types according to the string passed in from the
EasyAnimator class.
(determines what kind of view to produce based on user input that was passed to it)

#### TextualView:

This class represents a textual view of animation.

The text view allows the user to:

* View a full description of the original states of the shapes that are present in the animation.
* View all the changes made to every shape present in the animation.
* View description info of a specific shape present in the animation.
* View changes made to a specific shape present in the animation.
* Produce a text file that describes the animation.

#### SvgView:

This class represents an SVG view of animation.

It's main feature is to produce a svg file that can be played directly in the internet browser.

The text view allows the user to:
Same as textualView except that the animation is represented in svg/xml format.

#### VisualView:

This class represents a visual view of animation. It uses JFrame and JPanel to create a window to play the animation.
The Visual view allows a user to visualize in real time the animation that they passed into the AnimationReader.

* The JFrame implements a scroll bar in both the horizontal and vertical directions.

* VisualPanel

  This is a private class that represents a JFrame component for the visual view. It extends the JPanel class, It's used
  to store shapes and fill all shapes to be played on canvas.

#### EditorView:

It's main feature is to show a GUI to interact with users.

* EditorFrame

  This is a private class that represents a JFrame component for the editorView class. It extends the JFrame class,
    * It has these buttons:
    * Play, pause, resume, restart, loop
    * SpeedUp, SlowDown, speed>=1, shown on the GUI.
    * menu bar: NewFile, AddShape, RemoveShape, Export SVG, Export TEXT, EXIT

#### Future  Design

Action open file. and other buttons.

### Controller Design Choices and Decisions:

The controller should be given the view and model, and then control should be relinquished to the controller. The
methods of interface are * `playAnimation().` `getView().` `getModel().` `getTick().`

The controller consists of:

* **Interfaces**
    * AnimationController
* **Classes**
    * ControllerImpl

#### Controller

This class represents a Controller of an easy animation.

To be completed.

* set actions for the animator.
* write private functions for executing the behavior of the button.
* write function play visual view.

### EasyAnimator

This class represents a controller, it reads in command line. And it decides which of views been called, pass input to
model and view.

## Future  Design

Set actions in Controller of each button Easy animator Test.

## Overview of Run Design:

1. The user passes in a text file representing an animation.
2. The text file, along with any other command line arguments are passed into the EasyAnimator class.
    - Data structures in the model are populated based on the text file going into the AnimationReader.
      (They are populated through the use of the builder in the EasyAnimationModelImpl class).
    - The type of view, and the speed of the animation are also determined.
3. We pass along the new "filled" model, the speed, the type of view, and the output file name to the ViewFactory class.
4. Based on the view type passed into it from EasyAnimator, an instance of the appropriate IView subtype is created, and
   the view is generated.
