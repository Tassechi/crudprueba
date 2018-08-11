package crudprueba.com.crudprueba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView list;
    ArrayList<String>listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = (ListView) findViewById(R.id.list);

        CargarListado();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, listado.get(position), Toast.LENGTH_SHORT ).show();
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);
                String Nombre =listado.get(position).split(" ")[1];
                String Apellido =listado.get(position).split(" ")[2];
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("Id",clave);
                intent.putExtra("Nombre",Nombre);
                intent.putExtra("Apellido",Apellido);
                startActivity(intent);



            }
        });



        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }


    private void CargarListado(){
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        list.setAdapter(adapter);


    }

    private ArrayList<String> ListaPersonas(){
        ArrayList<String>datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this,"Demo", null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id, Nombre, Apellido from Personas";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()){
            do {
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                datos.add(linea);
            } while (c.moveToNext());
        }

        db.close();
        return datos;

    }

}
