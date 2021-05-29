ARG JAVA_VERSION=16
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

ENV HTTP_PORT=8080
ENV MANAGEMENT_PORT=8081

ARG LUNA_USER=luna
ARG LUNA_GROUP=luna
ARG DATA_DIR=/data

RUN set -o errexit -o nounset \
 && groupadd --system --gid 1000 ${LUNA_GROUP} \
 && useradd --system --gid ${LUNA_GROUP} --uid 1000 --shell /bin/bash --create-home ${LUNA_USER} \
 && mkdir -p ${DATA_DIR} \
 && chown --recursive ${LUNA_USER}:${LUNA_GROUP} ${DATA_DIR} \
 && chown --recursive ${LUNA_USER}:${LUNA_GROUP} /home/${LUNA_USER} \
 \
 &&  apt-get update \
 && apt-get install -y --no-install-recommends curl jq \
 && rm -rf /var/lib/apt/lists/*

WORKDIR ${DATA_DIR}

USER ${LUNA_USER}:${LUNA_GROUP}
VOLUME ${DATA_DIR}

EXPOSE ${HTTP_PORT}
EXPOSE ${MANAGEMENT_PORT}

COPY --from=extractor /src/dependencies/ /opt/luna
COPY --from=extractor /src/spring-boot-loader/ /opt/luna
COPY --from=extractor /src/snapshot-dependencies/ /opt/luna
COPY --from=extractor /src/application/ /opt/luna

ENTRYPOINT ["java", "-cp", "/opt/luna", "org.springframework.boot.loader.JarLauncher"]

HEALTHCHECK --start-period=10s --interval=30s --timeout=3s --retries=3 \
            CMD curl --silent --fail --request GET "http://localhost:${MANAGEMENT_PORT}/actuator/health" \
                | jq --exit-status '.status == "UP"' || exit 1
