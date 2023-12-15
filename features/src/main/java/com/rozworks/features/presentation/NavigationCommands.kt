package com.rozworks.features.presentation

import com.rozworks.core.navigation.NavigationCommand

object NavigationCommands {
    val loginCommand: NavigationCommand = object : NavigationCommand {
        override val destination: String = "loginDestination"
    }

    val registerCommand: NavigationCommand = object : NavigationCommand {
        override val destination: String = "registerDestination"
    }

    val homeCommand: NavigationCommand = object : NavigationCommand {
        override val destination: String = "homeDestination"
    }
}