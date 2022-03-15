package com.example.firstcomposeapp.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.presentation.home.components.BottomBarRow
import com.example.firstcomposeapp.presentation.home.components.CustomBottomNavigation
import com.example.firstcomposeapp.presentation.home.components.PostCart
import com.example.firstcomposeapp.presentation.home.components.Screens
import com.example.firstcomposeapp.uitle.customStuff.AnimationShimmer
import com.example.firstcomposeapp.uitle.theme.FirstComposeAppTheme
import com.example.firstcomposeapp.uitle.theme.Purple700
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MainViewModel>()
  private val currentScreen = mutableStateOf<BottomBarRow>(BottomBarRow.Home)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FirstComposeAppTheme {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
      }
    }
  }

  @Composable
  fun SetupNavGraph(navController: NavHostController) {
    NavHost(
      navController = navController,
      startDestination = Screens.Splash.route
    ) {
      composable(route = Screens.Splash.route) {
        AnimatedSplashScreen(navController = navController)
      }
      composable(route = Screens.Home.route) {
        HomeScreen()
      }
    }
  }

  @Composable
  fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
      targetValue = if (startAnimation) 1f else 0f,
      animationSpec = tween(
        durationMillis = 3000
      )
    )

    LaunchedEffect(key1 = true) {
      startAnimation = true
      delay(4000)
      navController.popBackStack()
      navController.navigate(Screens.Home.route)
    }
    Splash(alpha = alphaAnim.value)
  }

  @Composable
  fun Splash(alpha: Float) {
    Box(
      modifier = Modifier
        .background(if (isSystemInDarkTheme()) Color.Black else Purple700)
        .fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        modifier = Modifier
          .size(120.dp)
          .alpha(alpha = alpha),
        imageVector = Icons.Default.Email,
        contentDescription = "Logo Icon",
        tint = Color.White
      )
    }
  }

  @Composable
  fun HomeScreen() {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
      Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomBar(currentBottomBarRow = currentScreen) }
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

  @Composable
  fun TopAppBar() {
    val context = LocalContext.current
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
            .clickable(onClick = {
            Toast
              .makeText(context, "search", Toast.LENGTH_SHORT)
              .show()
          })

        )

        Box(Modifier.padding(end = 28.dp)) {
          Image(
            painterResource(id = R.drawable.ic_notification),
            contentDescription = "logo",
            Modifier.clickable(onClick = {
              Toast
                .makeText(context, "notification", Toast.LENGTH_SHORT)
                .show()
            })
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
            contentDescription = "logo",
            Modifier.clickable(onClick = {
              Toast
                .makeText(context, "cart", Toast.LENGTH_SHORT)
                .show()
            })
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
  fun BottomBar(currentBottomBarRow: MutableState<BottomBarRow>) {
    CustomBottomNavigation(screenCurrentId = currentBottomBarRow.value.id) {
      currentBottomBarRow.value = it
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

