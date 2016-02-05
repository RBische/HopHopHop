package bischof.raphael.hophophop;

import android.content.Intent;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = "/src/main/AndroidManifest.xml")
public class BeerActivityTest {

    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        BeerActivity activity = Robolectric.setupActivity(BeerActivity.class);
        activity.findViewById(R.id.hello).performClick();

        Intent expectedIntent = new Intent(activity, BeerActivity.class);
        assertEquals(shadowOf(activity).getNextStartedActivity(),expectedIntent);
    }
}
