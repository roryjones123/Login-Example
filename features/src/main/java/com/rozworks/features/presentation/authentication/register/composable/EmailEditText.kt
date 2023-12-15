package com.rozworks.features.presentation.authentication.register.composable

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
import androidx.compose.material.icons.outlined.Email
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
fun EmailEditText(
    label: String, mutableText: MutableState<TextFieldValue>,
    modifier: Modifier,
    validationErrors: MutableState<List<EmailValidationResult>>
) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Box {
            BasicTextField(
                value = mutableText.value,
                onValueChange = {
                    validationErrors.value = validateEmail(it.text)
                    if (it.text.matches(NO_BLANKS_OR_NEWLINES_PATTERN.toRegex())) {
                        mutableText.value = it
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
                            Icons.Outlined.Email,
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
        validationErrors.value.firstOrNull()?.let { error ->
            Text(
                text = error.description, style = Typography.bodySmall.copy(color = MaterialTheme.colorScheme.error)
            )
        }
    }
}

private fun validateEmail(email: String): List<EmailValidationResult> {
    val emailResults = mutableListOf<EmailValidationResult>()

    if (!email.matches(EMAIL_PATTERN.toRegex())) {
        emailResults.add(EmailValidationResult.INVALID_EMAIL)
    }

    return emailResults
}

enum class EmailValidationResult(val description: String) {
    INVALID_EMAIL("Email format invalid!"),
}

const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
const val NO_BLANKS_OR_NEWLINES_PATTERN = "^[^\\s\n\r]*$"
