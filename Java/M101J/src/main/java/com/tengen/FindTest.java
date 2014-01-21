package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by David González on 19/01/14.
 */
public class FindTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findTest");
        collection.drop();

        //insert 10 documents with a random integer as the value of field "x"
        for(int i = 0; i < 10; i++){
            collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
        }

        System.out.println("Find one:");
        DBObject one = collection.findOne();
        System.out.println(one);

        System.out.println("\nFind all:");
        DBCursor cursor = collection.find();
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally{
            cursor.close();
        }

        System.out.println("\nCount:");
        long count = collection.count();
        System.out.println(count);
    }
}
