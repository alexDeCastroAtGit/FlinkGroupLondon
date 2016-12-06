package main

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

import scala.util.Random

/**
  * Created by I-Vadaisa on 06/12/2016.
  */
object GenerateWords extends App {

  val topic = "Karol"
  val brokers = "localhost:8080"
  val props = new Properties()
  props.put("metadata.broker.list", brokers)
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  props.put("producer.type", "async")

  val config = new ProducerConfig(props)
  val producer = new Producer[String, String](config)

  val ip = "192.168.2.1"

  val rnd = new Random()

  val word_set = Seq("Dog", "Cat", "Cow")
  val n = word_set.length

  while (true) {

    val index = rnd.nextInt(n)
    val data = new KeyedMessage[String, String](topic, ip, word_set(index))
    producer.send(data)
    println(word_set(index))
  }

  producer.close()
}
