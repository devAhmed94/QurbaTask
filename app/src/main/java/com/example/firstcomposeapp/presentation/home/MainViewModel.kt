package com.example.firstcomposeapp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.data.network.RestClient
import com.example.firstcomposeapp.domain.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 14/03/2022
 */
class MainViewModel : ViewModel() {

  var loading = mutableStateOf(true)

  private val _resHome: MutableState<List<Post?>?> = mutableStateOf(null)
  var resHome = _resHome

  init {
    viewModelScope.launch {
      delay(2000)
      //  getHome()
      dummyPosts()
      loading.value = false
    }
  }

  /**
   * we should call this fun if we work with backend
   * */

  private fun getHome() {
    viewModelScope.launch(Dispatchers.IO) {
      _resHome.value = RestClient.api.getHome().body()
    }
  }

  /**
   * dummy data for posts
   * */
  fun dummyPosts() =
    mutableListOf<Post>().apply {
      add(
        Post(
          imgProfile = R.drawable.ic_image,
          nameProfile = "Chicken Chester",
          logoName = R.drawable.ic_image,
          time = "2 days ago",
          desRecipe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eros est, blandit eu " +
              "nunc sit amet",
          listImagesPost = listOf(R.drawable.res_img),
          logoImg = R.drawable.ic_image,
          nameRes = "Chicken Chester",
          desRes = "Cafe & Restaurant",
          btnMenu = true,
          likes = "223K",
          comments = "20",
          share = "50K",
          comment = Post.Comment(
            R.drawable.ic_image,
            "Jaxson Schleifer",
            content = "Lorem ipsum ",
            time = "1h",
            likes = 2
          ),
          shareBlock = null
        )
      )


      add(
        Post(
          imgProfile = R.drawable.ic_image,
          nameProfile = "Chicken Chester",
          logoName = R.drawable.ic_image,
          time = "Verified Buyer  •  2 days ago",
          desRecipe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eros est, blandit eu " +
              "nunc sit amet",
          listImagesPost = listOf(
            R.drawable.res_img,
            R.drawable.res_img,
            R.drawable.res_img,
            R.drawable.res_img
          ),
          logoImg = R.drawable.ic_image,
          nameRes = "Chicken MACDO, Carmel Sandae, Fri  Carmel Sandae",
          desRes = "Cafe & Restaurant",
          btnMenu = false,
          likes = "223K",
          comments = "20",
          share = "50K",
          comment = null,
          shareBlock = null
        )
      )


      add(
        Post(
          imgProfile = R.drawable.ic_image,
          nameProfile = "Chicken Chester",
          logoName = R.drawable.ic_image,
          time = "Verified Buyer  •  2 days ago",
          desRecipe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eros est, blandit eu " +
              "nunc sit amet",
          listImagesPost = listOf(R.drawable.res_img),
          logoImg = R.drawable.ic_image,
          nameRes = "Chicken MACDO, Carmel Sandae, Fri  Carmel Sandae",
          desRes = "Cafe & Restaurant",
          btnMenu = false,
          likes = "223K",
          comments = "20",
          share = "50K",
          comment = null,
          shareBlock = Post.ShareBlock(
            fromName = "Skylarani  Arcand",
            toName = "Chicken Chester Chester",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eros est, blandit",
            time = "1 sec ago"
          )
        )
      )

    }
}