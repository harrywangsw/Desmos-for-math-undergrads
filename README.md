# PhaseFlow
Team members: 
- Harry (harrywangsw)
- Brian (Brian031205)
- Hasnain (Hasnaink17)
- Paarth (paarth7777)
- Tanayjyot (tanayjyot)

# Table of contents
1. [Summary](#introduction)
2. [Features](#paragraph1)
3. [Installation](#paragraph2)
4. [Use Guide](#paragraph3)
5. [Feedback and Contribution](#paragraph4)

## Summary <a name="introduction"></a>
This is a set of computational tools related to Ordinary Differential Equations.
After inputting the equation of interest, the user can then choose to plot a time-series solution including the critical points, the phase portrait, or see the solution in a symbolic form.
Its purpose is to help math undergrads in their study of ODE. If you you would like to verify your solution or deepen your understanding through visualization, this program is for you.

## Features <a name="paragraph1"></a>
- Input a system of first-order ODE and initial conditions.
- Graph the numerical solutio of a 1D ODE and the phase portrait of 2D system of ODE.
- Display the critical points of any system of ODEs
- Displays the analytic solution of the system of ODE (if it exists)

## Installation <a name="paragraph2"></a>
Required software: JDK version 17 or above, IntelliJ IDE (ensure you have Maven setted up)
1. Download and un-zip the project files
2. in IntelliJ IDE, go to File|Open and select pom.xml file in the root of the project folder. (See the following page for more details on Maven in IntelliJ: https://www.jetbrains.com/help/idea/maven-support.html#maven_import_project_start).
3. Maven will automatically install any dependencies.
4. Run src/main/java/app/MainGraphApplication.java to start the program.


## Use Guide <a name="paragraph3"></a>
### Find&plot numerical solution
Input you ODE (must be 1 dimensional) and select Plot from the dropdown menu. Click run to see the plot.
### Find analytical solution
Input your ODE and click "solve system" button.
### Find critical points
Input your ODE and click "Find Critical points" button.
### Plot Phase Portrait
Input your ODE in the main program view. Select "plot phase portrait" in the scrolldown menu. ![Screenshot 2024-12-01 130042](https://github.com/user-attachments/assets/6e4cd06f-79cf-44d1-8de6-4eaf756afb5b)

The program will open a new window, where you can change the scale of the vectors and the view window
![Screenshot 2024-12-01 130354](https://github.com/user-attachments/assets/380efe22-728f-484f-8b3b-182bb6f7c986)
![Screenshot 2024-12-01 131556](https://github.com/user-attachments/assets/b42ef04e-637a-4f37-9e89-379205a2a042)

## Feedback and Contribution  <a name="paragraph4"></a>
Please report any feedback in the issues page(https://github.com/harrywangsw/Desmos-for-math-undergrads/issues)
We are not accepting contributions.
