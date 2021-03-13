ARG JAVA_VERSION=15
ARG JVM_IMPL=hotspot

FROM adoptopenjdk:${JAVA_VERSION}-jdk-${JVM_IMPL} AS builder

RUN set -o errexit -o nounset \
    && apt-get update \
    && apt-get install git -y

COPY . /src
WORKDIR /src

RUN chmod +x ./gradlew \
    && ./gradlew :luna-main:assemble --stacktrace --no-daemon

FROM adoptopenjdk:${JAVA_VERSION}-jre-${JVM_IMPL} AS extractor

WORKDIR /src

COPY --from=builder /src/luna-main/build/libs/luna-main-*.jar luna.jar
RUN java -Djarmode=layertools -jar luna.jar extract

FROM adoptopenjdk:${JAVA_VERSION}-jre-${JVM_IMPL}

ARG LUNA_USER=luna
ARG LUNA_GROUP=luna
ARG DATA_DIR=/data

RUN set -o errexit -o nounset \
    && groupadd --system --gid 1000 ${LUNA_GROUP} \
    && useradd --system --gid ${LUNA_GROUP} --uid 1000 --shell /bin/bash --create-home ${LUNA_USER} \
    && mkdir -p ${DATA_DIR} \
    && chown --recursive ${LUNA_USER}:${LUNA_GROUP} ${DATA_DIR} \
    && chown --recursive ${LUNA_USER}:${LUNA_GROUP} /home/${LUNA_USER}

WORKDIR ${DATA_DIR}

USER ${LUNA_USER}:${LUNA_GROUP}
EXPOSE 8080
VOLUME ${DATA_DIR}

COPY --from=extractor /src/dependencies/ /opt/luna
COPY --from=extractor /src/spring-boot-loader/ /opt/luna
COPY --from=extractor /src/snapshot-dependencies/ /opt/luna
COPY --from=extractor /src/application/ /opt/luna

ENTRYPOINT ["java", "-cp", "/opt/luna", "org.springframework.boot.loader.JarLauncher"]
