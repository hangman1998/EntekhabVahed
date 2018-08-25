package hoosmand;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelGroup;
import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.ModelSchedule;

public class DatabaseModified extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "info_db";

    private final static String TB_COURSE_NAME = "tb_course";
    private final static String TB_COURSE_KEY_NAME = "name";
    private final static String TB_COURSE_KEY_ID = "id";

    private final static String TB_RULE_NAME = "tb_rule";
    private final static String TB_RULE_KEY_DAY = "day";
    private final static String TB_RULE_KEY_TIME = "time";
    private final static String TB_RULE_KEY_RELATION = "relation";
    private final static String TB_RULE_KEY_COURSE = "course";
    private final static String TB_RULE_KEY_TEACHER = "teacher";
    private final static String TB_RULE_KEY_SCORE = "score";

    private final static String TB_GROUP_NAME = "tb_group_";
    private final static String TB_GROUP_KEY_TEACHER_NAME = "teacher_name";
    private final static String TB_GROUP_KEY_GROUP_ID = "group_id";
    private final static String TB_GROUP_KEY_DAY_1 = "day_1";
    private final static String TB_GROUP_KEY_DAY_2 = "day_2";
    private final static String TB_GROUP_KEY_DAY_3 = "day_3";
    private final static String TB_GROUP_KEY_START_TIME_1 = "start_time_1";
    private final static String TB_GROUP_KEY_START_TIME_2 = "start_time_2";
    private final static String TB_GROUP_KEY_START_TIME_3 = "start_time_3";
    private final static String TB_GROUP_KEY_FINISH_TIME_1 = "finish_time_1";
    private final static String TB_GROUP_KEY_FINISH_TIME_2 ="finish_time_2";
    private final static String TB_GROUP_KEY_FINISH_TIME_3 = "finish_time_3";

    private final static String TB_SCHEDULE_NAME = "tb_schedule_";
    private final static String TB_SCHEDULE_KEY_UNIQUE_ID = "unique_id_";
    private final static String TB_SCHEDULE_KEY_NO_COURSES = "NO.courses";
    private final static String TB_SCHEDULE_KEY_NO_UNIT = "NO.unit";

    public DatabaseModified(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_COURSE_NAME + "' " +
                "('" + TB_COURSE_KEY_NAME + "' TEXT" +
                ", '" + TB_COURSE_KEY_ID + "' NUMERIC PRIMARY KEY NOT NULL" +
                ")");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_RULE_NAME + "' " +
                "('" + TB_RULE_KEY_DAY + "' NUMERIC" +
                ", '" + TB_RULE_KEY_TIME + "' NUMERIC" +
                ", '" + TB_RULE_KEY_RELATION + "' NUMERIC" +
                ", '" + TB_RULE_KEY_COURSE + "' TEXT" +
                ", '" + TB_RULE_KEY_TEACHER + "' TEXT" +
                ", '" + TB_RULE_KEY_SCORE + "' NUMERIC" +
                ")");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_SCHEDULE_NAME+"main" + "' " +
                "('" + TB_SCHEDULE_KEY_UNIQUE_ID + "' NUMERIC PRIMARY KEY NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("1234","Upgrading Database");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TB_COURSE_NAME + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TB_RULE_NAME + "'");
        onCreate(sqLiteDatabase);
    }

    public long addGroup(ModelGroup group, int courseId){
        return addGroup(courseId,group.getTeacherName(),group.getGroupId()
                ,group.getDay1(),group.getDay2(),group.getDay3(),group.getStartTime1(),
                group.getStartTime2(),group.getStartTime3(),group.getFinishTime1(),
                group.getFinishTime2(),group.getFinishTime3());
    }

    public long addGroup(int courseId,String teacherName, int groupId
            , int day1, int day2, int day3, int startTime1, int startTime2,
                         int startTime3, int finishTime1, int finishTime2, int finishTime3){
        Log.i("hooshmand.Database","start add a group. groupId = " + groupId + " , teacherName = " + teacherName + " , courseId = "+courseId);
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_GROUP_NAME+courseId + "' " +
                "('" + TB_GROUP_KEY_TEACHER_NAME + "' TEXT" +
                ", '" + TB_GROUP_KEY_GROUP_ID + "' NUMERIC PRIMARY KEY NOT NULL"
                        +
                        ", '" + TB_GROUP_KEY_DAY_1 + "' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_DAY_2 + "' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_DAY_3 + "' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_START_TIME_1 + "' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_START_TIME_2 + "' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_START_TIME_3 + "' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_FINISH_TIME_1 +"' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_FINISH_TIME_2 +"' NUMERIC NOT NULL DEFAULT '0'"
                        +
                        ", '" + TB_GROUP_KEY_FINISH_TIME_3 + "' NUMERIC NOT NULL DEFAULT '0'" +
                ")");
        values.put(TB_GROUP_KEY_TEACHER_NAME,teacherName);
        values.put(TB_GROUP_KEY_GROUP_ID,groupId);
        values.put(TB_GROUP_KEY_DAY_1,day1);
        values.put(TB_GROUP_KEY_DAY_2,day2);
        values.put(TB_GROUP_KEY_DAY_3,day3);
        values.put(TB_GROUP_KEY_START_TIME_1,startTime1);
        values.put(TB_GROUP_KEY_START_TIME_2,startTime2);
        values.put(TB_GROUP_KEY_START_TIME_3,startTime3);
        values.put(TB_GROUP_KEY_FINISH_TIME_1,finishTime1);
        values.put(TB_GROUP_KEY_FINISH_TIME_2,finishTime2);
        values.put(TB_GROUP_KEY_FINISH_TIME_3,finishTime3);
        long id =db.insert(TB_GROUP_NAME+courseId,null,values);
        if(db.isOpen()) db.close();
        Log.i("hooshmand.Database","add group finish. id = "+id);
        return id;
    }

    public ArrayList<ModelGroup> readGroups(int courseId){
        Log.i("hooshmand.Database","start read groups. courseId = "+courseId);
        SQLiteDatabase db1 = getWritableDatabase();
        db1.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_GROUP_NAME+courseId + "' " +
                "('" + TB_GROUP_KEY_TEACHER_NAME + "' TEXT" +
                ", '" + TB_GROUP_KEY_GROUP_ID + "' NUMERIC PRIMARY KEY NOT NULL"
                +
                ", '" + TB_GROUP_KEY_DAY_1 + "' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_DAY_2 + "' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_DAY_3 + "' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_START_TIME_1 + "' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_START_TIME_2 + "' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_START_TIME_3 + "' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_FINISH_TIME_1 +"' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_FINISH_TIME_2 +"' NUMERIC NOT NULL DEFAULT '0'"
                +
                ", '" + TB_GROUP_KEY_FINISH_TIME_3 + "' NUMERIC NOT NULL DEFAULT '0'" +
                ")");
        if (db1.isOpen())db1.close();
        ArrayList<ModelGroup> groups = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_GROUP_NAME+courseId+"'",null);
        if(cursor.moveToFirst()){
            do{
                ModelGroup model = new ModelGroup();
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_GROUP_ID)));
                model.setTeacherName(cursor.getString(cursor.getColumnIndex(TB_GROUP_KEY_TEACHER_NAME)));

                model.setDay1(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_DAY_1)));
                model.setDay2(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_DAY_2)));
                model.setDay3(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_DAY_3)));

                model.setStartTime1(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_START_TIME_1)));
                model.setStartTime2(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_START_TIME_2)));
                model.setStartTime3(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_START_TIME_3)));

                model.setFinishTime1(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_FINISH_TIME_1)));
                model.setFinishTime2(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_FINISH_TIME_2)));
                model.setFinishTime3(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_FINISH_TIME_3)));

                groups.add(model);
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","group readied and return " + groups.size() + " objects");
        if(db.isOpen()) db.close();
        return groups;
    }

    public ModelGroup readGroup(int courseId,int groupId){
        Log.i("hooshmand.Database","start read a group. groupId = " + groupId + " , courseId = "+courseId);
        ModelGroup group = new ModelGroup();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_GROUP_NAME+courseId+"' WHERE "+TB_GROUP_KEY_GROUP_ID+" = " +groupId,null);
        if(cursor.moveToFirst()){
            do{
                group.setGroupId(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_GROUP_ID)));
                group.setTeacherName(cursor.getString(cursor.getColumnIndex(TB_GROUP_KEY_TEACHER_NAME)));

                group.setDay1(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_DAY_1)));
                group.setDay2(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_DAY_2)));
                group.setDay3(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_DAY_3)));

                group.setStartTime1(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_START_TIME_1)));
                group.setStartTime2(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_START_TIME_2)));
                group.setStartTime3(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_START_TIME_3)));

                group.setFinishTime1(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_FINISH_TIME_1)));
                group.setFinishTime2(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_FINISH_TIME_2)));
                group.setFinishTime3(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_FINISH_TIME_3)));
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","group readied and return " + group.toString() + " objects");
        if(db.isOpen()) db.close();
        return group;
    }

    public int deleteAllGroups(int courseId){
        Log.i("hooshmand.Database","start delete all groups. courseId = "+courseId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_GROUP_NAME+courseId,null,null);
        Log.i("hooshmand.Database","All group deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public int deleteGroup(int courseId,int groupId){
        Log.i("hooshmand.Database","start delete a group. courseId = "+courseId+ " , groupId = "+groupId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_GROUP_NAME+courseId,TB_GROUP_KEY_GROUP_ID + " = "+ groupId,null);
        Log.i("hooshmand.Database","group deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public int editGroup(int courseId,String teacherName, int groupId
            , int day1, int day2, int day3, int startTime1, int startTime2,
                         int startTime3, int finishTime1, int finishTime2, int finishTime3){
        Log.i("hooshmand.Database","start edit a group. courseId = "+courseId+ " , groupId = "+groupId + " , new teacherName = "+teacherName);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_GROUP_KEY_TEACHER_NAME,teacherName);
        values.put(TB_GROUP_KEY_GROUP_ID,groupId);
        values.put(TB_GROUP_KEY_DAY_1,day1);
        values.put(TB_GROUP_KEY_DAY_2,day2);
        values.put(TB_GROUP_KEY_DAY_3,day3);
        values.put(TB_GROUP_KEY_START_TIME_1,startTime1);
        values.put(TB_GROUP_KEY_START_TIME_2,startTime2);
        values.put(TB_GROUP_KEY_START_TIME_3,startTime3);
        values.put(TB_GROUP_KEY_FINISH_TIME_1,finishTime1);
        values.put(TB_GROUP_KEY_FINISH_TIME_2,finishTime2);
        values.put(TB_GROUP_KEY_FINISH_TIME_3,finishTime3);
        int count = db.update(TB_GROUP_NAME+courseId,values,TB_GROUP_KEY_GROUP_ID + " = " + groupId,null);
        Log.i("hooshmand.Database","group edited. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public int editGroup(ModelGroup editedGroup,int courseId){
        return editGroup(courseId,editedGroup.getTeacherName(),editedGroup.getGroupId()
                ,editedGroup.getDay1(),editedGroup.getDay2(),editedGroup.getDay3(),editedGroup.getStartTime1(),
                editedGroup.getStartTime2(),editedGroup.getStartTime3(),editedGroup.getFinishTime1(),
                editedGroup.getFinishTime2(),editedGroup.getFinishTime3());
    }

    public long addCourse(ModelCourse course){
        return addCourse(course.getId(),course.getName(),course.getGroups());
    }

    public long addCourse(int courseId,String courseName,ArrayList<ModelGroup> groups){
        Log.i("hooshmand.Database","start add a course. courseId = "+courseId + " , courseName = "+courseName);
        if(groups==null){
            Log.i("hooshmand.Database", "groups = null");
        }else{
            Log.i("hooshmand.Database", "groups.size() = "+groups.size());
        }
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(TB_COURSE_KEY_ID,courseId);
        values.put(TB_COURSE_KEY_NAME,courseName);
        long id =db.insert(TB_COURSE_NAME,null,values);
        if(db.isOpen()) db.close();
        if(groups!=null){
            for (int i = 0 ; i<groups.size();i++){
                addGroup(groups.get(i),courseId);
            }
        }
        Log.i("hooshmand.Database","add course finish. id = "+id);
        return id;
    }

    public ArrayList<ModelCourse> readCourses(){
        Log.i("hooshmand.Database","start read courses.");
        ArrayList<ModelCourse> courses = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_COURSE_NAME+"'",null);
        if(cursor.moveToFirst()){
            do{
                ModelCourse model = new ModelCourse();
                model.setId(cursor.getInt(cursor.getColumnIndex(TB_COURSE_KEY_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(TB_COURSE_KEY_NAME)));
                model.setGroups(readGroups(model.getId()));
                courses.add(model);
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","courses readied and return " + courses.size() + " objects");
        if(db.isOpen()) db.close();
        return courses;
    }

    public int deleteCourse(int courseId){
        Log.i("hooshmand.Database","start delete a course. courseId = "+courseId);
        deleteAllGroups(courseId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_COURSE_NAME,TB_COURSE_KEY_ID + " = "+ courseId,null);
        Log.i("hooshmand.Database","course deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public int editCourse(int courseId , String newCourseName,ArrayList<ModelGroup> newGroups){
        Log.i("hooshmand.Database","start edit a course. courseId = "+courseId+ " , new courseName = "+newCourseName);
        if(newGroups==null){
            Log.i("hooshmand.Database", "newGroups = null");
        }else{
            Log.i("hooshmand.Database", "newGroups.size() = "+newGroups.size());
        }
        if (newGroups!=null){
            for (int i = 0 ; i<newGroups.size();i++){
                editGroup(newGroups.get(i),courseId);
            }
        }
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_COURSE_KEY_ID,courseId);
        values.put(TB_COURSE_KEY_NAME,newCourseName);
        int count = db.update(TB_COURSE_NAME,values,TB_COURSE_KEY_ID + " = " + courseId,null);
        Log.i("hooshmand.Database","course edited. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public int editCourse(ModelCourse editedCourse){
        return editCourse(editedCourse.getId(),editedCourse.getName(),editedCourse.getGroups());
    }

    public long addRule(int day,int time,int relation,String course,String teacher,int score){
        Log.i("hooshmand.Database","start add a rule. day = "+day + " , time = "+time+" , relation = "+relation+" , course = "+course+" , teacher = "+teacher+" , score = "+score);
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(TB_RULE_KEY_DAY,day);
        values.put(TB_RULE_KEY_TIME,time);
        values.put(TB_RULE_KEY_RELATION,relation);
        values.put(TB_RULE_KEY_COURSE,course);
        values.put(TB_RULE_KEY_TEACHER,teacher);
        values.put(TB_RULE_KEY_SCORE,score);
        long id =db.insert(TB_RULE_NAME,null,values);
        if(db.isOpen()) db.close();
        Log.i("hooshmand.Database","add rule finish. id = "+id);
        return id;
    }

    public long addRule(ModelRule rule){
        return addRule(rule.getDay(),rule.getTime(),rule.getRelation(),rule.getCourse(),rule.getTeacher(),rule.getScore());
    }

    public ArrayList<ModelRule> readRule(){
        Log.i("hooshmand.Database","start read rules.");
        ArrayList<ModelRule> rules = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_RULE_NAME+"'",null);
        if(cursor.moveToFirst()){
            do{
                ModelRule model = new ModelRule();
                model.setDay(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_DAY)));
                model.setTime(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_TIME)));
                model.setRelation(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_RELATION)));
                model.setCourse(cursor.getString(cursor.getColumnIndex(TB_RULE_KEY_COURSE)));
                model.setTeacher(cursor.getString(cursor.getColumnIndex(TB_RULE_KEY_TEACHER)));
                model.setScore(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_SCORE)));
                rules.add(model);
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","rules readied and return " + rules.size() + " objects");
        if(db.isOpen()) db.close();
        return rules;
    }

    public int deleteAllRules(){
        Log.i("hooshmand.Database","start delete all rules.");
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_RULE_NAME,null,null);
        Log.i("hooshmand.Database","All rules deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    private void addScheduleMap(int uniqueId, ArrayList<ModelSchedule.MapCourseGroup> mapsCourseGroup){
        Log.i("hooshmand.Database","start add a schedule. uniqueId = " + uniqueId + " , mapsCourseGroup.size() = " + mapsCourseGroup.size());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_SCHEDULE_NAME+uniqueId + "' " +
                "('" + TB_SCHEDULE_KEY_NO_COURSES + "' NUMERIC PRIMARY KEY NOT NULL" +
                ", '" + TB_SCHEDULE_KEY_NO_UNIT + "' NUMERIC PRIMARY KEY NOT NULL" +
                ")");
        for(int i = 0 ; i< mapsCourseGroup.size();i++){
            ContentValues values = new ContentValues();
            values.put(TB_SCHEDULE_KEY_NO_COURSES,mapsCourseGroup.get(i).getNumberOfCourses());
            values.put(TB_SCHEDULE_KEY_NO_UNIT,mapsCourseGroup.get(i).getNumberOfUnit());
            long id =db.insert(TB_SCHEDULE_NAME+uniqueId,null,values);
            Log.i("hooshmand.Database","add schedule["+i+"] finish. id = "+id);
        }
        if(db.isOpen()) db.close();
    }

    private ArrayList<ModelSchedule.MapCourseGroup> readScheduleMap(int uniqueId){
        Log.i("hooshmand.Database","start read scheduleMap. uniqueId = "+uniqueId);
        ArrayList<ModelSchedule.MapCourseGroup> maps = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_SCHEDULE_NAME+uniqueId+"'",null);
        if(cursor.moveToFirst()){
            do{
                ModelSchedule.MapCourseGroup map = new ModelSchedule.MapCourseGroup();
                map.setNumberOfUnit(cursor.getInt(cursor.getColumnIndex(TB_SCHEDULE_KEY_NO_UNIT)));
                map.setNumberOfCourses(cursor.getInt(cursor.getColumnIndex(TB_SCHEDULE_KEY_NO_COURSES)));
                maps.add(map);
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","scheduleMap readied and return " + maps.size() + " objects");
        if(db.isOpen()) db.close();
        return maps;
    }

    private int deleteScheduleMap(int uniqueId){
        Log.i("hooshmand.Database","start delete a scheduleMap. uniqueId = "+uniqueId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_SCHEDULE_NAME+uniqueId,null,null);
        Log.i("hooshmand.Database","scheduleMap deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public long addSchedule(int uniqueId,ArrayList<ModelSchedule.MapCourseGroup> mapsCourseGroup){
        Log.i("hooshmand.Database","start add a schedule. uniqueId = "+ uniqueId + " , mapsCourseGroup.size()= "+mapsCourseGroup.size());
        addScheduleMap(uniqueId,mapsCourseGroup);
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(TB_SCHEDULE_KEY_UNIQUE_ID,uniqueId);
        long id =db.insert(TB_SCHEDULE_NAME+"main",null,values);
        if(db.isOpen()) db.close();
        Log.i("hooshmand.Database","add schedule finish. id = "+id);
        return id;
    }

    public long addSchedule(ModelSchedule schedule){
        return addSchedule(schedule.getUniqueId(),schedule.getSchedule());
    }

    public ArrayList<ModelSchedule> readSchedules(){
        Log.i("hooshmand.Database","start read schedules.");
        ArrayList<ModelSchedule> schedules = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_SCHEDULE_NAME+"main"+"'",null);
        if(cursor.moveToFirst()){
            do{
                ModelSchedule model = new ModelSchedule();
                model.setUniqueId(cursor.getInt(cursor.getColumnIndex(TB_SCHEDULE_KEY_UNIQUE_ID)));
                model.setSchedule(readScheduleMap(model.getUniqueId()));
                schedules.add(model);
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","schedules readied and return " + schedules.size() + " objects");
        if(db.isOpen()) db.close();
        return schedules;
    }

    public ModelSchedule readSchedule(int uniqueId){
        Log.i("hooshmand.Database","start read a schedule. uniqueId = " + uniqueId);
        ModelSchedule schedule = new ModelSchedule();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_SCHEDULE_NAME+"main"+"' WHERE "+TB_SCHEDULE_KEY_UNIQUE_ID+" = " +uniqueId,null);
        if(cursor.moveToFirst()){
            do{
                schedule.setUniqueId(cursor.getInt(cursor.getColumnIndex(TB_SCHEDULE_KEY_UNIQUE_ID)));
                schedule.setSchedule(readScheduleMap(schedule.getUniqueId()));
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","schedule readied and return " + schedule.toString() + " object");
        if(db.isOpen()) db.close();
        return schedule;
    }

    public int deleteSchedule(int uniqueId){
        Log.i("hooshmand.Database","start delete a schedule. courseId = "+uniqueId);
        deleteScheduleMap(uniqueId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_SCHEDULE_NAME+"main",TB_SCHEDULE_KEY_UNIQUE_ID + " = "+ uniqueId,null);
        Log.i("hooshmand.Database","schedule deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public void editSchedule(int uniqueId , ArrayList<ModelSchedule.MapCourseGroup> newMapsCourseGroup){
        Log.i("hooshmand.Database","start edit a schedule. uniqueId = "+uniqueId+ " , newMapsCourseGroup.size() = "+newMapsCourseGroup.size());
        deleteScheduleMap(uniqueId);
        addScheduleMap(uniqueId,newMapsCourseGroup);
        Log.i("hooshmand.Database","schedule edited.");
    }

    public void editSchedule(ModelSchedule schedule){
        editSchedule(schedule.getUniqueId(),schedule.getSchedule());
    }
}