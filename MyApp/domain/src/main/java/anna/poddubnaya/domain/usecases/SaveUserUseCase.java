package anna.poddubnaya.domain.usecases;

import javax.inject.Inject;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.executor.PostExecutionThread;
import anna.poddubnaya.domain.repository.UserRepository;
import io.reactivex.Completable;

public class SaveUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public SaveUserUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


    public Completable save(UserEntity userEntity) {
        return userRepository.save(userEntity).
                subscribeOn(threadExecution)
                .observeOn(postExecutionThread);

    }
}

