# category-processor
The small utility to parse the text file to the predefined categories
# Build
To build this project use
<code>mvn clean install</code>
# Tests
To run unit tests use
<code>mvn test</code>
#Using it
To run this project with the default inputs execute from the project root folder</br>
<code>java -jar target/categoryprocessor.jar</code><p>
Additionally it's possible to run this project with the relative path to the input text file and the predefined categories list
<ul>
<li><code>java -jar target/categoryprocessor.jar /input1.txt ANIMALS,NUMBERS</code>
<li><code>java -jar target/categoryprocessor.jar /input2.txt ANIMALS,NUMBERS,CARS</code>
</ul>

