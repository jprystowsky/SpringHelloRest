# SpringHelloRest [![Build Status](https://travis-ci.org/jprystowsky/SpringHelloRest.svg?branch=master)](https://travis-ci.org/jprystowsky/SpringHelloRest)

A simple Spring-based REST web service containing minimal cruft and configuration (except most notably for the choice
of Jetty over Tomcat for an embedded webserver). Although intended as a reference point and boilerplate starter, it
encourages and applies good design patterns and principles where possible, including, WLOG:

* Javadoc comments
* Composition over inheritance
* Convention over configuration
* Loose coupling
* Strong typing
* IoC via (Spring) dependency injection
* Unit testing (with Spring-provided Mockito and JUnit)
* Maven dependency/project management

# Running

Not much to it. :)

## Test

```
mvn test
```

## Compile

```
mvn package
```

## Run

```
java -jar target/SpringHelloRest-version.jar
```

# Contributions

Absolutely welcome, if you think this tiny shell of a project could use any!

# Author

Jacob Prystowsky

# License

MIT