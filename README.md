# FlinkGroupLondon
Meet us at http://www.meetup.com/Apache-Flink-London-Meetup/


# Apache Flink  
An open-source platform for distributed stream and batch data processing.  
* Apache Top-Level Project since Jan. 2015
* **Streaming Dataflow Engine** at its core
  * Low latency
  * High Throughput
  * Stateful
  * Distributed

## The “What”: Flink from the bottom-up

 ![Flink Stack](Images/flink_stack.png)  

### Features: Why Flink?

* Provides results that are **accurate**, even in the case of out-of-order or late-arriving data (Event Time).

* Is **stateful** and fault-tolerant and can seamlessly recover from failures while maintaining exactly-once application state.

* Performs at **large scale**, running on thousands of nodes with very good throughput and latency characteristics.


**In-Depth Features**

* Flink guarantees **exactly-once semantics for stateful computations**. ‘Stateful’ means that applications can maintain an aggregation or summary of data that has been processed over time, and Flink’s checkpointing mechanism ensures exactly-once semantics for an application’s state in the event of a failure.  
* Flink supports **flexible windowing** based on time, count, or sessions in addition to data-driven windows. Windows can be customized with flexible triggering conditions to support sophisticated streaming patterns.  
* Flink’s **fault tolerance is lightweight** and allows the system to maintain high throughput rates and provide exactly-once consistency guarantees at the same time. Flink recovers from failures with zero data loss while the tradeoff between reliability and latency is negligible.  
* Flink is capable of **high throughput and low latency** (processing lots of data quickly). The charts below show the performance of Apache Flink and Apache Storm completing a distributed item counting task that requires streaming data shuffles.  
* Flink’s **savepoints provide a state versioning** mechanism, making it possible to update applications or reprocess historic data with no lost state and minimal downtime.  
* Flink is designed to run on **large-scale clusters** with many thousands of nodes, and in addition to a standalone cluster mode, Flink provides support for YARN and Mesos.  

## Basic API Concepts

Flink programs are regular programs that implement transformations on distributed collections (e.g., filtering, mapping, updating state, joining, grouping, defining windows, aggregating).  Collections are initially created from sources (e.g., by reading from files, kafka topics, or from local, in-memory collections). Results are returned via sinks, which may for example write the data to (distributed) files, or to standard output (for example, the command line terminal).

Depending on the type of data sources, i.e. **bounded (DataSet)** or **unbounded (dataStream)** sources, you would either write a batch program or a streaming program where the DataSet API is used for batch and the DataStream API is used for streaming.

## Anatomy of a Flink Program

Flink program programs look like regular programs that transform collections of data. Each program consists of the same basic parts:

1. Obtain an execution environment,
2. Load/create the initial data,
3. Specify transformations on this data,
4. Specify where to put the results of your computations,
5. Trigger the program execution

**Note:** All Flink programs are executed lazily. The lazy evaluation lets you construct sophisticated programs that Flink executes as one holistically planned unit.

Example for Basic Flink program :  
// Create execution environment  
`val env = StreamExecutionEnvironment.getExecutionEnvironment`

// Load/create the initial data Specific for DataStream   
`val text: DataStream[String] = env.readTextFile("file:///path/to/file")`

// Load/create the initial data Specific for DataSet  
`val input: DataSet[String] = ...`  

// Trigger transformation on data   
`val mapped = input.map { x => x.toInt }`  

// Sink location  
`writeAsText(path: String)`  

// Execute the environment  
`env.execute("environment execution")`
