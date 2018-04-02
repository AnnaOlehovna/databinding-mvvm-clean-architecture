package anna.poddubnaya.presentation.screens.userList.recycler;


import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.presentation.base.BaseItemViewHolder;
import anna.poddubnaya.presentation.databinding.ItemUserEntityBinding;


public class UserItemViewHolder extends BaseItemViewHolder<UserEntity,
        ItemViewModel,ItemUserEntityBinding>{


    public UserItemViewHolder(ItemUserEntityBinding binding, ItemViewModel viewModel) {
        super(binding, viewModel);

        //здесь уже можно вешать клики на отдельные элементы item
    }


}
