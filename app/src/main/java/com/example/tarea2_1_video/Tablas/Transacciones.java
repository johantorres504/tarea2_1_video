package com.example.tarea2_1_video.Tablas;

public class Transacciones
{
    //Nombre de la base de datos
    public static final String NameDatabase = "PM01DB";

    /* Creacion de las tablas de la BD */
    public static final String TbVideos = "videos";

    /* Campos de la tabla videos */
    public static final String id = "id";

    // DDL
    public static final String CTvideos = "CREATE TABLE videos (id INTEGER PRIMARY KEY AUTOINCREMENT)";

    public static final String GetVideos = "SELECT * FROm " + Transacciones.TbVideos;

    public static final String DropTVideos = "DROP TABLE IF EXISTS videos";


}
