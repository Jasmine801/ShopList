package com.example.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.fragments.BaseFragment
import com.example.shoppinglist.fragments.FragmentManager
import com.example.shoppinglist.fragments.NoteFragment
import com.example.shoppinglist.fragments.ShopListNamesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
        setBottomNavListener()
    }

    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.settings ->{
                    Log.d("MyLog", "Settings")
                }
                R.id.notes ->{
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list ->{
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
                }
                R.id.new_item ->{
                    FragmentManager.currenFragment?.onClickNew()
                }
            }
            true
        }
    }
}