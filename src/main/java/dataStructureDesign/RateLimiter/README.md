# RATE LIMITER RUN RESULTS

### SAMPLE OUTPUT SORTED BY TIMESTAMP for scenario :
*   Rate limit of 3 RPS.
*   5 threads hitting the rate limiter concurrently with same user id.
*   These concurrent requests are throttled with milliseconds precision.

### Following info :
* 2023-09-10T07:58:46.918851Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:46.918851Z}
* 2023-09-10T07:58:46.918824Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:46.918824Z}
* 2023-09-10T07:58:46.918878Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:46.918878Z}
* 2023-09-10T07:58:46.918823Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:46.918823Z}
* 2023-09-10T07:58:46.918870Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:46.918870Z}
* 2023-09-10T07:58:48.000076Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:48.000076Z}
* 2023-09-10T07:58:48.000076Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:48.000076Z}
* 2023-09-10T07:58:48.000077Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:48.000077Z}
* 2023-09-10T07:58:48.000076Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:48.000076Z}
* 2023-09-10T07:58:48.000080Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:48.000080Z}
* 2023-09-10T07:58:49.004424Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:49.004424Z}
* 2023-09-10T07:58:49.004424Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:49.004424Z}
* 2023-09-10T07:58:49.004444Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:49.004444Z}
* 2023-09-10T07:58:49.004425Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:49.004425Z}
* 2023-09-10T07:58:49.004424Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:49.004424Z}
* 2023-09-10T07:58:50.008901Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:50.008901Z}
* 2023-09-10T07:58:50.008904Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:50.008904Z}
* 2023-09-10T07:58:50.008902Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:50.008902Z}
* 2023-09-10T07:58:50.008901Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:50.008901Z}
* 2023-09-10T07:58:50.008901Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:50.008901Z}
* 2023-09-10T07:58:51.012656Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:51.012656Z}
* 2023-09-10T07:58:51.012659Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:51.012659Z}
* 2023-09-10T07:58:51.012656Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:51.012656Z}
* 2023-09-10T07:58:51.012656Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:51.012656Z}
* 2023-09-10T07:58:51.012657Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:51.012657Z}
* 2023-09-10T07:58:52.014940Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:52.014940Z}
* 2023-09-10T07:58:52.014940Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:52.014940Z}
* 2023-09-10T07:58:52.014940Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:52.014940Z}
* 2023-09-10T07:58:52.014940Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:52.014940Z}
* 2023-09-10T07:58:52.014940Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:52.014940Z}
* 2023-09-10T07:58:53.018026Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:53.018026Z}
* 2023-09-10T07:58:53.018032Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:53.018032Z}
* 2023-09-10T07:58:53.018026Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:53.018026Z}
* 2023-09-10T07:58:53.018026Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:53.018026Z}
* 2023-09-10T07:58:53.018026Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:53.018026Z}
* 2023-09-10T07:58:54.020871Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:54.020871Z}
* 2023-09-10T07:58:54.020860Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:54.020860Z}
* 2023-09-10T07:58:54.020883Z Thread 25: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:54.020883Z}
* 2023-09-10T07:58:54.020878Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:54.020878Z}
* 2023-09-10T07:58:54.020860Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:54.020860Z}
* 2023-09-10T07:58:55.026472Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:55.026472Z}
* 2023-09-10T07:58:55.026499Z Thread 22: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:55.026499Z}
* 2023-09-10T07:58:55.026518Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:55.026518Z}
* 2023-09-10T07:58:55.026516Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:55.026516Z}
* 2023-09-10T07:58:55.026521Z Thread 21: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:55.026521Z}
* 2023-09-10T07:58:56.032051Z Thread 24: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:56.032051Z}
* 2023-09-10T07:58:56.032098Z Thread 23: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:56.032098Z}
* 2023-09-10T07:58:56.032101Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:56.032101Z}
* 2023-09-10T07:58:56.032104Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:56.032104Z}
* 2023-09-10T07:58:56.032079Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:56.032079Z}
* 2023-09-10T07:58:57.037584Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:57.037584Z}
* 2023-09-10T07:58:57.037583Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:57.037583Z}
* 2023-09-10T07:58:57.037583Z Thread 24: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:57.037583Z}
* 2023-09-10T07:58:57.037583Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:57.037583Z}
* 2023-09-10T07:58:57.037595Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:57.037595Z}
* 2023-09-10T07:58:58.040777Z Thread 25: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:58.040777Z}
* 2023-09-10T07:58:58.040799Z Thread 23: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:58.040799Z}
* 2023-09-10T07:58:58.040802Z Thread 22: Rate Limit Exceeded for Request: Request{userId='user1', requestTimestamp=2023-09-10T07:58:58.040802Z}
* 2023-09-10T07:58:58.040794Z Thread 21: Processing Request : Request{userId='user1', requestTimestamp=2023-09-10T07:58:58.040794Z}
*/