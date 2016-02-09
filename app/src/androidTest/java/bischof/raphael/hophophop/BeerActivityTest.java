package bischof.raphael.hophophop;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import bischof.raphael.hophophop.model.BeerContainerResponse;
import rx.observers.TestSubscriber;

/**
 * Created by rbischof on 22/04/2015.
 */
public class BeerActivityTest extends ActivityInstrumentationTestCase2<BeerActivity> {
    private static final long TIMEOUT_IN_MS = 500;
    private BeerActivity mBeerActivity;
    private RecyclerView mRvBeers;

    public BeerActivityTest() {
        super(BeerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        mBeerActivity = getActivity();
        mRvBeers = ((RecyclerView) mBeerActivity.findViewById(R.id.rvBeers));
    }

    public void testDataFetched(){
        TestSubscriber<BeerContainerResponse> testSubscriber = new TestSubscriber<>();
        //TODO: Finish writing this test
    }

    public void testOrientation(){
        Exception exceptionThrown=null;
        try {
            mBeerActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Thread.sleep(200);
            mBeerActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            Thread.sleep(200);
        }catch (Exception e){
            exceptionThrown = e;
        }
        assertNull(exceptionThrown);
    }
}
