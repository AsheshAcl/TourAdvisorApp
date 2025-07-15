package com.tour.advisor.dynamicUI.components

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.*

val componentModule = SerializersModule {
    polymorphic(UIComponent::class) {
        subclass(TopAppBarComponent::class, TopAppBarComponent.serializer())
        subclass(SplashComponent::class, SplashComponent.serializer())
        subclass(OnboardingComponent::class, OnboardingComponent.serializer())
        subclass(TextComponent::class, TextComponent.serializer())
        subclass(ApiTextComponent::class, ApiTextComponent.serializer())
        subclass(IconComponent::class, IconComponent.serializer())
        subclass(ButtonComponent::class, ButtonComponent.serializer())
        subclass(LoadingIndicatorComponent::class, LoadingIndicatorComponent.serializer())
        subclass(ImageSliderComponent::class, ImageSliderComponent.serializer())
        subclass(HorizontalListComponent::class, HorizontalListComponent.serializer())
        subclass(VerticalListComponent::class, VerticalListComponent.serializer())
        subclass(ImageComponent::class, ImageComponent.serializer())
        subclass(InfoRowComponent::class, InfoRowComponent.serializer())
        subclass(SingleRatingComponent::class, SingleRatingComponent.serializer())
    }
}
