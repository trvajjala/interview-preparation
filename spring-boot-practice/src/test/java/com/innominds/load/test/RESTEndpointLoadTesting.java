package com.innominds.load.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innominds.persistence.vo.LoginResponse;

public class RESTEndpointLoadTesting {

    public static void main(String[] args) throws Exception {

        final String token = getLoginToken("http://localhost:8080/api/login", "{\"username\":\"tvajjala\",\"password\":\"1234\"}");

        final ExecutorService executorService = Executors.newFixedThreadPool(1000);

        final Thread userDetailsThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    final URL userUrl = new URL("http://localhost:8080/api/user");
                    final HttpURLConnection httpConn = (HttpURLConnection) userUrl.openConnection();
                    httpConn.setRequestMethod("GET");
                    httpConn.setRequestProperty("x-auth-token", token);
                    System.out.println(httpConn.getResponseCode());
                    String line1 = null;

                    final BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    final StringBuffer responseContent = new StringBuffer();
                    responseContent.ensureCapacity(512);// .5 MB
                    while ((line1 = responseReader.readLine()) != null) {
                        responseContent.append(line1);
                    }
                    System.out.println(responseContent.toString());

                } catch (final Exception e) {
                    e.printStackTrace();
                }

            }
        });

        for (int i = 0; i < 5000; i++) {
            executorService.execute(userDetailsThread);
        }

        executorService.shutdown();

    }

    static String getLoginToken(String urlString, String payload) throws Exception {

        final URL url = new URL(urlString);
        final HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestProperty("content-type", "application/json");
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        final OutputStream os = http.getOutputStream();
        os.write(payload.getBytes());
        os.close();

        final BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String line = null;

        final StringBuffer buffer = new StringBuffer();
        buffer.ensureCapacity(512);// .5 MB
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }

        final ObjectMapper mapper = new ObjectMapper();
        final String token = mapper.readValue(buffer.toString(), LoginResponse.class).getAccessToken();
        System.err.println(token);
        return token;

    }

}
