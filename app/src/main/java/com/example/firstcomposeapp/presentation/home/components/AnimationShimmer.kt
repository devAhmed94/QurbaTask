package com.example.firstcomposeapp.uitle.customStuff

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeapp.R

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/03/2022
 */
@Composable
fun AnimationShimmer() {

  val animationColors = listOf(
    Color.LightGray.copy(alpha = 0.8f),
    Color.LightGray.copy(alpha = 0.3f),
    Color.LightGray.copy(alpha = 0.8f),

    )

  val transition = rememberInfiniteTransition()
  val translateAnim = transition.animateFloat(
    initialValue = 0f,
    targetValue = 1000f,
    animationSpec = infiniteRepeatable(
      animation = tween(
        durationMillis = 500,
        easing = FastOutSlowInEasing
      )
    )
  )

  val brush = Brush.linearGradient(
    colors = animationColors,
    start = Offset.Zero,
    end = Offset(x = translateAnim.value, y = translateAnim.value)
  )

  ShimmerGridItem(brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .padding(top = 11.dp, bottom = 1.dp, start = 15.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically

    ) {
      Spacer(
        modifier = Modifier
          .padding(10.dp)
          .size(80.dp)
          .clip(CircleShape)
          .background(brush)
      )

      Spacer(modifier = Modifier.padding(10.dp))

      Column(verticalArrangement = Arrangement.Center) {
        Spacer(
          modifier = Modifier
            .height(15.dp)
            .fillMaxWidth(fraction = .7f)
            .background(brush, shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Spacer(
          modifier = Modifier
            .height(12.dp)
            .fillMaxWidth(fraction = .4f)
            .background(brush, shape = RoundedCornerShape(20.dp))
        )
      }
    }

    Spacer(
      modifier = Modifier
        .padding(top = 6.dp)
        .fillMaxWidth()
        .height(190.dp)
        .background(brush)
    )

    Row(
      modifier = Modifier
        .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(
        modifier = Modifier
          .height(15.dp)
          .width(65.dp)
          .background(brush, shape = RoundedCornerShape(20.dp))
      )

      Spacer(
        modifier = Modifier
          .height(15.dp)
          .width(65.dp)
          .background(brush, shape = RoundedCornerShape(20.dp))
      )

      Spacer(
        modifier = Modifier
          .height(15.dp)
          .width(65.dp)
          .background(brush, shape = RoundedCornerShape(20.dp))
      )
    }


    Divider(
      thickness = 3.dp,
      color = colorResource(id = R.color.culture),
    )

    Row(
      modifier = Modifier
        .padding(top = 11.dp, bottom = 8.dp, start = 15.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically

    ) {
      Spacer(
        modifier = Modifier
          .padding(10.dp)
          .size(80.dp)
          .clip(CircleShape)
          .background(brush)
      )

      Spacer(modifier = Modifier.padding(10.dp))

      Column(verticalArrangement = Arrangement.Center) {
        Spacer(
          modifier = Modifier
            .height(15.dp)
            .fillMaxWidth(fraction = .7f)
            .background(brush, shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Spacer(
          modifier = Modifier
            .height(12.dp)
            .fillMaxWidth(fraction = .4f)
            .background(brush, shape = RoundedCornerShape(20.dp))
        )
      }
    }


    Spacer(
      modifier = Modifier
        .padding(start = 15.dp, end = 15.dp)
        .fillMaxWidth()
        .height(15.dp)
        .background(brush, shape = RoundedCornerShape(20.dp))
    )
    Spacer(modifier = Modifier.padding(6.dp))
    Spacer(
      modifier = Modifier
        .padding(start = 15.dp)
        .height(12.dp)
        .fillMaxWidth(fraction = .7f)
        .background(brush, shape = RoundedCornerShape(20.dp))
    )

    Spacer(
      modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth()
        .height(190.dp)
        .background(brush)
    )

    Row(
      modifier = Modifier
        .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(
        modifier = Modifier
          .height(15.dp)
          .width(65.dp)
          .background(brush, shape = RoundedCornerShape(20.dp))
      )

      Spacer(
        modifier = Modifier
          .height(15.dp)
          .width(65.dp)
          .background(brush, shape = RoundedCornerShape(20.dp))
      )

      Spacer(
        modifier = Modifier
          .height(15.dp)
          .width(65.dp)
          .background(brush, shape = RoundedCornerShape(20.dp))
      )
    }

  }
}

@Composable
@Preview(showBackground = true)
fun PrevShimmer() {
  ShimmerGridItem(
    brush = Brush.linearGradient(
      listOf(
        Color.LightGray.copy(alpha = 0.8f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.8f),

        )
    )
  )
}