package rdd
import com.typesafe.config.ConfigFactory
import org.apache.spark.{SparkConf, SparkContext}

/*
Simple word count example running local 
 */

object WordCount extends App{
  val config = ConfigFactory.load()
  // modify file_path from config.getString to hardcode here 
  //val filePath = config.getString("file_path")
  val filePath = "src/main/resources/sample.txt"
  val threshold = 2
  val sparkMaster = config.getString("SPARK_MASTER")
  val sparkAppName = config.getString("SPARK_APPNAME")

  val conf = new SparkConf().setMaster(sparkMaster).setAppName(sparkAppName)
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")

  val lines = sc.textFile(filePath)
  val words = lines.flatMap(line => line.split(" "))
  val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
  val filtered= counts.filter(_._2 >= threshold)
   // count characters
  val charCounts = filtered.flatMap(_._1.toCharArray).map((_, 1)).reduceByKey(_ + _)

  println("Count of words greater than or equal to threshold")
  filtered.collect().foreach(println)
  
  println("count of each letter: ")
  System.out.println(charCounts.collect().mkString(", "))

  sc.stop()
}

