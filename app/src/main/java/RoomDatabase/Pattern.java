package RoomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patternTable")
public class Pattern {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String patternName;

    public Pattern(String patternName) {
        this.patternName = patternName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }
}
