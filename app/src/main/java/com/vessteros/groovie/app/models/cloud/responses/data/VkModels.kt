package com.vessteros.groovie.app.models.cloud.responses.data

import kotlin.collections.ArrayList

class VkNewsFeedList {
    data class Response(
        val response: Body
    )

    data class Body(
        val items: ArrayList<Post>,
        val groups: ArrayList<Groups>
    )

    data class Post(
        val type: String?,
        val source_id: Int?,
        val date: Int?,
        val post_type: String?,
        val text: String?,
        val marked_as_ads: Int?,
        val attachments: ArrayList<Attachment>?,
        val post_source: PostSource?,
        val comments: Comments?,
        val likes: Likes?,
        val reposts: Reposts?,
        val views: Views?,
        val post_id: Int?
    )

    data class Attachment(
        val type: String?,
        val video: Video?,
        val photo: Photo?
    )

    data class Video(
        val id: Int?
    )

    data class Photo(
        val id: Int?,
        val album_id: Int?,
        val owner_id: Int?,
        val user_id: Int?,
        val text: String?,
        val date: Int,
        val sizes: ArrayList<PhotoSize>,
        val width: Int?,
        val height: Int?
    )

    data class PhotoSize(
        val type: String?,
        val url: String?,
        val width: Int?,
        val height: Int?
    )

    data class PostSource(
        val type: String?
    )

    data class Comments(
        val count: Int?,
        val can_post: Int?
    )

    data class Likes(
        val count: Int?,
        val user_likes: Int?,
        val can_like: Int?,
        val can_publish: Int?
    )

    data class Reposts(
        val count: Int?,
        val user_reposted: Int?
    )

    data class Views(
        val count: Int?
    )

    data class Groups(
        val id: Int?,
        val name: String?,
        val screen_name: String?,
        val is_closed: Int?,
        val deactivated: String?,
        val is_admin: Int?,
        val admin_level: Int?,
        val is_member: Int?,
        val is_advertiser: Int?,
        val invited_by: Int?,
        val type: String?,
        val photo_50: String?,
        val photo_100: String?,
        val photo_200: String?
    )
}

class VkUser {
    data class Response(
        val response: ArrayList<UserList>
    )

    data class UserList(
        val id: Int?,
        val first_name: String?,
        val last_name: String?,
        val photo_400_orig: String?
    )
}