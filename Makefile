.PHONY: build

build:
	./gradlew bootJar

docker:
	docker compose build

clean:
	./gradlew clean
	docker compose rm

all: build docker