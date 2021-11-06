package com.leomurca.boat.data.model.adapters

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class ItemAdapter(
    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "pubDate")
    var pubDate: String? = null,

    @field:Element(name = "description")
    var description: String? = null
)