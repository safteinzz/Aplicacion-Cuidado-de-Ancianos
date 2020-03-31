package pinfui.entidades;

import java.util.Date;

public class Nota {

    private int id;
    private String dni;
    private Date fecha;
    private String nota;

    public Nota() {

    }

    public Nota(int id, String dni, Date fecha, String nota) {
        super();
        this.dni = dni;
        this.fecha = fecha;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
