# Hello World (from DB)

This application features a **Java backend** and a **React web frontend**.

The Java backend runs on an embedded **Tomcat server**, serving the frontend's static resources and providing a web service that returns random greeting messages retrieved from a **PostgreSQL database**.

The entire application can be packaged into a Docker image and run alongside the PostgreSQL database using **Docker Compose**.

## Prerequisites:
- Docker with Compose

## How to run it?

To build and run the application, simply execute the following command from the `mpp-hello` directory:

```bash
docker compose up -d
