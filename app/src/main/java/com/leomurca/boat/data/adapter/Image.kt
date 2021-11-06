package com.leomurca.boat.data.adapter

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
class Image(
    @field:Element(name = "url")
    var url: String? = null,

    @field:Element(name = "width", required = false)
    var width: String? = null,

    @field:Element(name = "height", required = false)
    var height: String? = null
)
