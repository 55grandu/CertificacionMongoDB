package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by David González on 12/01/14.
 */
public class SparkRoutes {
    public static void main(String[] args){
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World From Spark";
            }
        });
        Spark.get(new Route("/test") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World From Route Test";
            }
        });
        Spark.get(new Route("/echo/:thing") {
            @Override
            public Object handle(Request request, Response response) {
                return request.params(":thing");
            }
        });
    }
}
