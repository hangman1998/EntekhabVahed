package hoosmand;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import javaherian.yousef.entekhabvahed.ModelCourse;

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

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TB_COURSE_NAME + "' " +
                "('" + TB_COURSE_KEY_NAME + "' TEXT" +
                ", '" + TB_COURSE_KEY_ID + "' NUMERIC" +
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

    public long insertCourses(ArrayList<ModelCourse> courses){
        ContentValues values;
        SQLiteDatabase db = getWritableDatabase();
        long id = 0;
        for (int i = 0 ; i<courses.size();i++){
            values = new ContentValues();
            values.put(TB_COURSE_KEY_NAME,courses.get(i).getName());
            values.put(TB_COURSE_KEY_ID,courses.get(i).getId());
            values.put(TB_COURSE_KEY_NUMBER_OF_GROUPS,courses.get(i).getNumberOfGroups());
            id = db.insert(TB_COURSE_NAME,null,values);
        }
        return id;
    }

}