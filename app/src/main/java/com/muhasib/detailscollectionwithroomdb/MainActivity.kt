package com.muhasib.detailscollectionwithroomdb

import android.os.Bundle
import android.widget.ListAdapter
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.muhasib.detailscollectionwithroomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), PersonDetailsAdapter.PersonDetailsClickListener,
    AddEditPersonFragment.AddEditPersonListener {

    lateinit var binding: ActivityMainBinding
    private  var Dao: PersonDao?=null
   private  lateinit var  Adapter : PersonDetailsAdapter
  private lateinit var searchQueryLiveData : MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initVars()
        AttachUiListener()
        subscribeDataStreams()



    }

    private fun subscribeDataStreams() {

        searchQueryLiveData.observe(this){query->

            lifecycleScope.launch {

                Adapter.submitList( Dao?.searchedData(query)?.first())

            }



        }

        lifecycleScope.launch {

            Dao?.getAllData()?.collect{mList->

                lifecycleScope.launch {

                    Adapter.submitList(Dao?.searchedData(searchQueryLiveData.value?:"")?.first())
                }
            }


        }
    }

    private fun AttachUiListener() {

        binding.floatingActionButton.setOnClickListener {
            showBottomSheet()
        }
        binding.searchcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false



            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText!=null){

                    onQueryChanged(newText)
                }
                return true
            }


        })



    }

    private fun onQueryChanged(newText: String) {

      searchQueryLiveData.postValue(newText)

    }

    private fun showBottomSheet(person: Person? = null) {
        // Check if the person is not null
        if (person != null) {
            // Create an instance of the BottomSheetDialogFragment
            val sheet = AddEditPersonFragment()

            // Optionally pass data to the fragment using arguments
            val args = Bundle().apply {
                putParcelable("person", person)
            }
            sheet.arguments = args

            // Show the BottomSheetDialogFragment
            sheet.show(supportFragmentManager, "AddEditPersonFragment")
        }
    }

    override fun onSaveBtnClicked(isUpdate: Boolean, person: Person) {


        lifecycleScope.launch(Dispatchers.IO) {

            if(isUpdate){
                Dao?.updatePeron(person)
            }else{
                Dao?.savePerson(person)
            }
        }

    }

    private fun initVars() {
        Dao =AppDatabase.getDatabase(this).personDao()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        Adapter= PersonDetailsAdapter(this)
        binding.recyclerView.adapter=Adapter
        searchQueryLiveData =MutableLiveData("")

    }

    override fun onEditClickListener(person: Person) {
        showBottomSheet(person)

    }

    override fun onDeleteClickListener(person: Person) {

            lifecycleScope.launch {

                Dao?.deletePerson(person)
            }

    }
}