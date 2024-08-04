package exceptions;

import domain.ErrorType;

public class CocktailsSdkException  extends Exception {

    private ErrorType type;

    public CocktailsSdkException(ErrorType type, String message){
        super(message);
        this.type = type;
    }

    public ErrorType getErrorType(){
        return  type;
    }


}
