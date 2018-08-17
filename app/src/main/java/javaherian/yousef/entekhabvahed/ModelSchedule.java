package javaherian.yousef.entekhabvahed;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * this class is for modeling courses
 * created by hasan
 */
public class ModelSchedule {

    /**
     * relation between course id and group id
     */
    private Map  <Integer,Integer> schedule;
    /**
     * number of courses
     */
    private Integer numberOfCourses;
    /**
     * amount of each courses
     */
    private Integer numberOfUnit;

    public ModelSchedule(Map<Integer, Integer> schedule, Integer numberOfCourses, Integer numberOfUnit) {
        this.schedule = schedule;
        this.numberOfCourses = numberOfCourses;
        this.numberOfUnit = numberOfUnit;
    }

    public ModelSchedule() {
    }

}