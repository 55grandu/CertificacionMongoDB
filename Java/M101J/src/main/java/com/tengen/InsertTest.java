package com.tengen;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by David González on 19/01/14.
 */
public class InsertTest {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("insertTest");

        DBObject doc = new BasicDBObject().append("x",1);
        collection.insert(doc);
        collection.insert(doc);
   }
}
