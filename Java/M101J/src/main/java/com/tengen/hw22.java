package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by David González on 19/01/14.
 */
public class hw22 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");

        QueryBuilder builder = QueryBuilder.start("type").is("homework");

        System.out.println("\nFind all:");
        DBCursor cursor = collection.find(builder.get());
        int studentId = -1;
        double score = 0.0;

        List<Double> listaIdUnico = new ArrayList<Double>();
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                if((Integer)cur.get("student_id") == studentId){
                    if((Double)cur.get("score")<score){
                        listaIdUnico.add((Double)cur.get("score"));
                    }else{
                        listaIdUnico.add(score);
                    }
                }else{
                    studentId = (Integer)cur.get("student_id");
                    score = (Double)cur.get("score");
                }
            }
        }finally{
            cursor.close();
        }

        for(Double nota:listaIdUnico){
            collection.remove(new BasicDBObject("score",nota));
            //System.out.println(id);
        }
        printCollection(collection);
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

}
