package postgres;

public class PostgresFactory {
	
	private PostgresNivell postgresNivell = null;
	
	private PostgresUsuariRegistrat postgresUsuariRegistrat = null;
	
	private PostgresPartida postgresPartida = null;
	
	private static PostgresFactory instance = null;
	
	private PostgresFactory(){}
	
	public static PostgresFactory getInstance() {
		if(instance == null) instance = new PostgresFactory();
		return instance;
	}
	
	public PostgresNivell getPostgresNivell() {
		if(postgresNivell == null) postgresNivell = new PostgresNivell();
		return postgresNivell;
	}
	
	public PostgresUsuariRegistrat getPostgresUsuariRegistrat() {
		if(postgresUsuariRegistrat == null) postgresUsuariRegistrat = new PostgresUsuariRegistrat();
		return postgresUsuariRegistrat;
	}
	
	public PostgresPartida getPostgresPartida() {
		if(postgresPartida == null) postgresPartida = new PostgresPartida();
		return postgresPartida;
	}
}
