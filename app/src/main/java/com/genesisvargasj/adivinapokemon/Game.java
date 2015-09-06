package com.genesisvargasj.adivinapokemon;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.view.View;

/**
 * Created by genesisvargas on 2/09/15.
 */
public class Game extends AppCompatActivity {

    private String[] nombre_pokemon = {"charmander", "bulbasaur", "caterpie", "charizard", "poliwag", "psyduck", "pikachu", "venonat"};
    private String[] sombra_pokemon = {"s_charmander", "s_bulbasaur", "s_caterpie", "s_charizard", "s_poliwag", "s_psyduck", "s_pikachu", "s_venonat"};
    private int intentos = 3;
    private TextView txtIntentos;
    private EditText txtPokemon;
    private int numero_generado;
    private ImageView imgPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        txtIntentos = (TextView) findViewById(R.id.txtIntentos);
        txtPokemon = (EditText) findViewById(R.id.txtPokemon);
        imgPokemon = (ImageView) findViewById(R.id.imgPokemon);
        numero_generado = GenerarNumero();
        EstablecerSombra(numero_generado);
    }

    public void EstablecerSombra(int numero)
    {
        int recurso = getResources().getIdentifier(sombra_pokemon[numero], "drawable", getPackageName());
        imgPokemon.setImageResource(recurso);
    }

    public int GenerarNumero()
    {
        return (int) (Math.random() * nombre_pokemon.length);
    }

    public void Aceptar(View view)
    {
        OcultarTeclado();
        String nombre = txtPokemon.getText().toString().toLowerCase();
        if (nombre.equals(nombre_pokemon[numero_generado]))
        {
            Toast.makeText(getApplicationContext(), "Felicidades!! HAS ADIVINADO EL POKEMON", Toast.LENGTH_SHORT).show();
            EstablecerPokemon(numero_generado);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                public void run()
                {
                    numero_generado = GenerarNumero();
                    EstablecerSombra(numero_generado);
                    txtPokemon.setText("");
                }
            }, 2000);
        }
        else
        {
            Vibrar();
            Toast.makeText(getApplicationContext(), "Perdsiste!! NO ES EL POKEMON", Toast.LENGTH_SHORT).show();
            intentos = intentos - 1;
            txtIntentos.setText("Tienes: " + intentos + " intentos");
        }
        if (intentos == 0)
        {
            finish();
        }
    }

    private void EstablecerPokemon(int numero)
    {
        int resId = getResources().getIdentifier(nombre_pokemon[numero], "drawable", getPackageName());
        imgPokemon.setImageResource(resId);
    }

    private void OcultarTeclado()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtPokemon.getWindowToken(), 0);
    }

    private void Vibrar()
    {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }
}
