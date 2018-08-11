package crudprueba.com.crudprueba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(edit1.getText().toString(),edit2.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }


    private void guardar(String Nombre, String Apellido){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {

            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);
            c.put("Apellido", Apellido);
            db.insert("PERSONAS", null, c);
            db.close();
            Toast.makeText(this, "Registro Insetado", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();


        }

    }

}
