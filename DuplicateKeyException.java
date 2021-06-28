package dictionaries;
public class DuplicateKeyException extends RuntimeException{
	public DuplicateKeyException(){
		this("");
	}
	public DuplicateKeyException(String e){
		super(e);
	}
}