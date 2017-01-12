package com.Mealpack;
//imports from p1
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
//imports from p2

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;



@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Example {

    static int mealid=1;
    static long time=1484209800000L;

    @Autowired
    JdbcTemplate jdbcTemplate;




    @RequestMapping(value = "/", produces = "text/html")
    String home() {
        return "<h1>APP is runnig</h1>";
    }


    @RequestMapping(value = "/filluser", produces = "application/json")
    String filluserandgetnumber(@RequestParam("userid") int user){

        long timenow;
        timenow=System.currentTimeMillis();
        Integer a,b,c,d;
        d=0;
        String e;


        if( (timenow - time)> 3600000l )//increments mealid every 12hrs and makes new row
        {
            mealid=mealid+1;
            jdbcTemplate.execute("INSERT INTO mealsession(id) VALUES ("+mealid+")");

        }

        if(user==1) {
            jdbcTemplate.execute("UPDATE mealsession SET user1=1 WHERE id="+mealid);

        }

        else if(user==2){
            jdbcTemplate.execute("UPDATE mealsession SET user2=1 WHERE id="+mealid);
        }

        else if(user==3){
            jdbcTemplate.execute("UPDATE mealsession SET user3=1 WHERE id="+mealid);
        }

        a = jdbcTemplate.queryForObject("SELECT user1 from mealsession WHERE id="+mealid, Integer.class); //+mealid use
        b = jdbcTemplate.queryForObject("SELECT user2 from mealsession WHERE id="+mealid, Integer.class);
        c = jdbcTemplate.queryForObject("SELECT user3 from mealsession WHERE id="+mealid, Integer.class);

        if(a!=null)
            d=d+a;

        if(b!=null)
            d=d+b;

        if(c!=null)
            d=d+c;

        e=d.toString();

        System.out.println(d);
        time=timenow;

        return "{\"eaten\":\""+e+"\"}";

    }





    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);


    }

}