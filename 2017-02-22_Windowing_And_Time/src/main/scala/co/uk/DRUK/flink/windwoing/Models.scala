package co.uk.DRUK.flink.windwoing

/**
  * Created by satyasatyasheel on 14/02/2017.
  */
object Models {
  case class Session(sessionId:String, value:Double, endSignal:Option[String])
}
