package anna.poddubnaya.presentation.constants;


public class Constants {

    public final String USER_ID = "USER_ID";
    public final String USER_NAME = "USER_NAME";
    public final String USER_AGE = "USER_AGE";
    public final String USER_URL = "USER_URL";

    public final String USER_EDIT = "USER_EDIT";


    private static Constants instance;

    public static synchronized Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

}
