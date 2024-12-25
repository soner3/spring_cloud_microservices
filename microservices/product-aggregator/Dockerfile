FROM bellsoft/liberica-openjre-debian:21-cds AS builder

WORKDIR /builder

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM bellsoft/liberica-openjre-debian:21-cds

ARG APP_DIR=/application
ARG USERNAME=spring
ARG USER_UID=1000
ARG USER_GID=${USER_UID}


WORKDIR ${APP_DIR}

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

RUN groupadd --gid ${USER_GID} ${USERNAME} \
    && useradd --uid ${USER_UID} --gid ${USER_GID} ${USERNAME} \
    && chown -R ${USER_UID}:${USER_GID} ${APP_DIR}

USER ${USERNAME}

ENTRYPOINT ["java", "-jar", "application.jar"]