package javaherian.yousef.entekhabvahed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * this class is for modeling courses
 * created by hasan
 */
public class ModelSchedule {

    private ArrayList<MapCourseGroup> schedule;
    private int size;

    public ModelSchedule(ArrayList<MapCourseGroup> schedule) {
        this.schedule = schedule;
        size=schedule.size();
    }

    public ModelSchedule(){
        this.schedule = new ArrayList<>();
        size=0;
    }

    public void addMapCourseGroup(int numberOfCourses,int numberOfUnit){
        MapCourseGroup map = new MapCourseGroup(numberOfCourses,numberOfUnit);
        this.schedule.add(map);
        size++;
    }

    public void addMapCourseGroup(MapCourseGroup map){
        this.schedule.add(map);
        size++;
    }

    public MapCourseGroup putMapCourseGroup(int index){
        return schedule.get(index);
    }

    public ArrayList<MapCourseGroup> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<MapCourseGroup> schedule) {
        this.schedule = schedule;
        size=schedule.size();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static class MapCourseGroup{

        private int numberOfCourses;
        private int numberOfUnit;

        public MapCourseGroup(int numberOfCourses, Integer numberOfUnit) {
            this.numberOfCourses = numberOfCourses;
            this.numberOfUnit = numberOfUnit;
        }

        public MapCourseGroup() {

        }

        public int getNumberOfCourses() {
            return numberOfCourses;
        }

        public void setNumberOfCourses(int numberOfCourses) {
            this.numberOfCourses = numberOfCourses;
        }

        public int getNumberOfUnit() {
            return numberOfUnit;
        }

        public void setNumberOfUnit(int numberOfUnit) {
            this.numberOfUnit = numberOfUnit;
        }
    }

}