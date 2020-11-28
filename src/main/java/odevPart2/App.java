/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package odevPart2;


import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static boolean search(ArrayList<Integer> array, int e, int k){
        //Is multiplication of e and k in the arrayList
        System.out.println("inside search");

        if(array == null){
            return false;
        }
        int r = e*k;
        for(int element: array){
            if(element==r) return true;
        }
        return false;
        
    }

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(App.class);
        logger.error("hello world");


        System.out.println(new App().getGreeting());
        
        get("/",(req, res)-> "Hello, World");

        post("/compute", (req, res) -> {
            //System.out.println(req.queryParams("input1"));
            //System.out.println(req.queryParams("input2"));
  
            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
              int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
              inputList.add(value);
            }
            sc1.close();
          System.out.println(inputList);


          String input2 = req.queryParams("input2").replaceAll("\\s","");
          int input2AsInt = Integer.parseInt(input2);

          String input3 = req.queryParams("input3").replaceAll("\\s","");
          int input3AsInt = Integer.parseInt(input3);


          boolean result = App.search(inputList, input2AsInt, input3AsInt);

          Map<String, Boolean> map = new HashMap<String, Boolean>();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/compute",
            (rq, rs) -> {
              Map<String, String> map = new HashMap<String, String>();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }
}
