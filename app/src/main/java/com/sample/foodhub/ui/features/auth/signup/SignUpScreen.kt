package com.sample.foodhub.ui.features.auth.signup

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.foodhub.R
import com.sample.foodhub.ui.FoodHubOutlinedTextField
import com.sample.foodhub.ui.GroupSocialButtons
import com.sample.foodhub.ui.theme.LightOrange
import java.util.Locale

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel<SignUpViewModel>()) {

    val name = viewModel.name.collectAsStateWithLifecycle()
    val email = viewModel.email.collectAsStateWithLifecycle()
    val password = viewModel.password.collectAsStateWithLifecycle()

    val errorMessage = remember { mutableStateOf<String?>("") }
    val loading = remember { mutableStateOf(false) }
    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value) {
        is SignUpViewModel.SignUpUiEvent.Success -> {
            // Navigate to the next screen or show success message
            loading.value = false
            errorMessage.value = null
        }

        is SignUpViewModel.SignUpUiEvent.Error -> {
            // Show error message to the user, e.g., using a Snackbar or Toast
            errorMessage.value = (uiState.value as SignUpViewModel.SignUpUiEvent.Error).message
            loading.value = false
        }

        is SignUpViewModel.SignUpUiEvent.Loading -> {
            loading.value = true
            errorMessage.value = null
        }

        is SignUpViewModel.SignUpUiEvent.Idle -> {
            // TODO()
        }
    }


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
                value = name.value,
                onValueChange = { viewModel.setName(it) },
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
                singleLine = true
            )

            FoodHubOutlinedTextField(
                value = email.value,
                onValueChange = { viewModel.setEmail(it) },
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
                singleLine = true
            )

            FoodHubOutlinedTextField(
                value = password.value,
                onValueChange = { viewModel.setPassword(it) },
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
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.size(24.dp))

            Button(
                onClick = { viewModel.onSignUpClick() },
                modifier = Modifier.size(248.dp, 60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(LightOrange.value))
            )
            {
                Box {
                    AnimatedContent(targetState = loading.value) {
                        if (it) {
                            androidx.compose.material3.CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.Center)
                            )
                        } else {
                            Text(text = stringResource(R.string.sign_up).toUpperCase(Locale.ROOT))
                        }
                    }
                }
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