package bischof.raphael.hophophop.recyclerview;

import android.support.v7.widget.RecyclerView;

import com.jakewharton.rxbinding.internal.MainThreadSubscription;

import rx.Observable;
import rx.Subscriber;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Inspired mostly by RxBindings (JakeWharton) and forked by biche on 07/02/2016.
 */
final class RecyclerViewScrollEventOnSubscribe
        implements Observable.OnSubscribe<RecyclerViewScrollEvent> {

    private final RecyclerView recyclerView;

    public RecyclerViewScrollEventOnSubscribe(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override public void call(final Subscriber<? super RecyclerViewScrollEvent> subscriber) {
        checkUiThread();

        final RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(RecyclerViewScrollEvent.create(recyclerView, dx, dy));
                }
            }
        };
        recyclerView.addOnScrollListener(listener);

        subscriber.add(new MainThreadSubscription() {
            @Override protected void onUnsubscribe() {
                recyclerView.removeOnScrollListener(listener);
            }
        });
        if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(RecyclerViewScrollEvent.create(recyclerView, 0, 0));
        }
    }
}
