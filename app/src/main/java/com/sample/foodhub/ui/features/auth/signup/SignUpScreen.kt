package com.sample.foodhub.ui.features.auth.signup

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.foodhub.R
import com.sample.foodhub.ui.FoodHubOutlinedTextField
import com.sample.foodhub.ui.GroupSocialButtons
import com.sample.foodhub.ui.theme.LightOrange
import java.util.Locale

@Composable
fun SignUpScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_signup_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = stringResource(R.string.sign_up),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, bottom = 32.dp),
                color = Color.Black,
                fontSize = 42.sp,
                fontWeight = Bold
            )

            FoodHubOutlinedTextField(
                value = "codeWithUmar",
                onValueChange = {},
                label = {
                    Text(
                        text = stringResource(R.string.full_name),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = null
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
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    // You can add an icon here for toggling password visibility
                    Image(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Toggle Password Visibility",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Button(
                onClick = {}, modifier = Modifier
                    .size(248.dp, 60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(LightOrange.value)) // Orange color
            )
            {
                Text(text = stringResource(R.string.sign_up).toUpperCase(Locale.ROOT))
            }

            TextButton(onClick = {}, modifier = Modifier.padding(bottom = 30.dp)) {
                Text(text = stringResource(R.string.already_have_account_sign_in))
            }
        }

        Column(
            modifier = Modifier
                .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.BottomCenter)
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
fun SignUpScreenPreview() {
    SignUpScreen()
}