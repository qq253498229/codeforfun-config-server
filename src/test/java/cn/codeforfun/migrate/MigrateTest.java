package cn.codeforfun.migrate;

import cn.codeforfun.migrate.core.Migrate;
import cn.codeforfun.migrate.core.diff.DiffResult;
import cn.codeforfun.migrate.core.entity.DatabaseInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class MigrateTest {
    @Test
//    @Disabled
    void test() throws SQLException {
        DatabaseInfo from = new DatabaseInfo("localhost", 3306, "root", "root", "application_configuration");
        DatabaseInfo to = new DatabaseInfo("localhost", 3306, "root", "root", "test");
        Migrate migrate = new Migrate(from, to);
        DiffResult diff = migrate.diff();
        List<String> sqlList = diff.getSqlList();
        sqlList.forEach(System.out::println);
    }
}
