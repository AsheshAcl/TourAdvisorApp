package com.tour.advisor.presentation.dynamicUI.components

import com.tour.advisor.data.screen.parser.model.ApiTextComponent
import com.tour.advisor.data.screen.parser.model.ButtonComponent
import com.tour.advisor.data.screen.parser.model.HorizontalListComponent
import com.tour.advisor.data.screen.parser.model.IconComponent
import com.tour.advisor.data.screen.parser.model.ImageComponent
import com.tour.advisor.data.screen.parser.model.ImageSliderComponent
import com.tour.advisor.data.screen.parser.model.InfoRowComponent
import com.tour.advisor.data.screen.parser.model.LoadingIndicatorComponent
import com.tour.advisor.data.screen.parser.model.LongCardImageComponent
import com.tour.advisor.data.screen.parser.model.OnboardingComponent
import com.tour.advisor.data.screen.parser.model.SingleRatingComponent
import com.tour.advisor.data.screen.parser.model.SmallCardImageComponent
import com.tour.advisor.data.screen.parser.model.SplashComponent
import com.tour.advisor.data.screen.parser.model.TextComponent
import com.tour.advisor.data.screen.parser.model.TopAppBarComponent
import com.tour.advisor.data.screen.parser.model.UIComponent
import com.tour.advisor.data.screen.parser.model.VerticalListComponent
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.*

val componentModule = SerializersModule {
    polymorphic(UIComponent::class) {
        subclass(TopAppBarComponent::class, TopAppBarComponent.serializer())
        subclass(SplashComponent::class, SplashComponent.serializer())
        subclass(OnboardingComponent::class, OnboardingComponent.serializer())
        subclass(TextComponent::class, TextComponent.serializer())
        subclass(LongCardImageComponent::class, LongCardImageComponent.serializer())
        subclass(SmallCardImageComponent::class, SmallCardImageComponent.serializer())
        subclass(ApiTextComponent::class, ApiTextComponent.serializer())
        subclass(IconComponent::class, IconComponent.serializer())
        subclass(ImageComponent::class, ImageComponent.serializer())
        subclass(ButtonComponent::class, ButtonComponent.serializer())
        subclass(LoadingIndicatorComponent::class, LoadingIndicatorComponent.serializer())
        subclass(ImageSliderComponent::class, ImageSliderComponent.serializer())
        subclass(HorizontalListComponent::class, HorizontalListComponent.serializer())
        subclass(VerticalListComponent::class, VerticalListComponent.serializer())
        subclass(InfoRowComponent::class, InfoRowComponent.serializer())
        subclass(SingleRatingComponent::class, SingleRatingComponent.serializer())
    }
}
