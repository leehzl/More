package com.lilynlee.zhihudailynews.interfaze;

/**
 * Created by dell on 2017/3/6.
 */

public interface ISkinChangingCallback {
    void onStart();
    void onError(Exception e);
    void onComplete();
    public static DefaultSkinChangingCallback DEFAULT_CALLBACK = new DefaultSkinChangingCallback();

    public class DefaultSkinChangingCallback implements ISkinChangingCallback{

        @Override
        public void onStart() {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
