package javaherian.yousef.entekhabvahed;

import android.content.Intent;

import java.io.Serializable;

/**
 * this GroupsClass related to any Course like electronics
 * created by : Heidari
 */
public class ModelGroup implements Serializable {
    public static final int SATURDAY = 1;
    public static final int SUNDAY = 2;
    public static final int MONDAY= 3;
    public static final int TUESDAY= 4;
    public static final int WEDNESDAY =5;
    public static final int THURSDAY = 6;
    public static final int NULL = 0;
    private String teacherName;
    private int  groupId;
    private int day1;
    private int day2;
    private int day3;
    private int startTime1;
    private int startTime2;
    private int startTime3;
    private int finishTime1;
    private int finishTime2;
    private int finishTime3;

    public ModelGroup(String teacherName, int groupId, int day1, int day2, int day3, int startTime1, int startTime2, int startTime3, int finishTime1, int finishTime2, int finishTime3) {
        this.teacherName = teacherName;
        this.groupId = groupId;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.startTime1 = startTime1;
        this.startTime2 = startTime2;
        this.startTime3 = startTime3;
        this.finishTime1 = finishTime1;
        this.finishTime2 = finishTime2;
        this.finishTime3 = finishTime3;
    }
    public ModelGroup (){
        this.teacherName = "";
        this.groupId = 0;
        this.day1 = NULL;
        this.day2 = NULL;
        this.day3 = NULL;
        this.startTime1 = 0;
        this.startTime2 =0;
        this.startTime3 = 0;
        this.finishTime1 =0;
        this.finishTime2 = 0;
        this.finishTime3 = 0;
    }
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

    public int getDay3() {
        return day3;
    }

    public void setDay3(int day3) {
        this.day3 = day3;
    }

    public int getStartTime1() {
        return startTime1;
    }

    public void setStartTime1(int startTime1) {
        this.startTime1 = startTime1;
    }

    public int getStartTime2() {
        return startTime2;
    }

    public void setStartTime2(int startTime2) {
        this.startTime2 = startTime2;
    }

    public int getStartTime3() {
        return startTime3;
    }

    public void setStartTime3(int startTime3) {
        this.startTime3 = startTime3;
    }

    public int getFinishTime1() {
        return finishTime1;
    }

    public void setFinishTime1(int finishTime1) {
        this.finishTime1 = finishTime1;
    }

    public int getFinishTime2() {
        return finishTime2;
    }

    public void setFinishTime2(int finishTime2) {
        this.finishTime2 = finishTime2;
    }

    public int getFinishTime3() {
        return finishTime3;
    }

    public void setFinishTime3(int finishTime3) {
        this.finishTime3 = finishTime3;
    }
    public String timingsToString(){
        return "test";
    }
}