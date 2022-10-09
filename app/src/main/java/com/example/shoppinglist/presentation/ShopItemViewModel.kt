package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopItemUseCase
import com.example.shoppinglist.domain.ShopItem

class ShopItemViewModel:ViewModel() {

        private var repository = ShopListRepositoryImpl

        private var getShopItemUseCase = GetShopItemUseCase(repository)
        private var addShopItemUseCase = AddShopItemUseCase(repository)
        private var editShopItemUseCase = EditShopItemUseCase(repository)

        val _errorInputName = MutableLiveData<Boolean>()
        val errorInputName : LiveData<Boolean>
                get() = _errorInputName

        val _errorInputCount = MutableLiveData<Boolean>()
        val errorInputCount : LiveData<Boolean>
                get() = _errorInputCount

        private val _shopItem = MutableLiveData<ShopItem>()
        val shopItem : LiveData<ShopItem>
                get() = _shopItem

        private val _shouldCloseScreen  = MutableLiveData<Unit>()
        val shouldCloseScreen:LiveData<Unit>
        get() = _shouldCloseScreen

        fun getShopItem(shopItemId : Int){
                val item = getShopItemUseCase.GetShopItem(shopItemId)
                _shopItem.value = item
        }

        fun addShopItem(inputName:String?,inputCount:String?){
                val name  = parseName(inputName)
                val count = parseCount(inputCount)
                val fieldValid = validateInput(name, count)
                if (fieldValid) {
                        val shopItem = ShopItem(name,count,true)
                        addShopItemUseCase.AddShopItem(shopItem)
                        finishWork()
                }
        }
        fun editShopItem(inputName:String?,inputCount:String?){
                val name  = parseName(inputName)
                val count = parseCount(inputCount)
                val fieldValid = validateInput(name, count)
                if (fieldValid) {
                        _shopItem.value?.let {
                                val item = it.copy(name = name, count = count)
                                editShopItemUseCase.EditShopItem(item)
                                finishWork()
                        }

                }
        }

        private fun parseName(inputName: String?):String{
                return inputName?.trim() ?: ""
        }

        private fun parseCount(inputCount: String?):Int{
                return try {
                    inputCount?.trim()?.toInt() ?: 0
                }catch (e:Exception){
                        0
                }
        }

        private fun validateInput(name : String,count:Int):Boolean{
                var result = true
                if (name.isBlank()){
                        _errorInputName.value = true
                        result = false
                }
                if (count<=0){
                        _errorInputCount.value = true
                        result = false
                }
                return result
        }

         fun resetErrorInputName(){
                _errorInputName.value = false
        }
         fun resetErrorInputCount(){
                _errorInputCount.value = false
        }
        private fun finishWork(){
                _shouldCloseScreen.value = Unit
        }

}