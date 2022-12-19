package RoomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LevelTable")
public class Level {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int CountPoint;

    public Level(int countPoint) {
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
