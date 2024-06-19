package com.example.rereccontracts.ui.theme.pages.admin.models

class Contracts {
    var companyName : String = ""
    var email : String = ""
    var services: String = ""
    var startDate: String = ""
    var endDate: String = ""

    constructor(companyName:String, email:String, services:String, startDate:String, endDate:String){
        this.companyName = companyName
        this.email = email
        this.services = services
        this.startDate = startDate
        this.endDate = endDate
    }
    constructor()
}