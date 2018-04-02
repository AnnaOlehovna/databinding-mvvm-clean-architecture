package anna.poddubnaya.presentation.screens.userList.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.presentation.base.BaseAdapter;
import anna.poddubnaya.presentation.databinding.ItemUserEntityBinding;


public class UserAdapter extends BaseAdapter<UserEntity, ItemViewModel> {

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserEntityBinding binding = ItemUserEntityBinding.inflate(inflater, parent, false);
        return new UserItemViewHolder(binding, new ItemViewModel());

    }
}