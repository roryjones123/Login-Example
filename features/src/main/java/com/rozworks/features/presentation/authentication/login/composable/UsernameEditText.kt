package com.rozworks.features.presentation.authentication.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun UsernameEditText(
    label: String,
    mutableText: MutableState<TextFieldValue>,
    modifier: Modifier,
    validationErrors: MutableState<List<UsernameValidationResult>>? = null
) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Box {
            BasicTextField(
                value = mutableText.value,
                onValueChange = {
                    if (it.text.length <= mutableText.value.text.length || usernamePattern.matches(it.text)) {
                        mutableText.value = it
                        validationErrors?.value = validateUsername(it.text)
                    }
                },
                textStyle = Typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(dimensionResource(id = R.dimen.edit_text_corners))
                            )
                            .padding(dimensionResource(id = R.dimen.edit_text_padding)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_small))
                        )
                        if (!isFocused.value && mutableText.value.text.isEmpty()) {
                            Text(
                                text = label,
                                style = Typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onTertiary)
                            )
                        }
                        innerTextField()
                    }
                },
                modifier = modifier
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    }
                    .focusRequester(focusRequester)
            )
        }
        validationErrors?.value?.firstOrNull()?.let { error ->
            Text(
                text = error.description, style = Typography.bodySmall.copy(color = MaterialTheme.colorScheme.error)
            )
        }
    }
}

fun validateUsername(username: String): List<UsernameValidationResult> {
    val usernameResults = mutableListOf<UsernameValidationResult>()

    if (username.length < USERNAME_MIN_LENGTH) usernameResults.add(UsernameValidationResult.USERNAME_TOO_SHORT)
    if (username.length > USERNAME_MAX_LENGTH) usernameResults.add(UsernameValidationResult.USERNAME_TOO_LONG)
    if (!username.matches(usernamePattern)) usernameResults.add(UsernameValidationResult.INVALID_USERNAME_FORMAT)

    return usernameResults
}

enum class UsernameValidationResult(val description: String) {
    USERNAME_TOO_SHORT("Username must be at least 4 characters long"),
    USERNAME_TOO_LONG("Username must be less than 18 characters long"),
    INVALID_USERNAME_FORMAT("Username can only contain letters and numbers"),
}

// Sample constants for username length boundaries.
const val USERNAME_MIN_LENGTH = 4
const val USERNAME_MAX_LENGTH = 18

val usernamePattern = Regex("^[a-zA-Z0-9_-]{0,20}$")