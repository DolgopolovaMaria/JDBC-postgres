package tables;

import db.IDBExecutor;
import db.PostGreSqlDBExecutor;
import dob.AObject;
import dob.Group;
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

    List<Student> students = new ArrayList<>();

    List<Map<String, String>> result = getIdbExecutor().executeWithResult(listStudentsQuery);

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
}
