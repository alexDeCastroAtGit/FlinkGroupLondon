# Flink London Meetup (Feb 22 - 2017)  

This repository consist of different concept and examples of Windowing and Time in Flink.

**For some example Flink should be run locally**  

## Flink on local machine  

* **Download flink : Flink 1.2.0**  
[Link - Scala-2.11](http://www.apache.org/dyn/closer.lua/flink/flink-1.2.0/flink-1.2.0-bin-hadoop2-scala_2.11.tgz)  
[Link - Scala-2.10](http://www.apache.org/dyn/closer.lua/flink/flink-1.2.0/flink-1.2.0-bin-hadoop2-scala_2.10.tgz)  

* **Extract downloaded file**  
`tar -zxvf <path to .tgz>`  

* **Start Flink in local mode**  
Go to locally extracted directory. Then run following command.   
`bin/start-local.sh`  
To stop flink local server  
`bin/stop-local.sh`  

  Flink UI can be open here [link](http://localhost:8081/).  

* **Packaging code**  
Go to project directory and run following command.   
`sbt clean package`   

* **Running complied code locally on flink**  
`bin/flink run -c <Class Name> <Path to .Jar>`
