package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created with IntelliJ IDEA.
 * User: dgonzal6
 * Date: 9/01/14
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args){
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World From Spark";
            }
        });
    }
}
