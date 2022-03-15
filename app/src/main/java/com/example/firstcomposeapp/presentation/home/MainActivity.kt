package com.example.firstcomposeapp.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.presentation.home.components.CustomBottomNavigation
import com.example.firstcomposeapp.presentation.home.components.PostCart
import com.example.firstcomposeapp.presentation.home.components.Screen
import com.example.firstcomposeapp.uitle.customStuff.AnimationShimmer
import com.example.firstcomposeapp.uitle.theme.FirstComposeAppTheme

class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MainViewModel>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val currentScreen = mutableStateOf<Screen>(Screen.Home)

      FirstComposeAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
          Scaffold(
            topBar = { TopAppBar() },
            bottomBar = { BottomBar(currentScreen = currentScreen) }
          ) {
            Column(
              Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
              Row(
                modifier = Modifier
                  .padding(start = 16.dp, end = 16.dp, top = 15.dp, bottom = 8.dp)
                  .align(Alignment.CenterHorizontally)
              ) {

                SetYourImage()
                SetYourExperience()

              }

              Divider(
                thickness = 3.dp,
                color = colorResource(id = R.color.culture),
              )

              val loading: Boolean = viewModel.loading.value
              val posts = viewModel.dummyPosts()
              if (loading) {
                AnimationShimmer()
              } else {
                LazyColumn(contentPadding = PaddingValues(bottom = 80.dp)) {
                  itemsIndexed(
                    items = posts
                  ) { _, post ->
                    PostCart(post = post, onClick = {})
                  }

                }
              }

            }

          }
        }
      }
    }
  }

  @Composable
  fun TopAppBar() {

    TopAppBar(
      title = {},
      Modifier.shadow(elevation = 7.dp, shape = RoundedCornerShape(bottomStart = 7.dp, bottomEnd = 7.dp)),
      backgroundColor = Color.White,
      navigationIcon = {
        Image(
          painterResource(id = R.drawable.ic_logo),
          contentDescription = "logo",
          Modifier.padding(start = 10.dp)
        )

      },
      actions = {

        Image(
          painterResource(id = R.drawable.ic_search),
          contentDescription = "search",
          Modifier.padding(end = 28.dp)

        )

        Box(Modifier.padding(end = 28.dp)) {
          Image(
            painterResource(id = R.drawable.ic_notification),
            contentDescription = "logo",
          )
          Image(
            painterResource(id = R.drawable.ic_small_circle_notify),
            contentDescription = "logo",
            Modifier.align(alignment = Alignment.BottomEnd)
          )
        }

        Box(Modifier.padding(end = 15.dp)) {
          Image(
            painterResource(id = R.drawable.ic_cart),
            contentDescription = "logo"
          )

          Image(
            painterResource(id = R.drawable.ic_small_circle_notify),
            contentDescription = "logo",
            Modifier.align(alignment = Alignment.TopEnd)
          )
        }

      },

      )
  }

  @Composable
  fun BottomBar(currentScreen: MutableState<Screen>) {
    CustomBottomNavigation(screenCurrentId = currentScreen.value.id) {
      currentScreen.value = it
    }
  }

  @Composable
  fun SetYourImage() {
    Image(
      painterResource(id = R.drawable.ic_image),
      contentDescription = null,
      Modifier
        .width(60.dp)
        .height(60.dp)
        .padding(end = 12.dp, bottom = 10.dp),
      alignment = Alignment.Center,
    )
  }

  @Composable
  fun SetYourExperience() {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    val padding = 1.dp
    val density = LocalDensity.current
    Surface(
      shape = RoundedCornerShape(25.dp),
      color = Color.White,
      elevation = 2.dp,
      modifier = Modifier
        .background(color = Color.Transparent)
        .padding(padding)
        .drawWithContent {
          val paddingPx = with(density) { padding.toPx() }
          clipRect(
            left = -paddingPx,
            top = -paddingPx,
            right = size.width + paddingPx,
            bottom = size.height + paddingPx
          ) {
            this@drawWithContent.drawContent()
          }
        }
    ) {
      TextField(
        modifier = Modifier
          .height(48.dp),
        value = inputValue.value,
        onValueChange = {
          inputValue.value = it
        },
        placeholder = {
          Text(
            text = stringResource(R.string.main_tf_share),
            fontSize = 15.sp, color = Color(R.color.hintColor)
          )
        },
        colors = TextFieldDefaults.textFieldColors(
          backgroundColor = Color.Transparent,
          disabledIndicatorColor = Color.Transparent,
          focusedIndicatorColor = Color.Transparent,
          disabledTextColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 12.sp)
      )

    }
  }
}

