package heidari.mohammad.entekhabvahed;

import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelGroup;
import javaherian.yousef.entekhabvahed.ModelSchedule;

public class ModelScheduleVersion1 {
    public ArrayMap<Integer,ArrayList<ModelCourseVersion1>> schedule;
    public int id;
    public int score;
    public ModelScheduleVersion1(ModelSchedule modelSchedule) {
        id=modelSchedule.getUniqueId();
        score=modelSchedule.getTotalScore();
        schedule = new ArrayMap<>();


        schedule.put(ModelGroup.SATURDAY,new ArrayList<ModelCourseVersion1>());
        schedule.put(ModelGroup.SUNDAY,new ArrayList<ModelCourseVersion1>());
        schedule.put(ModelGroup.MONDAY,new ArrayList<ModelCourseVersion1>());
        schedule.put(ModelGroup.TUESDAY,new ArrayList<ModelCourseVersion1>());
        schedule.put(ModelGroup.WEDNESDAY,new ArrayList<ModelCourseVersion1>());
        schedule.put(ModelGroup.THURSDAY,new ArrayList<ModelCourseVersion1>());

        for ( Map.Entry<ModelCourse, ModelGroup> itr : modelSchedule.getMap().entrySet()){
            ModelGroup group=itr.getValue();
            String teacherName = group.getTeacherName();
            String courseName = itr.getKey().getName();
            if (group.getDay1() != ModelGroup.NULL){
                schedule.get(group.getDay1()).add(new ModelCourseVersion1(courseName,teacherName,
                        getTiming(group.getStartTime1(),group.getFinishTime1()),group.getStartTime1()));
            }
            if (group.getDay2() != ModelGroup.NULL){
                schedule.get(group.getDay2()).add(new ModelCourseVersion1(courseName,teacherName,
                        getTiming(group.getStartTime2(),group.getFinishTime2()),group.getStartTime2()));
            }
            if (group.getDay3() != ModelGroup.NULL){
                schedule.get(group.getDay3()).add(new ModelCourseVersion1(courseName,teacherName,
                        getTiming(group.getStartTime3(),group.getFinishTime3()),group.getStartTime3()));
            }
        }
        for (ArrayList<ModelCourseVersion1> row :schedule.values()){
            Collections.sort(row,new comp());
        }
    }
    private class comp implements Comparator<ModelCourseVersion1> {

        @Override
        public int compare(ModelCourseVersion1 modelCourseVersion1, ModelCourseVersion1 t1) {
            if ( modelCourseVersion1.getStartTime() > t1.getStartTime())
                return 1;
            else if ( modelCourseVersion1.getStartTime() == t1.getStartTime())
                return 0;
            else
                return -1;
        }
    }
    private String getTiming(int st,int ft ) {
        return "" + st/60 + ":" + st%60 +" ~ " +  ft/60 + ":" + ft%60;
    }
}
