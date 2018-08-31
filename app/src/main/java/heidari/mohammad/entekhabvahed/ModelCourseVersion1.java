package heidari.mohammad.entekhabvahed;

public class ModelCourseVersion1 {
    public String courseName;
    public String teacherName;
    public String timing;
    private int startTime;

    public ModelCourseVersion1(String courseName, String teacherName, String timing, int startTime) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.timing = timing;
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
