# The Name Game<sup>2</sup>
### A Glorious Reduex of <em>GtKY! (Getting to Know You)</em> API

## Project Structure
	


## Build
**NOTE**: The default target location for all maven builds is currently set to `${basedir}/target` within each project's `pom.xml`.

1. First, clone the project:
```bash
> git clone https://github.com/hoovler/NameGameReduex.git
> cd NameGameReduex
```
2. Next, install the two dependency projects to your local .m2 repo:
```bash
> cd name-game-utils
> mvn package install
> ../general-dao
> mvn package install
```
3. Last, build the API binaries:
```bash
> cd ../name-game-api
> mvn package
```