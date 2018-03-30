package anna.poddubnaya.domain.repository;
import java.util.List;

import javax.inject.Inject;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.executor.PostExecutionThread;
import anna.poddubnaya.domain.usecases.UserRepository;
import io.reactivex.Observable;

public class GetUserListUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserListUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<List<UserEntity>> get() {
        return userRepository.get()
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}

