FROM ubuntu:20.04

WORKDIR /app
COPY target/release/container_runtime /app/container_runtime
RUN chmod +x /app/container_runtime

CMD /app/container_runtime
