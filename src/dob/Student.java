package dob;

import tables.GroupTable;

import java.util.List;

public class Student extends AObject {
  public Student(int id, String fio, String sex, int idGroup) {
    super(id);
    this.fio = fio;
    this.sex = sex;
    this.idGroup = idGroup;
  }

  public Student(String fio, String sex, int idGroup) {
    super(0);
    this.fio = fio;
    this.sex = sex;
    this.idGroup = idGroup;
  }

  private String fio;
  private String sex;
  private int idGroup;

  public String getFio() {
    return fio;
  }

  public String getSex() {
    return sex;
  }

  public int getIdGroup() {
    return idGroup;
  }

  @Override
  public void print(){
    System.out.printf("Student %s: FIO = %s, sex = %s, group = %s", getId(), getFio(), getSex(), getIdGroup());
    System.out.println();
  }
}
