package com.stepup.subcollectioninjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        String userName = "Maulik Patel";

        // Create user document
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", userName);
        // Add other user details here

        db.collection("users")
                .document(userName)
                .set(userDetails)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("User document created!");
                })
                .addOnFailureListener(e -> {
                    System.out.println("Error creating user document: " + e);
                });

        // Create measurements sub-collection with a specific document ID
        String measurementId = "9978402738"; // Specify the desired document ID here
        Map<String, Object> measurementData = new HashMap<>();
        measurementData.put("height", 180);
        measurementData.put("weight", 75);
        // Add other measurement fields here

        db.collection("users")
                .document(userName)
                .collection("measurements")
                .document(measurementId)
                .set(measurementData)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("Measurement document added with ID: " + measurementId);
                })
                .addOnFailureListener(e -> {
                    System.out.println("Error adding measurement document: " + e);
                });
    }
}