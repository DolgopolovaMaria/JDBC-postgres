package db;

import java.util.List;
import java.util.Map;

public interface IDBExecutor {
    List<Map<String, String>> executeWithResult(String query);

    void execute(String query);

    String executeWithSingleResult(String query);
}
