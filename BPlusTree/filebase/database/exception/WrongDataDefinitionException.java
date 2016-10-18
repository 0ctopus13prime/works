package fakeecommerce.database.exception;

/**
 * Created by buzz on 2016. 1. 2..
 */
public class WrongDataDefinitionException extends RuntimeException {
	public WrongDataDefinitionException() {
		super("Wrong data definition. Do you have at least one @Key? or may be column order mixed.");
	}
}
