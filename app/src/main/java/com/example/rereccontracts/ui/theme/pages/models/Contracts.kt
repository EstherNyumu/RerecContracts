package com.example.rereccontracts.ui.theme.pages.models

class Contracts {
    var companyName : String = ""
    var email : String = ""
    var services: String = ""
    var startDate: String = ""
    var endDate: String = ""
    var period:String = ""
    var contractId: String = ""

    constructor(companyName:String, email:String, services:String, startDate:String, endDate:String,period:String,contractId:String){
        this.companyName = companyName
        this.email = email
        this.services = services
        this.startDate = startDate
        this.endDate = endDate
        this.period = period
        this.contractId=contractId
    }
    constructor()
}