package hoosmand;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import javaherian.yousef.entekhabvahed.ModelCourse;
import javaherian.yousef.entekhabvahed.ModelGroup;

public class Database extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "info_db";

    private final static String TB_COURSE_NAME = "tb_course";
    private final static String TB_COURSE_KEY_NAME = "name";
    private final static String TB_COURSE_KEY_ID = "id";
    private final static String TB_COURSE_KEY_NUMBER_OF_GROUPS = "number_of_groups";

    private final static String TB_RULE_NAME = "tb_rule";
    private final static String TB_RULE_KEY_DAY = "day";
    private final static String TB_RULE_KEY_TIME = "time";
    private final static String TB_RULE_KEY_RELATION = "relation";
    private final static String TB_RULE_KEY_COURSE = "course";
    private final static String TB_RULE_KEY_TEACHER = "teacher";

    private final static String TB_GROUP_NAME = "tb_group_";
    private final static String TB_GROUP_KEY_TEACHER_NAME = "teacher_name";
    private final static String TB_GROUP_KEY_GROUP_ID = "group_id";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_COURSE_NAME + "' " +
                "('" + TB_COURSE_KEY_NAME + "' TEXT" +
                ", '" + TB_COURSE_KEY_ID + "' NUMERIC PRIMARY KEY NOT NULL" +
                ", '" + TB_COURSE_KEY_NUMBER_OF_GROUPS + "' NUMERIC" +
                ")");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_RULE_NAME + "' " +
                "('" + TB_RULE_KEY_DAY + "' NUMERIC" +
                ", '" + TB_RULE_KEY_TIME + "' NUMERIC" +
                ", '" + TB_RULE_KEY_RELATION + "' NUMERIC" +
                ", '" + TB_RULE_KEY_COURSE + "' TEXT" +
                ", '" + TB_RULE_KEY_TEACHER + "' TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TB_COURSE_NAME + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TB_RULE_NAME + "'");
        onCreate(sqLiteDatabase);
    }

    public void addCourses(ArrayList<ModelCourse> courses){
        ContentValues values;
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0 ; i<courses.size();i++){
            values = new ContentValues();
            values.put(TB_COURSE_KEY_NAME,courses.get(i).getName());
            values.put(TB_COURSE_KEY_ID,courses.get(i).getId());
            values.put(TB_COURSE_KEY_NUMBER_OF_GROUPS,courses.get(i).getNumberOfGroups());
            addGroup(courses.get(i).getGroups(),courses.get(i).getId(),db);
            db.insert(TB_COURSE_NAME,null,values);
        }
        if(db.isOpen()) db.close();
    }

    public void addCourses(ModelCourse course){
        ContentValues values;
        SQLiteDatabase db = getWritableDatabase();

        values = new ContentValues();
        values.put(TB_COURSE_KEY_NAME,course.getName());
        values.put(TB_COURSE_KEY_ID,course.getId());
        values.put(TB_COURSE_KEY_NUMBER_OF_GROUPS,course.getNumberOfGroups());
        addGroup(course.getGroups(),course.getId(),db);
        db.insert(TB_COURSE_NAME,null,values);
        if(db.isOpen()) db.close();
    }

    private void addGroup(ArrayList<ModelGroup> groups,int courseId, SQLiteDatabase db){
        ContentValues values;
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_GROUP_NAME+courseId + "' " +
                "('" + TB_GROUP_KEY_TEACHER_NAME + "' TEXT" +
                ", '" + TB_GROUP_KEY_GROUP_ID + "' NUMERIC" +
                ")");
        for (int i = 0 ; i<groups.size();i++){
            values = new ContentValues();
            values.put(TB_GROUP_KEY_TEACHER_NAME,groups.get(i).getTeacherName());
            values.put(TB_GROUP_KEY_GROUP_ID,groups.get(i).getGroupId());
            db.insert(TB_GROUP_NAME+courseId,null,values);
        }
    }

    public ArrayList<ModelCourse> readCourses(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ModelCourse> courses = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_COURSE_NAME +"'" , null);
        if(cursor.moveToFirst()){
            do{
                ModelCourse model = new ModelCourse();
                model.setName(cursor.getString(cursor.getColumnIndex(TB_COURSE_KEY_NAME)));
                model.setId(cursor.getInt(cursor.getColumnIndex(TB_COURSE_KEY_ID)));
                model.setNumberOfGroups(cursor.getInt(cursor.getColumnIndex(TB_COURSE_KEY_NUMBER_OF_GROUPS)));
                model.setGroups(readGroups(model.getId(),db));
                courses.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        if(db.isOpen()) db.close();
        return courses;
    }

    private ArrayList<ModelGroup> readGroups(int courseId,SQLiteDatabase db){
        ArrayList<ModelGroup> models = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM '"+TB_GROUP_NAME+courseId +"'" , null);
        if(cursor.moveToFirst()){
            do{
                ModelGroup model = new ModelGroup();
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(TB_GROUP_KEY_GROUP_ID)));
                model.setTeacherName(cursor.getString(cursor.getColumnIndex(TB_GROUP_KEY_TEACHER_NAME)));
                models.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return models;
    }

}