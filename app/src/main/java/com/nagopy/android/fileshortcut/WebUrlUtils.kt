package com.nagopy.android.fileshortcut

object WebUrlUtils {


    /*
    * https://www.regextester.com/94360
    */
    @JvmStatic
    fun isValidYoutubeUrl(url: String): Boolean {
        val youtubeUrlRegex = "^(http(s)?://)?((w){3}.)?youtu(be|.be)?(\\.com)?/.+";
        return regexUrlMatcher(url, youtubeUrlRegex)
    }

    @JvmStatic
    fun regexUrlMatcher(url: String, regexString: String): Boolean {
        val regex = Regex(pattern = regexString)
        val matched = regex.containsMatchIn(input = url)
        return matched;
    }

    @JvmStatic
    fun isValidInstagramUrl(url: String): Boolean {
        val instagramUrlRegex = "/(https?:\\/\\/www\\.)?instagram\\.com(\\/p\\/\\w+\\/?)/"
        return regexUrlMatcher(url, instagramUrlRegex)
    }

    /*
    * https://stackoverflow.com/questions/3452546/how-do-i-get-the-youtube-video-id-from-a-url
    */
    @JvmStatic
    fun getVideoIdFromUrl(url: String): String? {
        val youtubeVideoIdRegex = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val regex = Regex(pattern = youtubeVideoIdRegex)
        return regex.find(url)?.value;
    }


    @JvmStatic
    fun getThumbnailUrl(youtubeUrl: String): String {
        val videoId = getVideoIdFromUrl(youtubeUrl)
        return "https://img.youtube.com/vi/%s/default.jpg".format(videoId);
    }

}