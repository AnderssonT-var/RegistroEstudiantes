package com.magy.registroestudiantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //CREAMOS UNA VARIABLE  QUE VA A SER ACCEDIDA DESDE CUALQUIER LUGAR
    internal val conexion = BaseDeDatos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///CREAMOS NUESTRAS VARIABLES
        val guardar=findViewById<Button>(R.id.guardar)
        val nombre=findViewById<TextView>(R.id.nombre)
        val apellido=findViewById<TextView>(R.id.apellido)
        val edad=findViewById<TextView>(R.id.edad)
        val ver=findViewById<Button>(R.id.ver)

        guardar.setOnClickListener {
            val nombre = nombre.text.toString()
            val apellido = apellido.text.toString()
            val edad = edad.text.toString()
            conexion.insertarDatos(nombre, apellido, edad)
            Toast.makeText(this,"Se guardo el registro correctamente",Toast.LENGTH_LONG).show()
        }
        ver.setOnClickListener {
            val intent = Intent(this,ListaActivity::class.java)
            startActivity(intent)
        }
    }
}