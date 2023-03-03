package fr.m2i.todolistfull.models;

public class Urgency {
    private int idUrgency;
    private String nameUrgency;

    public Urgency(int idUrgency, String nameUrgency) {
        this.idUrgency = idUrgency;
        this.nameUrgency = nameUrgency;
    }

    public Urgency() {
    }

    public int getIdUrgency() {
        return idUrgency;
    }

    public void setIdUrgency(int idUrgency) {
        this.idUrgency = idUrgency;
    }

    public String getNameUrgency() {
        return nameUrgency;
    }

    public void setNameUrgency(String nameUrgency) {
        this.nameUrgency = nameUrgency;
    }
}
