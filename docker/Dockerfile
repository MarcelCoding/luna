ARG JAVA_VERSION=15
ARG JVM_IMPL=hotspot
FROM adoptopenjdk:${JAVA_VERSION}-jdk-${JVM_IMPL} AS builder

RUN set -o errexit -o nounset \
    && apt-get update \
    && apt-get install git -y

COPY . /home/src
WORKDIR /home/src

RUN chmod +x ./gradlew \
    && ./gradlew :luna-main:assemble --stacktrace --no-daemon

FROM adoptopenjdk:${JAVA_VERSION}-jre-${JVM_IMPL}

ARG LUNA_USER=luna
ARG LUNA_GROUP=luna

ENV LUNA_HOME /opt/luna
ENV DATA_DIR /data

RUN set -o errexit -o nounset \
    && groupadd --system --gid 1000 ${LUNA_GROUP} \
    && useradd --system --gid ${LUNA_GROUP} --uid 1000 --shell /bin/bash --create-home ${LUNA_USER} \
    && mkdir -p ${DATA_DIR} \
    && chown --recursive ${LUNA_USER}:${LUNA_GROUP} ${DATA_DIR} \
    && chown --recursive ${LUNA_USER}:${LUNA_GROUP} /home/${LUNA_USER}

WORKDIR ${DATA_DIR}
COPY --from=builder --chown=${LUNA_USER}:${LUNA_GROUP} /home/src/luna-main/build/libs/*.jar ${LUNA_HOME}/luna.jar

#RUN ln --symbolic ${LUNA_HOME}/bin/luna-main /usr/bin/luna \
#  && chmod +x /usr/bin/luna
USER ${LUNA_USER}:${LUNA_GROUP}
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/luna/luna.jar"]
