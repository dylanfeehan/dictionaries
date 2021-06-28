package dictionaries;
public class FullDictionaryException extends RuntimeException{
	public FullDictionaryException(){
		this("");
	}
	public FullDictionaryException(String e){
		super(e);
	}
}