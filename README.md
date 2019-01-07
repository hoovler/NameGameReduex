# The Name Game<sup>2</sup>
### A Glorious Reduex of <em>GtKY! (Getting to Know You)</em> API

## Structure

### Projects
The **NameGameReduex** project is actually comprised of three distinct Maven projects:

* `general-dao`
    * Houses data access objects (DAO) interfaces and default implementations which can be reused outside the context of the `name-game-api`
* `general-utils`
    * Houses utility classes and singleton objects that contain super-helpful methods which have utility beyond the context of how they're used within `name-game-api`.
	* Utility contexts include list operations (reverse and Python-like subset), security, determining object types, simple type operations, and boolean parsing.
* `name-game-api`
    * The primary project of the bunch, `name-game-api` houses the main class implementing the bootstrapping Spring Application annotion, the `GameController` class (which handles all endpoint requests), a myraid of `general-dao` and local DAO implementations, and the normalized, well-documented response object models after which the primary game endpoint JSON response bodies are modeled.

### Documentation

While this submission contains all project JavaDocs can be found in */NameGameReduex/javadoc/* for easier evaluation, each project also has it's own internal *javadoc* folder, containing the same information as that within consolidated JavaDoc folder:

* /NameGameReduex/general-dao/javadoc/
* /NameGameReduex/general-utils/javadoc/
* /NameGameReduex/name-game-api/javadoc/

The rationale for this is to make it easier to continue collaborative development on individual projects amongst different teams.

### Testing

All functionality presented within `name-game-api` is tested within that project, in */src/test/java/test/hoovler/api/controllers.GameControllerTests.java* and *LeadersEndpointControllerTest.java*.  The package resolves to `test.hoovler.api.controllers.*;`

Each primary endpoint is thoroughly tested with expected values, unexpected values, and bad values, and each test is run as part of a standard Maven build.

The other projects also contain testing classes, but the functionality isn't as thoroughly or completely tested.  This is due to the accelerated timeframe within which the project was developed; however, given a normal circumstance, **each method built would have at least one complimentary unit test -- and likely several.** There are plenty of IDE tooling options available to automate the generation of those tests as much as possible.
.
## Development

This project was developed using [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) in the [Spring Boot Framework](https://spring.io/projects/spring-boot).
* **IDE:** Eclipse Photon with [Spring Tools 4](https://spring.io/tools)
* **OS:** 
    * **IDE and GUI apps**: Windows 10
	* **git, mvn, other CLI tools:** [Ubuntu 18.04 LTS](http://releases.ubuntu.com/18.04/) [Windows Subsystem for Linux (WSL)](https://docs.microsoft.com/en-us/windows/wsl/faq)
* **Testing:** [Postman](https://www.getpostman.com/)

## Build and Run

Each of the three primary components is a Maven project, with a well-formatted `pom.xml`, but none exists within an online Maven repository.  In order to successfully build the `name-game-api` component, you need to first install the `general-*` components to your local .m2 repo.

Currently, each of the `general-*` projects have a default Maven target of `clean build install`, while the `name-game-api` project has the default target set to `clean build`.

The default target location for all maven builds is currently set to `${basedir}/target` within each project's `pom.xml`.

### Dependencies

First, clone the project:
```bash
$devdir# git clone https://github.com/hoovler/NameGameReduex.git
$devdir# cd NameGameReduex
```

With the project cloned, install the two dependency projects to your local .m2 repo:
```bash
NameGameReduex# cd general-utils
NameGameReduex/general-utils# mvn package install

NameGameReduex/general-utils# ../general-dao
NameGameReduex/general-dao# mvn package install
```

### Main Application

Then, simply build the API binaries:
```bash
NameGameReduex/general-dao# cd ../name-game-api
NameGameReduex/name-game-api# mvn package
```

Finally, to run the project, just execute the **.jar** file built from the previous step.

## API Usage

