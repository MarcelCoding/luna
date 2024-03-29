ARG JVM_VERSION=21

FROM eclipse-temurin:${JVM_VERSION}-jdk-jammy AS builder

RUN set -o errexit -o nounset \
 && apt-get update \
 && apt-get install git -y

COPY . /src
WORKDIR /src

RUN set -o errexit -o nounset \
 && chmod +x ./gradlew \
 && ./gradlew :luna-main:shadowJar :luna-main:assemble --stacktrace --no-daemon

FROM eclipse-temurin:${JVM_VERSION}-jdk-jammy AS jre
ARG JVM_VERSION

WORKDIR /src

COPY --from=builder /src/luna-main/build/libs/luna-main-*-all.jar luna.jar

RUN set -o errexit -o nounset \
 && jdeps \
     --ignore-missing-deps \
     -q \
     --multi-release ${JVM_VERSION} \
     --print-module-deps \
     ./luna.jar > jre-deps.info \
 && jlink \
     --add-modules $(cat jre-deps.info) \
     --strip-debug \
     --no-man-pages \
     --no-header-files \
     --compress 2 \
     --output ./jre

FROM eclipse-temurin:${JVM_VERSION}-jdk-jammy AS extractor

WORKDIR /src

COPY --from=builder /src/luna-main/build/libs/luna-main-*.jar luna.jar
RUN java -Djarmode=layertools -jar luna.jar extract

FROM ubuntu:jammy

ENV JAVA_HOME=/opt/jre
ENV PATH="/opt/jre/bin:${PATH}"

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
 && apt-get update \
 && apt-get install -y --no-install-recommends curl jq ca-certificates \
 && rm -rf /var/lib/apt/lists/* \
 \
 && curl -fsSL https://raw.githubusercontent.com/fabric8io-images/run-java-sh/master/fish-pepper/run-java-sh/fp-files/run-java.sh -so /opt/run-java.sh \
 && chmod +x /opt/run-java.sh

COPY --from=jre /src/jre ${JAVA_HOME}

WORKDIR ${DATA_DIR}

ENV JAVA_APP_DIR=${DATA_DIR}
ENV JAVA_MAIN_CLASS=org.springframework.boot.loader.launch.JarLauncher
ENV JAVA_APP_NAME=luna
ENV JAVA_CLASSPATH=/opt/luna

USER ${LUNA_USER}:${LUNA_GROUP}
VOLUME ${DATA_DIR}

EXPOSE ${HTTP_PORT}
EXPOSE ${MANAGEMENT_PORT}

COPY --from=extractor /src/dependencies/ /opt/luna
COPY --from=extractor /src/spring-boot-loader/ /opt/luna
COPY --from=extractor /src/snapshot-dependencies/ /opt/luna
COPY --from=extractor /src/application/ /opt/luna

ENTRYPOINT ["/opt/run-java.sh"]

HEALTHCHECK --start-period=10s --interval=30s --timeout=3s --retries=3 \
            CMD curl --silent --fail --request GET "http://localhost:${MANAGEMENT_PORT}/actuator/health" \
                | jq --exit-status '.status == "UP"' || exit 1
