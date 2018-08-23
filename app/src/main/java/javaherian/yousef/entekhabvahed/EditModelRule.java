package javaherian.yousef.entekhabvahed;

public class EditModelRule {

    /**
     * this class for modeling rule for scoring
     * created by : Heidari
     */
    public static final int START_TIME = 1;
    public static final int FINISH_TIME = 2;

    public static final int LESS_THAN = 3;
    public static final int MORE_THAN = 4;


    public static final int SATURDAY = 0;
    public static final int MONDAY = 1;
    public static final int SUNDAY = 2;
    public static final int THURSDAY = 3;
    public static final int WEDNESDAY = 4;
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
    private int start_time;
    /**
     * a time for finish_time
     */
    private int finish_time;
    /**
     * relation on start time
     */
    private int relation_start_time;
    /**
     * relation on finish time
     */
    private int relation_finish_time;


    private String course;
    private String teacher;
    int score;

    public EditModelRule(String name, int day, int start_time, int finish_time, int relation_start_time, int relation_finish_time, String course, String teacher, int score) {
        this.name = name;
        this.day = day;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.relation_start_time = relation_start_time;
        this.relation_finish_time = relation_finish_time;
        this.course = course;
        this.teacher = teacher;
        this.score = score;
    }

    public EditModelRule() {
        day = day = 0;
        start_time = 0;
        finish_time = 0;
        relation_finish_time = 0;
        relation_start_time = 0;
        course = "";
        teacher = "";
        score = 0;
    }

    public static int getStartTime() {
        return START_TIME;
    }

    public static int getFinishTime() {
        return FINISH_TIME;
    }

    public static int getLessThan() {
        return LESS_THAN;
    }

    public static int getMoreThan() {
        return MORE_THAN;
    }

    public static int getSATURDAY() {
        return SATURDAY;
    }

    public static int getMONDAY() {
        return MONDAY;
    }

    public static int getSUNDAY() {
        return SUNDAY;
    }

    public static int getTHURSDAY() {
        return THURSDAY;
    }

    public static int getWEDNESDAY() {
        return WEDNESDAY;
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

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public int getRelation_start_time() {
        return relation_start_time;
    }

    public void setRelation_start_time(int relation_start_time) {
        this.relation_start_time = relation_start_time;
    }

    public int getRelation_finish_time() {
        return relation_finish_time;
    }

    public void setRelation_finish_time(int relation_finish_time) {
        this.relation_finish_time = relation_finish_time;
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
