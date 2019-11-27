package com.mkyong.rest.client;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import org.apache.http.impl.client.DefaultHttpClient;

// http://localhost:8080/RESTfulExample/
// https://hc.apache.org/httpcomponents-client-ga/httpmime/apidocs/org/apache/http/entity/mime/MultipartEntityBuilder.html
public class ResteasyClient {

    // https://stackoverflow.com/questions/18964288/upload-a-file-through-an-http-form-via-multipartentitybuilder-with-a-progress
    public static void main(String[] args) throws IOException {

        String fileName = "testbild-svt-666.png";
        String filePath = "/tmp/".concat(fileName);

        HttpClient client = new DefaultHttpClient();
        final String url = "http://localhost:8080/RESTfulExample/rest/file/upload";

        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        final File file = new File(filePath);
        FileBody fb = new FileBody(file);

        builder.addPart("content", fb);
        
        builder.addTextBody("owner", "Ingimar");
        builder.addTextBody("filename", fileName);
        builder.addTextBody("licenseType", "CC-BY");
        final HttpEntity yourEntity = builder.build();

        post.setEntity(yourEntity);
        HttpResponse response = client.execute(post);

    }

}
