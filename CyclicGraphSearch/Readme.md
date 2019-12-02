# How to build and run project

1. In command prompt run `gradlew build`
2. In command prompt run `java -jar "build/libs/CyclicGraphSearch.jar" <commandLineArguments>`
    1. Alternatively you can call `RunProg.bat <commandLineArguments>`, it's a shortcut to call into `build/libs/CyclicGraphSearch.jar`
    2. Also alternatively you can simply call `RunProg.bat (no arguments)`, and it will prompt you for a line of input or a file name, and use that for the remainder of the program

## Ensure you have the jdk directory in the PATH environment variable or the JAVA_HOME variable
The jdk path should be in the system's PATH variable or the JAVA_HOME variable, the path should look like this:

`C:\Program Files\Java\jdk1.8.0_144`
