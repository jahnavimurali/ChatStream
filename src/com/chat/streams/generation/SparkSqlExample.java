package com.chat.streams.generation;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSqlExample {
    public static void main(String[] args) {
        // Configure Spark
        SparkConf conf = new SparkConf().setMaster("local").setAppName("Spark-Sql-Example");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

        // Read JSON file
        Dataset<Row> df = spark.read().json("chat-stream.json");

        // Print schema to verify structure
        df.printSchema();

        // Create temporary SQL view
        df.createOrReplaceTempView("chat");

        // Query 1: Count messages sent by each user
        Dataset<Row> userMessageCount = spark.sql("SELECT senderUsername, COUNT(*) AS message_count " +
                "FROM chat " +
                "GROUP BY senderUsername " +
                "ORDER BY message_count DESC");
        userMessageCount.show();

        // Query 2: Count total multimedia attachments
        Dataset<Row> totalMultimedia = spark.sql("SELECT COUNT(multiMediaAttachments) AS total_multimedia " +
                "FROM chat " +
                "WHERE isMultiMedia = true");
        totalMultimedia.show();

        // Query 3: Count messages sent in each group
        Dataset<Row> groupMessageCount = spark.sql("SELECT receiver AS group_name, COUNT(*) AS message_count " +
                "FROM chat " +
                "WHERE receiverType = 'group' " +
                "GROUP BY group_name " +
                "ORDER BY message_count DESC");
        groupMessageCount.show();

        // Write the result of one of the queries to a JSON file (optional)
        groupMessageCount.write().json("chat-stream-info");

        // Stop the Spark session
        spark.stop();
    }
}
