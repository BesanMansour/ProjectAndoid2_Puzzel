package RoomDatabase;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

@Entity(foreignKeys = {@ForeignKey(entity = Level.class,
parentColumns = {"id"},childColumns = {"levelId"})})
public class Mystery {
    @PrimaryKey
    @NonNull
    private int id;
    private String title;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String true_answer;
    private int points;
    private int levelId;
    private int duration;
    final int patternId;
    final String patternName;
    private String hint;

    public Mystery(int id, String title, String answer_1, String answer_2, String answer_3, String answer_4,
                   String true_answer, int points, int levelId, int duration, int patternId, String patternName, String hint) {
        this.id = id;
        this.title = title;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.true_answer = true_answer;
        this.points = points;
        this.levelId = levelId;
        this.duration = duration;
        this.patternId = patternId;
        this.patternName = patternName;
        this.hint = hint;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }

    public String getAnswer_4() {
        return answer_4;
    }

    public void setAnswer_4(String answer_4) {
        this.answer_4 = answer_4;
    }

    public String getTrue_answer() {
        return true_answer;
    }

    public void setTrue_answer(String true_answer) {
        this.true_answer = true_answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getPatternId() {
        return patternId;
    }

    public String getPatternName() {
        return patternName;
    }
}