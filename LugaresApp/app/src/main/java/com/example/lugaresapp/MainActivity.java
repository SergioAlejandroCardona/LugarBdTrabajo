package com.example.lugaresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

import java.text.Format;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etId;
    private EditText etNombre;
    private EditText etPoblacion;
    private EditText etLatitud;
    private EditText etLongitud;
    private TextView tvResultado;
    private Button btGuardar;
    private Button btListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.txtId);
        etNombre = findViewById(R.id.txtNombre);
        etPoblacion = findViewById(R.id.txtPoblacion);
        etLatitud = findViewById(R.id.txtLatitud);
        etLongitud = findViewById(R.id.txtLongitud);
        tvResultado = findViewById(R.id.tvResultado);
        btGuardar = findViewById(R.id.btnGuardar);
        btListar = findViewById(R.id.btnListar);

        tvResultado.setMovementMethod(new ScrollingMovementMethod());

        btGuardar.setOnClickListener(this);
        btListar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {

            if (v.getId() == R.id.btnGuardar){

                validarCampos();

                Guardarlugar();
            }
            if (v.getId() == R.id.btnListar){

                tvResultado.setText(Listar());
            }

        }catch (Exception e){


        }

    }

    private void Guardarlugar(){

        MyDbHelper db = new MyDbHelper(this);
        ArrayList<Lugares> lugar = db.selectLugar(db.getWritableDatabase());

        int etid = Integer.parseInt(etId.getText().toString());
        String etnombre= etNombre.getText().toString();
        int etpoblacion= Integer.parseInt(etPoblacion.getText().toString());
        int etlatitud= Integer.parseInt(etLatitud.getText().toString());
        int etlongitud= Integer.parseInt(etLongitud.getText().toString());
        Lugares lugares = new Lugares(etid, etnombre, etpoblacion, etlatitud, etlongitud);
        lugar.add(lugares);

        db.InsertLugar(db.getWritableDatabase(), lugares);
        CustomToastView.makeSuccessToast(this, "Lugar guardado correctamente", R.layout.custom_toast).show();
        limpiarCampos();
        tvResultado.setText(Listar());

    }

    private void validarCampos(){

        String Id = etId.getText().toString();
        String Nombre = etNombre.getText().toString();
        String Poblacion = etPoblacion.getText().toString();
        String Latitud = etLatitud.getText().toString();
        String Longitud = etLongitud.getText().toString();

        if(Id.isEmpty()){
            CustomToastView.makeInfoToast(this, "Error al validar el Id", R.layout.custom_toast).show();
            return;
        }
        if(Nombre.isEmpty()){
            CustomToastView.makeInfoToast(this, "Error al validar el Nombre", R.layout.custom_toast).show();
            return;
        }
        if(Poblacion.isEmpty()){
            CustomToastView.makeInfoToast(this, "Error al validar la Población", R.layout.custom_toast).show();
            return;
        }
        if(Latitud.isEmpty()){
            CustomToastView.makeInfoToast(this, "Error al validar la Latitud", R.layout.custom_toast).show();
            return;
        }
        if(Longitud.isEmpty()){
            CustomToastView.makeInfoToast(this, "Error al validar La Longitud", R.layout.custom_toast).show();
            return;
        }
    }

    private void limpiarCampos(){
        etId.getText().clear();
        etNombre.getText().clear();
        etPoblacion.getText().clear();
        etLatitud.getText().clear();
        etLongitud.getText().clear();
        etId.requestFocus();
    }


    private String Listar(){

        MyDbHelper db = new MyDbHelper(this);
        ArrayList<Lugares> lugar = db.selectLugar(db.getWritableDatabase());

        String Mensaje = "Lugares: \n";

        for (int i = 0; i <= lugar.size()-1; i++){
            Mensaje = Mensaje + "\n" + "Id: "+ lugar.get(i).getId()
                    + "\nNombre: " + lugar.get(i).getNombre()
                    + "\nPoblación: " + lugar.get(i).getPoblacion()
                    + "\nLatitud: " + lugar.get(i).getLatitud()
                    + "\nLongitud: " + lugar.get(i).getLongitud() +"\n";
        }
        return Mensaje;
    }
}