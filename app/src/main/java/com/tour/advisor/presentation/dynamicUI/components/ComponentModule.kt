package com.tour.advisor.presentation.dynamicUI.components

import com.tour.advisor.data.screen.model.ButtonComponent
import com.tour.advisor.data.screen.model.HorizontalListComponent
import com.tour.advisor.data.screen.model.IconComponent
import com.tour.advisor.data.screen.model.ImageComponent
import com.tour.advisor.data.screen.model.ImageSliderComponent
import com.tour.advisor.data.screen.model.InfoRowComponent
import com.tour.advisor.data.screen.model.LoadingIndicatorComponent
import com.tour.advisor.data.screen.model.LongCardImageComponent
import com.tour.advisor.data.screen.model.OnboardingComponent
import com.tour.advisor.data.screen.model.SingleRatingComponent
import com.tour.advisor.data.screen.model.SmallCardImageComponent
import com.tour.advisor.data.screen.model.SplashComponent
import com.tour.advisor.data.screen.model.TextComponent
import com.tour.advisor.data.screen.model.TopAppBarComponent
import com.tour.advisor.data.screen.model.UIComponent
import com.tour.advisor.data.screen.model.VerticalListComponent
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
