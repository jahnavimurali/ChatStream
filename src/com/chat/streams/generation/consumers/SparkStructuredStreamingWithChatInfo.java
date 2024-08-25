package com.chat.streams.generation.consumers;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SparkStructuredStreamingWithChatInfo {
    public static void main(String[] args) throws TimeoutException, StreamingQueryException {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkStructuredStreamingWithKafka");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");

        // Defining schema for chat stream data
        List<StructField> fields = new ArrayList<StructField>();
        fields.add(DataTypes.createStructField("messageId", DataTypes.StringType, false));
        fields.add(DataTypes.createStructField("senderUsername", DataTypes.StringType, false));
        fields.add(DataTypes.createStructField("status", DataTypes.StringType, false));
        fields.add(DataTypes.createStructField("isMultiMedia", DataTypes.BooleanType, false));
        fields.add(DataTypes.createStructField("multiMediaAttachments", DataTypes.createArrayType(DataTypes.StringType), true));
        fields.add(DataTypes.createStructField("messageContent", DataTypes.StringType, false));
        fields.add(DataTypes.createStructField("receiverType", DataTypes.StringType, false));
        fields.add(DataTypes.createStructField("receiver", DataTypes.StringType, false));
        StructType structType = DataTypes.createStructType(fields);


        // Reading Kafka stream
        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9093")
                .option("subscribe", "chatstream")
                .load();

        // Parsing and extracting chat data
        Dataset<Row> res = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
                .withColumn("jsonData", from_json(col("value"), structType))
                .select("jsonData.*");



        res.createOrReplaceTempView("chat");

        // 1. Count messages by each sender
        spark.sql("select senderUsername, count(*) as message_count from chat group by senderUsername")
                .writeStream()
                .format("console")
                .outputMode(OutputMode.Complete())
                .start();

        // 2. Count messages by status (online/away)
        spark.sql("select status, count(*) as status_count from chat group by status")
                .writeStream()
                .format("console")
                .outputMode(OutputMode.Complete())
                .start()
                .awaitTermination();
    }
}
