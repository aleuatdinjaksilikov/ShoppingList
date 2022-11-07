package com.example.shoppinglist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun EditShopItem(shopItem: ShopItem){
        shopListRepository.EditShopItem(shopItem)
    }
}