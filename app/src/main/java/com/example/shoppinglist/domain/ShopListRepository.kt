package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun AddShopItem(shopItem: ShopItem)
    fun DeleteShopItem(shopItem: ShopItem)
    fun EditShopItem(shopItem: ShopItem)
    fun GetShopItem(shopItemId:Int):ShopItem
    fun GetShopList():LiveData<List<ShopItem>>
}