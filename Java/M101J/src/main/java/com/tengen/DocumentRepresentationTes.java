package com.tengen;

import com.mongodb.BasicDBObject;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by David González on 19/01/14.
 */
public class DocumentRepresentationTes {
    public static void main (String[] args){
        BasicDBObject doc =new BasicDBObject();
        doc.put("userName","jyemin");
        doc.put("birthDate",new Date(234832423));
        doc.put("programmer",true);
        doc.put("age",8);
        doc.put("languages", Arrays.asList("Java", "C++"));
        doc.put("address",new BasicDBObject("street","20 Main")
                .append("town","Westfiled")
                .append("zip","56789"));
    }
}
