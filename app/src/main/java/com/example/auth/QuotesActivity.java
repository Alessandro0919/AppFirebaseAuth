package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity {

    private static final String QUOTE_KEY = "quote";
    private static final String AUTHOR_KEY = "author";
    private static final String TAG = "InspiringQuote";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("quote/DKozh6rDuVXdc9hi7eqy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
    }

    public void saveQuote(View view){
        EditText quoteView = (EditText) findViewById(R.id.editTextQuote);
        EditText authorView = (EditText) findViewById(R.id.editTextAuthor);
        String quoteText = quoteView.getText().toString();
        String authorText = authorView.getText().toString();

        if (quoteText.isEmpty() || authorText.isEmpty()){ return; }
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(QUOTE_KEY, quoteText);
        dataToSave.put(AUTHOR_KEY, authorText);
        mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document has been saved!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Document was not saved!", e);
            }
        });
    }
}
