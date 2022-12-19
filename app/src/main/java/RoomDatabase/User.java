package RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "UserTable")
@TypeConverters({DateConverter.class})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String UserName;
    @NonNull
    private String email;
    private Date birthday;
    private String gender;
    private String country;

    public User(@NonNull String userName, @NonNull String email, Date birthday, String gender, String country) {
        UserName = userName;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.country = country;
    }

    public User() {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
