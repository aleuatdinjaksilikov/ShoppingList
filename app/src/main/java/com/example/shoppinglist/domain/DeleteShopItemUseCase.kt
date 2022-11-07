package com.example.shoppinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun DeleteShopItem(shopItem: ShopItem){
        shopListRepository.DeleteShopItem(shopItem)
    }
}