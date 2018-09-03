package javaherian.yousef.entekhabvahed;

import java.util.ArrayList;

/**
 * this class is for modeling courses
 * cteated by hasan
 */
public class ModelCourse {
    private String name; //name if class
    private int id;

    private ArrayList<ModelGroup> groups;//a vector that holding all groups

    public ModelCourse(String name,int id, ArrayList<ModelGroup> groups) {
        this.name = name;
        this.id = id;
        this.groups = groups;
    }
    public ModelCourse()
    {
        name = "";
        groups = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ModelGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<ModelGroup> groups) {
        this.groups = groups;
    }
    public void addGroup(ModelGroup group){this.groups.add(group);}
    public ModelGroup findInGroupsById(int groupId){
        for (int i=0;i<groups.size();i++){
            if (groups.get(i).getGroupId() == groupId)
                return groups.get(i);
        }
        return null;
    }
    public void deleteGroup(int position){this.groups.remove(position);}

}