package com.genesisvargasj.adivinapokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Game(View view)
    {
        Intent frm = new Intent(this, Game.class);
        startActivity(frm);
    }

    public void About(View view)
    {
        Intent frm = new Intent(this, About.class);
        startActivity(frm);
    }
}
