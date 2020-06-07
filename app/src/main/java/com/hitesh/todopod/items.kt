package com.hitesh.todopod

class items {
    var title: String? = null
    private var title2: String? = null
    var year: String? = null

    constructor() {}
    constructor(title: String?, title2: String?, title3: String?) {
        this.title = title
        this.title2 = title2
        year = title3
    }

    var genre: String?
        get() = title2
        set(genre) {
            title2 = title2
        }

}