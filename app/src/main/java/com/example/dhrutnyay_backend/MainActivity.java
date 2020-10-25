package com.example.dhrutnyay_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity {
    EditText Reg_textView;
    private String TAG = "DynamoDb_Demo";
    private TextView type_textView;
    private TextView id_textView;
    private TextView name_textView;
    private TextView location_textView;
    private TextView date_textView;
    private TextView time_textView;
    private TextView status_textView;
    String data = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reg_textView=(EditText)findViewById(R.id.Reg_textView);
        final EditText Complaint_id=(EditText)findViewById(R.id.Complaint_ID);
        final EditText Complainant_name=(EditText)findViewById(R.id.Complaint_Name);
        final EditText Complaint_type=(EditText)findViewById(R.id.Complaint_Type);
        final EditText Location=(EditText)findViewById(R.id.Location);
        final EditText Date=(EditText)findViewById(R.id.Date);
        final EditText time1=(EditText)findViewById(R.id.Time);
        //final EditText desc=(EditText)findViewById(R.id.desc);

        int number= ThreadLocalRandom.current().nextInt(100000,1000000);


        Button buttonGetItem = findViewById(R.id.button3);
        id_textView = findViewById(R.id.compID_textView);
        type_textView = findViewById(R.id.compType_textView);
        name_textView = findViewById(R.id.compName_textView);
        location_textView = findViewById(R.id.compType_textView2);
        date_textView = findViewById(R.id.Date_textView);
        time_textView = findViewById(R.id.time_textView);
        status_textView = findViewById(R.id.status_textView2);

        buttonGetItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = Reg_textView.getText().toString();


                //Log.i(TAG, "Getting all devices...");

                //textView.setText("Getting all devices...");


                GetAllItemsAsyncTask getAllDevicesTask = new GetAllItemsAsyncTask();
                getAllDevicesTask.execute();
            }
        });
    }
    private class GetAllItemsAsyncTask extends AsyncTask<Void, Void, List<Document>> {
       @Override
        protected List<Document> doInBackground(Void... params) {

           String number = Reg_textView.getText().toString();

            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
            Log.d(TAG, "databases content"+databaseAccess.getAllItems().toString());
           Document DataReceived= databaseAccess.getItem_Complaint(number);
           String test_type=DataReceived.get("complaint_type").convertToAttributeValue().getS();
           type_textView.setText(test_type);

           String test_type1=DataReceived.get("complaint_id").convertToAttributeValue().getS();
           id_textView.setText(test_type1);

           String test_type2=DataReceived.get("complaint").convertToAttributeValue().getS();
           name_textView.setText(test_type2);

           String test_type3=DataReceived.get("location").convertToAttributeValue().getS();
           location_textView.setText(test_type3);

           String test_type4=DataReceived.get("date").convertToAttributeValue().getS();
           date_textView.setText(test_type4);

           String test_type5=DataReceived.get("Time1").convertToAttributeValue().getS();
           time_textView.setText(test_type5);

           String test_type6=DataReceived.get("status").convertToAttributeValue().getS();
           status_textView.setText(test_type6);

           return databaseAccess.getAllItems();




       }




        @Override
        protected void onPostExecute(List<Document> documents) {
        }

    }




    public void send(View view){
        Intent intent=new Intent(this,SendData.class);
        startActivityForResult(intent,1);
    }
}

