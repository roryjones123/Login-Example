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
import androidx.compose.material.icons.outlined.Lock
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun PasswordEditText(
    label: String, mutableText: MutableState<TextFieldValue>,
    modifier: Modifier,
    validationErrors: MutableState<List<PasswordValidationResult>>? = null
) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Box {
            BasicTextField(
                value = mutableText.value,
                onValueChange = {
                    if (pattern.matches(it.text)) {
                        mutableText.value = it
                        validationErrors?.value = validatePassword(it.text)
                    }
                },
                textStyle = Typography.bodyMedium,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
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
                            Icons.Outlined.Lock,
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
    }

    validationErrors?.value?.firstOrNull()?.let { error ->
        Text(
            text = error.description, style = Typography.bodySmall.copy(color = MaterialTheme.colorScheme.error),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_small))
        )
    }
}

private fun validatePassword(password: String): List<PasswordValidationResult> {
    val passwordResults = mutableListOf<PasswordValidationResult>()

    if (password.isEmpty()) passwordResults.add(PasswordValidationResult.PASSWORD_EMPTY)

    if (password.length < PASSWORD_MIN_LENGTH) passwordResults.add(PasswordValidationResult.PASSWORD_TOO_SHORT)

    if (!password.any { it.isUpperCase() }) passwordResults.add(PasswordValidationResult.MISSING_UPPERCASE)

    if (!password.any { it.isLowerCase() }) passwordResults.add(PasswordValidationResult.MISSING_LOWERCASE)

    if (!password.any { it.isDigit() }) passwordResults.add(PasswordValidationResult.MISSING_DIGIT)

    if (!password.any { it in SPECIAL_CHARACTERS }) passwordResults.add(PasswordValidationResult.MISSING_SPECIAL_CHARACTER)

    return passwordResults
}

enum class PasswordValidationResult(val description: String) {
    PASSWORD_EMPTY("Password field is empty"),
    PASSWORD_TOO_SHORT("Password must be at least 8 characters long"),
    MISSING_UPPERCASE("Password must contain at least one uppercase character"),
    MISSING_LOWERCASE("Password must contain at least one lowercase character"),
    MISSING_DIGIT("Password must contain at least one digit"),
    MISSING_SPECIAL_CHARACTER(
        "Password must contain at least one of the following special characters: !@#$%^&*()"
    )
}

const val PASSWORD_MIN_LENGTH = 8
const val SPECIAL_CHARACTERS = "!@#$%^&*()"
val pattern = Regex("^[^\\s\\n]{0,40}$")