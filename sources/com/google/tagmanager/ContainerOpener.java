package com.google.tagmanager;

import com.google.tagmanager.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class ContainerOpener {
    public static final long DEFAULT_TIMEOUT_IN_MILLIS = 2000;
    private static final Map<String, List<Notifier>> mContainerIdNotifiersMap = new HashMap();
    private Clock mClock = new Clock() {
        public long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    };
    /* access modifiers changed from: private */
    public volatile Container mContainer;
    private final String mContainerId;
    private boolean mHaveNotified;
    private Notifier mNotifier;
    private final TagManager mTagManager;
    private final long mTimeoutInMillis;

    public interface ContainerFuture {
        Container get();

        boolean isDone();
    }

    public interface Notifier {
        void containerAvailable(Container container);
    }

    public enum OpenType {
        PREFER_NON_DEFAULT,
        PREFER_FRESH
    }

    private class WaitForNonDefaultRefresh implements Container.Callback {
        public WaitForNonDefaultRefresh() {
        }

        public void containerRefreshBegin(Container container, Container.RefreshType refreshType) {
        }

        public void containerRefreshSuccess(Container container, Container.RefreshType refreshType) {
            ContainerOpener.this.callNotifiers(container);
        }

        public void containerRefreshFailure(Container container, Container.RefreshType refreshType, Container.RefreshFailure refreshFailure) {
            if (refreshType == Container.RefreshType.NETWORK) {
                ContainerOpener.this.callNotifiers(container);
            }
        }
    }

    private class WaitForFresh implements Container.Callback {
        private final long mOldestTimeToBeFresh;

        public WaitForFresh(long oldestTimeToBeFresh) {
            this.mOldestTimeToBeFresh = oldestTimeToBeFresh;
        }

        public void containerRefreshBegin(Container container, Container.RefreshType refreshType) {
        }

        public void containerRefreshSuccess(Container container, Container.RefreshType refreshType) {
            if (refreshType == Container.RefreshType.NETWORK || isFresh()) {
                ContainerOpener.this.callNotifiers(container);
            }
        }

        public void containerRefreshFailure(Container container, Container.RefreshType refreshType, Container.RefreshFailure refreshFailure) {
            if (refreshType == Container.RefreshType.NETWORK) {
                ContainerOpener.this.callNotifiers(container);
            }
        }

        private boolean isFresh() {
            return this.mOldestTimeToBeFresh < ContainerOpener.this.mContainer.getLastRefreshTime();
        }
    }

    private ContainerOpener(TagManager tagManager, String containerId, Long timeoutInMillis, Notifier notifier) {
        this.mTagManager = tagManager;
        this.mContainerId = containerId;
        this.mTimeoutInMillis = timeoutInMillis != null ? Math.max(1, timeoutInMillis.longValue()) : 2000;
        this.mNotifier = notifier;
    }

    public static void openContainer(TagManager tagManager, String containerId, OpenType openType, Long timeoutInMillis, Notifier notifier) {
        if (tagManager == null) {
            throw new NullPointerException("TagManager cannot be null.");
        } else if (containerId == null) {
            throw new NullPointerException("ContainerId cannot be null.");
        } else if (openType == null) {
            throw new NullPointerException("OpenType cannot be null.");
        } else if (notifier == null) {
            throw new NullPointerException("Notifier cannot be null.");
        } else {
            new ContainerOpener(tagManager, containerId, timeoutInMillis, notifier).open(openType == OpenType.PREFER_FRESH ? Container.RefreshType.NETWORK : Container.RefreshType.SAVED);
        }
    }

    public static ContainerFuture openContainer(TagManager tagManager, String containerId, OpenType openType, Long timeoutInMillis) {
        final ContainerFutureImpl future = new ContainerFutureImpl();
        openContainer(tagManager, containerId, openType, timeoutInMillis, new Notifier() {
            public void containerAvailable(Container container) {
                future.setContainer(container);
            }
        });
        return future;
    }

    private static class ContainerFutureImpl implements ContainerFuture {
        private volatile Container mContainer;
        private Semaphore mContainerIsReady;
        private volatile boolean mHaveGotten;

        private ContainerFutureImpl() {
            this.mContainerIsReady = new Semaphore(0);
        }

        public Container get() {
            if (this.mHaveGotten) {
                return this.mContainer;
            }
            try {
                this.mContainerIsReady.acquire();
            } catch (InterruptedException e) {
            }
            this.mHaveGotten = true;
            return this.mContainer;
        }

        public void setContainer(Container container) {
            this.mContainer = container;
            this.mContainerIsReady.release();
        }

        public boolean isDone() {
            return this.mHaveGotten || this.mContainerIsReady.availablePermits() > 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0041, code lost:
        if (r0 == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0043, code lost:
        r13.mNotifier.containerAvailable(r13.mContainer);
        r13.mNotifier = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        setTimer(java.lang.Math.max(1, r13.mTimeoutInMillis - (r13.mClock.currentTimeMillis() - r2)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void open(com.google.tagmanager.Container.RefreshType r14) {
        /*
            r13 = this;
            r12 = 0
            com.google.tagmanager.Clock r6 = r13.mClock
            long r2 = r6.currentTimeMillis()
            r0 = 0
            java.lang.Class<com.google.tagmanager.ContainerOpener> r7 = com.google.tagmanager.ContainerOpener.class
            monitor-enter(r7)
            com.google.tagmanager.TagManager r6 = r13.mTagManager     // Catch:{ all -> 0x0058 }
            java.lang.String r8 = r13.mContainerId     // Catch:{ all -> 0x0058 }
            com.google.tagmanager.Container r6 = r6.getContainer(r8)     // Catch:{ all -> 0x0058 }
            r13.mContainer = r6     // Catch:{ all -> 0x0058 }
            com.google.tagmanager.Container r6 = r13.mContainer     // Catch:{ all -> 0x0058 }
            if (r6 != 0) goto L_0x005b
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0058 }
            r1.<init>()     // Catch:{ all -> 0x0058 }
            com.google.tagmanager.ContainerOpener$Notifier r6 = r13.mNotifier     // Catch:{ all -> 0x0058 }
            r1.add(r6)     // Catch:{ all -> 0x0058 }
            r6 = 0
            r13.mNotifier = r6     // Catch:{ all -> 0x0058 }
            java.util.Map<java.lang.String, java.util.List<com.google.tagmanager.ContainerOpener$Notifier>> r6 = mContainerIdNotifiersMap     // Catch:{ all -> 0x0058 }
            java.lang.String r8 = r13.mContainerId     // Catch:{ all -> 0x0058 }
            r6.put(r8, r1)     // Catch:{ all -> 0x0058 }
            com.google.tagmanager.TagManager r8 = r13.mTagManager     // Catch:{ all -> 0x0058 }
            java.lang.String r9 = r13.mContainerId     // Catch:{ all -> 0x0058 }
            com.google.tagmanager.Container$RefreshType r6 = com.google.tagmanager.Container.RefreshType.SAVED     // Catch:{ all -> 0x0058 }
            if (r14 != r6) goto L_0x004d
            com.google.tagmanager.ContainerOpener$WaitForNonDefaultRefresh r6 = new com.google.tagmanager.ContainerOpener$WaitForNonDefaultRefresh     // Catch:{ all -> 0x0058 }
            r6.<init>()     // Catch:{ all -> 0x0058 }
        L_0x003a:
            com.google.tagmanager.Container r6 = r8.openContainer(r9, r6)     // Catch:{ all -> 0x0058 }
            r13.mContainer = r6     // Catch:{ all -> 0x0058 }
        L_0x0040:
            monitor-exit(r7)     // Catch:{ all -> 0x0058 }
            if (r0 == 0) goto L_0x0073
            com.google.tagmanager.ContainerOpener$Notifier r6 = r13.mNotifier
            com.google.tagmanager.Container r7 = r13.mContainer
            r6.containerAvailable(r7)
            r13.mNotifier = r12
        L_0x004c:
            return
        L_0x004d:
            com.google.tagmanager.ContainerOpener$WaitForFresh r6 = new com.google.tagmanager.ContainerOpener$WaitForFresh     // Catch:{ all -> 0x0058 }
            r10 = 43200000(0x2932e00, double:2.1343636E-316)
            long r10 = r2 - r10
            r6.<init>(r10)     // Catch:{ all -> 0x0058 }
            goto L_0x003a
        L_0x0058:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0058 }
            throw r6
        L_0x005b:
            java.util.Map<java.lang.String, java.util.List<com.google.tagmanager.ContainerOpener$Notifier>> r6 = mContainerIdNotifiersMap     // Catch:{ all -> 0x0058 }
            java.lang.String r8 = r13.mContainerId     // Catch:{ all -> 0x0058 }
            java.lang.Object r1 = r6.get(r8)     // Catch:{ all -> 0x0058 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0058 }
            if (r1 != 0) goto L_0x0069
            r0 = 1
            goto L_0x0040
        L_0x0069:
            com.google.tagmanager.ContainerOpener$Notifier r6 = r13.mNotifier     // Catch:{ all -> 0x0058 }
            r1.add(r6)     // Catch:{ all -> 0x0058 }
            r6 = 0
            r13.mNotifier = r6     // Catch:{ all -> 0x0058 }
            monitor-exit(r7)     // Catch:{ all -> 0x0058 }
            goto L_0x004c
        L_0x0073:
            long r6 = r13.mTimeoutInMillis
            com.google.tagmanager.Clock r8 = r13.mClock
            long r8 = r8.currentTimeMillis()
            long r8 = r8 - r2
            long r4 = r6 - r8
            r6 = 1
            long r6 = java.lang.Math.max(r6, r4)
            r13.setTimer(r6)
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.ContainerOpener.open(com.google.tagmanager.Container$RefreshType):void");
    }

    private void setTimer(long timeoutInMillis) {
        new Timer("ContainerOpener").schedule(new TimerTask() {
            public void run() {
                Log.m177i("Timer expired.");
                ContainerOpener.this.callNotifiers(ContainerOpener.this.mContainer);
            }
        }, timeoutInMillis);
    }

    /* access modifiers changed from: private */
    public synchronized void callNotifiers(Container container) {
        List<Notifier> notifiers;
        if (!this.mHaveNotified) {
            synchronized (ContainerOpener.class) {
                notifiers = mContainerIdNotifiersMap.remove(this.mContainerId);
            }
            if (notifiers != null) {
                for (Notifier notifier : notifiers) {
                    notifier.containerAvailable(container);
                }
            }
            this.mHaveNotified = true;
        }
    }
}
