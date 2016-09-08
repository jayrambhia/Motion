package com.fenchtose.motion.utils;

/**
 * Created by Jay Rambhia on 9/8/16.
 */
public class DimensionUtils {

    public static int dpToPx(int dp, float density) {
        return (int)(density * dp);
    }

}
