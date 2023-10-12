package com.rozworks.features.presentation.login.composable.modals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun FiveDigitInput(codes: List<MutableState<TextFieldValue>>) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.forgot_code_input_spacing)),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)),
        ) {
            codes.forEachIndexed { _, code ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .width(dimensionResource(id = R.dimen.forgot_code_input_width)),
                    contentAlignment = Alignment.Center
                ) {
                    BasicTextField(
                        value = code.value,
                        onValueChange = {
                            if (it.text.length <= 1 && it.text.all { char -> char.isDigit() }) {
                                code.value = it
                            }
                        },
                        textStyle = Typography.displaySmall.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        maxLines = 1,
                        modifier = Modifier
                            .background(
                                Color.Gray.copy(alpha = CODE_INPUT_ALPHA),
                                shape = RoundedCornerShape(dimensionResource(id = R.dimen.forgot_code_input_corners))
                            )
                            .padding(vertical = dimensionResource(id = R.dimen.dimen_small))
                    )
                }
            }
        }
    }
}

const val CODE_INPUT_ALPHA = 0.1f