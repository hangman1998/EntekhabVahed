package javaherian.yousef.entekhabvahed;

public class ModelRule {

    /**
     * this class for modeling rule for scoring
     * created by : Heidari
     * edited by : yousef
     */

    public static final int LESS_THAN = 0;
    public static final int MORE_THAN = 1;

    public static final int ALL = 0;
    public static final int SATURDAY = 1;
    public static final int SUNDAY = 2;
    public static final int MONDAY = 3;
    public static final int TUESDAY = 4;
    public static final int WEDNESDAY = 5;
    public static final int THURSDAY = 6;

    /**
     * used for remember information of rule without checking all information of rule
     */
    private String name;
    /**
     * can be 0 for SATURDAY  or 1 for MONDAY
     * 2 SUNDAY or 3 THURSDAY  4 WEDNESDAY
     */
    private int day;
    /**
     * a time for start_time
     */
    private int startTime;
    /**
     * a time for finish_time
     */
    private int finishTime;
    /**
     * relation on start time
     */
    private int startTimeRelation;
    /**
     * relation on finish time
     */
    private int finishTimeRelation;


    private String course;
    private String teacher;
    int score;

    public ModelRule(String name, int day, int startTime, int finishTime, int startTimeRelation, int finishTimeRelation, String course, String teacher, int score) {
        this.name = name;
        this.day = day;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.startTimeRelation = startTimeRelation;
        this.finishTimeRelation = finishTimeRelation;
        this.course = course;
        this.teacher = teacher;
        this.score = score;
    }

    public ModelRule() {
        name = "";
        day = 0;
        startTimeRelation = 0;
        finishTime = 0;
        startTime = 0;
        finishTimeRelation = 0;
        course = "";
        teacher = "";
        score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getStartTimeRelation() {
        return startTimeRelation;
    }

    public void setStartTimeRelation(int startTimeRelation) {
        this.startTimeRelation = startTimeRelation;
    }

    public int getFinishTimeRelation() {
        return finishTimeRelation;
    }

    public void setFinishTimeRelation(int finishTimeRelation) {
        this.finishTimeRelation = finishTimeRelation;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}