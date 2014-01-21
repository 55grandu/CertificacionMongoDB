package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by David González on 12/01/14.
 */
public class SparkFormHandling {
    public static void main(String[] args){
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class,"/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                try{
                    Map<String,Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

                    Template fruitPicker = configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitPicker.process(fruitsMap,writer);

                    return writer;
                }catch(Exception e){
                    halt(500);
                    e.printStackTrace();
                    return null;
                }
            }
        });

        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(final Request request, final Response response) {
                final String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "Why don´t you pick one?";
                }else{
                    return "Your favorite fruit is " + fruit;
                }
            }
        });
    }
}
