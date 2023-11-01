package modelo;

import java.io.Serializable;

public class Curso implements Serializable {

    private String cCodCurso;
    private String cNomCurso;
    private int nNumExa;

    public Curso(String cCodCurso, String cNomCurso, int nNumExa) {
        this.cCodCurso = cCodCurso;
        this.cNomCurso = cNomCurso;
        this.nNumExa = nNumExa;
    }

    public int getnNumExa() {
        return nNumExa;
    }

    public void setnNumExa(int nNumExa) {
        this.nNumExa = nNumExa;
    }

    public String getcCodCurso() {
        return cCodCurso;
    }

    public void setcCodCurso(String cCodCurso) {
        if (cCodCurso != null) {
            this.cCodCurso = cCodCurso;
        }
    }

    public String getcNomCurso() {
        return cNomCurso;
    }

    public void setcNomCurso(String cNomCurso) {
        if (cNomCurso != null) {
            this.cNomCurso = cNomCurso;
        }
    }

}
