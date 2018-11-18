package org.waes.differ.utils.Helpers;

import org.apache.commons.lang3.RandomUtils;

public class Common {

    public static int getRandomDigit() {
        return RandomUtils.nextInt(100, 100000);
    }

}
