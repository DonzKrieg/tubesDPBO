package com.kelf.happyfresh;

public class User {
    private String id;
    private String name;
    private String noHp;

    public User(String id, String name, String noHP) {
        this.id = id;
        this.name = name;
        this.noHp = noHp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return name;
    }

    public void setNama(String name) {
        this.name = name;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String email) {
        this.noHp = noHp;
    } 
    
}
