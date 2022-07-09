package tables;

import db.IDBExecutor;
import dob.AObject;
import dob.Student;

import java.util.List;

public abstract class ATable {

  private String tableName;

  private IDBExecutor idbExecutor;

  public ATable(String tableName, IDBExecutor idbExecutor){
    this.idbExecutor = idbExecutor;
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public IDBExecutor getIdbExecutor() {
    return idbExecutor;
  }

  public abstract List list();

  public abstract void createTable();

  public abstract void insertValue(AObject obj);

  public AObject getById(int id){
    List<AObject> allObjects = list();
    AObject obj = null;
    for (AObject o: allObjects){
      if (o.getId() == id){
        obj = o;
        break;
      }
    }
    return obj;
  }

  public void dropTable(){
    String query = String.format("DROP TABLE IF EXISTS %s", tableName);

    idbExecutor.execute(query);
  }

}
