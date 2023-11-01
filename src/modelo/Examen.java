package modelo;

import java.util.Date;

public class Examen {

    private String cCodAlu;
    private String cCodCurso;
    private int nNumExam;
    private String dFecExam;
    private int nNotaExam;

    public Examen(String cCodAlu, String cCodCurso, int nNumExam, String dFecExam, int nNotaExam) {
        this.cCodAlu = cCodAlu;
        this.cCodCurso = cCodCurso;
        this.nNumExam = nNumExam;
        this.dFecExam = dFecExam;
        this.nNotaExam = nNotaExam;
    }

    public Examen() {
    }

    public int getnNotaExam() {
        return nNotaExam;
    }

    public void setnNotaExam(int nNotaExam) {
        this.nNotaExam = nNotaExam;
    }

    public String getcCodAlu() {
        return cCodAlu;
    }

    public void setcCodAlu(String cCodAlu) {
        this.cCodAlu = cCodAlu;
    }

    public String getcCodCurso() {
        return cCodCurso;
    }

    public void setcCodCurso(String cCodCurso) {
        this.cCodCurso = cCodCurso;
    }

    public int getnNumExam() {
        return nNumExam;
    }

    public void setnNumExam(int nNumExam) {
        this.nNumExam = nNumExam;
    }

    public String getdFecExam() {
        return dFecExam;
    }

    public void setdFecExam(String dFecExam) {
        this.dFecExam = dFecExam;
    }

}
