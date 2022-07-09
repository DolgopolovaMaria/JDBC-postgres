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
        studentTable.insertValue(new Student("Семенов Семен Семенович", "m", 1));
        studentTable.insertValue(new Student("Петров Петр Петрович", "m", 1));
        studentTable.insertValue(new Student("Алексеев Алексей Алексеевич", "m", 1));
        studentTable.insertValue(new Student("Павлов Павел Павлович", "m", 1));
        studentTable.insertValue(new Student("Иванова Светлана Дмитриевна", "w", 2));
        studentTable.insertValue(new Student("Котова Татьяна Дмитриевна", "w", 2));
        studentTable.insertValue(new Student("Кирдяева Виктория Владимировна", "w", 2));
        studentTable.insertValue(new Student("Курьез Виктория Александровна", "w", 2));
        studentTable.insertValue(new Student("Дитковскя Анна Сергеевна", "w", 2));
        studentTable.insertValue(new Student("Агеева Ада", "w", 3));
        studentTable.insertValue(new Student("Николай", "m", 3));
        studentTable.insertValue(new Student("Гунова Анна", "w", 3));
        studentTable.insertValue(new Student("Иванова Любовь", "w", 3));
        studentTable.insertValue(new Student("Долгополова Татьяна", "w", 3));
    }

    public void printAllValues(){
        System.out.println("All values in all tables:");
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
        printListStudentsWithAllInformation(students);
        System.out.println();
    }

    public void printCountStudents(){
        System.out.println("Students count: " + studentTable.getCountValues());
        System.out.println();
    }

    public void printGirlsStudents(){
        System.out.println("Girls:");
        List<Student> students = studentTable.getStudentsBySex("w");
        printListStudentsWithAllInformation(students);
        System.out.println();
    }

    public void printGroupStudents(String group){
        System.out.printf("Students from %s group (using nested queries):", group);
        System.out.println();
        List<Student> students = studentTable.getStudentsByGroup(group);
        printListStudentsWithAllInformation(students);
        System.out.println();
    }

    public void printGroupWithCurators(){
        System.out.println("Groups with curators:");
        List<Group> groups = groupTable.list();
        for (Group g: groups){
            int curatorId = g.getId_curator();
            Curator curator = (Curator) curatorTable.getById(curatorId);
            String curatorName = curator.getFio();
            System.out.printf("Group = %s, curator = %s", g.getName(), curatorName);
            System.out.println();
        }
        System.out.println();
    }

    public void updateCurator(int idGroup, int idCurator){
        System.out.printf("Update group %s (set curator = %s)", idGroup, idCurator);
        System.out.println();
        groupTable.updateCurator(idGroup, idCurator);
        System.out.println("New groups values:");
        printGroupWithCurators();
    }

    private void printListStudentsWithAllInformation(List<Student> students){
        for (Student s: students){
            int groupId = s.getIdGroup();
            Group group = (Group) groupTable.getById(groupId);
            String groupName = group.getName();
            Curator curator = (Curator) curatorTable.getById(group.getId_curator());
            String curatorName = curator.getFio();
            System.out.printf("FIO = %s, sex = %s, group = %s, curator = %s", s.getFio(), s.getSex(), groupName, curatorName);
            System.out.println();
        }
    }
}
