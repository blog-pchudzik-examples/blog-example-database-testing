package db.migrations.truncate.h2;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class V9999__truncate_all_tables implements JdbcMigration {
	private static boolean DISABLED = false;
	private static boolean ENABLED = true;
	private static final int TABLE_NAME_COLUMN = 1;

	public static void truncateTables(Connection connection) throws Exception {
		final ResultSet allTables = connection.createStatement().executeQuery("show tables;");

		integrityCheck(connection, DISABLED);

		while(allTables.next()) {
			final String tableName = allTables.getString(TABLE_NAME_COLUMN);
			if (isFlywaySchemaTable(tableName)) {
				truncate(connection, tableName);
			}
		}

		integrityCheck(connection, ENABLED);
	}

	@Override
	public void migrate(Connection connection) throws Exception {
		registerTruncateAllTablesFunction(connection);
		connection.createStatement().execute("call truncate_tables()");
	}

	private void registerTruncateAllTablesFunction(Connection connection) throws SQLException {
		final String truncateFunction = this.getClass().getCanonicalName() + ".truncateTables";

		connection.createStatement()
				.execute(String.format("create alias truncate_tables for \"%s\"", truncateFunction));
	}

	private static void integrityCheck(Connection connection, boolean isEnabled) throws SQLException {
		connection.createStatement().execute("SET REFERENTIAL_INTEGRITY " + isEnabled);
	}

	private static void truncate(Connection connection, String tableName) throws SQLException {
		connection.createStatement().execute("truncate table `" + tableName + "`");
	}

	private static boolean isFlywaySchemaTable(String tableName) {
		return !"schema_version".equals(tableName);
	}
}
