package com.pmst.qa.testcases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import com.pmst.qa.base.TestBase;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bson.types.Binary;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

public class GoogleDriveTest  {
    public static final Logger LOGGER = Logger.getLogger(GoogleDriveTest.class);

    private String createdFolderId ;
    private String createdFileId;



    @Test
    public  void verifyFile() throws Exception {

        LOGGER.debug("Verifying the file existance ");

        HttpResponse<JsonNode> response = Unirest.get("https://www.googleapis.com/drive/v3/files")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ TestBase.getAuthToken())
                .asJson();

        Assert.assertTrue(response.getStatus() == 200);
    }

    @Test(priority = 1)
    public  void createFile() throws UnirestException, IOException {

        String [] parents = new String[1];
        parents[0] = createdFolderId;
        JSONObject obj = new JSONObject();
        obj.put("name","Validate.txt");
        obj.put("mimetype","text/plain");
        obj.put("parents",parents);



        HttpResponse<JsonNode> objBody = Unirest.post("https://www.googleapis.com/drive/v3/files")
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("Authorization","Bearer "+ TestBase.getAuthToken())
                  .body(obj).asJson();
        LOGGER.debug(objBody);
        LOGGER.debug(objBody.getStatus());

         Assert.assertTrue(objBody.getStatus() == 200);
         Assert.assertTrue(objBody.getBody().getObject().get("name").equals(obj.get("name")));
         this.createdFileId = objBody.getBody().getObject().get("id").toString();



    }

    @Test(priority = 0)
    public  void createFolder() throws UnirestException, IOException {

        JSONObject obj = new JSONObject();
        obj.put("name","Data"+Math.random());
        obj.put("mimeType","application/vnd.google-apps.folder");

        LOGGER.info("Creating a File");
        HttpResponse<JsonNode> objBody = Unirest.post("https://www.googleapis.com/drive/v3/files")
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("Authorization","Bearer "+ TestBase.getAuthToken())
                .body(obj).asJson();


        Assert.assertTrue(objBody.getStatus() == 200);
        Assert.assertTrue(objBody.getBody().getObject().get("name").equals(obj.get("name")));
        this.createdFolderId = objBody.getBody().getObject().get("id").toString();


    }

    @Test(priority = 2)
    public  void deleteFile() throws Exception {

        LOGGER.debug("Verifying the file existance ");

        HttpResponse<JsonNode> response = Unirest.delete("https://www.googleapis.com/drive/v3/files/"+createdFileId)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ TestBase.getAuthToken())
                .asJson();

        Assert.assertTrue(response.getStatus() == 204);
    }

    @Test(priority = 3)
    public  void deleteFolder() throws Exception {

        LOGGER.debug("Verifying the file existance ");

        HttpResponse<JsonNode> response = Unirest.delete("https://www.googleapis.com/drive/v3/files/"+createdFolderId)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ TestBase.getAuthToken())
                .asJson();

        Assert.assertTrue(response.getStatus() == 204);
    }


}
