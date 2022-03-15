package com.example.firstcomposeapp.presentation.home.components

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 15/03/2022
 */
sealed class Screens(val route: String) {

  object Splash : Screens("splash_screen")
  object Home : Screens("home_screen")
}
