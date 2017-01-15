package com.codecool.lottery.controller;

import com.codecool.lottery.service.APIService;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by patrik on 2017.01.03..
 */

/**
 * The logic behind the lottery api
 *
 */
public class LotteryAPIController {
     private Random generator;

    /**
     * Basic constructor
     * @param apiService needs an email service to handle the sending of emails
     */

    public LotteryAPIController(APIService apiService) {
        generator = new Random();

    }

    /**
     * Sets a random generator for the instance
     * @param generator not null
     */

    public void setGenerator(Random generator) {
        this.generator = generator;
    }

    /**
     *
     * creates a json object and passes it to the getRandomFromJson method
     *
     * @param req
     * @param res
     * @return String a winner
     * @throws JSONException
     */

    public String getWinner(Request req, Response res) throws JSONException {

        JSONObject object = new JSONObject(req.body());

        return getRandomFromJson(object);
    }

    /**
     * Selects a random winner from a Map
     *
     * @param object Json object
     * @return string email adress of the winner
     */

    public String getRandomFromJson(JSONObject object) {
        Iterator<String> keysItr = object.keys();
        Integer randomValue =  generator.nextInt(object.length());
        Integer count = 0;
        String key = "";
        while(keysItr.hasNext()){
            key = keysItr.next();
            if(count == randomValue){
                break;
            }
            count += 1;
        }
        return key;
    }

    /**
     * for checking the servers status
     *
     * @param request
     * @param response
     * @return string
     */

    public String status(Request request, Response response) {
        return "ok";
    }
}
