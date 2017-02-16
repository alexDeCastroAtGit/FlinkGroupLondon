package co.uk.DRUK.flink.eventTime

/**
  * Created by satyasatyasheel on 14/02/2017.
  */
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time


object EventTimeExample {
  // model which tracks stock price in a given point of time.
  case class Stock(time:Long, symbol:String,value:Double)

  def main(args: Array[String]) {
    // Obtain an execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // setting EventTime as time characteristic for the environment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    // Listening socket for text stream
    val source = env.socketTextStream("localhost",50050)

    // convert string network stream into Stock model --> "Stock(time:Long, symbol:String,value:Double)"
    val parsedStream = source.map(value => {
      val columns = value.split(",")
      Stock(columns(0).toLong, columns(1),columns(2).toDouble)
    })

    // **ascending watermarks** --> confirms that at timestamp "t" no events with timestamp lower than "t" occurs
    val timedValue = parsedStream.assignAscendingTimestamps(_.time)

    // Grouping the stream based on stock symbole
    val keyedStream = timedValue.keyBy(_.symbol)

    // max stock value in a 10 sec interval.
    val timeWindow = keyedStream.timeWindow(Time.seconds(10)).max("value").name("timedwindow")

    // stdout
    timeWindow.print.name("print sink")
    env.execute()
  }
}