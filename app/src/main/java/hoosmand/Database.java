package hoosmand;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.Map;

import javaherian.yousef.entekhabvahed.Global;
import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelGroup;
import javaherian.yousef.entekhabvahed.ModelRule;
import javaherian.yousef.entekhabvahed.ModelSchedule;

public class Database extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "info_db";

    private final static String TB_COURSE_NAME = "tb_course";
    private final static String TB_COURSE_KEY_NAME = "name";
    private final static String TB_COURSE_KEY_ID = "id";

    private final static String TB_RULE_NAME = "tb_rule";
    private final static String TB_RULE_KEY_NAME = "name";
    private final static String TB_RULE_KEY_DAY = "day";
    private final static String TB_RULE_KEY_START_TIME = "start_time";
    private final static String TB_RULE_KEY_START_TIME_RELATION = "start_time_relation";
    private final static String TB_RULE_KEY_FINISH_TIME = "finish_time";
    private final static String TB_RULE_KEY_FINISH_TIME_RELATION = "finish_time_relation";
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

    private final static String TB_SCHEDULE_NAME = "tb_schedule";
    private final static String TB_SCHEDULE_KEY_UNIQUE_ID = "unique_id";
    private final static String TB_SCHEDULE_KEY_TOTAL_SCORE = "total_score";

    private final static String TB_MAP_NAME = "tb_maps";
    private final static String TB_MAP_KEY_SCHEDULE_ID = "schedule_id";
    private final static String TB_MAP_KEY_COURSE_ID = "course_id";
    private final static String TB_MAP_KEY_GROUP_ID = "group_id";

    private final static String TB_FLAGS_NAME = "tb_flags";
    private final static String TB_FLAGS_KEY_NEED_TO_UPDATE = "need_to_update";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_COURSE_NAME + "' " +
                "('" + TB_COURSE_KEY_NAME + "' TEXT" +
                ", '" + TB_COURSE_KEY_ID + "' NUMERIC PRIMARY KEY NOT NULL" +
                ")");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_RULE_NAME + "' " +
                "( '" + TB_RULE_KEY_NAME + "' TEXT"+
                ", '" + TB_RULE_KEY_DAY + "' NUMERIC" +
                ", '" + TB_RULE_KEY_START_TIME + "' NUMERIC" +
                ", '" + TB_RULE_KEY_START_TIME_RELATION + "' NUMERIC" +
                ", '" + TB_RULE_KEY_FINISH_TIME + "' NUMERIC" +
                ", '" + TB_RULE_KEY_FINISH_TIME_RELATION + "' NUMERIC" +
                ", '" + TB_RULE_KEY_COURSE + "' TEXT" +
                ", '" + TB_RULE_KEY_TEACHER + "' TEXT" +
                ", '" + TB_RULE_KEY_SCORE + "' NUMERIC" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_SCHEDULE_NAME + "' " +
                "( '" + TB_SCHEDULE_KEY_UNIQUE_ID + "' NUMERIC PRIMARY KEY NOT NULL"+
                ", '" + TB_SCHEDULE_KEY_TOTAL_SCORE + "' NUMERIC" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_MAP_NAME + "' " +
                "( '" + TB_MAP_KEY_SCHEDULE_ID + "' NUMERIC"+
                ", '" + TB_MAP_KEY_COURSE_ID + "' NUMERIC"+
                ", '" + TB_MAP_KEY_GROUP_ID + "' NUMERIC" +
                ")");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_FLAGS_NAME + "' " +
                "( '" + TB_FLAGS_KEY_NEED_TO_UPDATE + "' NUMERIC"+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("1234","Upgrading Database");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TB_COURSE_NAME + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TB_RULE_NAME + "'");
        onCreate(sqLiteDatabase);


    }

    public void setNeedToUpdate(int newValue){
        Log.i("hooshmand.Database","set need to update to"+newValue);
        if (newValue == getNeedToUpdate())
            return;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_FLAGS_KEY_NEED_TO_UPDATE,newValue);
        db.delete(TB_FLAGS_NAME,null,null);
        db.insert(TB_FLAGS_NAME,null,values);
        if(db.isOpen()) db.close();
    }
    public int getNeedToUpdate(){

        int value=0;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_FLAGS_NAME+"'",null);
        if( cursor.moveToFirst())
            value=cursor.getInt(cursor.getColumnIndex(TB_FLAGS_KEY_NEED_TO_UPDATE));
        if(db.isOpen()) db.close();
        Log.i("hooshmand.Database","get need to update ,returned "+value);
       return value;
    }
    private long addGroup(ModelGroup group, int courseId){
        return addGroup(courseId,group.getTeacherName(),group.getGroupId()
                ,group.getDay1(),group.getDay2(),group.getDay3(),group.getStartTime1(),
                group.getStartTime2(),group.getStartTime3(),group.getFinishTime1(),
                group.getFinishTime2(),group.getFinishTime3());
    }

    public long addGroup(int courseId,String teacherName, int groupId
            , int day1, int day2, int day3, int startTime1, int startTime2,
                         int startTime3, int finishTime1, int finishTime2, int finishTime3){
        setNeedToUpdate(1);
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

    private int deleteAllGroups(int courseId){
        setNeedToUpdate(1);
        Log.i("hooshmand.Database","start delete all groups. courseId = "+courseId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_GROUP_NAME+courseId,null,null);
        Log.i("hooshmand.Database","All group deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }

    public int deleteGroup(int courseId,int groupId){
        setNeedToUpdate(1);
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
        setNeedToUpdate(1);
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
        setNeedToUpdate(1);
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
        setNeedToUpdate(1);
        Log.i("hooshmand.Database","start delete a course. courseId = "+courseId);
        deleteAllGroups(courseId);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_COURSE_NAME,TB_COURSE_KEY_ID + " = "+ courseId,null);
        Log.i("hooshmand.Database","course deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }
    public int editCourse(int courseId , String newCourseName,ArrayList<ModelGroup> newGroups){
        setNeedToUpdate(1);
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

    public long addRule(String name,int day,int startTime,int startTimeRelation,int finishTime,int finishTimeRelation,String course,String teacher,int score){
        setNeedToUpdate(1);
        Log.i("hooshmand.Database","start add a rule. day = "+day + " , start time = "+startTime+" , start time relation = "+startTimeRelation+"," +
                " finish time = "+finishTime+",finish time relation = "+finishTimeRelation+" , course = "+course+" , teacher = "+teacher+" , score = "+score);
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(TB_RULE_KEY_NAME,name);
        values.put(TB_RULE_KEY_DAY,day);
        values.put(TB_RULE_KEY_START_TIME,startTime);
        values.put(TB_RULE_KEY_START_TIME_RELATION,startTimeRelation);
        values.put(TB_RULE_KEY_FINISH_TIME,finishTime);
        values.put(TB_RULE_KEY_FINISH_TIME_RELATION,finishTimeRelation);
        values.put(TB_RULE_KEY_COURSE,course);
        values.put(TB_RULE_KEY_TEACHER,teacher);
        values.put(TB_RULE_KEY_SCORE,score);
        long id =db.insert(TB_RULE_NAME,null,values);
        if(db.isOpen()) db.close();
        Log.i("hooshmand.Database","add rule finish. id = "+id);
        return id;
    }
    public long addRule(ModelRule rule){
        return addRule(rule.getName(),rule.getDay(),rule.getStartTime(),rule.getStartTimeRelation(),
                rule.getFinishTime(),rule.getFinishTimeRelation(),rule.getCourse(),rule.getTeacher(),rule.getScore());
    }
    public int deleteRule(String name){
        setNeedToUpdate(1);
        Log.i("hooshmand.Database","start delete a course. courseId = "+name);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_RULE_NAME,TB_RULE_KEY_NAME+ " = "+"'" +name+"'",null);
        Log.i("hooshmand.Database","rule deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
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
                model.setStartTime(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_START_TIME)));
                model.setStartTimeRelation(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_START_TIME_RELATION)));
                model.setFinishTime(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_FINISH_TIME)));
                model.setFinishTimeRelation(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_FINISH_TIME_RELATION)));
                model.setCourse(cursor.getString(cursor.getColumnIndex(TB_RULE_KEY_COURSE)));
                model.setTeacher(cursor.getString(cursor.getColumnIndex(TB_RULE_KEY_TEACHER)));
                model.setScore(cursor.getInt(cursor.getColumnIndex(TB_RULE_KEY_SCORE)));
                model.setName(cursor.getString(cursor.getColumnIndex(TB_RULE_KEY_NAME)));
                rules.add(model);
            }while (cursor.moveToNext());
        }
        Log.i("hooshmand.Database","rules readied and return " + rules.size() + " objects");
        if(db.isOpen()) db.close();
        return rules;
    }

    public int deleteAllRules(){
        setNeedToUpdate(1);
        Log.i("hooshmand.Database","start delete all rules.");
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(TB_RULE_NAME,null,null);
        Log.i("hooshmand.Database","All rules deleted. count = " + count + " objects");
        if(db.isOpen()) db.close();
        return count;
    }
    public long addMap(int scheduleId,int courseId,int groupId){
        ContentValues values = new ContentValues();

        SQLiteDatabase db = this.getWritableDatabase();

        values.put("schedule_id",scheduleId);

        values.put("course_id",courseId);

        values.put("group_id",groupId);

        long id =db.insert("tb_maps",null,values);

        if(db.isOpen()) db.close();

        return id;

    }
    public void addSchedule(int uniqueId,int totalScore,ArrayMap<ModelCourse,ModelGroup> map){

        ContentValues values = new ContentValues();

        SQLiteDatabase db = this.getWritableDatabase();

        values.put("unique_id",uniqueId);

        values.put("total_score",totalScore);

        db.insert("tb_schedule",null,values);

        if(db.isOpen()) db.close();

        for (Map.Entry<ModelCourse, ModelGroup> itr : map.entrySet())
            addMap(uniqueId,itr.getKey().getId(),itr.getValue().getGroupId());
    }

    public ArrayMap<ModelCourse,ModelGroup> readMaps(int scheduleId){

        ArrayMap<ModelCourse,ModelGroup> maps = new ArrayMap<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_MAP_NAME+"' WHERE "+TB_MAP_KEY_SCHEDULE_ID+" = " +scheduleId,null);
        if(cursor.moveToFirst()){

            do{
               int  groupId = cursor.getInt(cursor.getColumnIndex(TB_MAP_KEY_GROUP_ID));
               int  courseId = cursor.getInt(cursor.getColumnIndex(TB_MAP_KEY_COURSE_ID));

               ModelGroup group = readGroup(courseId,groupId);
               ModelCourse course = readCourse(courseId);
               maps.put(course,group);

            }while (cursor.moveToNext());

        }
        if(db.isOpen()) db.close();
        return maps;
    }

    public ModelCourse readCourse(int courseId){

        ModelCourse course = new ModelCourse();

        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_COURSE_NAME+"' WHERE '" + TB_COURSE_KEY_ID +"' = " + courseId,null);

        if(cursor.moveToFirst()){

            do{

                course.setId(cursor.getInt(cursor.getColumnIndex(TB_COURSE_KEY_ID)));

                course.setName(cursor.getString(cursor.getColumnIndex(TB_COURSE_KEY_NAME)));

                course.setGroups(readGroups(course.getId()));

            }while (cursor.moveToNext());

        }

        if(db.isOpen()) db.close();

        return course;

    }

    public ArrayList<ModelSchedule> readAllSchedule(){
        ArrayList<ModelSchedule> schedules = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM 'tb_schedule'",null);
        if(cursor.moveToFirst()){

            do{
                ModelSchedule model = new ModelSchedule();
                model.setUniqueId(cursor.getInt(cursor.getColumnIndex("unique_id")));
                model.setTotalScore(cursor.getInt(cursor.getColumnIndex("total_score")));
                model.setMap(readMaps(model.getUniqueId()));
                schedules.add(model);

            }while (cursor.moveToNext());

        }

        if(db.isOpen()) db.close();

        return schedules;

    }

    public void deleteAllSchedule(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TB_SCHEDULE_NAME,null,null);
        db.delete(TB_MAP_NAME,null,null);
        if(db.isOpen()) db.close();
    }
}