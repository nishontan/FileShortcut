package com.nagopy.android.fileshortcut

import timber.log.Timber

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
//        val instagramUrlRegex = "/(https?:\\/\\/www\\.)?instagram\\.com(\\/p\\/\\w+\\/?)/"
//        return regexUrlMatcher(url, instagramUrlRegex)
        return url.contains("instagram");
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
    fun getThumbnailUrl(url: String): String {
        var imageUrl: String = ""
        if (isValidInstagramUrl(url)) {
            val postId = getInstagramPostIdFromUrl(url)
            imageUrl = "https://www.instagram.com/p/%s/media/?size=t".format(postId)
        } else if (isValidYoutubeUrl(url)) {
            val videoId = getVideoIdFromUrl(url)
            imageUrl = "https://img.youtube.com/vi/%s/default.jpg".format(videoId)
        }

        return imageUrl
    }

    /**
     * https://nono.ma/says/get-an-instagram-image-url
     * parsing https://www.instagram.com/p/Bva8L69gjAx/?utm_source=ig_share_sheet&igshid=e7zwwsm4b9rj for Bva8L69gjAx
     */
    private fun getInstagramPostIdFromUrl(url: String): String {
        var id = url.split("/")[4]
        Timber.i(id)
        return id
    }

}