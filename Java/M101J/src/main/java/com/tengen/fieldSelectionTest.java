package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by David González on 19/01/14.
 */
public class fieldSelectionTest {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("fieldSelectionTest");
        collection.drop();
        Random rand = new Random();

        //insert 10 documents with a random integer as the value of field "x"
        for(int i = 0; i < 10; i++){
            collection.insert(new BasicDBObject("x", rand.nextInt(2))
                    .append("y", rand.nextInt(100))
                    .append("z", rand.nextInt(1000)));
        }

        DBObject query = QueryBuilder.start("x").is(0)
                .and("y").greaterThan(10).lessThan(70).get();

        System.out.println("\nFind all:");
        DBCursor cursor = collection.find(query,
                new BasicDBObject("y", true).append("_id", 0));
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally{
            cursor.close();
        }

    }
}
