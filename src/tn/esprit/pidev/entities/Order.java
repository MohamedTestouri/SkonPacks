package tn.esprit.pidev.entities;

public class Order {
private int id;
private int idUser;
private int idPack;
private String status;

    public Order() {
    }

    public Order(int id, int idUser, int idPack, String status) {
        this.id = id;
        this.idUser = idUser;
        this.idPack = idPack;
        this.status = status;
    }

    public Order(int idUser, int idPack) {
        this.idUser = idUser;
        this.idPack = idPack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
