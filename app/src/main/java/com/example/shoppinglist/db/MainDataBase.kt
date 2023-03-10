package com.example.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.entities.LibraryItem
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.entities.ShoppingListItem
import com.example.shoppinglist.entities.ShoppingListName

@Database(entities = [LibraryItem::class, NoteItem::class,
    ShoppingListItem::class, ShoppingListName::class], version = 1)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao(): Dao
    companion object{
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDatabase(context: Context) : MainDataBase{
            return INSTANCE?: synchronized(this){
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "shopping_list_db"
                ).build()
                instance
            }
        }
    }
}