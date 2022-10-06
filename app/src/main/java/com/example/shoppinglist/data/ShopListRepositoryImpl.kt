package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl:ShopListRepository {

    private var shopListLD = MutableLiveData<List<ShopItem>>()

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    init {
        for (i in 1 until 1000){
            val item = ShopItem("Name $i",i,kotlin.random.Random.nextBoolean())
            AddShopItem(item)
        }
    }

    override fun AddShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun DeleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun EditShopItem(shopItem: ShopItem) {
        val oldElement = GetShopItem(shopItem.id)
        shopList.remove(oldElement)
        AddShopItem(shopItem)
    }

    override fun GetShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun GetShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}