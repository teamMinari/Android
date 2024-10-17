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

    //퀴즈
    data object QuizPlaycreen : Screens(
        rout = "quizplay",
        title = "quizplay",
    )
    data object QuizEndScreen : Screens(
        rout = "quizend",
        title = "QuizEnd",
    )
    data object QuizSelectO : Screens(
        rout = "Select_O",
        title = "Select_O",
    )
    data object QuizSelectX : Screens(
        rout = "Select_X",
        title = "Select_X",
    )

    //저장목록
    data object Directory: Screens(
        rout = "myDirectory",
        title = "Directory"
    )

    //칭호
    data object Alias: Screens(
        rout = "myAlias",
        title = "Alias"
    )

    data object Search: Screens(
        rout = "search",
        title = "Search"
    )
}