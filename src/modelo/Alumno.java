package modelo;

import java.io.Serializable;

public class Alumno implements Serializable{

    private String cCodALu;
    private String cNomAlu;

    public Alumno(String cCodALu, String cNomAlu) {
        this.cCodALu = cCodALu;
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

    public String getcCodALu() {
        return cCodALu;
    }

    public void setcCodALu(String cCodALu) {
        if (cCodALu != null) {
            this.cCodALu = cCodALu;
        }
    }

}
