package com.magy.registroestudiantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class ListaActivity : AppCompatActivity() {
    //CREAMOS UNA VARIABLE  QUE VA A SER ACCEDIDA DESDE CUALQUIER LUGAR
    internal val conexion = BaseDeDatos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val listView=findViewById<ListView>(R.id.listView)
        var list:ArrayList<String> = conexion.mostrarNombre()
        var alumnos:List<Alumnos> = conexion.seleccionarTodo()

        var adp: ArrayAdapter<String>
        adp = ArrayAdapter(this,android.R.layout.simple_list_item_1,list) //REVISAR
        listView.adapter =adp

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val id=alumnos[i].id
            val nom=alumnos[i].nombre
            val apellido=alumnos[i].apellido
            val edad=alumnos[i].edad

            val intent = Intent(this,editActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("nombre",nom)
            intent.putExtra("apellido",apellido)
            intent.putExtra("edad",edad)
            startActivity(intent)
            finish()
        }
        }
    }