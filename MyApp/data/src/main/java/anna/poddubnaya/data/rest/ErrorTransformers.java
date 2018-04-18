package anna.poddubnaya.data.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import anna.poddubnaya.data.entity.ErrorType;
import anna.poddubnaya.data.entity.MyError;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

@Singleton
public class ErrorTransformers {

    private Gson gson;

    @Inject
    public ErrorTransformers(Gson gson) {
        this.gson = gson;
    }

    public <Model, ErrorException extends MyError> FlowableTransformer<Model, Model> parseHttpError() {

        return new FlowableTransformer<Model, Model>() {
            @Override
            public Publisher<Model> apply(Flowable<Model> upstream) {
                return upstream.onErrorResumeNext(new Function<Throwable, Publisher<? extends Model>>() {
                    @Override
                    public Publisher<? extends Model> apply(Throwable throwable) throws Exception {

                        if (throwable instanceof SocketTimeoutException) {
                            return Flowable.error(new MyError(ErrorType.SERVER_NOT_AVAILABLE));
                        } else if (throwable instanceof IOException) {
                            return Flowable.error(new MyError(ErrorType.NO_INTERNET));

                        } else if (throwable instanceof HttpException) {
                            HttpException httpException = (HttpException) throwable;
                            String bodyError = (String) httpException.response().body();

                            Type errorType = new TypeToken<Error>() {
                            }.getType();
                            ErrorException error = gson.fromJson(bodyError, errorType);
                            return Flowable.error(error);

                        } else {
                            return Flowable.error(new MyError(ErrorType.UNKNOWN));
                        }
                    }
                });
            }
        };
    }

    public CompletableTransformer parseError(){
        return new CompletableTransformer() {
            @Override
            public CompletableSource apply(Completable upstream) {
                return upstream.onErrorResumeNext(new Function<Throwable, CompletableSource>() {
                    @Override
                    public CompletableSource apply(Throwable throwable) throws Exception {
                        if (throwable instanceof SocketTimeoutException) {
                            return Completable.error(new MyError(ErrorType.SERVER_NOT_AVAILABLE));
                        } else if (throwable instanceof IOException) {
                            return Completable.error(new MyError(ErrorType.NO_INTERNET));
                        } else {
                            return Completable.error(new MyError(ErrorType.UNKNOWN));
                        }
                    }
                });
            }
        };

    }
}
