package com.example.playingwithmotionlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.playingwithmotionlayout.component.RepoItem
import com.example.playingwithmotionlayout.component.colors
import com.example.playingwithmotionlayout.component.repos
import com.example.playingwithmotionlayout.ui.theme.PlayingWithMotionLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingWithMotionLayoutTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF2C0723))
                ) {
                    MotionAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionAppBar() {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    val lazyScrollState = rememberLazyListState()

    val progress by animateFloatAsState(targetValue = if(lazyScrollState.firstVisibleItemIndex in 0..4) 0f else 1f, tween(500))
    val motionHeight by animateDpAsState(targetValue = if(lazyScrollState.firstVisibleItemIndex in 0..4) 300.dp else 56.dp, tween(500))

    // handle composables within motion layout just like xml, first is always on bottom

    Column(modifier = Modifier.fillMaxSize()) {

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(motionHeight)
        ) {

            val boxProperties = motionProperties(id = "box")
            val roundedShape = RoundedCornerShape(
                bottomStart = boxProperties.value.int("roundValue").dp,
                bottomEnd = boxProperties.value.int("roundValue").dp
            )

            Box(
                modifier = Modifier
                    .layoutId("box")
                    .clip(roundedShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors,
                            endY = 350f
                        )
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.design),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(roundedShape)
                    .layoutId("box_image")
            )


            Image(
                painter = painterResource(id = R.drawable.compose_icon),
                contentDescription = null,
                modifier = Modifier
                    .layoutId("weapon_icon")
                    .background(brush = Brush.radialGradient(colors = listOf(Color.White, Color.Transparent)))
            )

            Text(
                text = "Astro",
                fontSize = 24.sp,
                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.layoutId("user_name")
            )

            Image(
                painter = painterResource(id = R.drawable.pic),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .layoutId("user_image")
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .animateContentSize(),
            state = lazyScrollState
        ) {
            itemsIndexed(repos) { index, item ->
                RepoItem(repo = item)

                if (index != repos.lastIndex)
                    Divider(modifier = Modifier.padding(8.dp))

            }
        }
    }
}