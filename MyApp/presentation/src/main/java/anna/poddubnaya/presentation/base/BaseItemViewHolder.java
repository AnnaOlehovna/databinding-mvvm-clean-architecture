package anna.poddubnaya.presentation.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import anna.poddubnaya.presentation.BR;


public abstract class BaseItemViewHolder<Model,
        ItemViewModel extends BaseItemViewModel,
        Binding extends ViewDataBinding> extends RecyclerView.ViewHolder{

    private Binding binding;
    private ItemViewModel viewModel;

    public ItemViewModel getViewModel() {
        return viewModel;
    }

    public BaseItemViewHolder(Binding binding, ItemViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;
        this.viewModel = viewModel;
        this.viewModel.init();
        initViewModel();
    }

    protected void initViewModel(){
        binding.setVariable(BR.viewModel,viewModel);
    }



    public void bindTo(Model model, int position){
       viewModel.setItem(model,position);
       binding.executePendingBindings();
    }

    public void release(){
        viewModel.release();
    }

}
