package ec.edu.epn.guiaquito.utils;

import com.google.gson.Gson;
import ec.edu.epn.guiaquito.entities.InterestType;
import ec.edu.epn.guiaquito.entities.ObjectOfInterest;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Icon;

public class ObjectOfInterestUtil {

    private final static Gson gson = new Gson();

    public static ObjectOfInterest convertToObjectOfInterest(CompactVenue place, InterestType interestType) {
        String icon;
        icon = extractIcon(place.getCategories());
        return new ObjectOfInterest(place.getName(), place.getLocation().getLat(),
                place.getLocation().getLng(), 0F, interestType, icon, place.getId());
    }

    public static ObjectOfInterest convertToObjectOfInterest(CompactVenue place) {
        String icon = extractIcon(place.getCategories());
        System.out.println(icon);
        return new ObjectOfInterest(place.getName(), place.getLocation().getLat(),
                place.getLocation().getLng(), 0F, icon, place.getId());
    }

    private static String extractIcon(Category[] categories) {
        String icon = "";
        for (Category category : categories) {
            if (category.getPrimary()) {
                Icon icon1 = gson.fromJson(category.getIcon(), Icon.class);
                icon = icon1.getPrefix() + "bg_32" + icon1.getSuffix();
                break;
            }
        }
        return icon;
    }
}
