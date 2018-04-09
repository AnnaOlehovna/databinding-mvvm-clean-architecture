package anna.poddubnaya.presentation.screens.userMVP;

public interface BaseView {

    void showProgress();

    void dismissProgress();

    void showError(Exception e);

}
