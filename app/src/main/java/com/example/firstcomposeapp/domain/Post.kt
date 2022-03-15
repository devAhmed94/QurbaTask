package com.example.firstcomposeapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 14/03/2022
 */
@Parcelize
data class Post(
  @SerializedName("imgProfile")
  val imgProfile: Int,
  @SerializedName("nameProfile")
  val nameProfile: String,
  @SerializedName("logoName")
  val logoName: Int,
  @SerializedName("time")
  val time: String,
  @SerializedName("desRecipe")
  val desRecipe: String,
  @SerializedName("listImagesPost")
  val listImagesPost: List<Int>,
  @SerializedName("logoImg")
  val logoImg: Int,
  @SerializedName("nameRes")
  val nameRes: String,
  @SerializedName("desRes")
  val desRes: String,
  @SerializedName("btnMenu")
  val btnMenu: Boolean,
  @SerializedName("likes")
  val likes: String,
  @SerializedName("comments")
  val comments: String,
  @SerializedName("share")
  val share: String,
  @SerializedName("comment")
  val comment: Comment?,
  @SerializedName("share_block")
  val shareBlock: ShareBlock?
) : Parcelable {

  @Parcelize
  data class Comment(
    @SerializedName("image")
    val image: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("likes")
    val likes: Int
  ) : Parcelable

  @Parcelize
  data class ShareBlock(
    @SerializedName("fromName")
    val fromName: String,
    @SerializedName("toName")
    val toName: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("time")
    val time: String,
  ) : Parcelable
}
