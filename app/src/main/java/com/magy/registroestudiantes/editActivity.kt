package com.magy.registroestudiantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class editActivity : AppCompatActivity() {
    //CREAMOS UNA VARIABLE  QUE VA A SER ACCEDIDA DESDE CUALQUIER LUGAR
    internal val conexion = BaseDeDatos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val editNombre=findViewById<EditText>(R.id.editNombre)
        val editApellido=findViewById<EditText>(R.id.editApellido)
        val editEdad=findViewById<EditText>(R.id.editEdad)
        val editar=findViewById<Button>(R.id.editar)
        val borrar=findViewById<Button>(R.id.borrar)

        var b: Bundle?=intent.extras
        val id = b?.getInt("id").toString()
        val nombre = b?.getString("nombre")
        val apellido = b?.getString("apellido")
        val edad = b?.getInt("edad").toString()

        editNombre.setText(nombre)
        editApellido.setText(apellido)
        editEdad.setText(edad)

        editar.setOnClickListener {
            val siActualiza = conexion.actualizarDatos(id,
            editNombre.text.toString(),
            editApellido.text.toString(),
            editEdad.text.toString())

            if (siActualiza==true){
                val intent= Intent(this,ListaActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"No puede ser actualizado",Toast.LENGTH_LONG).show()
            }
        }
        borrar.setOnClickListener {
            val eliminar = conexion.borrarDatos(id)
            if (eliminar != 0){
                val intent= Intent(this,ListaActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this," No se pudo borrar el registro",Toast.LENGTH_LONG).show()
            }
        }
    }
}