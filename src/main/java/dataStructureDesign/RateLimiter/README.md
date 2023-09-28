# RATE LIMITER RUN RESULTS

### SAMPLE OUTPUT SORTED BY TIMESTAMP for scenario :
*   Rate limit of 3 RPS.
*   5 threads hitting the rate limiter concurrently with same user id.
*   These concurrent requests are throttled with milliseconds precision.

### Following info :
```
* 2023-09-11T08:32:29.931642Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:29.931642Z}
* 2023-09-11T08:32:29.931630Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:29.931630Z}
* 2023-09-11T08:32:29.931617Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:29.931617Z}
* 2023-09-11T08:32:29.931616Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:29.931616Z}
* 2023-09-11T08:32:29.931617Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:29.931617Z}
* 2023-09-11T08:32:31.031085Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:31.031085Z}
* 2023-09-11T08:32:31.031077Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:31.031077Z}
* 2023-09-11T08:32:31.031094Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:31.031094Z}
* 2023-09-11T08:32:31.031094Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:31.031094Z}
* 2023-09-11T08:32:31.031117Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:31.031117Z}
* 2023-09-11T08:32:32.036103Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:32.036103Z}
* 2023-09-11T08:32:32.036146Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:32.036146Z}
* 2023-09-11T08:32:32.036122Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:32.036122Z}
* 2023-09-11T08:32:32.036144Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:32.036144Z}
* 2023-09-11T08:32:32.036142Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:32.036142Z}
* 2023-09-11T08:32:33.037477Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:33.037477Z}
* 2023-09-11T08:32:33.037477Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:33.037477Z}
* 2023-09-11T08:32:33.037477Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:33.037477Z}
* 2023-09-11T08:32:33.037477Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:33.037477Z}
* 2023-09-11T08:32:33.037477Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:33.037477Z}
* 2023-09-11T08:32:34.042034Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:34.042034Z}
* 2023-09-11T08:32:34.042034Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:34.042034Z}
* 2023-09-11T08:32:34.042034Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:34.042034Z}
* 2023-09-11T08:32:34.042034Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:34.042034Z}
* 2023-09-11T08:32:34.042034Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:34.042034Z}
* 2023-09-11T08:32:35.042744Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:35.042744Z}
* 2023-09-11T08:32:35.043057Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:35.043057Z}
* 2023-09-11T08:32:35.043080Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:35.043080Z}
* 2023-09-11T08:32:35.043056Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:35.043056Z}
* 2023-09-11T08:32:35.043517Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:35.043517Z}
* 2023-09-11T08:32:36.046461Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:36.046461Z}
* 2023-09-11T08:32:36.046462Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:36.046462Z}
* 2023-09-11T08:32:36.046462Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:36.046462Z}
* 2023-09-11T08:32:36.046462Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:36.046462Z}
* 2023-09-11T08:32:36.046462Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:36.046462Z}
* 2023-09-11T08:32:37.047095Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:37.047095Z}
* 2023-09-11T08:32:37.047151Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:37.047151Z}
* 2023-09-11T08:32:37.047153Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:37.047153Z}
* 2023-09-11T08:32:37.047118Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:37.047118Z}
* 2023-09-11T08:32:37.047152Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:37.047152Z}
* 2023-09-11T08:32:38.049809Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:38.049809Z}
* 2023-09-11T08:32:38.049809Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:38.049809Z}
* 2023-09-11T08:32:38.049793Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:38.049793Z}
* 2023-09-11T08:32:38.049820Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:38.049820Z}
* 2023-09-11T08:32:38.049827Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:38.049827Z}
* 2023-09-11T08:32:39.055006Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:39.055006Z}
* 2023-09-11T08:32:39.055041Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:39.055041Z}
* 2023-09-11T08:32:39.055017Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:39.055017Z}
* 2023-09-11T08:32:39.055047Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:39.055047Z}
* 2023-09-11T08:32:39.055044Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:39.055044Z}
* 2023-09-11T08:32:40.059603Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:40.059603Z}
* 2023-09-11T08:32:40.059603Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:40.059603Z}
* 2023-09-11T08:32:40.059603Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:40.059603Z}
* 2023-09-11T08:32:40.059603Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:40.059603Z}
* 2023-09-11T08:32:40.059603Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:40.059603Z}
* 2023-09-11T08:32:41.060735Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:41.060735Z}
* 2023-09-11T08:32:41.060748Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:41.060748Z}
* 2023-09-11T08:32:41.060756Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:41.060756Z}
* 2023-09-11T08:32:41.060751Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:41.060751Z}
* 2023-09-11T08:32:41.060748Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:41.060748Z}
* 2023-09-11T08:32:42.062111Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:42.062111Z}
* 2023-09-11T08:32:42.062106Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:42.062106Z}
* 2023-09-11T08:32:42.062104Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:42.062104Z}
* 2023-09-11T08:32:42.062141Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:42.062141Z}
* 2023-09-11T08:32:42.062139Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:42.062139Z}
* 2023-09-11T08:32:43.065891Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:43.065891Z}
* 2023-09-11T08:32:43.065913Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:43.065913Z}
* 2023-09-11T08:32:43.065891Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:43.065891Z}
* 2023-09-11T08:32:43.065891Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:43.065891Z}
* 2023-09-11T08:32:43.065921Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:43.065921Z}
* 2023-09-11T08:32:44.067349Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:44.067349Z}
* 2023-09-11T08:32:44.067343Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:44.067343Z}
* 2023-09-11T08:32:44.067344Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:44.067344Z}
* 2023-09-11T08:32:44.067344Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:44.067344Z}
* 2023-09-11T08:32:44.067348Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:44.067348Z}
* 2023-09-11T08:32:45.070155Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:45.070155Z}
* 2023-09-11T08:32:45.070163Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:45.070163Z}
* 2023-09-11T08:32:45.070188Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:45.070188Z}
* 2023-09-11T08:32:45.070154Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:45.070154Z}
* 2023-09-11T08:32:45.070216Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-11T08:32:45.070216Z}
* 2023-09-11T08:32:46.070650Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:46.070650Z}
* 2023-09-11T08:32:46.070843Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:46.070843Z}
* 2023-09-11T08:32:46.070843Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:46.070843Z}
* 2023-09-11T08:32:46.071933Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:46.071933Z}
* 2023-09-11T08:32:46.071920Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:46.071920Z}
* 2023-09-11T08:32:47.075159Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:47.075159Z}
* 2023-09-11T08:32:47.075159Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:47.075159Z}
* 2023-09-11T08:32:47.075159Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-11T08:32:47.075159Z}
```