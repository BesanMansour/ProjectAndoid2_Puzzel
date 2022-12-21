package RoomDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LevelTable")
public class Level {
    @PrimaryKey
    //@NonNull
    private int id;
    private int CountPoint;

    public Level(int id, int countPoint) {
        this.id = id;
        CountPoint = countPoint;
    }

    public Level() {
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getCountPoint() {
        return CountPoint;
    }

    public void setCountPoint(int countPoint) {
        CountPoint = countPoint;
    }
}
