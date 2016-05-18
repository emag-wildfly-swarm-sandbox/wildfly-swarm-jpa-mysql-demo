package wildflyswarm.jpa_mysql;

import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jpa.mysql.MySQLJPAFraction;

public class MyContainer {

  private static final String MYSQL_OPTIONS = "autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

  public static Container newContainer() throws Exception {
    Container container = new Container();

    container.fraction(new DatasourcesFraction()
        .jdbcDriver("mysql", (d) -> {
          d.driverClassName("com.mysql.cj.jdbc.Driver");
          d.xaDatasourceClass("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
          d.driverModuleName("com.mysql.jdbc.driver");
        })
        .dataSource("MyDS", (ds) -> {
          ds.driverName("mysql");
          ds.connectionUrl("jdbc:mysql://localhost:3306/test?" + MYSQL_OPTIONS);
          ds.userName("root");
          ds.password("password");
        })
    );

    container.fraction(new MySQLJPAFraction()
        .inhibitDefaultDatasource()
        .defaultDatasource("jboss/datasources/MyDS")
    );

    return container;
  }
}
