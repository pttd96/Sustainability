# Notice me please

This is a mini Spring Boot project for Sustainability

- Microservice style.
- Models: Water Supply, Electricity (usage) and Waste (management).
  - Waste: data comes from user inputting via REST API.
  - Electricity: data comes from user uploading CSV file.
  - Water supply: data comes from pulling / fetching from external service (integration). 
    - Preferably from Kafka messages but scheduler saves more effort :) 
- REST APIs: 1 GET per model and 2 POSTs.
- UI: a super simple index html page with some Javascript to call the GET APIs for data.
- Database: Flyway for migration and Postgres.
- Docker to containerize Flyway and Postgres.
- Automated test: basic unit testing. Full spring application test might not work, yet :( 
