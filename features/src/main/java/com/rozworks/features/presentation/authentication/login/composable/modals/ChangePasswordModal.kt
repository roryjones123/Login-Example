package com.rozworks.features.presentation.authentication.login.composable.modals

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.core.design.Typography
import com.rozworks.features.presentation.authentication.login.composable.ForgotPasswordButton
import com.rozworks.features.presentation.authentication.login.composable.PasswordEditText
import com.rozworks.features.presentation.authentication.login.composable.PasswordValidationResult
import com.rozworks.loginexample.features.R

@Composable
fun ChangePasswordCodeModal(
    showChangePasswordModal: MutableState<Boolean>,
    resetPassword: (String) -> Unit
) {
    val newPassword = mutableStateOf(TextFieldValue())
    val confirmPassword = mutableStateOf(TextFieldValue())

    val sheetState = rememberModalBottomSheetState()
    val newPasswordValidationErrors = remember { mutableStateOf<List<PasswordValidationResult>>(emptyList()) }
    val confirmPasswordValidationErrors = remember { mutableStateOf<List<PasswordValidationResult>>(emptyList()) }

    if (showChangePasswordModal.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showChangePasswordModal.value = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
            ) {
                Text(
                    stringResource(id = R.string.reset_password),
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_large))
                )
                Text(
                    stringResource(id = R.string.set_password),
                    style = Typography.bodySmall,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large))
                )

                Text(
                    stringResource(id = R.string.new_password),
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                )

                PasswordEditText(
                    label = stringResource(id = R.string.password),
                    mutableText = newPassword,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                        .fillMaxWidth(),
                    validationErrors = newPasswordValidationErrors
                )

                Text(
                    stringResource(id = R.string.confirm_password),
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                )

                PasswordEditText(
                    label = stringResource(id = R.string.password),
                    mutableText = confirmPassword,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                        .fillMaxWidth(),
                    validationErrors = confirmPasswordValidationErrors
                )

                val warningString = stringResource(id = R.string.passwords_match_warning)
                val context = LocalContext.current
                ForgotPasswordButton(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)),
                    onIntent = {
                        if (newPassword.value.text != confirmPassword.value.text) {
                            Toast.makeText(context, warningString, Toast.LENGTH_SHORT).show()
                        } else {
                            resetPassword.invoke(newPassword.value.text)
                        }
                    },
                    enabled = confirmPasswordValidationErrors.value.isEmpty()
                            && newPassword.value.text.isNotEmpty()
                            && confirmPassword.value.text.isNotEmpty()
                )
            }
        }
    }
}
