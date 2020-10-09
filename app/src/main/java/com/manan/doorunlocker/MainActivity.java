package com.manan.doorunlocker;
import android.annotation.SuppressLint;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        setContentView(R.layout.activity_main);
        Button btnConnect = findViewById(R.id.gateButton);
        statusTV = findViewById(R.id.StatusTV);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SERVER_IP = "2405:204:340b:3a26:501f:a126:ef32:565d";
                SERVER_IP = SERVER_IP.trim();
                String SERVER_PORTs = "63890";
                SERVER_PORT = Integer.parseInt(SERVER_PORTs.trim());
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
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
