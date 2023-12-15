package com.rozworks.features.presentation.authentication.login.composable.modals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.core.design.Typography
import com.rozworks.features.presentation.authentication.login.composable.ForgotPasswordButton
import com.rozworks.loginexample.features.R

@Composable
fun VerifyResetCodeModal(
    showForgotPasswordCodeModal: MutableState<Boolean>,
    email: String,
    verifyCodeFailed: Boolean,
    forgotPasswordCodeInput: (String, String) -> Unit
) {
    val codes = List(NUMBER_OF_INPUTS) { mutableStateOf(TextFieldValue()) }
    val sheetState = rememberModalBottomSheetState()

    if (showForgotPasswordCodeModal.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showForgotPasswordCodeModal.value = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
            ) {
                Text(
                    stringResource(id = R.string.enter_code),
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_large))
                )
                Text(
                    stringResource(id = R.string.enter_code_body),
                    style = Typography.bodySmall,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large))
                )

                FiveDigitInput(codes)

                if (verifyCodeFailed) {
                    Text(
                        stringResource(id = R.string.fail_verification),
                        style = Typography.bodySmall.copy(color = MaterialTheme.colorScheme.error),
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large))
                    )
                }

                ForgotPasswordButton(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)),
                    onIntent = {
                        forgotPasswordCodeInput.invoke(
                            email,
                            codes.joinToString(separator = "") { it.value.text },
                        )
                    },
                    enabled = codes.all { it.value.text.isNotEmpty() }
                )
            }
        }
    }
}

const val NUMBER_OF_INPUTS = 5