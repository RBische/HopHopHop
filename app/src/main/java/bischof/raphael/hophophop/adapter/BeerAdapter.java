package bischof.raphael.hophophop.adapter;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Locale;

import bischof.raphael.hophophop.R;
import bischof.raphael.hophophop.model.Beer;
import bischof.raphael.hophophop.model.BeerContainerResponse;

/**
 * Adapter that shows {@link Beer} and ask for new items to show automatically
 * Created by biche on 06/02/2016.
 */
public class BeerAdapter extends RecyclerView.Adapter<BeerRowViewHolder> implements PagingAdapter {
    private BeerContainerResponse mBeerContainerResponse;
    private BeerContainerResponse mFallbackBeerContainerResponse;
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
    public void changeBeers(BeerContainerResponse container){
        if (mBeerContainerResponse !=null&& mBeerContainerResponse.getCurrentPage()-1!=container.getCurrentPage()-1){
            mFallbackBeerContainerResponse = mBeerContainerResponse;
        }
        mBeerContainerResponse = container;
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
            if(beer.getBreweries()!=null&&beer.getBreweries().size()>0){
                holder.mTvBrewery.setText(beer.getBreweries().get(0).getNameShortDisplay());
            }
            Locale current = mContext.getResources().getConfiguration().locale;
            holder.mTvCreationDate.setText(DateTimeFormat.forPattern("EEE MMM dd yyyy").withLocale(current).print(new DateTime(beer.getCreateDate())));
            holder.mTvBeerName.setText(beer.getNameDisplay());
            if (beer.getStyle()!=null){
                holder.mTvBeerStyle.setText(beer.getStyle().getShortName());
            }
            if (beer.getLabels()!=null){
                Picasso.with(mContext).load(beer.getLabels().getIcon()).into(holder.mIvIcon);
            }
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
        if (mBeerContainerResponse !=null){
            beerContainerPosition = position- (mBeerContainerResponse.getCurrentPage()-1)* BeerContainerResponse.RESULTS_COUNT_PER_PAGE;
            if (beerContainerPosition<50&&beerContainerPosition>=0){
                if(mBeerContainerResponse.getData()!=null&& mBeerContainerResponse.getData().size()>beerContainerPosition&&beerContainerPosition>=0){
                    return mBeerContainerResponse.getData().get(beerContainerPosition);
                }else{
                    return null;
                }
            }
        }
        if (mFallbackBeerContainerResponse !=null){
            beerContainerPosition = position- (mFallbackBeerContainerResponse.getCurrentPage()-1)* BeerContainerResponse.RESULTS_COUNT_PER_PAGE;
            if (beerContainerPosition<50&&beerContainerPosition>=0){
                if(mFallbackBeerContainerResponse.getData()!=null&& mFallbackBeerContainerResponse.getData().size()>beerContainerPosition&&beerContainerPosition>=0){
                    return mFallbackBeerContainerResponse.getData().get(beerContainerPosition);
                }else{
                    return null;
                }
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
        if (mBeerContainerResponse !=null){
            return mBeerContainerResponse.getTotalResults();
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
        if (mBeerContainerResponse !=null){
            return mBeerContainerResponse.getCurrentPage();
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
