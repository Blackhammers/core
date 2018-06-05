package com.blackhammers.kalisz.planuz;

import java.io.Serializable;

/**
 * Created by kalis on 2018-05-25.
 */

public class Subjects implements Serializable {
    String Przedmiot, Nauczyciel, Do, Od, RZ, Sale, TerminyUwagi ;

    public Subjects() {
    }


    public Subjects(String przedmiot, String nauczyciel, String aDo, String od, String RZ, String sale, String terminyUwagi) {
        Przedmiot = przedmiot;
        Nauczyciel = nauczyciel;
        Do = aDo;
        Od = od;
        this.RZ = RZ;
        Sale = sale;
        TerminyUwagi = terminyUwagi;
    }

    public String getPrzedmiot() {
        return Przedmiot;
    }

    public void setPrzedmiot(String przedmiot) {
        Przedmiot = przedmiot;
    }

    public String getNauczyciel() {
        return Nauczyciel;
    }

    public void setNauczyciel(String nauczyciel) {
        Nauczyciel = nauczyciel;
    }

    public String getDo() {
        return Do;
    }

    public void setDo(String aDo) {
        Do = aDo;
    }

    public String getOd() {
        return Od;
    }

    public void setOd(String od) {
        Od = od;
    }

    public String getRZ() {
        return RZ;
    }

    public void setRZ(String RZ) {
        this.RZ = RZ;
    }

    public String getSale() {
        return Sale;
    }

    public void setSale(String sale) {
        Sale = sale;
    }

    public String getTerminyUwagi() {
        return TerminyUwagi;
    }

    public void setTerminyUwagi(String terminyUwagi) {
        TerminyUwagi = terminyUwagi;
    }
}
