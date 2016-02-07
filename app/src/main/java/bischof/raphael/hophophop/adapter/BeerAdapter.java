package bischof.raphael.hophophop.adapter;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import bischof.raphael.hophophop.R;
import bischof.raphael.hophophop.model.Beer;
import bischof.raphael.hophophop.model.BeerContainer;

/**
 * Adapter that shows {@link Beer} and ask for new items to show automatically
 * Created by biche on 06/02/2016.
 */
public class BeerAdapter extends RecyclerView.Adapter<BeerRowViewHolder> implements PagingAdapter {
    private BeerContainer mBeerContainer;
    private BeerContainer mFallbackBeerContainer;
    private final Context mContext;
    private final View mEmptyView;
    private boolean loading = false;

    public BeerAdapter(Context mContext, View emptyView) {
        this.mContext = mContext;
        this.mEmptyView = emptyView;
    }

    /**
     * Refreshes data and keeps the last data fetched to have the ability to show items between two pages
     * @param container BeerContainer result from BreweryDB
     */
    public void changeBeers(BeerContainer container){
        if (mBeerContainer!=null&&mBeerContainer.getCurrentPage()!=container.getCurrentPage()){
            mFallbackBeerContainer = mBeerContainer;
        }
        mBeerContainer = container;
        int itemCount = getItemCount();
        mEmptyView.setVisibility(itemCount == 0 ? View.VISIBLE : View.GONE);
        notifyDataSetChanged();
        loading = false;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public BeerRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_beer, parent, false);
        view.setFocusable(true);
        return new BeerRowViewHolder(view);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public void onBindViewHolder(BeerRowViewHolder holder, int position) {
        clearView(holder);
        Beer beer = getItem(position);
        if (beer!=null){
            holder.mTvCreationDate.setText(beer.getFormattedDate(mContext));
            holder.mTvBeerName.setText(beer.getName());
            holder.mTvBeerStyle.setText(beer.getStyleName());
            holder.mTvBrewery.setText(beer.getBreweryName());
            Picasso.with(mContext).load(beer.getLabel()).into(holder.mIvIcon);
        }
    }

    private void clearView(BeerRowViewHolder holder) {
        holder.mTvCreationDate.setText(null);
        holder.mTvBeerName.setText(null);
        holder.mTvBeerStyle.setText(null);
        holder.mTvBrewery.setText(null);
        Picasso.with(holder.mIvIcon.getContext())
                .cancelRequest(holder.mIvIcon);
        holder.mIvIcon.setImageResource(android.R.color.transparent);
    }

    /**
     * Returns an item at a specified position, may return null if no item exists or if data are not loaded
     * @param position The position
     * @return The item at a specified position
     */
    @CheckResult
    private Beer getItem(int position) {
        int beerContainerPosition;
        if (mBeerContainer!=null){
            beerContainerPosition = position-mBeerContainer.getCurrentPage()*BeerContainer.RESULTS_COUNT_PER_PAGE;
            if (beerContainerPosition<50&&beerContainerPosition>=0){
                return mBeerContainer.getBeer(beerContainerPosition);
            }
        }
        if (mFallbackBeerContainer !=null){
            beerContainerPosition = position- mFallbackBeerContainer.getCurrentPage()*BeerContainer.RESULTS_COUNT_PER_PAGE;
            if (beerContainerPosition<50&&beerContainerPosition>=0){
                return mFallbackBeerContainer.getBeer(beerContainerPosition);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int getItemCount() {
        if (mBeerContainer !=null){
            return mBeerContainer.getTotalResults();
        }else{
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int getCurrentPage() {
        if (mBeerContainer!=null){
            return mBeerContainer.getCurrentPage()+1;
        }else{
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isLoading() {
        return loading;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

}
