package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by David González on 19/01/14.
 */
public class findAndModifyTest {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = createCollection();

        final String counterId = "abc";
        int first, numNeeded;

        numNeeded = 2;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded -1));

        numNeeded = 3;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded -1));

        numNeeded = 10;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded -1));

        System.out.println();

        printCollection(collection);
    }

    private static DBCollection createCollection() throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findAndModifyTest");
        collection.drop();
        return collection;
    }

    private static void printCollection(DBCollection collection) throws UnknownHostException{
        DBCursor cursor = collection.find();
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally{
            cursor.close();
        }
    }

    private static int getRange(String id, int range, DBCollection collection){
        // Como parametros al findAndModify son query (document), sort (document)
        // remove (boolean), update (document), returnNew(boolean) y upsert(boolean)
        DBObject doc = collection.findAndModify(
                new BasicDBObject("_id",id),null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
        return (Integer)doc.get("counter") - range + 1;
    }
}