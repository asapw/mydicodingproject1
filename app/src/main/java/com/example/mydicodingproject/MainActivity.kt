package com.example.mydicodingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvRappers: RecyclerView
    private val list = ArrayList<Rapper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvRappers = findViewById(R.id.rv_rappers)
        rvRappers.setHasFixedSize(true)
        list.addAll(getListRappers())
        showRecyclerList()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                rvRappers.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.action_grid -> {
                rvRappers.layoutManager = GridLayoutManager(this, 2)
                true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun getListRappers(): ArrayList<Rapper> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataAlbums = resources.getStringArray(R.array.data_albums)
        val dataAwards = resources.getStringArray(R.array.data_awards)

        val listRapper = ArrayList<Rapper>()
        for (i in dataName.indices) {
            val rapper = Rapper(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataAlbums[i],      //i add albums and dataawards
                dataAwards[i]
            )
            listRapper.add(rapper)
        }
        dataPhoto.recycle()
        return listRapper
    }
    private fun showRecyclerList() {
        rvRappers.layoutManager = LinearLayoutManager(this)
        val listRapperAdapter = ListRapperAdapter(list)
        rvRappers.adapter = listRapperAdapter
    }
}