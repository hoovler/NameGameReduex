# The Name Game<sup>2</sup>
### A Glorious Reduex of <em>GtKY! (Getting to Know You)</em> API

This began as a project challenge for an employment opportunity wherein I had to design any kind of API that could be used in **The Name Game**.  After the original response was submitted, I decided to carry it through to its logical completion.  It is now a (putting the *fun* in) mostly-functiona, server-side set of RESTful API endpoints, which may be used for mobile app development, or really any front-end context in which a REST API is useful.

> **NOTE:** the packages used for the project are now deprecated, and surely have security vulnerabilities which have been patched in newer versions of Java and Spring.  I have tried to keep up with the automated security branch pulls, but make sure you do your own due-diligence!

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

This API has several endpoints, some of which have discrete URI parameter patterns that are allow.  The endpoints are subdivided into the categories of STANDARD, DIAGNOSTIC, and ADMINISTRATIVE, based on the level of authorization that should be implemented around access to these endpoints.

The STANDARD endpoints are those which meet the primary requirements given for this application, and which limit access to the backend DAO repositories so an acceptable level for end-user usage.

`/ask`
* **GET** `http://localhost:8080/namegame/v2.0.0/ask?email={{email}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/ask?email={{email}}&reverse={{doReverse}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/ask?email={{email}}&reverse={{doReverse?}}&matts={{getMattsOnly?}}`

`/answer`
* **POST** `http://localhost:8080/namegame/v2.0.0/answer`

`/leaders`
* **GET** `http://localhost:8080/namegame/v2.0.0/leaders`
* **GET** `http://localhost:8080/namegame/v2.0.0/leaders?start={{startIndex}}&stop={{startIndex}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/leaders?start={{startIndex}}&velocity={{velocity}}`

DIAGNOSTIC endpoints are those which would, if exposed, allow an end-user access to more information than STANDARD `/ask` endpoint provides, and thus allow them to pad their score; however, access to the back-end DAO objects is still restricted to a READ-ONLY level.  Their purpose is to provide developers more access to the aforementioned information to include additional and future features in a consuming client.

`/questions`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions?id={{id}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions?start={{startIndex}}&stop={{stopIndex}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions?start={{startIndex}}&velocity={{velocity}}`

`/questions/pared`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions/pared`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions/pared?id={{id}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions/pared?start={{startIndex}}&stop={{stopIndex}}`
* **GET** `http://localhost:8080/namegame/v2.0.0/questions/pared?start={{startIndex}}&velocity={{velocity}}`

ADMINISTRATIVE endpoints are the TS//SCI of this application.  Exposure to end-users could result in catastrophic damage to operation of the application, and even end up requiring an application restart to reset the back-end DAO objects to their empty state.  These exist for the same reaon as DIAGNOSTIC endpoints, but require a greater level of care when implementing the client that may consume them.  Note that these are simply different REST methods being invoked on the previous endpoints (`/players` runs against the same DAO repository as `/leaders`).

`/players`
* **POST** `http://localhost:8080/namegame/v2.0.0/players`
* **PUT** `http://localhost:8080/namegame/v2.0.0/players`
* **DELETE** `http://localhost:8080/namegame/v2.0.0/players?email={{email}}`

`/questions/pared` [Not fully implemented]
* **POST** `http://localhost:8080/namegame/v2.0.0/questions/pared`
* **PUT** `http://localhost:8080/namegame/v2.0.0/questions/pared`
* **DELETE** `http://localhost:8080/namegame/v2.0.0/questions/pared?id={{id}}`

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

1. Generate a new question for player 'foo@bar.com'  wherein the player is presented with one image and six names.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
			<code>http://localhost:8080/namegame/v2.0.0/ask?email=foo@bar.com</code>
		</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
			<code>email=foo@bar.com</code>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td>
			<code>GET</code>
		</td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
		</td>
	</tr>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
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
</pre>
		</td>
	</tr>
</table>

2. Generate a new question for player 'foo@bar.com' wherein the player is presented with one name and six images.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
			<code>http://localhost:8080/namegame/v2.0.0/ask?email=foo@bar.com&reverse=yes</code>
		</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
<pre>email=foo@bar.com
reverse=yes</pre>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td><code>GET</code></td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>	
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
		</td>
	</tr>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
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
</pre>
		</td>
	</tr>
</table>

3. Generate a new question for player 'foo@bar.com' wherein the player is presented with one name of someone who's name starts with 'Mat', and six images of people who's names start with 'Mat'.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
		 <code>http://localhost:8080/namegame/v2.0.0/ask?email=foo@bar.com&reverse=yes&matts=yes</code>
		</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
<pre>email=foo@bar.com
reverse=yes
matts=yes</pre>
		</td>			
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td><code>GET</code></td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
	</tr>
		</td>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
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
</pre>
		</td>
	</tr>
</table>

***

### /answer

This is the other critical endpoint, wherein players submit their answers via request body content with a **POST** method.  The response body contains a JSON object with a boolean indicating whether the asnwer was correct, the `Player` object with updated stats, and a message that gives more information - if necessary.

The request body must be well-formatted JSON, and must contain the following JSON objects:

<table width="100%">
	<tr>
		<th>Param Name</th>
		<th>Type</th>
		<th>Default</th>
		<th>Required?</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>email</td>
		<td>String</td>
		<td><code>StringUtils.EMPTY_STRING</code></td>
		<td><b><em>Yes</em></b></td>
		<td>The email of the player to whom the question was asked.</td>
	</tr>
		<tr>
		<td>answer_id</td>
		<td>String</td>
		<td><code>"0"</code></td>
		<td><em>No</em></td>
		<td>The <code>option_id</code> the player selects from the list of options received from the <code>/ask</code> endpoint.</td>
	</tr>
		<tr>
		<td>question_id</td>
		<td>String (alphanumeric hex)</td>
		<td><code>"0"</code></td>
		<td><em>No</em></td>
		<td>This is the <code>question_id</code> recieved from the call to the <code>/ask</code> endpoint.  This value will only work when passed with the <code>email</code> to whom the question was originally asked.</td>
	</tr>
</table>

* **POST** `http://localhost:8080/namegame/v2.0.0/answer`

#### Examples

1. POST the answer to the question I was asked, using the email I used to generate the question, the ID of the question, and the ID of the `option` which I believe matches the `target`.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
			<code>http://localhost:8080/namegame/v2.0.0/answer</code>
		</td>		
	</tr>
	<tr>
		<td><i>Request Body</i></td>
		<td>
<pre>{
    "answer_id": "2hf3dZwYT2GwSGUWUmkWUU",
    "email": "foo@bar.com",
    "question_id": "7b7b656d61696c7d7d36343435373337303037363639353032313638"
}</pre>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td>
			<pre>POST</pre>
		</td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>
			<pre>
Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT
			</pre>
		</td>
	</tr>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
{
    "message": "Awesome job!  The answer wasn't 42, afterall!",
    "correct": true,
    "player": {
        "email": "foo@bar.com",
        "stats": {
            "numberAsked": 3,
            "numberAnswered": 7,
            "numberCorrect": 1,
            "averageResponseTime": "PT42S",
            "lastAskedTime": "2019-01-08T16:26:49.627+0000",
            "lastAnswerTime": "2019-01-08T16:27:47.296+0000",
            "score": 14.285714285714285,
            "answerTimes": [
                37,
                39,
                40,
                41,
                41,
                43,
                57
            ]
        }
    }
}
</pre>
		</td>
	</tr>
</table>

***

### /leaders

This endpoint presents a list of players that have been sorted in order of descending rank.  The player with the highest score is listed first, the second highest next, and so on... The parameterized URI allows a developer to limit the number of objects returned, and even gives a front-end developer the option to reverse the list.

<table width="100%">
	<tr>
		<th>Param Name</th>
		<th>Type</th>
		<th>Default</th>
		<th>Required?</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>start</td>
		<td>Integer</td>
		<td><code>null</code></td>
		<td><em>No</em></td>
		<td>This is the 0-based starting index of the returned subset of leaders.</td>
	</tr>
		<tr>
		<td>stop</td>
		<td>Integer</td>
		<td><code>null</code></td>
		<td><em>No</em>, but only when <code>start=null || (start!=null && velocity!=null)</code></td>
		<td>This is the 0-based stopping index of the returned subset of leaders.</td>
	</tr>
		<tr>
		<td>velocity</td>
		<td>Integer</td>
		<td><code>null</code></td>
		<td><em>No</em>, but only when <code>start=null || (start!=null && stop!=null)</code></td>
		<td>the integer value indicating whether go left (<code>velocity < 0</code>) or right (<code>velocity > 0</code>) from the <code>start</code> index, and by how many (<code>Math.abs(velocity)</code>) elements.</td>
	</tr>
</table>


#### Examples

1. Return all players, sorted by descending rank.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
			<code>http://localhost:8080/namegame/v2.0.0/leaders</code>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td>
			<code>GET</code>
		</td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
		</td>
	</tr>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
[
    {
        "email": "foo@bar.com",
        "stats": {
            "numberAsked": 14,
            "numberAnswered": 2,
            "numberCorrect": 2,
            "averageResponseTime": "PT28S",
            "lastAskedTime": "2019-01-09T18:06:02.015+0000",
            "lastAnswerTime": "2019-01-09T18:06:18.958+0000",
            "score": 100,
            "answerTimes": [
                40,
                16
            ]
        }
    },
    {
        "email": "rab@oof.com",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 7,
            "numberCorrect": 1,
            "averageResponseTime": "PT26S",
            "lastAskedTime": "2019-01-09T18:06:36.857+0000",
            "lastAnswerTime": "2019-01-09T18:07:19.279+0000",
            "score": 14.285714285714285,
            "answerTimes": [
                21,
                23,
                23,
                25,
                26,
                26,
                42
            ]
        }
    },
    {
        "email": "rab@foo.bar",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 0,
            "numberCorrect": 0,
            "averageResponseTime": "PT0S",
            "lastAskedTime": "2019-01-09T18:04:18.469+0000",
            "lastAnswerTime": "1970-01-01T00:00:00.000+0000",
            "score": 0,
            "answerTimes": []
        }
    },
    {
        "email": "bar@foo.com",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 0,
            "numberCorrect": 0,
            "averageResponseTime": "PT0S",
            "lastAskedTime": "2019-01-09T18:04:07.081+0000",
            "lastAnswerTime": "1970-01-01T00:00:00.000+0000",
            "score": 0,
            "answerTimes": []
        }
    }
]
</pre>
		</td>
	</tr>
</table>

2. Get the third-place player.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
			<code>http://localhost:8080/namegame/v2.0.0/leaders?start=1&stop=2</code>
		</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
<pre>start=1
stop=2</pre>
		</td>
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td><code>GET</code></td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>	
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
		</td>
	</tr>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
[
    {
        "email": "rab@oof.com",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 7,
            "numberCorrect": 1,
            "averageResponseTime": "PT26S",
            "lastAskedTime": "2019-01-09T18:06:36.857+0000",
            "lastAnswerTime": "2019-01-09T18:07:19.279+0000",
            "score": 14.285714285714285,
            "answerTimes": [
                21,
                23,
                23,
                25,
                26,
                26,
                42
            ]
        }
    }
]
</pre>
		</td>
	</tr>
</table>

3. Return the list of players, sorted by <em>ascending</em> rank; in other words, reverse the leaderboard.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
		 <code>http://localhost:8080/namegame/v2.0.0/leaders?start=0&velocity=-5</code>
		</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
<pre>start=0
velocity=-5</pre>
		</td>			
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td><code>GET</code></td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
	</tr>
		</td>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
[
    {
        "email": "foo@bar.com",
        "stats": {
            "numberAsked": 14,
            "numberAnswered": 2,
            "numberCorrect": 2,
            "averageResponseTime": "PT28S",
            "lastAskedTime": "2019-01-09T18:06:02.015+0000",
            "lastAnswerTime": "2019-01-09T18:06:18.958+0000",
            "score": 100,
            "answerTimes": [
                40,
                16
            ]
        }
    },
    {
        "email": "bar@foo.com",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 0,
            "numberCorrect": 0,
            "averageResponseTime": "PT0S",
            "lastAskedTime": "2019-01-09T18:04:07.081+0000",
            "lastAnswerTime": "1970-01-01T00:00:00.000+0000",
            "score": 0,
            "answerTimes": []
        }
    },
    {
        "email": "rab@foo.bar",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 0,
            "numberCorrect": 0,
            "averageResponseTime": "PT0S",
            "lastAskedTime": "2019-01-09T18:04:18.469+0000",
            "lastAnswerTime": "1970-01-01T00:00:00.000+0000",
            "score": 0,
            "answerTimes": []
        }
    },
    {
        "email": "rab@oof.com",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 7,
            "numberCorrect": 1,
            "averageResponseTime": "PT26S",
            "lastAskedTime": "2019-01-09T18:06:36.857+0000",
            "lastAnswerTime": "2019-01-09T18:07:19.279+0000",
            "score": 14.285714285714285,
            "answerTimes": [
                21,
                23,
                23,
                25,
                26,
                26,
                42
            ]
        }
    },
    {
        "email": "foo@bar.com",
        "stats": {
            "numberAsked": 14,
            "numberAnswered": 2,
            "numberCorrect": 2,
            "averageResponseTime": "PT28S",
            "lastAskedTime": "2019-01-09T18:06:02.015+0000",
            "lastAnswerTime": "2019-01-09T18:06:18.958+0000",
            "score": 100,
            "answerTimes": [
                40,
                16
            ]
        }
    }
]
</pre>
		</td>
	</tr>
</table>


3. Return the last-ranked player.

<table width="100%">
	<tr>
		<td><i>Request URI</i></td>
		<td>
		 <code>http://localhost:8080/namegame/v2.0.0/leaders?start=-1&velocity=0</code>
		</td>
	</tr>
	<tr>
		<td><i>Request Headers</i></td>
		<td>
<pre>start=-1
velocity=0</pre>
		</td>			
	</tr>
	<tr>
		<td><i>Method</i></td>
		<td><code>GET</code></td>
	</tr>
	<tr>
		<td><b>Response Headers</b></td>
		<td>
<pre>Status=200 OK
Content-Type=application/json;charset=UTF-8
Transfer-Encoding=chunked
Date=Tue, 08 Jan 2019 17:06:35 GMT</pre>
	</tr>
		</td>
	<tr>
		<td><b>Response Body</b></td>
		<td>
<pre>
[
    {
        "email": "bar@foo.com",
        "stats": {
            "numberAsked": 1,
            "numberAnswered": 0,
            "numberCorrect": 0,
            "averageResponseTime": "PT0S",
            "lastAskedTime": "2019-01-09T18:04:07.081+0000",
            "lastAnswerTime": "1970-01-01T00:00:00.000+0000",
            "score": 0,
            "answerTimes": []
        }
    }
]
</pre>
		</td>
	</tr>
</table>
