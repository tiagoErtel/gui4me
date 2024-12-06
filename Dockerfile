FROM ubuntu:latest
LABEL authors="ertel"

ENTRYPOINT ["top", "-b"]