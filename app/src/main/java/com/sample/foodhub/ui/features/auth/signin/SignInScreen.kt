package com.sample.foodhub.ui.features.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.sample.foodhub.ui.FoodHubOutlinedTextField
import com.sample.foodhub.ui.GroupSocialButtons
import com.sample.foodhub.ui.theme.LightOrange
import java.util.Locale

@Composable
fun SignInScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_signup_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.FillBounds
        )

        // revisit later
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(start = 16.dp, top = 30.dp)
                .size(98.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 180.dp, bottom = 30.dp),
                color = Color.Black,
                fontSize = 42.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )

            FoodHubOutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(R.string.email),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )

            FoodHubOutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(R.string.password),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_eye),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
            )

            TextButton(onClick = {}) {
                Text(
                    text = stringResource(R.string.forget_password),
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                )
            }

            Button(
                onClick = {}, modifier = Modifier
                    .padding(top = 16.dp)
                    .size(248.dp, 60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(LightOrange.value))
            ) {
                Text(
                    text = stringResource(R.string.login).uppercase(
                        Locale.getDefault()
                    ),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                )
            }

            TextButton(onClick = {}) {
                Text(
                    text = stringResource(R.string.dont_have_account_sign_up),
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GroupSocialButtons(
                onFaceBookClick = { /*TODO*/ },
                onGoogleClick = { /*TODO*/ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}