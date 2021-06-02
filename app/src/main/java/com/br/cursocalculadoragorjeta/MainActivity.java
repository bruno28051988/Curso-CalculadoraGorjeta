package com.br.cursocalculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText campoValorDigitado;
    private SeekBar barraPorcentagem;
    private TextView valorPorcentagem, valorGorjeta, valorGorjetaTotal;

    private double porcentagem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoValorDigitado = findViewById(R.id.edit_valorGorjeta);
        barraPorcentagem = findViewById(R.id.seekBar_PorcentagemGorjeta);
        valorPorcentagem = findViewById(R.id.textPorcentagem_Gorjeta);
        valorGorjeta = findViewById(R.id.textValorGorjeta);
        valorGorjetaTotal = findViewById(R.id.textValorGorjeta_Total);

        barraPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                valorPorcentagem.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){

        String valorRecuperado = campoValorDigitado.getText().toString();

        if (valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(getApplicationContext(),
                    "Preencher o valor da Conta",
                    Toast.LENGTH_SHORT).show();
        }else{

            //converter de String para double
           double valorDigitado = Double.parseDouble(valorRecuperado);

            //Calculo gorjeta e total
           double gorjeta = valorDigitado * (porcentagem /100);
           double valorTotal = valorDigitado + gorjeta;

            //exibir gorjeta e total
            valorGorjeta.setText("R$ "+ gorjeta);
            valorGorjetaTotal.setText("R$ " + valorTotal);
        }

    }

}