.PHONY: all clean git setup restore jar

JSON_REMOTE=https://github.com/douglascrockford/JSON-java
JSON_LOCAL=./src/org/json

setup: git
	@echo "> Done!"

clean:
	rm -rf src/rentalcar/data/*.class 
	rm -rf src/rentalcar/ui/*.class 
	rm -rf src/rentalcar/util/*.class
	rm -rf src/rentalcar/web/*.class
	rm -rf src/rentalcar/system/*.class
	rm -rf src/org/json/*.class
	rm -rf test/*.class
	rm -rf *.class
	rm -rf bin

restore: clean
	git rm -rf $(JSON_LOCAL)

git:
	-git submodule add $(JSON_REMOTE) $(JSON_LOCAL)
	git submodule init
	git submodule update
