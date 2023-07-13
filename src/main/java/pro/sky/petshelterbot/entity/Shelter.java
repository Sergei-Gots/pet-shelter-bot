package pro.sky.petshelterbot.entity;

import javax.persistence.*;

@Entity
@Table(name = "shelters")
public class Shelter {

    public enum SHELTER_TYPE {
        CAT,
        DOG
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String workTime;
    private String address;
    private String tel;
    private String email;

    private String type;

    public Shelter(String name, String workTime, String address, String tel, String email, String type) {
        this.name = name;
        this.workTime = workTime;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.type = type;
    }

    public Shelter() {}

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workTime='" + workTime + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
