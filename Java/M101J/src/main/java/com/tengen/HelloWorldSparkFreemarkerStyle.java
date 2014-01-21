package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by David González on 12/01/14.
 */
public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args){

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class,"/");



        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try{
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String,Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name","Freemarker");

                    helloTemplate.process(helloMap, writer);

                    System.out.println(writer);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return writer;
            }
        });
    }

}
