package com.sample.foodhub.ui.features.auth.signup

import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.foodhub.R
import com.sample.foodhub.ui.FoodHubOutlinedTextField
import com.sample.foodhub.ui.GroupSocialButtons
import com.sample.foodhub.ui.theme.LightOrange
import kotlinx.coroutines.flow.collectLatest
import java.util.Locale

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onSignUpSuccess: () -> Unit,
    onSignInClicked: () -> Unit
) {

    val context = LocalContext.current
    val name = viewModel.name.collectAsStateWithLifecycle()
    val email = viewModel.email.collectAsStateWithLifecycle()
    val password = viewModel.password.collectAsStateWithLifecycle()

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = uiState.value is SignUpViewModel.SignUpUiEvent.Error
    val isLoading = uiState.value == SignUpViewModel.SignUpUiEvent.Loading

    val setName: (String) -> Unit = { viewModel.setName(it) }
    val setEmail: (String) -> Unit = { viewModel.setEmail(it) }
    val setPassword: (String) -> Unit = { viewModel.setPassword(it) }

    val signUpClicked: () -> Unit = { viewModel.onSignUpClick() }
    val gotoLoginClicked: () -> Unit = { viewModel.onGotoLoginScreenClicked() }

    LaunchedEffect(uiState.value) {
        when (val state = uiState.value) {
            is SignUpViewModel.SignUpUiEvent.Success -> {
                // Navigate to the next screen or show success message
                Toast.makeText(context, "Sign Up Successful again", Toast.LENGTH_LONG).show()
            }

            else -> {
                // No action needed for Loading and Error handled top
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                is SignUpViewModel.SignUpUiNavigationEvent.NavigateToHomeScreen -> {
                    onSignUpSuccess()
                }

                is SignUpViewModel.SignUpUiNavigationEvent.NavigateToLoginScreen -> {
                    onSignInClicked()
                }

                else -> {
                    // No navigation action
                }
            }
        }
    }

    SignUpScreenContent(
        name.value,
        email.value,
        password.value,
        errorMessage,
        isLoading,
        setName,
        setEmail,
        setPassword,
        signUpClicked,
        gotoLoginClicked,
        uiState.value
    )
}

@Composable
fun SignUpScreenContent(
    name: String,
    email: String,
    password: String,
    errorMessage: Boolean,
    isLoading: Boolean,
    setName: (String) -> Unit,
    setEmail: (String) -> Unit,
    setPassword: (String) -> Unit,
    signUpClicked: () -> Unit,
    gotoLoginClicked: () -> Unit,
    uiState: (SignUpViewModel.SignUpUiEvent)
) {
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
                value = name,
                onValueChange = { setName(it) },
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
                value = email,
                onValueChange = { setEmail(it) },
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
                value = password,
                onValueChange = { setPassword(it) },
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

            //Show error
            if (errorMessage) Text(
                text = (uiState as SignUpViewModel.SignUpUiEvent.Error).message,
                color = Color.Red,
                fontSize = 12.sp
            )

            Button(
                onClick = { signUpClicked() },
                modifier = Modifier
                    .size(248.dp, 60.dp)
                    .clip(ButtonDefaults.shape),
                colors = ButtonDefaults.buttonColors(containerColor = Color(LightOrange.value))
            )
            {
                Box {
                    AnimatedContent(targetState = isLoading) {
                        if (it) {
                            CircularProgressIndicator(
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

            TextButton(
                onClick = { gotoLoginClicked() },
                modifier = Modifier.padding(bottom = 30.dp)
            ) {
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
    SignUpScreenContent(
        name = "",
        email = "",
        password = "",
        errorMessage = false,
        isLoading = false,
        setName = {},
        setEmail = {},
        setPassword = {},
        signUpClicked = {},
        gotoLoginClicked = {},
        uiState = SignUpViewModel.SignUpUiEvent.Idle
    )
}