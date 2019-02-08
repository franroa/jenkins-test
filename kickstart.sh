#!/bin/bash
set -ex

docker kill interviewDBContainer > /dev/null 2>&1 || true
docker rm -v interviewDBContainer > /dev/null 2>&1 || true

docker run --name interviewDBContainer \
    -p 54322:5432 \
    -e POSTGRES_PASSWORD=sa \
    -e POSTGRES_USER=sa \
    -d postgres:alpine

sleep 5;

docker exec -i interviewDBContainer psql -U sa -c "create database \"interviewDB\";"
docker exec -i interviewDBContainer psql -U sa -c "create database \"interviewDB-test\";"



# Starting OAuth server
# [...]