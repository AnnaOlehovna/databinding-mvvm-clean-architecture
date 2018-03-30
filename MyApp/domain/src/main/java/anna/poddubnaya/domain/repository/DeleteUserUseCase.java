package anna.poddubnaya.domain.repository;
import javax.inject.Inject;

import anna.poddubnaya.domain.executor.PostExecutionThread;
import anna.poddubnaya.domain.usecases.UserRepository;
import io.reactivex.Completable;

public class DeleteUserUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public DeleteUserUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable remove(String id) {
        return userRepository.remove(id)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
