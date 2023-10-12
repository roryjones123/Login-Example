package com.rozworks.core.navigation

import androidx.navigation.NavOptions
import com.rozworks.loginexample.core.R

interface NavigationCommand {
    val destination: String
    val configuration: NavOptions
        get() = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
}
