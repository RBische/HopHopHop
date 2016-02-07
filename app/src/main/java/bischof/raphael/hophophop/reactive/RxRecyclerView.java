package bischof.raphael.hophophop.reactive;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import rx.Observable;
import rx.functions.Action1;

/**
 * Static factory methods for creating {@linkplain Observable observables} and {@linkplain Action1
 * actions} for {@link RecyclerView}.
 * Inspired mostly by RxBindings (JakeWharton) and forked by biche on 07/02/2016.
 */
public class RxRecyclerView {
    /**
     * Create an observable of scroll events on {@code recyclerView}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code recyclerView}.
     * Unsubscribe to free this reference.
     */
    @CheckResult
    @NonNull
    public static Observable<RecyclerViewScrollEvent> scrollEvents(
            @NonNull RecyclerView recyclerView) {
        return Observable.create(new RecyclerViewScrollEventOnSubscribe(recyclerView));
    }
}
