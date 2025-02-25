.DEFAULT_GOAL := build

PROJECT_NAME := mvn_padelTournement

MVN := mvn
JAR_FILE := target/$(PROJECT_NAME)-1.0-SNAPSHOT.jar

build:
	$(MVN) clean package

clean:
	$(MVN) clean

run: build
	java -jar $(JAR_FILE)

# Visa hjälp
help:
	@echo "Makefile för Maven-projekt"
	@echo "Användbara kommandon:"
	@echo "  make build    - Bygg och skapa en .jar"
	@echo "  make clean    - Rensa byggfiler"
	@echo "  make run      - Bygg och kör programmet"