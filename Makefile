.PHONY = make jar runjar clean

CLASSPATH = '.;json-simple-1.1.1.jar'

make: 
	javac -cp $(CLASSPATH) -d . application/*.java

run:
	javac -cp $(CLASSPATH) -d . application/*.java
	java application.Main

jar: 
	javac -cp $(CLASSPATH) -d . application/*.java
	jar cvmf manifest.txt executable.jar .

runjar:
	java -jar executable.jar

zip:
	zip team.zip application/* *

clean:
	rm application/*.class
	rm executable.jar
