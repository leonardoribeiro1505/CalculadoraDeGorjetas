package com.example.leonardoribeiro.calculadoradegorjetas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem, textGorjeta, textTotal;
    private SeekBar seekGorjeta;
    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        seekGorjeta = findViewById(R.id.seekGorjeta);

        seekGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = seekBar.getProgress();
                textPorcentagem.setText(Math.round(porcentagem) + "%");

                //Recuperar valores digitados
                String valorDigitado = editValor.getText().toString();

                //Validar campo digitado
                Boolean campoValidado = validarCampos(valorDigitado);
                if (campoValidado) {
                    calcular(valorDigitado);
                }else {

                    Toast.makeText(getApplicationContext(), "Informe o valor da conta", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

    }

    public void calcular(String vDigitado) {

            //recupera valor digitado e converte para double
            double valorDigitado = Double.parseDouble(vDigitado);

            //calcula a gorjeta total
            double gorjeta = valorDigitado * (porcentagem/100);
            double total = gorjeta + valorDigitado;

            //exibe a gorjeta total
            textGorjeta.setText("R$ " + Math.round(gorjeta));
            textTotal.setText("R$ " + Math.round(total));


    }

    public Boolean validarCampos(String vDigitado) {

        Boolean camposValidados = true;

        if (vDigitado == null || vDigitado.equals("")) {
            camposValidados = false;

        }
        return camposValidados;
    }
}
