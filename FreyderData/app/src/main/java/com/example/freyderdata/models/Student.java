package com.example.freyderdata.models;

public class Student {

    int id;// id
    String nombre; // nombre
    boolean gender;//genero
    short edad; // edad
    String code; // codigo
    int semester; //semestre;

    public Student(int id, String nombre, boolean gender, short edad, String code, int semester) {
        this.id = id;
        this.nombre = nombre;
        this.gender = gender;
        this.edad = edad;
        this.code = code;
        this.semester = semester;
    }

    public Student(String nombre, boolean gender, short edad, String code, int semester) {
        this.nombre = "-";
        this.gender = false;
        this.edad = 0;
        this.code = "-";
        this.semester = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", gender=" + gender +
                ", edad=" + edad +
                ", code='" + code + '\'' +
                ", semester=" + semester +
                '}';
    }
}



