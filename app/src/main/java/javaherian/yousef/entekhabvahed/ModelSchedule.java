package javaherian.yousef.entekhabvahed;
import android.support.v4.util.ArrayMap;
import android.view.Display;

/**
 * this class is for modeling courses
 * created by hasan
 * edited by yousef
 */
public class ModelSchedule {

    private ArrayMap<ModelCourse,ModelGroup> map;
    private int uniqueId;
    private int totalScore;

    public ModelSchedule(ArrayMap<ModelCourse, ModelGroup> map, int uniqueId, int totalScore) {
        this.map = new ArrayMap<ModelCourse, ModelGroup>(map);
        this.uniqueId = uniqueId;
        this.totalScore = totalScore;
    }

    public ModelSchedule(){
        map = new ArrayMap<>();
        this.uniqueId = 0;
        this.totalScore = 0;
    }
    public void addToMap(ModelCourse course,ModelGroup group){
        map.put(course,group);
    }
    public void deleteFromMap(ModelCourse course){
        map.remove(course);
    }
    public ArrayMap<ModelCourse, ModelGroup> getMap() {
        return map;
    }

    public void setMap(ArrayMap<ModelCourse, ModelGroup> map) {
        this.map = map;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public ModelSchedule clone(){
        ModelSchedule temp = new ModelSchedule(map,uniqueId,totalScore);
        return  temp;
    }
}