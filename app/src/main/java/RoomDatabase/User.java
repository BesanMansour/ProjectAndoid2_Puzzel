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
    private String Gender;
    private String country;

    public User() {
    }

    public User(int id, @NonNull String userName, @NonNull String email, String birthday, String Gender , String country) {
        this.id = id;
        UserName = userName;
        this.email = email;
        this.birthday = birthday;
        this.Gender = Gender;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
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
                ", country='" + country + '\'' +
                '}';
    }
}
