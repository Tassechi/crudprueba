package crudprueba.com.crudprueba;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    EditText edit3, edit4;
    Button btn3,btn4;
    int id;
    String nombre;
    String apellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle b = getIntent().getExtras();
        if (b!= null) {

            id = b.getInt("Id");
            nombre = b.getString("Nombre");
            apellido = b.getString("Apellido");


        }


        edit3 = (EditText) findViewById(R.id.edit3);
        edit4 = (EditText) findViewById(R.id.edit4);

        edit3.setText(nombre);
        edit4.setText(apellido);


        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id, edit3.getText().toString(), edit4.getText().toString());
                onBackPressed();

            }
        });

    }

    private void Modificar(int Id, String Nombre, String Apellido) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update Personas set nombre'" + Nombre + "',Apellido='" + Apellido + "'where Id=" + Id;
        db.execSQL(sql);
        db.close();


    }

    private void Eliminar(int Id) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from Personas where Id=" + Id;
        db.execSQL(sql);
        db.close();

    }
}
