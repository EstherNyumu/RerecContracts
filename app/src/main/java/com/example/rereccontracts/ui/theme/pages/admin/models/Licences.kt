package com.example.rereccontracts.ui.theme.pages.admin.models

class Licences {
    var softwareName:String = ""
    var vendorName: String = ""
    var licenceType: String = ""
    var cost: String = ""
    var startDate: String = ""
    var endDate: String = ""

    constructor(softwareName: String, vendorName: String, licenceType: String, cost: String, startDate: String, endDate: String){
        this.softwareName = softwareName
        this.vendorName = vendorName
        this.licenceType = licenceType
        this.cost = cost
        this.startDate = startDate
        this.endDate = endDate
    }
    constructor()
}