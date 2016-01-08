package com.align.calculdelimc;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        setupLogic();

    }

    private void setupLogic() {
        // La chaîne de caractères par défaut
        final String defaut = getString(R.string.screen_placeholder);

        final String bmi = getString(R.string.bmi);

        // La chaîne de caractères de la megafonction
        final String megaString = getString(R.string.mega_function_key);

        final Button envoyer = (Button) findViewById(R.id.calcul);
        final Button raz = (Button) findViewById(R.id.raz);
        final EditText poids = (EditText) findViewById(R.id.poids);
        final EditText taille = (EditText) findViewById(R.id.taille);
        final RadioGroup group = (RadioGroup) findViewById(R.id.group);
        final TextView result = (TextView) findViewById(R.id.result);
        final CheckBox mega = (CheckBox) findViewById(R.id.mega);


        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                result.setText(defaut);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        // Uniquement pour le bouton "calculer"
        final View.OnClickListener envoyerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupère la taille
                String t = taille.getText().toString();
                // On récupère le poids
                String p = poids.getText().toString();
                if ((t.isEmpty() || p.isEmpty())) {
                    Toast.makeText(MainActivity.this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                } else if (!mega.isChecked()) {// Si la megafonction n'est pas activée:
                    float tValue = Float.valueOf(t);

                    if (tValue <= 0)// On vérifie que la taille est cohérente
                        Toast.makeText(MainActivity.this, R.string.small_size, Toast.LENGTH_SHORT).show();
                    else {
                        float pValue = Float.valueOf(p);
// Si l'utilisateur a indiqué que la taille était en centimètres
// On vérifie que la Checkbox sélectionnée est la deuxième à l'aide de son identifiant
                        if (group.getCheckedRadioButtonId() == R.id.radio2)
                            tValue = tValue / 100;
                        tValue = (float) Math.pow(tValue, 2);
                        float imc = pValue / tValue;
                        final String tresor = String.valueOf(imc);
                        result.setText(bmi + tresor);
                    }
                } else
                    result.setText(megaString);
            }
        };
        // Listener du bouton de remise à zéro
        final View.OnClickListener razListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poids.getText().clear();
                taille.getText().clear();
                result.setText(defaut);
            }
        };
        envoyer.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gollar = result.getText().toString();
                //make some #75 vibration here
                if (gollar.isEmpty()) {
                } else {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("BMI result", gollar);
                    clipboardManager.setPrimaryClip(clipData);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == R.id.action_help) {
            HelpDialog.show(this);
            return true;
        } else if (id == R.id.action_about) {
            AboutDialog.show(this);
        }
        return super.onOptionsItemSelected(item);
    }

}