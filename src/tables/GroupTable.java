package tables;

import db.PostGreSqlDBExecutor;
import dob.AObject;
import dob.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupTable extends ATable {
    public GroupTable(){
        super("group_students", new PostGreSqlDBExecutor(new ArrayList<>(){{
            add("id");
            add("name");
            add("id_curator");
        }}));
    }

    @Override
    public List list() {
        String listQuery = String.format("select * from %s", getTableName());

        List<Group> groups = new ArrayList<>();

        List<Map<String, String>> result = getIdbExecutor().executeWithResult(listQuery);

        for(Map<String, String> res: result) {
            groups.add(
                    new Group(
                            Integer.parseInt(res.get("id")),
                            res.get("name"),
                            Integer.parseInt(res.get("id_curator"))
                    )
            );
        }
        return groups;
    }

    @Override
    public void createTable(){
        String query = String.format("create table %s(id serial primary key, name text, id_curator integer references curator(id))", getTableName());

        getIdbExecutor().execute(query);
    }

    @Override
    public void insertValue(AObject obj){
        Group group = (Group) obj;
        String query = String.format("insert into %s (name, id_curator) values ('%s', %s)", getTableName(), group.getName(), group.getId_curator());

        getIdbExecutor().execute(query);
    }

    public void updateCurator(int idGroup, int idCurator){
        String query = String.format("update %s set id_curator=%s where id=%s", getTableName(), idCurator, idGroup);

        getIdbExecutor().execute(query);
    }
}
