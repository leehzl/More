package com.lilynlee.zhihudailynews.http;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 作者：Lilynlee on 2017/3/20
 * 邮箱：1132860085@qq.com
 */

public class RetryWhenNetworkException implements Func1<Observable<? extends Throwable>,Observable<?>> {

    /*retry次数*/
    private int count = 3;

    /*延迟*/
    private long delay = 1000;

    /*叠加延迟*/
    private long increaseDelay = 1000;

    public RetryWhenNetworkException(){

    }

    public RetryWhenNetworkException(int count, long delay, long increaseDelay) {
        this.count = count;
        this.delay = delay;
        this.increaseDelay = increaseDelay;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable
                .zipWith(Observable.range(1, count + 1), new Func2<Throwable, Integer, Wrapper>() {
                    @Override
                    public Wrapper call(Throwable throwable, Integer integer) {
                        return new Wrapper(integer,throwable);
                    }
                })
                .flatMap(new Func1<Wrapper, Observable<?>>() {
                    @Override
                    public Observable<?> call(Wrapper wrapper) {
                        if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof TimeoutException)
                                && wrapper.index < count +1) {    //如果超出重试次数也抛出错误，否则默认会进入onCompleted
                            return Observable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(wrapper.throwable);
                    }
                });
    }



    private class Wrapper{
        private int index;
        private Throwable throwable;

        public Wrapper(int index, Throwable throwable) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}
