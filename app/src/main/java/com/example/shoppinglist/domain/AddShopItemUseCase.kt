package com.example.shoppinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun AddShopItem(shopItem: ShopItem){
        shopListRepository.AddShopItem(shopItem)
    }
}