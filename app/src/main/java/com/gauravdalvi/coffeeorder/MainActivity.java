package com.gauravdalvi.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;



    public void minus(View view) {
        TextView quan = (TextView) findViewById(R.id.quantity);
        if (quantity != 1){
            quantity--;
            quan.setText(Integer.toString(quantity));
        }
        else {
            Toast.makeText(this,"Minimum Order of 1", Toast.LENGTH_LONG).show();
        }
    }

    public void plus(View view) {
        TextView quan = (TextView) findViewById(R.id.quantity);
        if (quantity != 10) {
            quantity++;
            quan.setText(Integer.toString(quantity));
        }
        else {
            Toast.makeText(this,"Maximum Order of 10", Toast.LENGTH_LONG).show();
        }
    }

    public void order(View view) {
        int cost = 200;
        int whippedcream = 50;
        int chocolate = 100;
        int total = 0;

        CheckBox cream = (CheckBox) findViewById(R.id.whippedcream);
        CheckBox choco = (CheckBox) findViewById(R.id.chocolate);
        EditText name = (EditText) findViewById(R.id.name);
        TextView summary = (TextView) findViewById(R.id.summary);

        if (cream.isChecked()==true && choco.isChecked()==true ) {
            total = (cost + whippedcream + chocolate) * quantity;
        }
        else if (cream.isChecked()==true ) {
            total = (cost + whippedcream) * quantity;
        }
        else if (choco.isChecked()==true ) {
            total = (cost + chocolate) * quantity;
        }
        else {
            total = cost * quantity;
        }

        String s = name.getText().toString();
        String message = "Name: " + s + "\nQuantity: "+ quantity + "\nWhipped Cream: " + cream.isChecked() + "\nChocolate: " + choco.isChecked() + "\nOrder Total = Rs." + total + "\nThank You!!";
        summary.setText(message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + s);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
}