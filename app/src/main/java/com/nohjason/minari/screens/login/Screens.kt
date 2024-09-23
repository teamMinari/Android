package com.nohjason.minari.screens.login

sealed class Screens(
    val rout: String,
    val title: String,
) {
    data object FirstScreen : Screens(
        rout = "firstScreen",
        title = "First",
    )

    data object Login : Screens(
        rout = "login",
        title = "Login",
    )

    data object Signup : Screens(
        rout = "signup",
        title = "Signup",
    )

    data object Question : Screens(
        rout = "question",
        title = "Question",
    )

    data object LastSignup : Screens(
        rout = "lastsignup",
        title = "LastSignup",
    )

    data object Style : Screens(
        rout = "userstyle",
        title = "UserStyle",
    )

    data object Grape : Screens(
        rout = "grape",
        title = "Grape",
    )
    data object Grapes : Screens(
        rout = "grapes",
        title = "Grapes",
    )

    data object Term : Screens(
        rout = "term",
        title = "Term",
    )

    data object QuizEndScreen : Screens(
        rout = "quizend",
        title = "QuizEnd",
    )
}