package com.example.geneticcalc.data.models

class RelativesListItem {
    var relativesType: String? = null
        private set
    var eyeColor: String? = null
        private set
    var hairColor: String? = null
        private set
    var dateofBirth: String? = null
        private set
    var bloodType: String? = null
        private set

    fun RelativesListItem(
        relativesType: String?, eyeColor: String?,
        hairColor: String?, dateofBirth: String?,
        bloodType: String?
    ) {
        this.relativesType = relativesType
        this.eyeColor = eyeColor
        this.hairColor = hairColor
        this.dateofBirth = dateofBirth
        this.bloodType = bloodType
    }
}

