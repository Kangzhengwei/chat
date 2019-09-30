package com.sopho.chat.rxJava;

import com.sopho.chat.bean.Base;
import com.sopho.chat.network.helper.ApiException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

public class RxHelper {

    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> handleResult() {
        return tObservable -> {
            return tObservable.flatMap(result -> {
                return createData(result);
            });
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(final T data) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<Base<T>, T> handleBaseResult() {
        return httpResponseFlowable ->
                httpResponseFlowable.flatMap((Function<Base<T>, Flowable<T>>) httpResponse -> {
                    if (httpResponse.code == 0) {
                        if (httpResponse.data != null)
                            return createData(httpResponse.data);
                        return Flowable.error(new ApiException(httpResponse.msg));
                    } else {
                        return Flowable.error(new ApiException(httpResponse.msg));
                    }
                });
    }

}
