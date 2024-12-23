package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Log implements Serializable {
    private Timestamp time;
    String type;
    String document;

    public Log() {

    }

    public Timestamp getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getDocument() {
        return document;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Log(Timestamp time, String type, String document) {
        this.time = time;
        this.type = type;
        this.document = document;
    }
}
