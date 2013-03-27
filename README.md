#Objectify-Fork
This is my fork of Objectify for experimentation.

* https://objectify-appengine.googlecode.com/ - The official project documentation
and repository are here.

##Discussion
* [Objectify App Engine User Group](https://groups.google.com/forum/?fromgroups#!forum/objectify-appengine) 

##Git
An officially-maintained github mirror of the repository (to which you can
submit pull requests) is available here:

* https://github.com/stickfigure/objectify

##Maven Usage
* [Previous Versions](https://code.google.com/p/objectify-appengine/wiki/MavenRepository)

    ```
    <dependencies>
       <dependency>
          <groupId>com.googlecode.objectify</groupId>
          <artifactId>objectify</artifactId>
          <version>4.0b1</version>
       </dependency>
    </dependencies>
    ```

##Building
This project is built with Gradle.
  
* Install Gradle and then run "gradle build".
* Don't be confused by the vestigial ant/ivy files which will someday be removed.

##Development
Install the Gradle Eclipse plugin. Read the contributing to objectify wiki. 

* [Gradle Plugin Juno Installation Instructions](http://blog.springsource.org/2012/03/14/early-access-springsource-tool-suite-for-eclipse-juno-4-2/)
* [Contributing to Objectify](https://code.google.com/p/objectify-appengine/wiki/ContributingToObjectify)

##GWT
Add this to your GWT project module.

    <inherits name="com.googlecode.objectify.Objectify"/>

##IDE Setup

###Eclipse IDE Setup
Install m2e and m2e-git for importing maven projects from git works space.
* Right click on git workspace and import maven projects
* Goto the java perspective and right click on project and be sure the GWT App Engine have have been enable for get-testing.

###IDEA Setup
Installing from IDEA is easy.
* Import as a Maven project and enable multi modules in the maven settings. 

###IDE Reset
* run 'mvn eclipse:clean' - run this to clean up Eclipse settings
* run 'mvn idea:clean' - run this to clean up IDEA settings

##Development Sandbox
Setting up the source for a development sandbox with Eclipse. This will allow Objectify testing with out building a jar.

1. Link Objectify src. It might look something like this sandboxproject/src_objectify = git/objectify/src/main/java
2. If your using GWT, Link Objectify resources. It might look something like this  sandboxproject/src_objectify_resources = git/objectify/src/main/resources
 - Note the supersource will look like it has errors because of the re-rooted classes.  
3. Add the needed dependencies in the sandbox project via maven or gradle. 

