package com.sample.foodhub.ui.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.foodhub.R
import com.sample.foodhub.ui.theme.LightOrange

@Composable
fun AuthScreen() {

    // State to hold the size of the background image
    val getBgImageSize = remember {
        mutableStateOf(IntSize.Zero)
    }

    // Create a vertical gradient brush
    val brush: Brush = Brush.verticalGradient(
        colors = listOf(
            androidx.compose.ui.graphics.Color.Transparent,
            androidx.compose.ui.graphics.Color.Black
        ),
        startY = getBgImageSize.value.height.toFloat() / 3
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background), contentDescription = null,
            modifier = Modifier
                .onGloballyPositioned() {
                    getBgImageSize.value = it.size
                }
                .alpha(0.5f),
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(brush = brush)
        )
        Button(
            onClick = { /*TODO*/ },
            colors = buttonColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.TopEnd)
                .padding(top = 26.dp, end = 26.dp),
        ) {
            Text(text = stringResource(R.string.skip), color = LightOrange)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, top = 160.dp, end = 26.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.welcome_to_foodhub),
                fontSize = 48.sp,
                fontWeight = Bold,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 40.sp,
                fontWeight = Bold,
                color = LightOrange,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.food_hub_desc),
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 200.dp)
                .fillMaxWidth(),

            ) {
            Text(
                text = stringResource(R.string.sign_in_with),
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.CenterHorizontally)
                    .padding(top = 400.dp, bottom = 16.dp)
            )
            // Draw buttons for Google and Facebook sign-in

        }

    }
}


@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}