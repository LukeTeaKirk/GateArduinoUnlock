package com.manan.doorunlocker;
import android.annotation.SuppressLint;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CopyObjectRequest;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    TextView statusTV;
    EditText etMessage;
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
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
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

            BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAYCG5WKDQAVEQLYMD", "EfPD9jZ9JHWoOwQsvVIxYV6zvu8hxKUEenRlivI8");

            AmazonS3 s3 = new AmazonS3Client(awsCreds);
            s3.deleteObject("dooropener", "example2.txt");
            CopyObjectRequest copyObjRequest = new CopyObjectRequest("dooropener", "example.txt", "dooropener", "example2.txt");
            s3.copyObject(copyObjRequest);
            statusTV.setText("Open");
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
                        statusTV.setText("Open");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
