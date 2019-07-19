package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private TextView todoList;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveList = (Button) findViewById(R.id.saveButton);
        todoList = (TextView) findViewById(R.id.todoList);

        saveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logic
                if (todoList.getText().toString().equals("")){
                String message = todoList.getText().toString();
                writeToFile(message);



        }else {
               //nothing
                }

                try {
                    if (readFromFile() !=null){
                     todoList.setText(readFromFile());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    private void writeToFile(String message){
           try{
               OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todolist.txt", Context.MODE_PRIVATE));
       outputStreamWriter.write(message);
       outputStreamWriter.close();

        }catch (FileNotFoundException e){
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
    private String readFromFile() throws IOException {
    String result = "";
        InputStream inputStream = openFileInput("todolist.txt");

        if(inputStream !=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tempString= "";
            StringBuilder stringBuilder = new StringBuilder();
    while ( (tempString = bufferedReader.readLine()) !=null) {
        stringBuilder.append(tempString);
    }
        inputStream.close();
    result = (String) stringBuilder.toString();

    }
    return result;}});
    }
}