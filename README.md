# INCOMPLETE - THIS IS WORK IN PROGRESS

  Spring.io based REST services

### Setup

```bash
# code
$ git clone https://github.com/partharamanujam/spring-rest.git
# build + run
$ cd spring-rest/starter-project
$ ./gradlew run
```

## Features

  * Framework [Spring.io](https://spring.io/)
  * Build using [Gradle](http://www.gradle.org/)
  * Logging using [Log4J](http://logging.apache.org/log4j/)
  * WAR builds for deployment using [Tomcat](http://tomcat.apache.org/)
  * Supports cmd-line builds, and multiple IDEs (STS/Eclipse, IDEA, etc.)
  * Supports content-negotiation for both JSON & XML
  * Supports [Spring Security](http://docs.spring.io/autorepo/docs/spring-security/3.2.4.RELEASE/reference/htmlsingle)
  * Supports AJAX via HTTP headers for [CSRF tokens](http://docs.spring.io/autorepo/docs/spring-security/3.2.4.RELEASE/reference/htmlsingle/#csrf-using)

### Installation Dependencies

  * STS-3.5.1.RELEASE (Spring Tool Suite Eclipse IDE)

### Dependency Module Versions

  View [build.gradle](starter-project/build.gradle) file for versions of dependencies. 

## Philosophy

  Spring.io based REST-API implementation integrated with commonly used components used in
  any serious development. Can be used as a starting point to get going. 


## Builds & Configuration

  There are many aspects that can be configured or used differently depending on the needs.
  This section lists some of these important aspects.

### Gradle tasks

  The build-tool gradle is invoked using gradlew which is a shell-script in unix-like
  environments, and a batch-file on MS-Windows. It supports multiple tasks, and some
  of the useful ones are:

```bash
$ ./gradlew clean
$ ./gradlew jar
$ ./gradlew war
$ ./gradlew build # all targets
$ ./gradlew run
$ ./gradlew eclipse # IDE setup for STS/Eclipse
```

### STS (Eclipse IDE)

  If you want to edit & debug the project within the comforts of Eclipse IDE, you will need
  to download Spring Tool Suite (tested on STS-3.5.1.RELEASE). After setting up STS:
  * run "./gradlew eclipse" from within project-folder
  * "Import" in Eclipse/STS "Existing Projects into Workspace"
  * "Browse" for project-folder, and select "root" directory
  * "Select" listed project, and press "Finish" button
  * Build & Run project within STS/Eclipse

### Command-line

```bash
$ cd spring-rest/starter-project
$ ./gradlew run
```

### WAR file

  On successful build, the war-file for deployment in Tomcat is available in "build/libs" directory.

### Log4J Configuration

  See properties setup in /spring-rest/starter-project/src/main/resources/javalog4j.properties file.
  You can also set logger-level per package and/or class in the properties file.

### Content Negotiation

  Support is available for JSON & XML contents. These are checked in the following order:
  * path extension suffix to URL, e.g., http://localhost:8080/greeting.xml?name=partha
  * format URL parameter, e.g., http://localhost:8080/greeting.xml?name=partha&format=xml
  * accept headers, e.g., application/json
  * defaults to JSON

## License

  [MIT](LICENSE)
