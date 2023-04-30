package io.github.mohamed.sallam.awb.util;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.executor.TaskExecutor;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class InstantExecutorExtension implements AfterEachCallback, BeforeEachCallback {


    @Override
    public void afterEach(ExtensionContext context) {
        ArchTaskExecutor.getInstance().setDelegate(null);
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        ArchTaskExecutor.getInstance()
                .setDelegate(new TaskExecutor() {
                    @Override
                    public void executeOnDiskIO(@NonNull Runnable runnable) {
                        runnable.run();
                    }

                    @Override
                    public void postToMainThread(@NonNull Runnable runnable) {
                        runnable.run();
                    }

                    @Override
                    public boolean isMainThread() {
                        return true;
                    }
                });
    }
}
