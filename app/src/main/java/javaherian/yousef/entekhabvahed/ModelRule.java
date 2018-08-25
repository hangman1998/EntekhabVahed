package javaherian.yousef.entekhabvahed;

/**
 * this class for modeling rule for scoring
 * created by : Heidari
 */
public class ModelRule {

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
     * can be 1 for START_TIME or 2 for FINISH_TIME
     */
    private int time;
    /**
     * can be 1 for LESS_THAN or 2 for MORE_THAN
     */
    private int relation;
    private String course;
    private String teacher;
    int score;

    public ModelRule(int day, int time, int relation, String course, String teacher, int score,String name) {
        this.day = day;
        this.time = time;
        this.relation = relation;
        this.course = course;
        this.teacher = teacher;
        this.score = score;
        this.name = name;
    }

    public ModelRule() {
        day = day=0;
        time =START_TIME;
        relation = LESS_THAN ;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
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