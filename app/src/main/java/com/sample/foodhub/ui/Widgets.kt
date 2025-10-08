package com.sample.foodhub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.foodhub.R
import com.sample.foodhub.ui.theme.Orange

@Composable
fun GroupSocialButtons(onFaceBookClick: () -> Unit, onGoogleClick: () -> Unit) {
    // Implementation for social buttons like Google, Facebook, etc.
    Column(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
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

@Composable
fun FoodHubOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        unfocusedIndicatorColor = Color.LightGray.copy(0.5f),
        focusedIndicatorColor = Orange,
    )
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        label?.let {
            Row {
                it().toString()
            }
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = null,
            modifier = modifier,
            shape = shape,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle.copy(fontWeight = FontWeight.SemiBold),
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = supportingText,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            colors = colors
        )
    }
}

@Preview(showBackground = false)
@Composable
fun GroupSocialButtonsPreview() {
    GroupSocialButtons(onFaceBookClick = {}, onGoogleClick = {})
}

@Preview
@Composable
fun FoodHubOutlinedTextFieldPreview() {
    FoodHubOutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = "Test",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}