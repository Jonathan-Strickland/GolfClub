Golf Club API

A Spring Boot REST service for managing golf club Members and Tournaments, with Docker support.

Run project in docker

  1. Build
  
  ```mvn clean package -DskipTests```
  
  2. Run with Docker Compose
  
  ```docker compose up --build -d```

Endpoints

  - Members (/api/members)
  
  - ```GET /``` – list all
  
  - ```POST /``` – create
  
  - ```GET /{id}``` – get by ID
  
  - ```GET /search``` – query by name, phone, duration, tournamentStartDate

Tournaments (/api/tournaments)

  - ```GET /``` – list all
  
  - ```POST /``` – create
  
  - ```GET /{id}``` – get by ID
  
  - ```GET /search``` – query by startDate, location
  
  - ```GET /{id}/members``` – list participants
  
  - ```POST /{id}/members?memberId={memberId}``` – add participant
