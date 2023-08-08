package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.approvaltests.combinations.CombinationApprovals;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQualituyTest() {
        CombinationApprovals.verifyAllCombinations(
            this::doUpdateQualituy,
            new String[]{"foo", "+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert", "Backstage passes to a TAFKAL80ETC concert", "Backstage passes to a TAFKAL80ETC concert",
                "Backstage passes to a TAFKAL80ETC concert", "test item 001", "Backstage passes to a TAFKAL80ETC concert", "Aged Brie",
                "Sulfuras, Hand of Ragnaros", "Conjured Mana Cake"},
            new Integer[]{0, 10, 2, 5, 0, -1, 15, 10, 5, 5, -1, -1, -1, 10, 3},
            new Integer[]{0, 20, 0, 7, 80, 20, 49, 49, 20, 2, 10, 0, 1, 6});
    }

    private String doUpdateQualituy(String name, int sellIn, int quality) {
        Item[] items = new Item[] {new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0].toString();
    }

}
