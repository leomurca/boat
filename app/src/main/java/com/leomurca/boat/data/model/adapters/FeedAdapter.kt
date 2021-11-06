package com.leomurca.boat.data.model.adapters

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class FeedAdapter(
    @field:Element
    var channel: ChannelAdapter? = null,
)