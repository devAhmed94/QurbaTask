package com.example.firstcomposeapp.presentation.home.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.domain.Post

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 15/03/2022
 */

@Composable
fun PostCart(
  post: Post,
  onClick: () -> Unit
) {
  val context = LocalContext.current

  Column(Modifier.padding(top = 12.dp)) {

    if (post.shareBlock != null) {

      val annotatedString = buildAnnotatedString {
        append(post.shareBlock.fromName)
        appendInlineContent(id = "imageId")
        append(post.shareBlock.toName)
      }
      val inlineContentMap = mapOf(
        "imageId" to InlineTextContent(
          Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
        ) {
          Image(
            painterResource(id = R.drawable.ic_arrow),
            modifier = Modifier.fillMaxSize(),
            contentDescription = ""
          )
        }
      )


      Row(
        Modifier
          .padding(start = 16.dp, end = 16.dp)

      ) {
        Image(
          painterResource(id = post.imgProfile),
          contentDescription = null,
          Modifier
            .padding(end = 8.dp)
            .width(45.dp)
            .height(45.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
          Row(horizontalArrangement = Arrangement.SpaceAround) {
            Text(
              annotatedString,
              inlineContent = inlineContentMap,
              color = Color(R.color.hintColor),
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier
                .weight(1f)
            )

            Image(
              painterResource(id = R.drawable.ic_policy),
              contentDescription = null,
              modifier = Modifier
                .padding(top = 10.dp)
                .wrapContentSize()
                .clickable(onClick = {
                  Toast
                    .makeText(context, "setting", Toast.LENGTH_SHORT)
                    .show()
                }),
            )
          }

          Text(
            text = post.shareBlock.time,
            fontSize = 14.sp,
            color = Color(R.color.geyser)
          )

        }
      }

      Text(
        text = post.shareBlock.content,
        color = Color(R.color.hintColor),
        fontSize = 14.sp,
        modifier = Modifier
          .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
          .fillMaxWidth()
      )

      Column(
        Modifier
          .padding(horizontal = 16.dp, vertical = 9.dp)
          .fillMaxWidth()
          .border(
            border = BorderStroke(1.dp, Color(R.color.culture).copy(alpha = .2f)),
            shape = RoundedCornerShape(20.dp)
          )
      ) {
        Row(
          Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth()
        ) {
          Image(
            painterResource(id = post.imgProfile),
            contentDescription = null,
            Modifier
              .padding(end = 8.dp)
              .width(45.dp)
              .height(45.dp)
          )
          Column {
            Row {
              Text(
                text = post.nameProfile,
                color = Color(R.color.hintColor),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
              Image(
                painterResource(id = post.logoName),
                contentDescription = null,
                Modifier
                  .padding(start = 8.dp, top = 5.dp)
                  .width(20.dp)
                  .height(20.dp)
              )
            }

            Text(
              text = post.time,
              fontSize = 14.sp,
              color = Color(R.color.geyser)
            )

          }

        }

        Text(
          text = post.desRecipe,
          color = Color(R.color.hintColor),
          fontSize = 14.sp,
          modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
        )

        if (post.listImagesPost.isNotEmpty()) {
          if (post.listImagesPost.size == 1) {
            Image(
              painterResource(id = post.listImagesPost[0]),
              contentDescription = null,
              Modifier
                .fillMaxWidth()
                .height(200.dp),
              contentScale = ContentScale.Crop
            )
          } else {
            StaggeredVerticalGrid(
              numColumns = 2,
              modifier = Modifier.padding(5.dp)
            ) {
              post.listImagesPost.forEach { img ->
                Image(
                  painterResource(id = img),
                  contentDescription = null,
                  Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                  contentScale = ContentScale.Crop
                )

              }
            }
          }
        }





        Row(Modifier.padding(top = 13.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)) {
          Image(
            painterResource(id = post.logoImg),
            contentDescription = null,
            Modifier
              .padding(end = 8.dp)
              .width(45.dp)
              .height(45.dp)
          )
          Column {
            Text(
              text = post.nameRes,
              color = Color(R.color.hintColor),
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis

            )
            Text(
              text = post.desRes,
              fontSize = 14.sp,
              color = Color(R.color.geyser)
            )

          }

        }

      }

      Row(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = post.likes,
            Modifier.padding(end = 4.dp),
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
          )
          Image(painterResource(id = R.drawable.ic_like), contentDescription = null)
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = post.comments,
            Modifier.padding(end = 4.dp),
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
          )
          Image(painterResource(id = R.drawable.ic_reply), contentDescription = null)
        }



        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = post.share,
            Modifier.padding(end = 4.dp),
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
          )
          Image(painterResource(id = R.drawable.ic_share), contentDescription = null)
        }
      }

      Divider(
        thickness = 3.dp,
        color = colorResource(id = R.color.culture),
        modifier = Modifier.padding(top = 12.dp)
      )
    } else {
      Row(
        Modifier
          .padding(start = 16.dp, end = 16.dp)
          .fillMaxWidth()
      ) {
        Image(
          painterResource(id = post.imgProfile),
          contentDescription = null,
          Modifier
            .padding(end = 8.dp)
            .width(45.dp)
            .height(45.dp)
        )
        Column {
          Row {
            Text(
              text = post.nameProfile,
              color = Color(R.color.hintColor),
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
            Image(
              painterResource(id = post.logoName),
              contentDescription = null,
              Modifier
                .padding(start = 8.dp, top = 5.dp)
                .width(20.dp)
                .height(20.dp)
            )
          }

          Text(
            text = post.time,
            fontSize = 14.sp,
            color = Color(R.color.geyser)
          )

        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
          painterResource(id = R.drawable.ic_policy),
          contentDescription = null,
          modifier = Modifier
            .padding(top = 10.dp)
            .clickable(onClick = {
              Toast
                .makeText(context, "setting", Toast.LENGTH_SHORT)
                .show()
            }),
        )
      }

      Text(
        text = post.desRecipe,
        color = Color(R.color.hintColor),
        fontSize = 14.sp,
        modifier = Modifier
          .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
          .fillMaxWidth()
      )

      if (post.listImagesPost.isNotEmpty()) {
        if (post.listImagesPost.size == 1) {
          Image(
            painterResource(id = post.listImagesPost[0]),
            contentDescription = null,
            Modifier
              .fillMaxWidth()
              .height(200.dp),
            contentScale = ContentScale.Crop
          )
        } else {
          StaggeredVerticalGrid(
            numColumns = 2,
            modifier = Modifier.padding(5.dp)
          ) {
            post.listImagesPost.forEach { img ->
              Image(
                painterResource(id = img),
                contentDescription = null,
                Modifier
                  .fillMaxWidth()
                  .padding(2.dp),
                contentScale = ContentScale.Crop
              )

            }
          }
        }
      }

      Row(Modifier.padding(top = 13.dp, start = 16.dp, end = 16.dp)) {
        Image(
          painterResource(id = post.logoImg),
          contentDescription = null,
          Modifier
            .padding(end = 8.dp)
            .width(45.dp)
            .height(45.dp)
        )
        Column {
          Text(
            text = post.nameRes,
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

          )
          Text(
            text = post.desRes,
            fontSize = 14.sp,
            color = Color(R.color.geyser)
          )

        }

      }

      if (post.btnMenu) {
        Box(
          modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = {
              Toast
                .makeText(context, "menu", Toast.LENGTH_SHORT)
                .show()
            }),
          contentAlignment = Alignment.Center,

          ) {
          Image(
            painterResource(id = R.drawable.ic_bg_btnmenu),
            contentDescription = null,
          )
          Text(
            text = stringResource(R.string.main_post_btnMenu),
            color = Color(R.color.mediumPurple),
            fontSize = 16.sp
          )

        }
      }

      Divider(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .fillMaxWidth(),
        thickness = 2.dp,
        color = colorResource(id = R.color.culture),
      )

      Row(
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = post.likes,
            Modifier.padding(end = 4.dp),
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
          )
          Image(
            painterResource(
              id = R.drawable.ic_like
            ),
            contentDescription = null,
            Modifier.clickable(onClick = {
              Toast
                .makeText(context, "like", Toast.LENGTH_SHORT)
                .show()
            })
          )
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = post.comments,
            Modifier.padding(end = 4.dp),
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
          )
          Image(
            painterResource(id = R.drawable.ic_reply),
            contentDescription = null,
            Modifier.clickable(onClick = {
              Toast
                .makeText(context, "comment", Toast.LENGTH_SHORT)
                .show()
            })
          )
        }



        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = post.share,
            Modifier.padding(end = 4.dp),
            color = Color(R.color.hintColor),
            fontSize = 16.sp,
          )
          Image(
            painterResource(id = R.drawable.ic_share),
            contentDescription = null,
            Modifier.clickable(onClick = {
              Toast
                .makeText(context, "share", Toast.LENGTH_SHORT)
                .show()
            })
          )
        }
      }



      if (post.comment != null) {

        Divider(
          modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
          thickness = 2.dp,
          color = colorResource(id = R.color.culture),
        )

        Row(
          modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        ) {

          Image(
            painterResource(id = post.comment.image),
            contentDescription = null,
            Modifier
              .padding(end = 8.dp)
              .width(45.dp)
              .height(45.dp)
          )
          Column {
            Column(
              Modifier
                .background(Color(R.color.culture).copy(.05f), shape = RoundedCornerShape(13.dp))
            ) {
              Text(
                text = post.comment.name,
                color = Color(R.color.hintColor),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 9.dp, end = 9.dp, top = 8.dp)
              )
              Text(
                text = post.comment.content,
                fontSize = 12.sp,
                color = Color(R.color.geyser),
                modifier = Modifier.padding(start = 9.dp, end = 9.dp, bottom = 8.dp)
              )
            }

            Row(modifier = Modifier.padding(top = 2.dp)) {

              Text(
                text = post.comment.time,
                fontSize = 12.sp,
                color = Color(R.color.geyser),
                modifier = Modifier.padding(start = 9.dp, end = 9.dp, bottom = 8.dp)
              )

              Text(
                text = stringResource(R.string.main_commentLike),
                fontSize = 12.sp,
                color = Color(R.color.geyser),
                modifier = Modifier
                  .padding(horizontal = 16.dp)
                  .clickable(onClick = {
                    Toast
                      .makeText(context, "comment", Toast.LENGTH_SHORT)
                      .show()
                  })
              )


              Text(
                text = stringResource(R.string.main_commentReply),
                fontSize = 12.sp,
                color = Color(R.color.geyser),
                modifier = Modifier.padding(end = 16.dp)
              )

              Text(
                text = post.comment.likes.toString(),
                fontSize = 12.sp,
                color = Color(R.color.geyser),
                modifier = Modifier.padding(end = 4.dp)
              )

              Image(painterResource(id = R.drawable.ic_comment_like), contentDescription = null)
            }

          }

        }
      }

      Divider(
        thickness = 3.dp,
        color = colorResource(id = R.color.culture),
        modifier = Modifier.padding(top = 12.dp)
      )
    }

  }
}


