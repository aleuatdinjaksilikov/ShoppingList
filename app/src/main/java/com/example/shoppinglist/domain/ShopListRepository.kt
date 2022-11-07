package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun AddShopItem(shopItem: ShopItem)
    suspend fun DeleteShopItem(shopItem: ShopItem)
    suspend fun EditShopItem(shopItem: ShopItem)
    suspend fun GetShopItem(shopItemId:Int):ShopItem
    fun GetShopList():LiveData<List<ShopItem>>
}