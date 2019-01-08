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

* */NameGameReduex/general-dao/javadoc/*
* */NameGameReduex/general-utils/javadoc/*
* */NameGameReduex/name-game-api/javadoc/*

The rationale for this is to make it easier to continue collaborative development on individual projects amongst different teams.

## Development

This project was developed using [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) in the [Spring Boot Framework](https://spring.io/projects/spring-boot).
* **IDE:** Eclipse Photon with [Spring Tools 4](https://spring.io/tools)
* **OS:** 
    * **IDE and GUI apps**: Windows 10
	* **git, mvn, other CLI tools:** [Ubuntu 18.04 LTS](http://releases.ubuntu.com/18.04/) [Windows Subsystem for Linux (WSL)](https://docs.microsoft.com/en-us/windows/wsl/faq)
* **Testing:** [Postman](https://www.getpostman.com/)

### Testing

All functionality presented within `name-game-api` is tested within that project, in */src/test/java/test/hoovler/api/controllers.GameControllerTests.java* and *LeadersEndpointControllerTest.java*.  The package resolves to `test.hoovler.api.controllers.*;`

Each primary endpoint is thoroughly tested with expected values, unexpected values, and bad values, and each test is run as part of a standard Maven build.

The other projects also contain testing classes, but the functionality isn't as thoroughly or completely tested.  This is due to the accelerated timeframe within which the project was developed; however, given a normal circumstance, **each method built would have at least one complimentary unit test -- and likely several.** There are plenty of IDE tooling options available to automate the generation of those tests as much as possible.

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

### /ask

This is one of two critical endpoints for this API.  The `/ask` endpoint is capable of receiving the following parameters:

|Param Name|Type|Description|Default|Required?|
|---|-----|----|-----|
|email|String|The email of the player to whom the question is attributed.|NA|***Yes***|
|reverse|Boolean|A normal, non-reverse question presents one face and six names.  This parameter reverses that, and shows one name and six faces!|`false`|*No*|
|matts|Boolean|If true, the question will draw profiles from the pool of employees named 'Matt'; in other words, first names starting with the regex `[m|M][a|A][t|T]`|*No*|

* **GET** `http://localhost:8080/namegame/v2.0.0/ask?email={{email}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/ask?email={{email}}&reverse={{doReverse}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/ask?email={{email}}&reverse={{doReverse?}}&matts={{getMattsOnly?}}`

#### Examples

_**Request**_

<table>
	<tr>
		<td><i>Request URI</i></td>
		<td>http://localhost:8080/namegame/v2.0.0/ask?email=foo@bar.com</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
			<table>
				<tr><th>Key</th><th>Value</th></tr>
				<tr><td>email</td><td>foo@bar.com</td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td>GET</td>
	</tr>
</table>

_**Response**_

<table>
	<tr>
		<td><i>Response Headers</i></td>
		<td>
			<table>
				<tr><th>Key</th><th>Value</th></tr>
				<tr><td>Status</td><td>200 OK</td></tr>
				<tr><td>Content-Type</td><td>application/json;charset=UTF-8</td></tr>
				<tr><td>Transfer-Encoding</td><td>chunked</td></tr>
				<tr><td>Date</td><td>Tue, 08 Jan 2019 17:06:35 GMT</td></tr>
			</table>
		</td>
	</tr>
</table>

_Response Body_
```json
{
    "message": null,
    "answered": false,
    "target": "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTU3ODc5MDgxMzI4MTI1MjU3/james_k_polk.jpg",
    "options": [
        {
            "optionId": "4aSoSNPp5e6yAmgIqAGoIy",
            "optionValue": "George Washington"
        },
        {
            "optionId": "1RcXIywcByigaCeWK6UKqO",
            "optionValue": "John Candy"
        },
        {
            "optionId": "406rycFMu40YkMEQ8iW6s6",
            "optionValue": "James K. Polk"
        },
        {
            "optionId": "3VoELOt5hY8wKwO2M68e4i",
            "optionValue": "Willie Nelson"
        },
        {
            "optionId": "6CrM4BdoJ2yUmeaOAYsCA2",
            "optionValue": "Chuck Berry"
        },
        {
            "optionId": "2hf3dZwYT2GwSGUWUmkWUU",
            "optionValue": "Weadabaybee Itsaboy"
        }
    ],
    "questionId": "7b7b656d61696c7d7d36343435373337303037363639353032313638"
}
```

_**Request**_

<table>
	<tr>
		<td><i>Request URI</i></td>
		<td>http://localhost:8080/namegame/v2.0.0/ask?email=foo@bar.com&reverse=yes</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
			<table>
				<tr><th>Key</th><th>Value</th></tr>
				<tr><td>email</td><td>foo@bar.com</td></tr>
				<tr><td>reverse</td><td>yes</td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td>GET</td>
	</tr>
</table>

_**Response**_

<table>
	<tr>
		<td><i>Response Headers</i></td>
		<td>
			<table>
				<tr><th>Key</th><th>Value</th></tr>
				<tr><td>Status</td><td>200 OK</td></tr>
				<tr><td>Content-Type</td><td>application/json;charset=UTF-8</td></tr>
				<tr><td>Transfer-Encoding</td><td>chunked</td></tr>
				<tr><td>Date</td><td>Tue, 08 Jan 2019 17:06:35 GMT</td></tr>
			</table>
		</td>
	</tr>
</table>

_Response Body_
```json
{
    "message": null,
    "answered": false,
    "target": "James K. Polk",
    "options": [
        {
            "optionId": "4aSoSNPp5e6yAmgIqAGoIy",
            "optionValue": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg/1200px-Gilbert_Stuart_Williamstown_Portrait_of_George_Washington.jpg"
        },
        {
            "optionId": "1RcXIywcByigaCeWK6UKqO",
            "optionValue": "https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cg_face%2Cq_auto:good%2Cw_300/MTE4MDAzNDEwNzc5NzM1NTY2/john-candy-9542625-1-402.jpg"
        },
        {
            "optionId": "406rycFMu40YkMEQ8iW6s6",
            "optionValue": "https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTU3ODc5MDgxMzI4MTI1MjU3/james_k_polk.jpg"
        },
        {
            "optionId": "3VoELOt5hY8wKwO2M68e4i",
            "optionValue": "https://consequenceofsound.files.wordpress.com/2017/08/willie-nelson.jpg?quality=80"
        },
        {
            "optionId": "6CrM4BdoJ2yUmeaOAYsCA2",
            "optionValue": "https://cbsnews3.cbsistatic.com/hub/i/r/2017/03/19/87d22150-6779-481b-a6f5-66530a6e0beb/thumbnail/620x350/e015d77441259c4d39b91fd804a1c3fa/chuck-berry-1950s-620.jpg"
        },
        {
            "optionId": "2hf3dZwYT2GwSGUWUmkWUU",
            "optionValue": "https://i.ytimg.com/vi/9JxhTnWrKYs/hqdefault.jpg"
        }
    ],
    "questionId": "7b7b656d61696c7d7d36343435373337303037363639353032313638"
}
```


_**Request**_

<table>
	<tr>
		<td><i>Request URI</i></td>
		<td>http://localhost:8080/namegame/v2.0.0/ask?email=foo@bar.com&reverse=yes&matts=yes</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
			<table>
				<tr><th>Key</th><th>Value</th></tr>
				<tr><td>email</td><td>foo@bar.com</td></tr>
				<tr><td>reverse</td><td>yes</td></tr>
				<tr><td>matts</td><td>yes</td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td>GET</td>
	</tr>
</table>

_**Response**_

<table>
	<tr>
		<td><i>Response Headers</i></td>
		<td>
			<table>
				<tr><th>Key</th><th>Value</th></tr>
				<tr><td>Status</td><td>200 OK</td></tr>
				<tr><td>Content-Type</td><td>application/json;charset=UTF-8</td></tr>
				<tr><td>Transfer-Encoding</td><td>chunked</td></tr>
				<tr><td>Date</td><td>Tue, 08 Jan 2019 17:06:35 GMT</td></tr>
			</table>
		</td>
	</tr>
</table>

_Response Body_
```json
{
    "message": null,
    "answered": false,
    "target": "Matt Smith",
    "options": [
        {
            "optionId": "4aSoSNPp5e6yAmgIqAGoIy",
            "optionValue": "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Matt_Damon_TIFF_2015.jpg/220px-Matt_Damon_TIFF_2015.jpg"
        },
        {
            "optionId": "1RcXIywcByigaCeWK6UKqO",
            "optionValue": "https://m.media-amazon.com/images/M/MV5BMzk4NjI3NjQ0MF5BMl5BanBnXkFtZTgwMzE3NzI5NzE@._V1_UY317_CR10,0,214,317_AL_.jpg"
        },
        {
            "optionId": "406rycFMu40YkMEQ8iW6s6",
            "optionValue": "https://images.crateandbarrel.com/is/image/Crate/LatticeWoodenMatSHS16"
        },
        {
            "optionId": "3VoELOt5hY8wKwO2M68e4i",
            "optionValue": "https://www.verywellfit.com/thmb/wPWrK293T9Jxf3fVOwi-G-vSGrs=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/104518149-56aa41f05f9b58b7d00355e9.jpg"
        },
        {
            "optionId": "6CrM4BdoJ2yUmeaOAYsCA2",
            "optionValue": "https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1440,w_2560,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1503088667/170815-wilstein-leblanc-tease_flqrz7"
        },
        {
            "optionId": "2hf3dZwYT2GwSGUWUmkWUU",
            "optionValue": "https://hips.hearstapps.com/digitalspyuk.cdnds.net/18/34/1534929472-eleventh-doctor.jpg?resize=480:*"
        }
    ],
    "questionId": "7b7b656d61696c7d7d36343435373337303037363639353032313638"
}
```