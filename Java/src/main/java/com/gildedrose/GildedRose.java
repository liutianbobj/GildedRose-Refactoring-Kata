package com.gildedrose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GildedRose {
    Item[] items;

    public interface UpdateQuality {
        void updateQuality(Item item);
    }

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            doUpdateQuality(item);
        }
    }

    private void doUpdateQuality(Item item) {
        if (isAgedBrie(item)) {
            updateAgedBrie(item);
        } else if (isBackstagePasses(item)){
            updateBackstagePasses(item);
        } else if (isSulfuras(item)){
            updateSulfuras(item);
        } else if (isConjured(item)) {
            updateConjured(item);
        } else {
            updateGeneral(item);
        }
    }

    private boolean isConjured(Item item) {
        return isPatternMatch(item.name, "^Conjured.*");
    }

    private boolean isSulfuras(Item item) {
        return isPatternMatch(item.name, "^Sulfuras.*");
    }

    private boolean isBackstagePasses(Item item) {
        return isPatternMatch(item.name, "^Backstage passes.*");
    }

    private boolean isAgedBrie(Item item) {
        return isPatternMatch(item.name, "Aged Brie");
    }

    private boolean isPatternMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private void updateGeneral(Item item) {
        decreaseQuality(item, 1);

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            decreaseQuality(item, 1);
        }
    }

    private void updateConjured(Item item) {
        decreaseQuality(item, 2);

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            decreaseQuality(item, 2);
        }
        item.quality = Math.max(0, item.quality);
    }

    private void updateSulfuras(Item item) {
    }

    private void updateBackstagePasses(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                increaseQuality(item, 1);
            }

            if (item.sellIn < 6) {
                increaseQuality(item, 1);
            }
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item, 1);

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    private static void increaseQuality(Item item, int value) {
        if (item.quality < 50) {
            item.quality = item.quality + value;
        }
    }

    private void decreaseQuality(Item item, int value) {
        if (item.quality > 0) {
            item.quality = item.quality - value;
        }
    }
}
