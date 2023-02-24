package com.example.shoppinglist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.Menu
import android.view.MenuItem
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityNewNoteBinding
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.fragments.NoteFragment
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewNoteBinding
    private var note: NoteItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarSettings()
        getNote()
    }

    private fun getNote(){
        var sNote = intent.getSerializableExtra(NoteFragment.NEW_NOTE_KEY)
        if(sNote != null) {
            note = sNote as NoteItem
            fillNote()
        }
    }

    private fun fillNote() = with(binding){
        edTitle.setText(note?.title)
        edDescription.setText(note?.content)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nw_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.save){
            setMainResult()
        }
        else if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionBarSettings(){
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    private fun createNewNote() : NoteItem{
        return NoteItem(
            null,
            binding.edTitle.text.toString(),
            binding.edDescription.text.toString(),
            getCurrentTime(),
            ""
        )
    }

    private fun getCurrentTime() : String{
        val formatter = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return formatter.format(Calendar.getInstance().time)
    }

    private fun setMainResult(){
        var editState = "new"
        val tempNote: NoteItem?
        if(note == null){
            tempNote = createNewNote()
        }
        else{
            editState = "update"
            tempNote = updateNote()
        }
        val i = Intent().apply {
            putExtra(NoteFragment.NEW_NOTE_KEY, tempNote)
            putExtra(NoteFragment.EDIT_STATE_KEY, editState)
        }
        setResult(RESULT_OK, i)
        finish()
    }

    private fun updateNote() : NoteItem? = with(binding){
        return note?.copy(
            title = edTitle.text.toString(),
            content = edDescription.text.toString()
        )
    }
}