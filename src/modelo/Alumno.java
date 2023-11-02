package modelo;

import java.io.Serializable;

public class Alumno implements Serializable {

    private String cCodAlu;
    private String cNomAlu;

    public Alumno(String cCodAlu, String cNomAlu) {
        this.cCodAlu = cCodAlu;
        this.cNomAlu = cNomAlu;
    }

    public String getcNomAlu() {
        return cNomAlu;
    }

    public void setcNomAlu(String cNomAlu) {
        if (cNomAlu != null) {
            this.cNomAlu = cNomAlu;
        }
    }

    public String getcCodAlu() {
        return cCodAlu;
    }

    public void setcCodAlu(String cCodAlu) {
        if (cCodAlu != null) {
            this.cCodAlu = cCodAlu;
        }
    }

}
