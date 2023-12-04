FROM bellsoft/liberica-openjdk-alpine:17.0.8

RUN apk add curl jq

WORKDIR /home/selenium-docker

ADD target/docker-resources /home/selenium-docker

ADD runner.sh runner.sh

#Environment Variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

ENTRYPOINT sh runner.sh
#ENTRYPOINT ["runner.sh"]
#ENTRYPOINT ["/bin/bash", "-c", "./runner.sh"]
