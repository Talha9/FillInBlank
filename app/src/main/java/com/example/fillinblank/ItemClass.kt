package com.example.fillinblank

class ItemClass {

    var viewType: Int


    var text: String? = null

    var ans: String? = null

    constructor(viewType: Int, text: String?, ans: String?) {
        this.viewType = viewType
        this.text = text
        this.ans = ans
    }


    companion object {
        // Integers assigned to each layout
        // these are declared static so that they can
        // be accessed from the class name itself
        // And final so that they are not modified later
        const val LayoutOne = 0
        const val LayoutTwo = 1
    }
}