package anna.poddubnaya.data.entity;

public class MyError extends Exception{

   private ErrorType myError;


    public MyError(ErrorType myError) {
        this.myError = myError;
    }

    public ErrorType getMyError() {
        return myError;
    }
}
