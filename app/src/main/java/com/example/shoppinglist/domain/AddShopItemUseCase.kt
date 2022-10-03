package com.example.shoppinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun AddShopItem(shopItem: ShopItem){
        shopListRepository.AddShopItem(shopItem)
    }
}