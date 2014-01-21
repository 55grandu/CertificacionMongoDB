package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by David González on 19/01/14.
 */
public class UpdateRemoveTest {
    public static void main(String[] args) throws UnknownHostException{
        DBCollection collection = createCollection();

        List<String> names = Arrays.asList("alice","booby","catty","david","ethan");
        for(String name: names){
            collection.insert(new BasicDBObject("_id",name));
        }

        collection.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("age",24));

        // Se sobreescribe el update y solo se muestra el ultimo update
        //collection.update(new BasicDBObject("_id","alice"),
        //        new BasicDBObject("gender","F"));

        // Incluyendo la etiqueta $set, no se sobreescribe el primer update y se muestran ambos
        collection.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("$set", new BasicDBObject("gender","F")));


        // En caso de poner un _id no incluido en la lista, no se realiza ningun update
        // Para incluir el update como un insert se pone el ",true, false" al final como parametros del update
        collection.update(new BasicDBObject("_id","frank"),
                new BasicDBObject("$set", new BasicDBObject("gender","M")), true, false);

        // Para modificar todos los documents, el primer objecto tiene que ir vacio
        // Y los parametros ",false,true" para que sea multi-update
        collection.update(new BasicDBObject(),
                new BasicDBObject("$set", new BasicDBObject("title","Dr.")), false, true);

        // Elimina toda la collection
        //collection.remove(new BasicDBObject());

        collection.remove(new BasicDBObject("_id", "alice"));

        printCollection(collection);
    }

    private static DBCollection createCollection() throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("UpdateRemoveTest");
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
}
