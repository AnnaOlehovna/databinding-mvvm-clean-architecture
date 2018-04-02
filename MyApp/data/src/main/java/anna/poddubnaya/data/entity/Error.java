package anna.poddubnaya.data.entity;

public class Error extends Exception{

   private ErrorType myError;


    public Error(ErrorType myError) {
        this.myError = myError;
    }

    public ErrorType getMyError() {
        return myError;
    }
}
