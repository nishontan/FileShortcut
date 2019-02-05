package com.nagopy.android.fileshortcut

object WebUrlUtils {


    /*
    * https://www.regextester.com/94360
    */
    @JvmStatic
    fun isValidYoutubeUrl(url: String): Boolean {
        val youtubeRegex = "^(http(s)?://)?((w){3}.)?youtu(be|.be)?(\\.com)?/.+";
        val regex = Regex(pattern = youtubeRegex)
        val matched = regex.containsMatchIn(input = url)
        return matched;
    }

}