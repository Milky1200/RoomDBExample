package com.example.roomdbexample;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    List<Contact> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ContactDB db = ContactDB.getInstance(getApplicationContext());
        Date currentDate = new Date();

        // Thread for database insertion
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    db.dao().insertContact(new Contact(1, "ADI", "Rfsdf"));
                    db.dao().insertContact(new Contact(4, "Kirti", "knaf", currentDate, 1));
                } catch (Exception e) {
                    Log.e("appdata", "run: stopped", e);
                }
            }
        });
        t1.start();

        // Wait for t1 to complete before starting t2
        try {
            t1.join();  // Ensure t1 finishes before continuing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Thread for database fetch operation
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Fetch all contacts
                    list = db.dao().fetchAll();

                    // After fetching the data, update the UI on the main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // You can update the UI with the fetched list here
                            // Example: Log the fetched contacts
                            for (Contact contact : list) {
                                Log.e("Fetched Contact", contact.toString());
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e("appdata", "run: fetch failed", e);
                }
            }
        });
        t2.start();
    }
}