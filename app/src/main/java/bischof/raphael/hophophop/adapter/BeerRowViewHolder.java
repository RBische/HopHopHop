package bischof.raphael.hophophop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bischof.raphael.hophophop.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class containing the rowview showed in a {@link BeerAdapter}
 * Created by biche on 06/02/2016.
 */
public class BeerRowViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tvBeerName)
    TextView mTvBeerName;
    @Bind(R.id.tvBeerStyle)
    TextView mTvBeerStyle;
    @Bind(R.id.tvBrewery)
    TextView mTvBrewery;
    @Bind(R.id.tvCreationDate)
    TextView mTvCreationDate;
    @Bind(R.id.ivIcon)
    ImageView mIvIcon;

    public BeerRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
