package com.m6code.saverestorestatechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddToListActivity extends AppCompatActivity {

    public static final String ADD_ITEM = "com.m6code.saverestorestatechallenge.AddToListActivity.extras.ADD_ITEM";
    private Button btWine, btCookies, btSoda, btCabbage, btEggs, btGuava, btBanana, btCake, btApple, btCarrot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        btCarrot = findViewById(R.id.button);
        btCake = findViewById(R.id.button2);
        btApple = findViewById(R.id.button3);
        btBanana = findViewById(R.id.button4);
        btGuava = findViewById(R.id.button5);
        btEggs = findViewById(R.id.button6);
        btCabbage = findViewById(R.id.button7);
        btSoda = findViewById(R.id.button8);
        btCookies = findViewById(R.id.button9);
        btWine = findViewById(R.id.button10);
    }

    public void addWine(View view) {
        String wine = btWine.getText().toString();
        returnReply(wine);
    }

    public void addCookies(View view) {
        String cookies = btCookies.getText().toString();
        returnReply(cookies);
    }

    public void addSoda(View view) {
        String soda = btSoda.getText().toString();
        returnReply(soda);
    }

    public void addCabbage(View view) {
        String cabbage = btCabbage.getText().toString();
        returnReply(cabbage);
    }

    public void addEggs(View view) {
        String eggs = btEggs.getText().toString();
        returnReply(eggs);
    }

    public void addGuava(View view) {
        String guava = btGuava.getText().toString();
        returnReply(guava);
    }

    public void addBanana(View view) {
        String banana = btBanana.getText().toString();
        returnReply(banana);
    }

    public void addApple(View view) {
        String apple = btApple.getText().toString();
        returnReply(apple);
    }

    public void addCake(View view) {
        String cake = btCake.getText().toString();
        returnReply(cake);
    }

    public void addCarrot(View view) {
        String carrot = btCarrot.getText().toString();
        returnReply(carrot);
    }

    private void returnReply(String item) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(ADD_ITEM, item);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}