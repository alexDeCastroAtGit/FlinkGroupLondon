package co.uk.DRUK.flink.windwoing

/**
  * Created by satyasatyasheel on 14/02/2017.
  */

import org.apache.flink.streaming.api.scala._


object StreamingWordCount {
  def main(args: Array[String]) {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // create a stream using socket

    val socketStream = env.socketTextStream("localhost",9000)

    // implement word count

    val wordsStream = socketStream.flatMap(value => value.split("\\s+")).map(value => (value,1))

    val keyValuePair = wordsStream.keyBy(0)

    val countPair = keyValuePair.sum(1)

    // print the results

    countPair.print()

    // execute the program

    env.execute()

  }
}
