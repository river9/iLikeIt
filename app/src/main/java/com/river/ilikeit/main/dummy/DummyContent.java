package com.river.ilikeit.main.dummy;

import com.river.ilikeit.main.photo.PhotoInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<PhotoInfo> ITEMS = new ArrayList<PhotoInfo>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, PhotoInfo> ITEM_MAP = new HashMap<String, PhotoInfo>();

    static {
        // Add 3 sample items.
        addItem(new PhotoInfo("1", "Sunset Wave ocean",
                "https://s-media-cache-ak0.pinimg.com/736x/0c/2a/ce/0c2acec44168c5ece6d24ad7a9f97ede.jpg",
                "Laura Wolfgang", 12, 14, true, false));
        addItem(new PhotoInfo("2", "Axel Arigato black chukka sneaker with marble sole www.axelarigato.com #axelarigato",
                "https://s-media-cache-ak0.pinimg.com/736x/26/10/92/2610924869ebd5175c36bbb993b59523.jpg",
                "Zgo Photography and Design", 12, 14, true, false));
        addItem(new PhotoInfo("3", "LOUIS VUITTON TAMBOUR CHRONOGRAPH.",
                "https://s-media-cache-ak0.pinimg.com/736x/9e/98/b5/9e98b51899fbc50ac94cda279a75a7b7.jpg",
                "Jackie Hawkins", 4, 1, true, false));
        addItem(new PhotoInfo("4", "@_@",
                "https://s-media-cache-ak0.pinimg.com/236x/dd/dc/2a/dddc2ab45d3e04e3427e194d2bc50f2e.jpg",
                "Joe & Angie", 12, 14, true, false));
        addItem(new PhotoInfo("5", "Audemars Piguet ambassador, Novak Djokovic",
                "https://s-media-cache-ak0.pinimg.com/736x/dd/c1/97/ddc1977e0a4795dbb2d888433f3912f4.jpg",
                "Jelena Sijan", 12, 14, true, false));
        addItem(new PhotoInfo("5", "Audemars Piguet ambassador, Novak Djokovic",
                "https://s-media-cache-ak0.pinimg.com/736x/41/7f/6e/417f6e70a36f080e540154e9e4ec7711.jpg",
                "Jelena Sijan", 12, 14, true, false));
        addItem(new PhotoInfo("5", "Audemars Piguet ambassador, Novak Djokovic",
                "https://s-media-cache-ak0.pinimg.com/236x/d2/54/5f/d2545f60fbfe2886e97f77ac03eb0b65.jpg",
                "Jelena Sijan", 12, 14, true, false));
    }

    private static void addItem(PhotoInfo item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
