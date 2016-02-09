package bischof.raphael.hophophop.fakedagger;

import com.google.gson.Gson;

import bischof.raphael.hophophop.model.BeerContainerResponse;
import bischof.raphael.hophophop.network.ApiManager;
import rx.Observable;

/**
 * Faking retrieving data from ApiManager
 * Created by biche on 09/02/2016.
 */
public class FakeApiManager extends ApiManager {
    private static final String FAKE_FIRST_PAGE = "{\"currentPage\":1,\"data\":[{\"breweries\":[{\"nameShortDisplay\":\"Dust Bowl\"}],\"createDate\":\"2015-03-25 20:28:19\",\"id\":\"xBKAka\",\"nameDisplay\":\"\\\"Galactic Wrath\\\" IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Working Man\"}],\"createDate\":\"2013-07-27 14:02:13\",\"id\":\"5UcMBc\",\"nameDisplay\":\"\\\"Ignition\\\" IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Little Machine Beer\"}],\"createDate\":\"2015-11-03 17:01:42\",\"id\":\"8f8vqK\",\"nameDisplay\":\"\\\"Sniff\\\" IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Victory\"}],\"createDate\":\"2014-12-23 13:20:57\",\"id\":\"6VZsVo\",\"nameDisplay\":\"#429 Red IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Ohana\"}],\"createDate\":\"2015-03-22 16:53:15\",\"id\":\"QWkRz9\",\"nameDisplay\":\"#Hashtag Hops IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Dry Ground\"}],\"createDate\":\"2015-03-09 20:33:04\",\"id\":\"Dk2xuM\",\"nameDisplay\":\"\\u002737 Flood\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Isley\"}],\"createDate\":\"2014-07-04 13:01:31\",\"id\":\"BjmFMX\",\"nameDisplay\":\"\\u0027Scott\\u0027s Addition\\u0027 India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stony Creek Beer\"}],\"createDate\":\"2013-07-22 23:17:15\",\"id\":\"GMWSAD\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/GMWSAD/upload_3dlRGl-icon.png\"},\"nameDisplay\":\"(401) India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"(512)\"}],\"createDate\":\"2012-01-03 02:42:36\",\"id\":\"ezGh5N\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/ezGh5N/upload_r8SNni-icon.png\"},\"nameDisplay\":\"(512) IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"(512)\"}],\"createDate\":\"2015-06-19 15:35:51\",\"id\":\"bXwskR\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/bXwskR/upload_0m1DZl-icon.png\"},\"nameDisplay\":\"(512) White IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stony Creek Beer\"}],\"createDate\":\"2013-09-15 19:48:52\",\"id\":\"jj8YRF\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/jj8YRF/upload_BwXlKY-icon.png\"},\"nameDisplay\":\"(860) India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Coppertail\"}],\"createDate\":\"2015-07-08 13:24:18\",\"id\":\"FLkbzq\",\"nameDisplay\":\"(KU)Jenga Smash\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Fairhope\"}],\"createDate\":\"2015-03-11 18:58:53\",\"id\":\"3BSrTW\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/3BSrTW/upload_c9mjLD-icon.png\"},\"nameDisplay\":\"(Take The) Causeway IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Lincoln\"}],\"createDate\":\"2015-08-28 14:07:22\",\"id\":\"ljNzIS\",\"nameDisplay\":\".58 Caliber\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Tonopah\"}],\"createDate\":\"2014-07-10 13:12:39\",\"id\":\"nMK1nd\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/nMK1nd/upload_2lmQHA-icon.png\"},\"nameDisplay\":\".999\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"The Lost Borough\"}],\"createDate\":\"2015-11-04 03:18:07\",\"id\":\"IWY0Yp\",\"nameDisplay\":\"007 Undercover IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"10 Barrel\"}],\"createDate\":\"2013-03-22 15:13:07\",\"id\":\"BWQehj\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/BWQehj/upload_mJfcvl-icon.png\"},\"nameDisplay\":\"10 Barrel OG. Wheat IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Spiteful\"}],\"createDate\":\"2015-01-26 14:58:31\",\"id\":\"GedKyt\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/GedKyt/upload_sRCSlb-icon.png\"},\"nameDisplay\":\"10-9 Bike Messenger Appreciation IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Yazoo\"}],\"createDate\":\"2014-01-20 17:04:31\",\"id\":\"RLLEqZ\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/RLLEqZ/upload_JLW4sK-icon.png\"},\"nameDisplay\":\"10-Year IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"White Rabbit\"}],\"createDate\":\"2015-04-06 16:30:05\",\"id\":\"bzcvG5\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/bzcvG5/upload_wz2YGb-icon.png\"},\"nameDisplay\":\"10/6th IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Harpoon\"}],\"createDate\":\"2014-04-05 19:56:36\",\"id\":\"FYezHA\",\"nameDisplay\":\"100 Barrel Series #49 - Brown IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Redwood Curtain\"}],\"createDate\":\"2015-03-20 16:57:04\",\"id\":\"rrAghW\",\"nameDisplay\":\"100 Galaxies India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Finger Lakes\"}],\"createDate\":\"2014-05-06 13:08:12\",\"id\":\"DyNPlC\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/DyNPlC/upload_71zbue-icon.png\"},\"nameDisplay\":\"11/11 India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Crow Peak\"}],\"createDate\":\"2012-01-03 02:42:36\",\"id\":\"mtFCP5\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/mtFCP5/upload_Z9H8Xl-icon.png\"},\"nameDisplay\":\"11th Hour IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Pigskin\"}],\"createDate\":\"2016-01-14 19:25:11\",\"id\":\"k6x1Eb\",\"nameDisplay\":\"12 Gauge\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Millbock\"}],\"createDate\":\"2013-01-21 22:21:52\",\"id\":\"TRptV0\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/TRptV0/upload_GGmj14-icon.png\"},\"nameDisplay\":\"12 Hops Of Christmas\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Right Brain\"}],\"createDate\":\"2014-04-29 10:04:17\",\"id\":\"n1oDDP\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/n1oDDP/upload_GSX07n-icon.png\"},\"nameDisplay\":\"1225 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Crafty Dan\"}],\"createDate\":\"2015-06-20 16:20:13\",\"id\":\"5dPcWz\",\"nameDisplay\":\"13 Guns\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Dangerous Man\"}],\"createDate\":\"2015-04-29 15:30:49\",\"id\":\"TW67DY\",\"nameDisplay\":\"13th Task Golden IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Twin Leaf\"}],\"createDate\":\"2014-03-07 19:00:22\",\"id\":\"598y5y\",\"nameDisplay\":\"144 - Juicy Fruit\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Lock 27\"}],\"createDate\":\"2015-06-23 19:18:12\",\"id\":\"8OS7bA\",\"nameDisplay\":\"1492\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Toppling Goliath\"}],\"createDate\":\"2012-04-22 12:52:27\",\"id\":\"7Q94GB\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/7Q94GB/upload_EL4rBS-icon.png\"},\"nameDisplay\":\"1492 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Rincon\"}],\"createDate\":\"2015-08-13 19:43:07\",\"id\":\"nhWaz0\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/nhWaz0/upload_ChoC3o-icon.png\"},\"nameDisplay\":\"17 @ 17 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stone\"}],\"createDate\":\"2013-08-25 12:18:43\",\"id\":\"w6nBIz\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/w6nBIz/upload_lcOQuJ-icon.png\"},\"nameDisplay\":\"17 Anniversary Götterdämmerung IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Bearpaw River\"}],\"createDate\":\"2016-01-04 21:00:35\",\"id\":\"0lOg3f\",\"nameDisplay\":\"18-Pound Test IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Homestead Beer Co.\"}],\"createDate\":\"2015-05-05 15:27:23\",\"id\":\"AE0Qmm\",\"nameDisplay\":\"1805 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Augusta\"}],\"createDate\":\"2014-10-17 00:57:27\",\"id\":\"ilFQn8\",\"nameDisplay\":\"1856 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Ghost River\"}],\"createDate\":\"2015-07-21 18:42:24\",\"id\":\"wPqLwT\",\"nameDisplay\":\"1887 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stone\"}],\"createDate\":\"2014-07-17 14:45:22\",\"id\":\"SR5L1F\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/SR5L1F/upload_cYUCqc-icon.png\"},\"nameDisplay\":\"18th Anniversary Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stone\"}],\"createDate\":\"2015-08-26 13:50:39\",\"id\":\"ZQGP3X\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/ZQGP3X/upload_3Cudwz-icon.png\"},\"nameDisplay\":\"19 Anniversary Thunderstruck IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Forgotten Boardwalk\"}],\"createDate\":\"2014-06-03 11:51:34\",\"id\":\"9wNKio\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/9wNKio/upload_NaK1Fh-icon.png\"},\"nameDisplay\":\"1916 Shore Shiver\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Highway 1\"}],\"createDate\":\"2014-06-09 18:37:20\",\"id\":\"TTFy4W\",\"nameDisplay\":\"1PA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"BackPedal\"}],\"createDate\":\"2015-09-03 16:38:27\",\"id\":\"t4XVri\",\"nameDisplay\":\"1st Gear IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"3 Guys and a Beer\\u0027d\"}],\"createDate\":\"2014-04-29 10:06:12\",\"id\":\"dyydfi\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/dyydfi/upload_otCF76-icon.png\"},\"nameDisplay\":\"2 Bit\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Paw Paw\"}],\"createDate\":\"2014-08-11 20:32:48\",\"id\":\"6k8Zth\",\"nameDisplay\":\"2 Paws IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Atomic Ale Brewpub and Eatery\"}],\"createDate\":\"2015-08-07 22:11:15\",\"id\":\"IzPs6w\",\"nameDisplay\":\"200 West White IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Hunter Gatherer Brewery and Alehouse\"}],\"createDate\":\"2015-07-12 20:25:20\",\"id\":\"gPiI06\",\"nameDisplay\":\"2007 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Natty Greene\\u0027s\"}],\"createDate\":\"2015-02-23 13:24:00\",\"id\":\"7zB0r4\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/7zB0r4/upload_XqVNOv-icon.png\"},\"nameDisplay\":\"201 Central Brett IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"GBrewhouse\"}],\"createDate\":\"2014-03-05 18:33:41\",\"id\":\"FVnYRW\",\"nameDisplay\":\"2013 Holiday IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Golden Road\"}],\"createDate\":\"2015-03-20 21:24:21\",\"id\":\"mZsdU6\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/mZsdU6/upload_tnJ2xz-icon.png\"},\"nameDisplay\":\"2020 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}}],\"fromNetwork\":true,\"numberOfPages\":2,\"styleId\":30,\"totalResults\":100}";
    private static final String FAKE_SECOND_PAGE = "{\"currentPage\":2,\"data\":[{\"breweries\":[{\"nameShortDisplay\":\"Dust Bowl\"}],\"createDate\":\"2015-03-25 20:28:19\",\"id\":\"xBKAka\",\"nameDisplay\":\"\\\"Galactic Wrath\\\" IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Working Man\"}],\"createDate\":\"2013-07-27 14:02:13\",\"id\":\"5UcMBc\",\"nameDisplay\":\"\\\"Ignition\\\" IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Little Machine Beer\"}],\"createDate\":\"2015-11-03 17:01:42\",\"id\":\"8f8vqK\",\"nameDisplay\":\"\\\"Sniff\\\" IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Victory\"}],\"createDate\":\"2014-12-23 13:20:57\",\"id\":\"6VZsVo\",\"nameDisplay\":\"#429 Red IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Ohana\"}],\"createDate\":\"2015-03-22 16:53:15\",\"id\":\"QWkRz9\",\"nameDisplay\":\"#Hashtag Hops IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Dry Ground\"}],\"createDate\":\"2015-03-09 20:33:04\",\"id\":\"Dk2xuM\",\"nameDisplay\":\"\\u002737 Flood\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Isley\"}],\"createDate\":\"2014-07-04 13:01:31\",\"id\":\"BjmFMX\",\"nameDisplay\":\"\\u0027Scott\\u0027s Addition\\u0027 India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stony Creek Beer\"}],\"createDate\":\"2013-07-22 23:17:15\",\"id\":\"GMWSAD\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/GMWSAD/upload_3dlRGl-icon.png\"},\"nameDisplay\":\"(401) India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"(512)\"}],\"createDate\":\"2012-01-03 02:42:36\",\"id\":\"ezGh5N\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/ezGh5N/upload_r8SNni-icon.png\"},\"nameDisplay\":\"(512) IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"(512)\"}],\"createDate\":\"2015-06-19 15:35:51\",\"id\":\"bXwskR\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/bXwskR/upload_0m1DZl-icon.png\"},\"nameDisplay\":\"(512) White IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stony Creek Beer\"}],\"createDate\":\"2013-09-15 19:48:52\",\"id\":\"jj8YRF\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/jj8YRF/upload_BwXlKY-icon.png\"},\"nameDisplay\":\"(860) India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Coppertail\"}],\"createDate\":\"2015-07-08 13:24:18\",\"id\":\"FLkbzq\",\"nameDisplay\":\"(KU)Jenga Smash\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Fairhope\"}],\"createDate\":\"2015-03-11 18:58:53\",\"id\":\"3BSrTW\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/3BSrTW/upload_c9mjLD-icon.png\"},\"nameDisplay\":\"(Take The) Causeway IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Lincoln\"}],\"createDate\":\"2015-08-28 14:07:22\",\"id\":\"ljNzIS\",\"nameDisplay\":\".58 Caliber\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Tonopah\"}],\"createDate\":\"2014-07-10 13:12:39\",\"id\":\"nMK1nd\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/nMK1nd/upload_2lmQHA-icon.png\"},\"nameDisplay\":\".999\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"The Lost Borough\"}],\"createDate\":\"2015-11-04 03:18:07\",\"id\":\"IWY0Yp\",\"nameDisplay\":\"007 Undercover IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"10 Barrel\"}],\"createDate\":\"2013-03-22 15:13:07\",\"id\":\"BWQehj\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/BWQehj/upload_mJfcvl-icon.png\"},\"nameDisplay\":\"10 Barrel OG. Wheat IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Spiteful\"}],\"createDate\":\"2015-01-26 14:58:31\",\"id\":\"GedKyt\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/GedKyt/upload_sRCSlb-icon.png\"},\"nameDisplay\":\"10-9 Bike Messenger Appreciation IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Yazoo\"}],\"createDate\":\"2014-01-20 17:04:31\",\"id\":\"RLLEqZ\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/RLLEqZ/upload_JLW4sK-icon.png\"},\"nameDisplay\":\"10-Year IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"White Rabbit\"}],\"createDate\":\"2015-04-06 16:30:05\",\"id\":\"bzcvG5\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/bzcvG5/upload_wz2YGb-icon.png\"},\"nameDisplay\":\"10/6th IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Harpoon\"}],\"createDate\":\"2014-04-05 19:56:36\",\"id\":\"FYezHA\",\"nameDisplay\":\"100 Barrel Series #49 - Brown IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Redwood Curtain\"}],\"createDate\":\"2015-03-20 16:57:04\",\"id\":\"rrAghW\",\"nameDisplay\":\"100 Galaxies India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Finger Lakes\"}],\"createDate\":\"2014-05-06 13:08:12\",\"id\":\"DyNPlC\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/DyNPlC/upload_71zbue-icon.png\"},\"nameDisplay\":\"11/11 India Pale Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Crow Peak\"}],\"createDate\":\"2012-01-03 02:42:36\",\"id\":\"mtFCP5\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/mtFCP5/upload_Z9H8Xl-icon.png\"},\"nameDisplay\":\"11th Hour IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Pigskin\"}],\"createDate\":\"2016-01-14 19:25:11\",\"id\":\"k6x1Eb\",\"nameDisplay\":\"12 Gauge\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Millbock\"}],\"createDate\":\"2013-01-21 22:21:52\",\"id\":\"TRptV0\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/TRptV0/upload_GGmj14-icon.png\"},\"nameDisplay\":\"12 Hops Of Christmas\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Right Brain\"}],\"createDate\":\"2014-04-29 10:04:17\",\"id\":\"n1oDDP\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/n1oDDP/upload_GSX07n-icon.png\"},\"nameDisplay\":\"1225 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Crafty Dan\"}],\"createDate\":\"2015-06-20 16:20:13\",\"id\":\"5dPcWz\",\"nameDisplay\":\"13 Guns\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Dangerous Man\"}],\"createDate\":\"2015-04-29 15:30:49\",\"id\":\"TW67DY\",\"nameDisplay\":\"13th Task Golden IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Twin Leaf\"}],\"createDate\":\"2014-03-07 19:00:22\",\"id\":\"598y5y\",\"nameDisplay\":\"144 - Juicy Fruit\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Lock 27\"}],\"createDate\":\"2015-06-23 19:18:12\",\"id\":\"8OS7bA\",\"nameDisplay\":\"1492\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Toppling Goliath\"}],\"createDate\":\"2012-04-22 12:52:27\",\"id\":\"7Q94GB\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/7Q94GB/upload_EL4rBS-icon.png\"},\"nameDisplay\":\"1492 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Rincon\"}],\"createDate\":\"2015-08-13 19:43:07\",\"id\":\"nhWaz0\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/nhWaz0/upload_ChoC3o-icon.png\"},\"nameDisplay\":\"17 @ 17 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stone\"}],\"createDate\":\"2013-08-25 12:18:43\",\"id\":\"w6nBIz\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/w6nBIz/upload_lcOQuJ-icon.png\"},\"nameDisplay\":\"17 Anniversary Götterdämmerung IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Bearpaw River\"}],\"createDate\":\"2016-01-04 21:00:35\",\"id\":\"0lOg3f\",\"nameDisplay\":\"18-Pound Test IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Homestead Beer Co.\"}],\"createDate\":\"2015-05-05 15:27:23\",\"id\":\"AE0Qmm\",\"nameDisplay\":\"1805 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Augusta\"}],\"createDate\":\"2014-10-17 00:57:27\",\"id\":\"ilFQn8\",\"nameDisplay\":\"1856 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Ghost River\"}],\"createDate\":\"2015-07-21 18:42:24\",\"id\":\"wPqLwT\",\"nameDisplay\":\"1887 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stone\"}],\"createDate\":\"2014-07-17 14:45:22\",\"id\":\"SR5L1F\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/SR5L1F/upload_cYUCqc-icon.png\"},\"nameDisplay\":\"18th Anniversary Ale\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Stone\"}],\"createDate\":\"2015-08-26 13:50:39\",\"id\":\"ZQGP3X\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/ZQGP3X/upload_3Cudwz-icon.png\"},\"nameDisplay\":\"19 Anniversary Thunderstruck IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Forgotten Boardwalk\"}],\"createDate\":\"2014-06-03 11:51:34\",\"id\":\"9wNKio\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/9wNKio/upload_NaK1Fh-icon.png\"},\"nameDisplay\":\"1916 Shore Shiver\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Highway 1\"}],\"createDate\":\"2014-06-09 18:37:20\",\"id\":\"TTFy4W\",\"nameDisplay\":\"1PA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"BackPedal\"}],\"createDate\":\"2015-09-03 16:38:27\",\"id\":\"t4XVri\",\"nameDisplay\":\"1st Gear IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"3 Guys and a Beer\\u0027d\"}],\"createDate\":\"2014-04-29 10:06:12\",\"id\":\"dyydfi\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/dyydfi/upload_otCF76-icon.png\"},\"nameDisplay\":\"2 Bit\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Paw Paw\"}],\"createDate\":\"2014-08-11 20:32:48\",\"id\":\"6k8Zth\",\"nameDisplay\":\"2 Paws IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Atomic Ale Brewpub and Eatery\"}],\"createDate\":\"2015-08-07 22:11:15\",\"id\":\"IzPs6w\",\"nameDisplay\":\"200 West White IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Hunter Gatherer Brewery and Alehouse\"}],\"createDate\":\"2015-07-12 20:25:20\",\"id\":\"gPiI06\",\"nameDisplay\":\"2007 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Natty Greene\\u0027s\"}],\"createDate\":\"2015-02-23 13:24:00\",\"id\":\"7zB0r4\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/7zB0r4/upload_XqVNOv-icon.png\"},\"nameDisplay\":\"201 Central Brett IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"GBrewhouse\"}],\"createDate\":\"2014-03-05 18:33:41\",\"id\":\"FVnYRW\",\"nameDisplay\":\"2013 Holiday IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}},{\"breweries\":[{\"nameShortDisplay\":\"Golden Road\"}],\"createDate\":\"2015-03-20 21:24:21\",\"id\":\"mZsdU6\",\"labels\":{\"icon\":\"https://s3.amazonaws.com/brewerydbapi/beer/mZsdU6/upload_tnJ2xz-icon.png\"},\"nameDisplay\":\"2020 IPA\",\"style\":{\"id\":30,\"shortName\":\"American IPA\"}}],\"fromNetwork\":true,\"numberOfPages\":2,\"styleId\":30,\"totalResults\":100}";
    private Gson mGson;
    public FakeApiManager(Gson gson, String apiKey) {
        super(gson, apiKey);
        this.mGson = gson;
    }

    @Override
    public Observable<BeerContainerResponse> getBeers(int styleID, int page) {
        if (page==1){
            return Observable.from(new BeerContainerResponse[]{getFirstResponse()});
        }else{
            BeerContainerResponse response = mGson.fromJson(FAKE_SECOND_PAGE, BeerContainerResponse.class);
            return Observable.from(new BeerContainerResponse[]{response});
        }
    }

    public BeerContainerResponse getFirstResponse() {
        return mGson.fromJson(FAKE_FIRST_PAGE, BeerContainerResponse.class);
    }
}
