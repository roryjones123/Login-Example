package com.rozworks.features.presentation.login.composable.modals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.core.design.Typography
import com.rozworks.features.presentation.login.composable.ForgotPasswordButton
import com.rozworks.features.presentation.register.composable.EmailEditText
import com.rozworks.features.presentation.register.composable.EmailValidationResult
import com.rozworks.loginexample.features.R

@Composable
fun ForgotPasswordEmailModal(
    showForgotPasswordModal: MutableState<Boolean>,
    email: MutableState<TextFieldValue>,
    forgotPasswordAttempt: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val emailValidationErrors = remember { mutableStateOf<List<EmailValidationResult>>(emptyList()) }

    if (showForgotPasswordModal.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showForgotPasswordModal.value = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
            ) {


                Text(
                    stringResource(id = R.string.forgot_password_2),
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_large))
                )
                Text(
                    stringResource(id = R.string.forgot_password_email),
                    style = Typography.bodySmall,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large))
                )

                EmailEditText(
                    label = stringResource(id = R.string.email),
                    mutableText = email,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                        .fillMaxWidth(),
                    validationErrors = emailValidationErrors
                )

                ForgotPasswordButton(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)),
                    onIntent = { forgotPasswordAttempt.invoke(email.value.text) },
                    enabled = emailValidationErrors.value.isEmpty() && email.value.text.isNotEmpty()
                )
            }
        }
    }
}