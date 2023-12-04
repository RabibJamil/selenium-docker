FROM bellsoft/liberica-openjdk-alpine:17.0.8

RUN apk add curl jq

WORKDIR /home/selenium-docker

ADD target/docker-resources /home/selenium-docker

ADD runner.sh /home/selenium-docker/runner.sh

#Environment Variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

ENTRYPOINT sh runner.sh
#ENTRYPOINT ["runner.sh"]
