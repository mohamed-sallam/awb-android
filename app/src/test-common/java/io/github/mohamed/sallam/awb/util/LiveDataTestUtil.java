package io.github.mohamed.sallam.awb.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil<T> {
    public T getValue(final LiveData<T> liveData) throws InterruptedException {
        final List<T> data = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);

        liveData.observeForever(new Observer<T>() {
            @Override
            public void onChanged(T item) {
                data.add(item);
                latch.countDown();
                liveData.removeObserver(this);
            }
        });

        latch.await(2, TimeUnit.SECONDS);

        return (data.size() > 0) ? data.get(0) : null;
    }
}

