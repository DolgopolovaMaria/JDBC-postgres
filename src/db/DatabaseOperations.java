package db;

import dob.AObject;
import dob.Curator;
import dob.Group;
import dob.Student;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentTable;

import java.util.List;

public class DatabaseOperations {
    private StudentTable studentTable;
    private CuratorTable curatorTable;
    private GroupTable groupTable;

    public DatabaseOperations(){
        studentTable = new StudentTable();
        curatorTable = new CuratorTable();
        groupTable = new GroupTable();
    }

    public void createTables(){
        studentTable.dropTable();
        groupTable.dropTable();
        curatorTable.dropTable();

        curatorTable.createTable();
        groupTable.createTable();
        studentTable.createTable();
    }

    public void fillTables(){
        curatorTable.insertValue(new Curator("Долгополова Мария Андреевна"));
        curatorTable.insertValue(new Curator("Фунт Дина Дмитриевна"));
        curatorTable.insertValue(new Curator("Ярош Дмитрий Сергеевич"));
        curatorTable.insertValue(new Curator("Королихин Владимир Игоревич"));

        groupTable.insertValue(new Group("Group1", 1));
        groupTable.insertValue(new Group("Group2", 2));
        groupTable.insertValue(new Group("Group3", 3));

        studentTable.insertValue(new Student("Иванов Иван Иванович", "m", 1));
        studentTable.insertValue(new Student("Агеева Ада", "w", 2));
    }

    public void printAllValues(){
        List<AObject> students = studentTable.list();
        Student.printList(students);
        System.out.println();

        List<AObject> groups = groupTable.list();
        Group.printList(groups);
        System.out.println();

        List<AObject> curators = curatorTable.list();
        Curator.printList(curators);
        System.out.println();
    }

    public void printStudents(){
        System.out.println("Students with groups and curators:");
        List<Student> students = studentTable.list();
        for (Student s: students){
            int groupId = s.getIdGroup();
            Group group = (Group) groupTable.getById(groupId);
            String groupName = group.getName();
            Curator curator = (Curator) curatorTable.getById(group.getId_curator());
            String curatorName = curator.getFio();
            System.out.printf("Student %s: FIO = %s, sex = %s, group = %s, curator = %s", s.getId(), s.getFio(), s.getSex(), groupName, curatorName);
            System.out.println();
        }
        System.out.println();
    }

    public void printCountStudents(){
        System.out.println("Students count: " + studentTable.getCountValues());
        System.out.println();
    }
}
