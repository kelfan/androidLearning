package com.kelfan.kotlineditor.util

import android.graphics.Color

/**
 * Created by Administrator on 21/01/2018.
 */
class ColorWorker {
    companion object{
        val YELLOW_DEEP = Color.rgb(247, 209, 106)
        val YELLOW_VERY_LIGHT = Color.parseColor("#D8D8BF")
        val RED_VERY_LIGHT = Color.parseColor("#D8BFD8")
        val BLUE_SEA = Color.parseColor("#70DB93")
        val ORANGE = Color.parseColor("#FF2400")
        val GREY_LIGHT = Color.parseColor("#D9D9F3")
        val SILVER_LIGHT = Color.parseColor("#E6E8FA")
        val CHOCOLATE = Color.parseColor("#5C3317")
        val PURPLE_BLUE = Color.parseColor("#FF2400")
        val BROWN = Color.parseColor("#A67D3D")
        val BROWN_COLD = Color.parseColor("#D98719")
        val RED_CORAL = Color.parseColor("#FF7F00")
        val BLUE_PURPLE = Color.parseColor("#42426F")
        val BROWN_DEEP = Color.parseColor("#5C4033")
        val PURPLE_DEEP = Color.parseColor("#9932CD")
        val PURPLE_DARK = Color.parseColor("#6B238E")
        val BLUE_LIGHT = Color.parseColor("#7093DB")
        val BROWN_WOOD = Color.parseColor("#855E42")
        val RED_DEEP = Color.parseColor("#8E2323")
        val GREEN_FOREST = Color.parseColor("#238E23")
        val GOLD = Color.parseColor("#CD7F32")
        val YELLOW_LIGHT = Color.parseColor("#DBDB70")
        val GREEN_COPPER = Color.parseColor("#527F76")
        val GREEN_HUNTER = Color.parseColor("#215E21")
        val PURPLE_LIGHT = Color.parseColor("#8F8FBD")
        val BLUE_VERY_LIGHT = Color.parseColor("#C0D9D9")
        val ORANGE_LIGHT = Color.parseColor("#E47833")
        val GREEN_GRASS = Color.parseColor("#7FFF00")
        val BLUE_DEEP = Color.parseColor("#00009C")
        val BLUE = Color.parseColor("#38B0DE")
        val BLUE_SKY = Color.parseColor("#3299CC")
        val RED_CRIMSON = Color.parseColor("#BC1717")
        val SALMON = Color.parseColor("#6F4242")
        val GREEN_SEA = Color.parseColor("#238E68")

        /**
         * Returns the complimentary (opposite) color.
         * @param color int RGB color to return the compliment of
         * @return int RGB of compliment color
         */
        fun getComplimentColor(color: Int): Int {
            // get existing colors
            val alpha = Color.alpha(color)
            var red = Color.red(color)
            var blue = Color.blue(color)
            var green = Color.green(color)

            // find compliments
            red = red.inv() and 0xff
            blue = blue.inv() and 0xff
            green = green.inv() and 0xff

            return Color.argb(alpha, red, green, blue)
        }
    }

}
