${project.artifactId}
======

A template java application that includes:

- command line argument processing
- AWS SDK dependencies
- init'ing AWS creds
- POM elements to help you integrate with github and make releases
- POM elements for creating an uberjar (maven-shade-plugin) and specifying a main class
- POM elements for creating an executable shell script/batfile to invoke
  the uberjar.

Building
=====

Run `mvn clean package appassembler:assemble` to:

- clean any generated artifacts (the `target` directory)
- compile and run tests, if any
- create an executable uberjar at `target/{project.artifactId}-${project.version}.jar`
- create an 'assembly' in the directory `target/appassembler`: this will contain all
  project dependencies and a shell script + windows bat file to invoke your
  uberjar.

Then you can run the program as:

	sh target/appassembler/bin/awscli
	
(Replace awscli with whatever you configured the shell script name to be.)

You can run the program straight from the uberjar. For e.g.:

	java -jar target/awscli-1.0-SNAPSHOT.jar

Version Control
=====
Importing your code into github
-----
Ensure you can run `mvn` and `git` from the command line.

Ensure you have uploaded your SSH public key to github.com and are able to login without a password.

Run `mvn clean install` to ensure you can build the project.

Create a new github repository. Note the repository URL. Update the `<developerConnection>` section of the POM with the repository URL.

Then run the following commands to checkin your code/project into github:

	mvn clean
	git init
	git add -A
	git commit -m "Importing the ... project"
	git remote add origin git@github.com:<username>/<projectname>.git
	git push -u origin master

Making a release
-----

Run `mvn release:prepare`. It will:

- update the `<version>` in the POM from `1.0-SNAPSHOT` to `1.0`
- commit the new POM and tag it as `<projectname>-1.0`
- change the version in the POM to `1.1-SNAPSHOT`

Deploying a release
-----
Add a `distributionManagement` section in your POM and run `mvn release:perform`.  Without
a distributionManagement section you will get an error when you run this command, but you can try anyway to see what it does.
 
