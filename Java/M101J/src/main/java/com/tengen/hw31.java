package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by David González on 22/01/14.
 */
public class hw31 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("school");
        DBCollection collection = db.getCollection("students");

        //QueryBuilder builder = QueryBuilder.start("scores.type").is("homework");

        System.out.println("\nFind all:");
        DBCursor cursor = collection.find();
        int studentId = -1;

        List<Double> listaIdUnico = new ArrayList<Double>();
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                BasicDBList listaMongoDB = (BasicDBList) cur.get("scores");
                double score = 0.0;
                for(int i = 0; i < listaMongoDB.size(); i++){
                    BasicDBObject object = (BasicDBObject) listaMongoDB.get(i);
                    if(object.get("type").toString().equals("homework")){
                        if(score == 0.0){
                            score = Double.parseDouble(object.get("score").toString());
                        }else{
                            if(score > Double.parseDouble(object.get("score").toString())){
                                // Borramos el segundo homework
                                collection.update(new BasicDBObject("_id",cur.get("_id")),
                                        new BasicDBObject("$pull", new BasicDBObject("scores",new BasicDBObject("score",object.get("score")))), false, true);
                            }else{
                                // Borramos el primer homework
                                collection.update(new BasicDBObject("_id",cur.get("_id")),
                                        new BasicDBObject("$pull", new BasicDBObject("scores",new BasicDBObject("score",score))), false, true);
                            }
                        }
                        System.out.println(score);
                    }
                }
                /**if((Integer)cur.get("student_id") == studentId){
                    if((Double)cur.get("score")<score){
                        listaIdUnico.add((Double)cur.get("score"));
                    }else{
                        listaIdUnico.add(score);
                    }
                }else{
                    studentId = (Integer)cur.get("student_id");
                    score = (Double)cur.get("score");
                }*/
            }
        }finally{
            cursor.close();
        }
/**
        for(Double nota:listaIdUnico){
            collection.remove(new BasicDBObject("score",nota));
            //System.out.println(id);
        }
**/
    }

}
