package bischof.raphael.hophophop;

import android.support.v7.widget.LinearLayoutManager;

import org.junit.Before;
import org.junit.Test;

import bischof.raphael.hophophop.reactive.ScrollFilter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Tests the behavior of {@link ScrollFilter} class
 * Created by rbischof on 09/02/2016.
 */
public class ScrollFilterTest {
    private LinearLayoutManager mLayoutManager;

    @Before
    public void setUp() {
        mLayoutManager = mock(LinearLayoutManager.class);
    }

    @Test
    public void scrollFilter_FilterAnswersFalseIfAdapterLoading() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(1);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,0,0,true,1);
        assertFalse("Scroll filter must return false if content is ever loading", result);
    }

    @Test
    public void scrollFilter_FilterAnswersTrueIfDoesntMove() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(1);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,0,0,false,1);
        assertTrue("Scroll filter must return true if no dx and dy are given", result);
    }

    @Test
    public void scrollFilter_FilterAnswersTrueIfMoveDown() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(49);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,1,100,false,1);
        assertTrue("Scroll filter must return true if dy is positive and lastItemVisible < threshold", result);
    }

    @Test
    public void scrollFilter_FilterAnswersFalseIfMoveDownAndNearestItemsAlreadyLoaded() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(0);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,1,0,false,1);
        assertFalse("Scroll filter must return false if dy is positive and lastItemVisible > threshold", result);
    }

    @Test
    public void scrollFilter_FilterAnswersTrueIfMoveUp() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(0);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,-1,0,false,2);
        assertTrue("Scroll filter must return true if dy is negative and firstItemVisible < threshold", result);
    }

    @Test
    public void scrollFilter_FilterAnswersFalseIfMoveUpAndNearestItemsAlreadyLoaded() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(50*2);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(0);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,-1,0,false,2);
        assertFalse("Scroll filter must return false if dy is negative and firstItemVisible > threshold", result);
    }

    @Test
    public void scrollFilter_FilterAnswersFalseIfMoveUpAndFirstPage() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(0);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,-1,0,false,1);
        assertFalse("Scroll filter must return false if dy is negative and firstItemVisible < threshold and already on first page", result);
    }

    @Test
    public void scrollFilter_FilterAnswersFalseIfMoveDownAndLastPage() {
        when(mLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);
        when(mLayoutManager.findLastVisibleItemPosition()).thenReturn(49);
        ScrollFilter scrollFilter = new ScrollFilter(mLayoutManager);
        boolean result = scrollFilter.mustBeLoaded(0,1,0,false,1);
        assertFalse("Scroll filter must return false if dy is positive and lastItemVisible < threshold and already on last page", result);
    }
}
