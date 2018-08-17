package javaherian.yousef.entekhabvahed;

/**
 * this GroupsClass related to any Course like electronics
 * created by : Heidari
 */
public class ModelGroup {

    private String teacherName;
    private int  groupId;

    public ModelGroup(String teacherName, int groupId) {
        this.teacherName = teacherName;
        this.groupId = groupId;
    }

    public ModelGroup (){
        groupId=0;
        teacherName="";
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

}