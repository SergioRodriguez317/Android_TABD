package com.example.sergiorodriguez.ejercicio;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Estas variables permitiran obtener los controles creados y asi poder manipularlos
    EditText editId;
    EditText editNombre;
    EditText editApellido;
    EditText editTelefono;
    EditText editDireccion;
    EditText editCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapeamos las variables creadasa con los controles. De esta maneraa podemos setear valores u obtenerlos
        editId = (EditText) findViewById(R.id.editId);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTelefono = (EditText) findViewById(R.id.editTelefono);
        editDireccion = (EditText) findViewById(R.id.editDireccion);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Estamos asignando el menu al activity
        getMenuInflater().inflate(R.menu.menu_clientes,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //De acuerdo al icono seleccionado , se debe realizar una accion
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_add:
                //Aca debemos trabajar con todos los controles que definen el cliente para poderlo ingesar
                String ident = editId.getText().toString();
                String nombres = editNombre.getText().toString();
                String apellidos = editApellido.getText().toString();
                String telefonos = editTelefono.getText().toString();
                String direccions = editDireccion.getText().toString();
                String correos = editCorreo.getText().toString();
                //Validamos que se ingresen todos los campos
                if(ident.length() > 0 && nombres.length() > 0 && apellidos.length() > 0 && telefonos.length() > 0 && direccions.length() > 0 && correos.length() >0){
                    //Abrimos la base de datos de clientes
                    UsuarioSQLiteHelper usuario = new UsuarioSQLiteHelper(this, "DBClientes", null,2);
                    SQLiteDatabase db = usuario.getWritableDatabase();

                    db.execSQL("INSERT INTO Cliente(Identificacion, Nombres, Apellidos, Telefono, Direccion, Correo) VALUES ("+ ident +",'" + nombres + "', '"+ apellidos+ "','"+ telefonos+"','" + direccions + "','" + correos + "')");
                    db.close();
                    Toast.makeText(this, "El usuario se ha creado exitosamente", Toast.LENGTH_SHORT).show();

                    editId.setText("");
                    editNombre.setText("");
                    editApellido.setText("");
                    editTelefono.setText("");
                    editDireccion.setText("");
                    editCorreo.setText("");
                }

                else{
                    Toast.makeText(this, "Debe ingresar toddos los datos asociados al usuario", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
