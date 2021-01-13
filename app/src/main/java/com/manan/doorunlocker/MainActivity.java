package com.manan.doorunlocker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    TextView statusTV;
    TextView statusServer;
    boolean flag;
    String SERVER_IP;
    int SERVER_PORT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Button btnConnect = findViewById(R.id.gateButton);
        statusTV = findViewById(R.id.StatusTV);
        statusServer = findViewById(R.id.statusServer);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upload();
                SERVER_IP = "3.138.136.60";
                        // SERVER_IP = "2405:204:340b:3a26:501f:a126:ef32:565d";
                SERVER_IP = SERVER_IP.trim();
                String SERVER_PORTs = "63890";
                SERVER_PORT = Integer.parseInt(SERVER_PORTs.trim());
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
    }

    public void upload() {
        SendfeedbackJob job = new SendfeedbackJob();
        String[] J = null;
        job.doInBackground(J);
    }
    private class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {

            /*BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAYCG5WKDQAVEQLYMD", "EfPD9jZ9JHWoOwQsvVIxYV6zvu8hxKUEenRlivI8");

            AmazonS3 s3 = new AmazonS3Client(awsCreds);
            s3.deleteObject("dooropener", "example2.txt");
            CopyObjectRequest copyObjRequest = new CopyObjectRequest("dooropener", "example.txt", "dooropener", "example2.txt");
            s3.copyObject(copyObjRequest);
            statusTV.setText("Open");*/
            return "some message";
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }

    private PrintWriter output;
    private BufferedReader input;
    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                socket.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //vMessages.setText("Connected\n");
                        statusServer.setText("Live");
                        statusTV.setText("Open");
                    }
                });
            } catch (IOException e) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        statusServer.setText("Offline, inform manan plz");
                        // Stuff that updates the UI

                    }
                });
                e.printStackTrace();
            }
        }
    }
}

