package com.pmst.qa.testcases;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.annotations.Test;

public class GoogleDriveTest {

    @Test
    public  void createFile() throws UnirestException {



        Unirest.post("postApi")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                //.body(template.render(model))
                .asJson();


    }


}
