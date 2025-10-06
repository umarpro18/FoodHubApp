package com.sample.foodhub.ui.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.foodhub.R
import com.sample.foodhub.ui.FoodHubOutlinedTextField
import com.sample.foodhub.ui.theme.LightOrange

@Composable
fun PhoneRegistrationScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_signup_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier.padding(
                start = 24.dp,
                top = 180.dp,
                bottom = 24.dp,
                end = 24.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.phone_registration),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.phone_registration_detail),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, end = 80.dp),
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )

            FoodHubOutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
            )

            Button(
                onClick = {}, modifier = Modifier
                    .padding(top = 50.dp)
                    .size(240.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(LightOrange.value))
            ) {
                Text(
                    text = stringResource(R.string.send_otp),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PhoneRegistrationScreenPreview() {
    PhoneRegistrationScreen()
}