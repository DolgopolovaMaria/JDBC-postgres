package tables;

import db.PostGreSqlDBExecutor;
import dob.AObject;
import dob.Curator;
import dob.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CuratorTable extends ATable {
    public CuratorTable(){
        super("curator", new PostGreSqlDBExecutor(new ArrayList<>(){{
            add("id");
            add("fio");
        }}));
    }

    @Override
    public List list() {
        String listQuery = String.format("select * from %s", getTableName());

        List<Curator> curators = new ArrayList<>();

        List<Map<String, String>> result = getIdbExecutor().executeWithResult(listQuery);

        for(Map<String, String> res: result) {
            curators.add(
                    new Curator(
                            Integer.parseInt(res.get("id")),
                            res.get("fio")
                    )
            );
        }
        return curators;
    }

    @Override
    public void createTable(){
        String query = String.format("create table %s (id serial primary key, fio text)", getTableName());

        getIdbExecutor().execute(query);
    }

    @Override
    public void insertValue(AObject obj){
        Curator curator = (Curator) obj;
        String query = String.format("insert into %s (fio) values ('%s')", getTableName(), curator.getFio());

        getIdbExecutor().execute(query);
    }
}
