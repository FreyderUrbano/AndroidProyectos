package com.example.freyderdata.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.freyderdata.models.Student;

import java.util.ArrayList;

public class DBM extends SQLiteOpenHelper {

    Context context;
    //TIPOS DE DATOS EN LAS TABLAS
    String STRING_TYPE = "text";
    String INT_TYPE = "integer";
    String BOOLEAN_TYPE = "boolean";

    //NOMBRES DE LAS TABLAS DE LAS BASES DE DATOS

    String TABLE_NAME_STUDENTS = "Students";

    /*
    SECCION PARA ESTRUCTURA DE TABLAS
     */
    //ATRIBUTOS DE LA TABLA ESTUDIANTE

    String STD_ID = "id";
    String STD_NAME = "nombre";
    String STD_GENDER = "gender";
    String STD_AGE = "edad";
    String STD_CODE = "code";
    String STD_SEMESTER = "semester";
    //script para la tabla estudiante
    String CREATE_STUDENT_TABLE_SCRIPT =
            "CREATE TABLE " + TABLE_NAME_STUDENTS +
                    " (" +
                    STD_ID + " " + INT_TYPE + ", " +
                    STD_NAME + " " + STRING_TYPE + ", " +
                    STD_GENDER + " " + BOOLEAN_TYPE + ", " +
                    STD_AGE + " " + INT_TYPE + ", " +
                    STD_CODE + " " + STRING_TYPE + ", " +
                    STD_SEMESTER + " " + INT_TYPE + ");";


    public DBM(Context context) {
        super(context, "Academic", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //ELIMINAR LAS TABLAS
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STUDENTS);

        //CREAR TABLAS CON NUEVA ESTRUCTURA
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE_SCRIPT);
    }

    public boolean insertStudentTuple(Student s) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            try {
                String q =
                        "INSERT INTO " + TABLE_NAME_STUDENTS + " {"
                                + STD_ID + ", "
                                + STD_NAME + ", "
                                + STD_GENDER + ", "
                                + STD_AGE + ", "
                                + STD_CODE + ", "
                                + STD_SEMESTER + "} "
                                + " VALUES ("
                                + s.getId() + ", "
                                + " '" + s.getNombre() + "',"
                                + " '" + s.isGender() + "',"
                                + s.getEdad() + "',"
                                + " '" + s.getCode() + "' ,"
                                + s.getSemester() + ");";

                db.execSQL(q);
                ret = true;

            } catch (Exception e) {
                db.close();
                e.getStackTrace();
                ret = false;

            }
            db.close();
        }

        return ret;
    }

    public ArrayList<Student> listStudent() {
        ArrayList<Student> l = new ArrayList<Student>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME_STUDENTS, null);


        while (c.moveToNext()) {
            Student s = new Student(
                    c.getInt(0),
                    c.getString(1),
                    Boolean.parseBoolean(c.getString(2)),
                    c.getShort(3),
                    c.getString(4),
                    c.getInt(5)
            );
            l.add(s);
        }
        c.close();
        db.close();
        return l;
    }
}
