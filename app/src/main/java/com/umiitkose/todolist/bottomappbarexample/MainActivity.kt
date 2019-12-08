package com.umiitkose.todolist.bottomappbarexample

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.umiitkose.todolist.bottomappbarexample.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_app_bar)

        val addVisibilityChanged: FloatingActionButton.OnVisibilityChangedListener = object : FloatingActionButton.OnVisibilityChangedListener() {
            override fun onShown(fab: FloatingActionButton?) {
                super.onShown(fab)
            }
            override fun onHidden(fab: FloatingActionButton?) {
                super.onHidden(fab)
                bottom_app_bar.toggleFabAlignment()
                bottom_app_bar.replaceMenu(
                    if(currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) R.menu.bottomappbar_menu_two
                    else R.menu.bottomappbar_menu
                )
                fab?.setImageDrawable(
                    if(currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) getDrawable(R.drawable.ic_save_black_24dp)
                    else getDrawable(R.drawable.ic_add_black_24dp)
                )
                fab?.show()
            }
        }

        fab.setOnClickListener {
            Toast.makeText(this,"Fab Click", Toast.LENGTH_LONG).show()
            fab.hide(addVisibilityChanged)
            invalidateOptionsMenu()
            bottom_app_bar.navigationIcon = if (bottom_app_bar.navigationIcon != null) null
            else getDrawable(R.drawable.ic_search)
            when(tv_text.text) {
                getString(R.string.primary_screen_text) -> tv_text.text = getString(R.string.secondary_sceen_text)
                getString(R.string.secondary_sceen_text) -> tv_text.text = getString(R.string.primary_screen_text)
            }
        }

    }

    private fun BottomAppBar.toggleFabAlignment() {
        currentFabAlignmentMode = fabAlignmentMode
        fabAlignmentMode = currentFabAlignmentMode.xor(1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     val inflater = menuInflater.inflate(R.menu.bottomappbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.app_bar_fav ->toast("Fav Menu item ",this)
            R.id.app_bar_settings ->toast("Settings Menu item ",this)
            R.id.app_bar_search ->toast("Search Menu item ",this)
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }

}
