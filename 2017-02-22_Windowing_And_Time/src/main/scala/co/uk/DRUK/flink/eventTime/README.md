## Time Concept in Streaming Application

A streaming application is an always running application. So in order to understand the behavior of the application over time, we need to take snapshots of the stream in various points. Normally these various points are defined using a time component.  

Using time, we can group, correlate different events happening in the stream. Some of the constructs like window, heavily use the time component. Most of the streaming frameworks supports a single meaning of time, which is mostly tied to the processing time.  

### Time in Flink

When we say, last "t" seconds then what does this means?  
In Flink it depends and it could be one of three following.

* **Processing Time**  
Most of the streaming application uses this concept and this is one of the most familiar concept users. This time is tracked using a clock run by the processing engine. So, last "t" seconds means the records arrived in last "t" seconds for the processing.  

  Processing time is very good way of keeping track of time, but not always helpful. Let's say we want to measure the state of sensor at a given point of time so, we want to collect the event at that time. But if the events arrive lately to processing system due to various reasons, we may miss some of the events as processing clock does not care about the actual time of events. To address this, Flink support another kind of time called event time.  

* **Event Time**  
This time is embedded in data. Means this time comes with the data. So here last "t" seconds means, all the records generated in those last "t" seconds at the source. These may come out of order to processing. This time is independent of the clock that is kept by the processing engine.Event time is extremely useful for handling the late arrival events.  

* **Ingestion Time**  
Ingestion time is the time when events ingested into the system. This time is in between of the event time and processing time. Normally in processing time, each machine in cluster is used to assign the time stamp to track events. This may result in little inconsistent view of the data, as there may be delays in time across the cluster. But ingestion time, timestamp is assigned in ingestion so that all the machines in the cluster have exact same view. These are useful to calculate results on data that arrive in order at the level of ingestion.  


### WaterMarks

Now we know Flink supports different kinds of time but how it keeps track of these time. Tracking of processing time can be understood as it uses system clock, but what about event and Ingestion time?   
Watermarks is the mechanism used by the flink in order to signify the passing of time in stream. Watermarks are the special control events which are part of the stream itself.
