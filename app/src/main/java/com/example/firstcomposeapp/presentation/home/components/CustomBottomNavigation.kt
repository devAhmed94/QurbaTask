package com.example.firstcomposeapp.presentation.home.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeapp.R

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/03/2022
 */
@Composable
fun CustomBottomNavigation(
  screenCurrentId: String,
  onItemSelected: (Screen) -> Unit
) {

  val items = Screen.Items.list

  Row(
    modifier = Modifier
      .shadow(elevation = 30.dp, shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
      .background(MaterialTheme.colors.background)
      .padding(8.dp)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically
  ) {

    items.forEach { item ->
      CustomBottomNavigationItem(item = item, isSelected = item.id == screenCurrentId) {
        onItemSelected(item)
      }
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomBottomNavigationItem(
  item: Screen,
  isSelected: Boolean,
  onClick: () -> Unit
) {

  Box(
    modifier = Modifier
      .padding(10.dp)
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center
  ) {
    if (isSelected) {
      Image(
        painterResource(id = R.drawable.ic_background_item_nav),
        contentDescription = null
      )

      Image(
        painterResource(id = item.iconWhiteAsInt),
        contentDescription = null,
        Modifier.padding(bottom = 5.dp)
      )
    } else {
      Image(
        painterResource(id = item.iconAsInt),
        contentDescription = null,
        Modifier.padding(bottom = 5.dp)
      )
    }

  }
}

@Composable
@Preview
fun Prev1() {
  CustomBottomNavigation(screenCurrentId = Screen.Home.id) {

  }
}

@Composable
@Preview
fun Prev2() {
  CustomBottomNavigationItem(item = Screen.Home, isSelected = true) {

  }
}