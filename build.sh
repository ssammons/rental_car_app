JSONREMOTE=https://github.com/douglascrockford/JSON-java
JSONLOCAL=./src/org/json
JARNAME=AllenEaton.jar

# Init submodules
git submodule add $JSONREMOTE $JSONLOCAL
git submodule init
git submodule update

# Clean workspace
rm -rf src/rentalcar/data/*.class 
rm -rf src/rentalcar/ui/*.class 
rm -rf src/rentalcar/util/*.class
rm -rf src/rentalcar/web/*.class
rm -rf src/rentalcar/system/*.class
rm -rf src/org/json/*.class
rm -rf test/*.class
rm -rf *.class
rm -rf bin

# Set up classpath for compilation
export CLASSPATH=$(pwd):$(pwd)/test:$(pwd)/src:$(pwd)/src/swingx-all-1.6.4.jar:$(pwd)/src/org/json/java-json.jar:$(pwd)/src/rentalcar/ui

# Create build directories
mkdir bin

# Compile class files
javac AllenEaton.java -d bin -Xlint:unchecked

# Copy manifest int build dir
cp manifest.txt bin

# Build jar file
cd bin/
jar -cfm $JARNAME manifest.txt AllenEaton.class org rentalcar
cd ..

# Copy dependencies into staging
cp bin/$JARNAME staging/
cp src/swingx-all-1.6.4.jar staging/
