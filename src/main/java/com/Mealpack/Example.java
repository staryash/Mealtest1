package com.Mealpack;
//imports from p1

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
//imports from p2
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Example {

    static int mealid = 1;
    static long time = 1484209800000L;
    static Map<String, String> Pair = new HashMap<String, String>();


    @Autowired
    JdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/", produces = "text/html")
    String home() {
        return "<h1>APP is runnig</h1>";
    }

    @RequestMapping(value = "/pushkeyvalue", produces = "text/html")
    String PushString(@RequestParam("key") String key1, @RequestParam("value") String value1) {
        if (key1 != null && value1 != null) {
            Pair.put(key1, value1);
            return "updated";
        } else
            return "invalid";
    }

    @RequestMapping(value = "/getvalue", produces = "application/json")
    String GetValue(@RequestParam("key") String key2) {
        if (key2 != null) {
            String value2;
            value2 = Pair.get(key2);
            return "{\"" + key2 + "\":\"" + value2 + "\"}";
        } else
            return "invalid";
    }

    @RequestMapping(value = "/filluser", produces = "application/json")
    String filluserandgetnumber(@RequestParam("userid") int user) {

        long timenow;
        timenow = System.currentTimeMillis();
        Integer a, b, c, d;
        d = 0;
        String e;


        if ((timenow - time) > 3600000l)//increments mealid every 12hrs and makes new row
        {
            mealid = mealid + 1;
            jdbcTemplate.execute("INSERT INTO mealsession(id) VALUES (" + mealid + ")");

        }

        if (user == 1) {
            jdbcTemplate.execute("UPDATE mealsession SET user1=1 WHERE id=" + mealid);

        } else if (user == 2) {
            jdbcTemplate.execute("UPDATE mealsession SET user2=1 WHERE id=" + mealid);
        } else if (user == 3) {
            jdbcTemplate.execute("UPDATE mealsession SET user3=1 WHERE id=" + mealid);
        }

        a = jdbcTemplate.queryForObject("SELECT user1 from mealsession WHERE id=" + mealid, Integer.class); //+mealid use
        b = jdbcTemplate.queryForObject("SELECT user2 from mealsession WHERE id=" + mealid, Integer.class);
        c = jdbcTemplate.queryForObject("SELECT user3 from mealsession WHERE id=" + mealid, Integer.class);

        if (a != null)
            d = d + a;

        if (b != null)
            d = d + b;

        if (c != null)
            d = d + c;

        e = d.toString();

        System.out.println(d);
        time = timenow;

        return "{\"eaten\":\"" + e + "\"}";

    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);


    }

}