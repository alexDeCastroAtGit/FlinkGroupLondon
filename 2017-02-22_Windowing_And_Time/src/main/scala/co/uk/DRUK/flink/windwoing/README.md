# Window in Streaming

Window is a mechanism to take a snapshot of the stream. This snapshot can be based on time or other variables. For example, if we create a window for 5 seconds then it will be all the records which arrived in the that time frame. One can define the window based on no of records or other stream specific variables also.  

## Why do we need windowing?
  * Aggregation on DataStream is different from aggregation dataset, One cannot count all records on infinite stream.
  * DataStream aggregation makes sense on window stream.

## Types of window in Flink

Flink support wide variety of window operations. The different windows supported in flink are  
  * **Time Windows**
    * **Tumbling Windows**  : Alined, fixed length, non-overlapping Windows.
    * **Sliding Windows** : Alined, fixed length, overlapping window.
    * **Session Windows** : Non alined, variable length windows.
  * **Count Windows** :

** Note: ** Most of the window operations are encouraged to be used on KeyedDataStream. A KeyedDataStream is a datastream which is partitioned by the key. This partitioning by key allows window to be distributed across machines resulting in good performance.  

## Keyed vs Non-Keyed Windows

In the case of keyed streams, any attribute of your incoming events can be used as a key (more details here). Having a keyed stream will allow your windowed computation to be performed in parallel by multiple tasks, as each logical keyed stream can be processed independently from the rest. All elements referring to the same key will be sent to the same parallel task.  

In case of non-keyed streams, your original stream will not be split into multiple logical streams and all the windowing logic will be performed by a single task, i.e. with parallelism of 1.

# Anatomy of Window API

The different functions needed to define a window in flink are,

* **Window Assigner:** A function which is responsible for assigning given element to window. Depending upon the definition of window, one element can belong to one or more window at a time.

* **Trigger:** Function which defines the condition for triggering window evaluation. This function control when a given window created by window assigner is evaluated.

* **Evictor:** An optional function which defines the preprocessing before firing window operations.

The above 3 different functions are recipes for putting together the window.

### Understanding Count window

Let's try to understand the different component of window API with count window.
  * **Window Assigner**  
  When we build an count based window, there is no start or end to the window. So for those kind of non time based windows, we have window assigner called GlobalWindow. For a given key, all values are filled into the same window.  

  `keyValue.window(GlobalWindows.create())`   

  window API allows us to add the window assigner to the window. Every window assigner has a default trigger. The default trigger for a global window is NeverTrigger which never triggers. So this window assigner has to be used with a custom trigger.  

  * **Count trigger**  

  `trigger(CountTrigger.of(2))`  

  Once we have the window assigner, we have to define when the window needs to be trigger. In the above code, we add a trigger which evaluates the window for every two records.  

  * **Evictor**  
  For example let's say we want to remove the every 3rd element of all window. This preprocessing can be done in Evictor.  
  In general Flink comes with 3 different evictors.  
    1. **CountEvictor:** keeps up to a user-specified number of elements from the window and discards the remaining ones from the beginning of the window buffer.
    2. **DeltaEvictor:** takes a DeltaFunction and a threshold, computes the delta between the last element in the window buffer and each of the remaining ones, and removes the ones with a delta greater or equal to the threshold.
    3. **TimeEvictor:** takes as argument an interval in milliseconds and for a given window, it finds the maximum timestamp max_ts among its elements and removes all the elements with timestamps smaller than max_ts - interval.  

    **Note:** All evictors apply their logic before the window function.
