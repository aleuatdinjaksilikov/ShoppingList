package com.example.shoppinglist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun EditShopItem(shopItem: ShopItem){
        shopListRepository.EditShopItem(shopItem)
    }
}