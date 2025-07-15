package com.tour.advisor.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tour.advisor.dynamicUI.components.IconComponent
import com.tour.advisor.utility.UIUtils.Companion.getDrawableIdFromName

@Composable
fun IconComponent(modifier: Modifier, component: IconComponent) {
    component.resource.getDrawableIdFromName()?.let { painterResource(id = it) }?.let {
        Image(
            painter = it, contentDescription = null, modifier = modifier.size(64.dp)
        )
    }
}


@Preview
@Composable
private fun IconComponentPreview() {
    IconComponent(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        IconComponent(id = "", type = "icon", resource = "ic_app_icon")
    )
}