package com.sample.foodhub.ui.features.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.foodhub.R
import com.sample.foodhub.ui.GroupSocialButtons
import com.sample.foodhub.ui.theme.LightOrange

@Composable
fun AuthScreen(onEmailOrPhoneLoginClicked: () -> Unit) {

    // State to hold the size of the background image
    val getBgImageSize = remember {
        mutableStateOf(IntSize.Zero)
    }

    // Create a vertical gradient brush
    val brush: Brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black
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
                .alpha(0.7f),
            contentScale = ContentScale.FillBounds
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
                .align(Alignment.TopEnd)
                .padding(top = 26.dp, end = 26.dp),
        ) {
            Text(text = stringResource(R.string.skip), color = LightOrange)
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                .padding(start = 16.dp, top = 160.dp, end = 16.dp),
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
                .align(androidx.compose.ui.Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GroupSocialButtons(onFaceBookClick = {}, onGoogleClick = {})

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 20.dp, bottom = 8.dp)
            ) {
                Button(
                    onClick = { onEmailOrPhoneLoginClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .size(size = 50.dp),
                    colors = buttonColors(
                        containerColor = Color.LightGray.copy(alpha = 0.2f),
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Text(
                        text = stringResource(R.string.start_with_email_ph),
                        color = Color.White
                    )
                }

                TextButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.already_have_account_sign_in),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen(onEmailOrPhoneLoginClicked = {})
}