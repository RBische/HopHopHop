package bischof.raphael.hophophop.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Describes a beer
 * Created by biche on 05/02/2016.
 */
@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class Beer extends RealmObject {
    private String id;
    private Label labels;
    private String nameDisplay;
    private RealmList<Brewery> breweries;
    private BeerStyle style;
    private Date createDate;

    public void setId(String id) {
        this.id = id;
    }

    public void setLabels(Label labels) {
        this.labels = labels;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public void setBreweries(RealmList<Brewery> breweries) {
        this.breweries = breweries;
    }

    public void setStyle(BeerStyle style) {
        this.style = style;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public String getId() {
        return id;
    }

    public Label getLabels() {
        return labels;
    }

    public RealmList<Brewery> getBreweries() {
        return breweries;
    }

    public BeerStyle getStyle() {
        return style;
    }

    public Date getCreateDate() {
        return createDate;
    }

}
