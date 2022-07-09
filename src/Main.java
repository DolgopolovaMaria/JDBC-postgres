

import db.DatabaseOperations;
import dob.AObject;
import dob.Student;
import tables.ATable;
import tables.StudentTable;

import java.util.List;

public class Main {

  public static void main(String... args){
    DatabaseOperations databaseOperations = new DatabaseOperations();
    databaseOperations.createTables();
    databaseOperations.fillTables();
    databaseOperations.printStudents();
    databaseOperations.printCountStudents();
  }

}
