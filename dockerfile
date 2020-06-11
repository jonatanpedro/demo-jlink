FROM debian:buster-slim AS builder
LABEL maintainer="Jonatan Pedro <jonatanpedro@gmail.com>"

RUN set -ex && \
    apt-get update && apt-get install -y wget unzip && \
    wget https://d3pxv6yz143wms.cloudfront.net/11.0.3.7.1/amazon-corretto-11.0.3.7.1-linux-x64.tar.gz -nv && \
    mkdir -p /opt/jdk && \
    tar zxvf amazon-corretto-11.0.3.7.1-linux-x64.tar.gz -C /opt/jdk --strip-components=1 && \
    rm amazon-corretto-11.0.3.7.1-linux-x64.tar.gz && \
    rm /opt/jdk/lib/src.zip

RUN /opt/jdk/bin/jlink \
    --module-path /opt/jdk/jmods \
    --verbose \
    --add-modules java.base,java.logging,java.naming,java.net.http,java.se,java.security.jgss,java.security.sasl,jdk.aot,jdk.attach,jdk.compiler,jdk.crypto.cryptoki,jdk.crypto.ec,jdk.internal.ed,jdk.internal.le,jdk.internal.opt,jdk.naming.dns,jdk.net,jdk.security.auth,jdk.security.jgss,jdk.unsupported,jdk.zipfs \
    --output /opt/jdk-minimal \
    --compress 2 \
    --no-header-files

FROM debian:buster-slim

COPY --from=builder /opt/jdk-minimal /opt/jdk-minimal

ENV JAVA_HOME=/opt/jdk-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"

RUN mkdir /opt/app && apt-get update && apt-get install curl -y
COPY target/demo-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080

CMD ["java", "-server", "-XX:+DoEscapeAnalysis", "-XX:+UseStringDeduplication", \
        "-XX:+UseCompressedOops", "-XX:+UseG1GC", \
        "-jar", "opt/app/demo-0.0.1-SNAPSHOT.jar"]