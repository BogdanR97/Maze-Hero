.PHONY: build clean run

build:
	javac *.java

run:
	java -Xmx1G Main ${ARGS}

clean:
	rm -rf *.class
