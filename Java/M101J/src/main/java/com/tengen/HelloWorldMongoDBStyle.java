package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: dgonzal6
 * Date: 9/01/14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldMongoDBStyle {

    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        DBCollection collection = database.getCollection("hello");

        DBObject document = collection.findOne();
        System.out.println(document);
    }

}
