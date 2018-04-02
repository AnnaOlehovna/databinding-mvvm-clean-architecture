package anna.poddubnaya.domain.usecases;
import javax.inject.Inject;

import anna.poddubnaya.domain.entity.UserEntity;
import anna.poddubnaya.domain.executor.PostExecutionThread;
import anna.poddubnaya.domain.repository.UserRepository;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class GetUserByIdUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserByIdUseCase(PostExecutionThread postExecutionThread,
                              UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    //по идее должен быть один публичный метод, который выполняет одну функцию
    public Flowable<UserEntity> get(String id) {
        return userRepository.get(id)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);

    }


}
