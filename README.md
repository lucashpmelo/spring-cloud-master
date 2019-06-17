# Spring cloud netflix stack

- Server module - Eureka server
- Client module - eureka client with hystrix command
- Dasboard module - hystrix dashboard
- Turbine module - turbine aggregator

# How to run

1. Start eureka server
   - verify that server started 
   ```
   http://localhost:8761/
   ```
1. Start client instance
   - verify that app works 
   ```
   http://localhost:port/to-read/book
   ```
   App starts on a random port. Check logs for port details.
   - verify that hystrix actuator is available    
    ``` 
    http://localhost:port/actuator/hystrix.stream
    ```
    
1. Start hystrix dashboard
    - verify that dashboard started
    ```
    http://localhost:7777/hystrix 
    ```    
    if you don't need to aggregate metrics from several instances just pass hystrix actuator URL from step 2 to dashboard.
1. Start the turbine
    -  verify that turbine stream is available
    ``` 
    http://localhost:8888/turbine.stream
    ```
    -  pass turbine stream URL to hystrix dashboard.
