package com.sample.foodhub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.foodhub.R

@Composable
fun GroupSocialButtons(onFaceBookClick: () -> Unit, onGoogleClick: () -> Unit) {
    // Implementation for social buttons like Google, Facebook, etc.
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
                color = Color.Gray,
                thickness = 1.dp
            )

            Text(
                text = stringResource(R.string.sign_in_with),
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                color = Color.Gray,
                thickness = 1.dp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
                .padding(start = 14.dp, end = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SocialButtons(
                icon = R.drawable.ic_facebook,
                title = R.string.continue_with_fb,
                onClick = onFaceBookClick
            )
            SocialButtons(
                icon = R.drawable.ic_google,
                title = R.string.continue_with_google,
                onClick = onGoogleClick
            )
        }
    }
}

@Composable
fun SocialButtons(icon: Int, title: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = buttonColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .size(height = 50.dp, width = 160.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = stringResource(title),
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}


@Preview(showBackground = false)
@Composable
fun GroupSocialButtonsPreview() {
    GroupSocialButtons(onFaceBookClick = {}, onGoogleClick = {})
}