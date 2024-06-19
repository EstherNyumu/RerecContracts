package com.example.rereccontracts.ui.theme.pages.admin.models

class Users {
    var email:String = ""
    var password:String = ""
    var userId:String = ""

    constructor(email:String, password:String, userId:String) {
        this.email = email
        this.password = password
        this.userId = userId
    }
    constructor()
}