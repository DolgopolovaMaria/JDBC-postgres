package tables;

import db.PostGreSqlDBExecutor;
import dob.AObject;
import dob.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentTable extends ATable {

  public StudentTable(){
    super("student", new PostGreSqlDBExecutor(new ArrayList<>(){{
      add("id");
      add("fio");
      add("sex");
      add("id_group");
    }}));
  }

  @Override
  public List list() {
    String listStudentsQuery = String.format("select * from %s", getTableName());

    return selectQuery(listStudentsQuery);
  }

  public List getStudentsBySex(String sex) {
    String listStudentsQuery = String.format("select * from %s where sex='%s'", getTableName(), sex);

    return selectQuery(listStudentsQuery);
  }

  public List getStudentsByGroup(String groupName) {
    String listStudentsQuery = String.format("select * from %s where id_group=(select id from group_students where name='%s')", getTableName(), groupName);

    return selectQuery(listStudentsQuery);
  }

  public int getCountValues() {
    String listStudentsQuery = String.format("select count(*) from %s", getTableName());

    List<Student> students = new ArrayList<>();

    String result = getIdbExecutor().executeWithSingleResult(listStudentsQuery);

    int count = Integer.parseInt(result);

    return count;
  }

  @Override
  public void createTable(){
    String query = String.format("create table %s (id serial primary key, fio text, sex text, id_group integer references group_students(id))", getTableName());

    getIdbExecutor().execute(query);
  }

  @Override
  public void insertValue(AObject obj){
    Student student = (Student) obj;
    String query = String.format("insert into %s (fio, sex, id_group) values ('%s', '%s', %s)", getTableName(), student.getFio(), student.getSex(), student.getIdGroup());

    getIdbExecutor().execute(query);
  }

  private List selectQuery(String query){
    List<Student> students = new ArrayList<>();

    List<Map<String, String>> result = getIdbExecutor().executeWithResult(query);

    for(Map<String, String> res: result) {
      students.add(
              new Student(
                      Integer.parseInt(res.get("id")),
                      res.get("fio"),
                      res.get("sex"),
                      Integer.parseInt(res.get("id_group"))
              )
      );
    }
    return students;
  }
}
