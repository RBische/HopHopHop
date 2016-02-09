package bischof.raphael.hophophop;

import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;

import bischof.raphael.hophophop.fakedagger.FakeApiModule;
import bischof.raphael.hophophop.modules.ApiModule;

/**
 * Tests the behavior of the beer activity
 * Created by rbischof on 22/04/2015.
 */
public class BeerActivityTest extends ActivityInstrumentationTestCase2<BeerActivity> {
    private BeerActivity mBeerActivity;
    private RecyclerView mRvBeers;

    public BeerActivityTest() {
        super(BeerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        HopHopHopApplication.getInstance().setMockMode(new FakeApiModule(HopHopHopApplication.getInstance()));
        mBeerActivity = getActivity();
        mRvBeers = ((RecyclerView) mBeerActivity.findViewById(R.id.rvBeers));
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

    public void testScroll(){
        Exception exceptionThrown=null;
        try {
            mBeerActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mRvBeers.scrollToPosition(21);
                }
            });
            Thread.sleep(5000);
        }catch (Exception e){
            exceptionThrown = e;
        }
        assertNull(exceptionThrown);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        HopHopHopApplication.getInstance().setMockMode(new ApiModule(HopHopHopApplication.getInstance()));
    }
}
