package RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "UserTable")
@TypeConverters({DateConverter.class})
public class User {
    @PrimaryKey
    private int id;
    @NonNull
    private String UserName;
    private String email;
    private String birthday;
    private String male;
    private String female;
    private String country;

    public User() {
    }

    public User(int id, @NonNull String userName, @NonNull String email, String birthday, String male, String female, String country) {
        this.id = id;
        UserName = userName;
        this.email = email;
        this.birthday = birthday;
        this.male = male;
        this.female = female;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return UserName;
    }

    public void setUserName(@NonNull String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", UserName='" + UserName + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", male='" + male + '\'' +
                ", female='" + female + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
