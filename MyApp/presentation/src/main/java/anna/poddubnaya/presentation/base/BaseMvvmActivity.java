package anna.poddubnaya.presentation.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import anna.poddubnaya.presentation.BR;
import anna.poddubnaya.presentation.notification.NewMessageNotification;


public abstract class BaseMvvmActivity<Binding extends ViewDataBinding,
        ViewModel extends BaseViewModel, R extends Router>  extends AppCompatActivity{

    protected Binding binding;
    protected ViewModel viewModel;

    @Nullable
    protected R router;


    public abstract int provideLayoutId();
    public abstract ViewModel provideViewModel();
    public abstract R provideRouter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = provideViewModel();
        binding = DataBindingUtil.setContentView(this, provideLayoutId());
        binding.setVariable(BR.viewModel,viewModel);
        binding.executePendingBindings();
        router = provideRouter();
        viewModel.attachRouter(router);

        NewMessageNotification.notify(this,"Message",15);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        router =null;
        viewModel.detachRouter();
    }
}