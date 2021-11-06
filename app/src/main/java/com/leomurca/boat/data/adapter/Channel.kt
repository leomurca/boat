package com.leomurca.boat.data.adapter

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel(
    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "image", required = false)
    var image: Image? = null,

    @field:Element(name = "language", required = false)
    var language: String? = null,

    @field:ElementList(inline = true, required = false)
    var items: List<Item>? = null
)