package anna.poddubnaya.presentation.screens.userList;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.presentation.R;
import anna.poddubnaya.presentation.databinding.ItemUserEntityBinding;

public class UserAdapter extends RecyclerView.Adapter {

    private List<UserEntity> userList = new ArrayList<>();
    private OnUserClickListener listener;

    public void setUserList(List<UserEntity> users){
        this.userList = users;
        notifyDataSetChanged();
    }

    public void setListener(OnUserClickListener listener){
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserEntityBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_user_entity,
                parent,false);

        return new MyHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        UserEntity user = userList.get(position);
        myHolder.binding.getViewModel().setUserEntity(user);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!= null)listener.onClick(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    private class MyHolder extends RecyclerView.ViewHolder{
        ItemUserEntityBinding binding;

        public MyHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.setViewModel(new ItemViewModel());
        }
    }

    interface OnUserClickListener{
        void onClick (UserEntity user);
    }
}