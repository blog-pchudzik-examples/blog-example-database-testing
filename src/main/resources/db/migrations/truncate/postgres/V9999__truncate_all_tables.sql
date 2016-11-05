CREATE OR REPLACE FUNCTION truncate_tables() RETURNS void AS $$
DECLARE
	statements CURSOR FOR
		SELECT tablename FROM pg_tables
		WHERE tableowner = 'postgres' AND schemaname = 'public' and tablename != 'schema_version';
BEGIN
	FOR stmt IN statements LOOP
		EXECUTE 'TRUNCATE TABLE ' || quote_ident(stmt.tablename) || ' CASCADE;';
	END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT truncate_tables();
