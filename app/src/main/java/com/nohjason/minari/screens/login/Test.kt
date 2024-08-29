package com.nohjason.minari.screens.login

sealed class Test(
    val rout: String,
    val title: String,
) {
    data object FirstScreen : Test(
        rout = "firstScreen",
        title = "First",
    )

    data object Login : Test(
        rout = "login",
        title = "Login",
    )

    data object Signup : Test(
        rout = "signup",
        title = "Signup",
    )

    data object Question : Test(
        rout = "question",
        title = "Question",
    )

    data object LastSignup : Test(
        rout = "lastsignup",
        title = "LastSignup",
    )

    data object Style : Test(
        rout = "userstyle",
        title = "UserStyle",
    )

    data object Grape : Test(
        rout = "grape",
        title = "Grape",
    )
}