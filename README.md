# Self Service Agent for Test Data

This application provides sample data for QA team to use on their projects. It allow the user to manually inputs details describing the test data and then generates the data in accordance to the requirements.

## Getting Started

*These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.*

### Environment Setup

Here are some prerequisites that you need to install before being able to use this tool.

1. Download and install [Java](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
2. Download and install [Git](https://git-scm.com/downloads)
3. Download and install [Maven](https://maven.apache.org/)
4. Download and install [IntelliJ](https://www.jetbrains.com/idea/download/). You can use other IDEs as well, such as [Eclipse](http://www.eclipse.org/downloads/), but it is recommended to use IntelliJ for consistency among team members.
5. Download and install [Gradle](https://gradle.org/gradle-download/)
6. Download and install [NodeJS](https://nodejs.org/en/)
7. Add *<b>Katalon Recorder</b>* extension in [Chrome](https://chrome.google.com/webstore/detail/katalon-recorder-selenium/ljdobmomdgdljniojadhoplhkpialdid?hl=en-US) or [FireFox](https://addons.mozilla.org/en-US/firefox/addon/katalon-automation-record/) for automation testing (_optional_)

*NOTE: Java Home Path*<br/>
Make sure you have a Java SDK installed on your computer. You can download it at Oricle's website.
To set the Java Home Path click
-> Start
-> Right click on "Computer" and go to properties
-> "Advanced Properties" on the left side panel
-> "Environment Variables" towards the bottom right
-> In the bottom half section labeled "System Variables" look for the variable name "JAVA_HOME"
-> Set its value to the path where your SDK is stored.


*NOTE: Information Resource*<br/>
Argo Theme for Material UI - Has code and information for various UI components such as tables, grid, cards, etc.
<ux/aui> (just input that into a browsers address field) to access the projects in BitBucket.


### Tools and Development Practices

Here are some additional tools and practice recommendations for working on this project:
1. [Protégé](https://protege.stanford.edu/): Open source ontology editor.
2. [Gitflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow): Development process for managing Git branches.
3. [Git Collaboration](https://help.github.com/categories/collaborating-with-issues-and-pull-requests/): Describes the process of working as a group on a Git repo.

## Initial Set Up and Run

To view the summary of the project structure, click [here](./ProjectStructure.md).

Here are instructions to setup the project in IntelliJ and run for the first time:

***Jena***

1. Run the [fuseki-server.bat](fuseki/fuseki-server.bat) file

***Back-end***

1. Run the [ArgoApplication.java](src/main/java/com/argo/ArgoApplication.java)

***Front-end***

1. Navigate to [static](src/main/resources/static) folder
2. Run `npm start`
3.  The [index.html](src/main/resources/static/index.html) file should be generated automatically. Open this file in any browser

***ESLint***

1.. To be able to run the static code analysis tool ESLint, you will need to run the commands:
```
npm set registry http://nextgen-nuget:4873
npm install -g eslint babel-eslint eslint-plugin-react
```

2. To perform static code analysis, run the following:
```
eslint src
```

Additionally, you may set up IntelliJ IDEA to automatically run ESLint as a part
of normal code analysis by selecting the Settings option under the File menu.
When the Settings dialog is displayed, search for the ESLint option using the search
bar at the top left of the dialog box.  The ESLint option should be visible under
the Code Quality Tools section of the JavaScript options.  Click the checkbox to enable
ESLint support, then verify that the option "ESLint Package" is set to the
`./node_modules/eslint` package.  Additionally, ensure that the option
'Search for .eslintrc' is selected under Configuration.


## Development Process

*NOTE: This tutorial is for IntelliJ. If you use Eclipse, you will have to find some plug-ins to integrate with GitHub, or just use CLI.*


#### Changing branches
In the bottom right of IntelliJ you are able to see which branch you are on with "Git" preceding it.
Clicking on this will allow you to manage which branch you are currently working on and which branches are available.
To change to another branch, click on the branch that you want and check it out as a new local branch.
This will create a local branch in the Local Branch section.


#### Committing/Push

Click on VCS in the top menu -> Git -> Commit File...
(Shortcut: Alt+s -> 'g' -> 'i') <br/>
If you do not have this option, then click VSC -> Enable Version Control Integration -> Select **Git** in the box for *Select version control system to associate with project root*<br/>
If you are committing multiple files you can right click on the parent directory in the project navigation and navigate to Git -> Commit Directory... <br/>
The top section shows which files you are going to commit so make sure those are the correct files. The bottom section allows you to add comments to your commit. <br/>
When you are ready, click on the commit button near the bottom right or if you would like to push it was well, you can click on the dropdown arrow in the commit button and select "Commit and Push".


#### Pull

Click on VCS in the top menu -> Git -> Pull and select where you would like to pull from.


## Running the Automation Tests

### UI

We use [Katalon Recorder](https://www.katalon.com/) (newer version of the famous [Selenium IDE](http://www.seleniumhq.org/)) to perform Functional Testing and Integration Testing for the Web UI.

[Here](https://forum.katalon.com/discussion/4056/katalon-automation-recorder-powerful-selenium-ide-to-record-debug-play-tests-in-any-browsers) is the tutorial on how to use Katalon Recorder.

*NOTE*: both of these tools can be executed as scripts or be converted to other languages (such as Java, C#, Python) via [TestNG](http://testng.org/doc/) or [JUnit](http://junit.org/junit5/) testing frameworks. But because of the simplicity in the UI of this tool, we only use the scripting version.

### UnitTest

We use JUnit5 Jupiter for our backend automated unit tests (already included).

### Break Down into End-to-End Tests

<!-- UI Test Section
All test scripts are in the **_test_** folder. These scripts *ARE* meant for Integration Testing, *NOT* Unit Testing. They simulate how a QA tester would interact with the software to generate the test data.

Please read the [READ_ME]() of the test folder for further details about the test cases.
-->

All unit tests are located inside the [test/java](src/test/java) folder.

In here, tests are organized by the suites, which are the container package of each class being tested. Each test class contains number of individual test cases.

### Execute the Tests

<!-- UI Section
To execute the test, do the followings:
1. Open Katalon Recorder
2. Click `Open Test Suite` (the folder icon)
3. Navigate to `RegressionTestSuite.html` and open it. This will load the entire test suite to the IDE
4. Select the suite name, then click the `Play Suite` button to execute the entire test suite
-->

1. To run the full regression test, right click on the [test/java](src/test/java) folder and choose `Run 'All Tests'`
2. To run the particular test suite, right click on the suite name (package name) and choose `Run Tests in 'suite name'` where _suite name_ is the package name.
3. To run all tests for a particular class, right click on the class name and choose `Run`.
3. To run a particular test case, right click on the method name and choose `Run`.

## Deployment

Add additional notes about how to deploy this on a live system

## Current Status

Check out current features, bugs reports, limitations, etc. [here](CurrentFeaturesAndBugsReport.md)

## Contributors

https://github.com/park4jh <br/>
https://github.com/donaldlsmith <br/>
https://github.com/hamzahquadri <br/>
[Bao Nguyen](https://github.com/baonguyen96) <br/>
[Mai Tran](https://github.com/tqmai04) <br/>

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## License

Copyright © 2016 ARGO Data Resource Corporation, all rights reserved.

ARGO Data Resource Corporation
1500 N. Greenville Ave., Suite 500
Richardson, Texas  75081
USA

Unauthorized distribution, adaptation or use may be subject to civil and criminal penalties.

