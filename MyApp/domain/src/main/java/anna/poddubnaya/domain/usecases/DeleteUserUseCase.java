package anna.poddubnaya.domain.usecases;
import javax.inject.Inject;

import anna.poddubnaya.domain.executor.PostExecutionThread;
import anna.poddubnaya.domain.repository.UserRepository;
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
