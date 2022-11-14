package com.magy.registroestudiantes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

///VAMOS A CREAR LA BBDD JUNTO  CON LAS CONSULTAS
data class Alumnos(val id: Int, val nombre: String , val apellido: String, val edad: Int)

class BaseDeDatos (context: Context) : SQLiteOpenHelper(context,"Alumnos.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
       db!!.execSQL("create table alumnos (id integer primary key autoincrement, nombre text,apellido text, edad integer )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
       db!!.execSQL("drop table if EXISTS alumnos")
        onCreate(db)
    }
    ///CREAMOS UNA FUNCION PARA INGRESAR LOS DATOS
    fun insertarDatos(nombre:String,apellido:String,edad:String){
        val db =this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put("nombre",nombre)
        contentValues.put("apellido",apellido)
        contentValues.put("edad",edad)
        db.insert("alumnos",null,contentValues)
    }
    @Suppress("Range")
    fun mostrarNombre(): ArrayList<String>{
        var list:ArrayList<String> = arrayListOf()
        val db: SQLiteDatabase = readableDatabase
        var c = db.rawQuery("select * from alumnos",null)
        while (c.moveToNext()){
            var nombre : String = c.getString(c.getColumnIndex("nombre"))
            list.add(nombre)
        }
        return  list
    }
    fun seleccionarTodo(): List<Alumnos>{
        var listaAlumnos : MutableList<Alumnos> = ArrayList<Alumnos>()
        var db = this.readableDatabase
        var c = db.rawQuery("select * from alumnos",null)
        if(c.moveToFirst()){
            do {
                val todo = Alumnos(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3))
                listaAlumnos.add(todo)
            }while (c.moveToNext())
        }
        return listaAlumnos
    }
    fun actualizarDatos(id:String,nombre: String,apellido: String,edad: String):Boolean{
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("id",id)
        contentValues.put("nombre",nombre)
        contentValues.put("apellido",apellido)
        contentValues.put("edad",edad)
        db.update("alumnos",contentValues,"id=?", arrayOf(id))
        return true
    }
    fun borrarDatos(id: String):Int{
        val db=this.writableDatabase
        return db.delete("alumnos","id=?", arrayOf(id))
    }
}