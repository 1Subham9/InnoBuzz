package com.amtron.innobuzz.helper

import androidx.viewpager2.widget.ViewPager2
import com.amtron.innobuzz.model.Post
import java.text.FieldPosition
import kotlin.properties.Delegates

class Common {

    companion object{
        lateinit var data : List<Post>
        lateinit var viewPage : ViewPager2
        var position by Delegates.notNull<Int>()
    }
}