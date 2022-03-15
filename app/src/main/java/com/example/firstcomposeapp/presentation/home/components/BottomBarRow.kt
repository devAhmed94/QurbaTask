package com.example.firstcomposeapp.presentation.home.components

import com.example.firstcomposeapp.R

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/03/2022
 */
sealed class BottomBarRow(
  val id: String,
  val iconAsInt: Int,
  val iconWhiteAsInt: Int
) {

  object Home : BottomBarRow("home", R.drawable.ic_home, R.drawable.ic_home_white)
  object Restaurants : BottomBarRow("restaurants", R.drawable.ic_resturants, R.drawable.ic_resturants_white)
  object Setting : BottomBarRow("setting", R.drawable.ic_setting, R.drawable.ic_setting_white)
  object Friends : BottomBarRow("friends", R.drawable.ic_friend, R.drawable.ic_friend_white)
  object Profile : BottomBarRow("profile", R.drawable.ic_profile, R.drawable.ic_profile_white)

  object Items {

    val list = listOf(
      Home, Restaurants, Setting, Friends, Profile
    )
  }
}
