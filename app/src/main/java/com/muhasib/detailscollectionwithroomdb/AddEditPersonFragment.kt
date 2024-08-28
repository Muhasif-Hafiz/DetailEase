package com.muhasib.detailscollectionwithroomdb

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muhasib.detailscollectionwithroomdb.databinding.FragmentAddEditPersonBinding


class AddEditPersonFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddEditPersonBinding
    private var listener: AddEditPersonListener? = null
    private var person: Person? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AddEditPersonListener
            ?: throw ClassCastException("$context must implement AddEditPersonListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEditPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        person = arguments?.getParcelable("person")
        person?.let { setExistingDataOnUi(it) }
        attachUiListener()
    }

    private fun setExistingDataOnUi(person: Person) {
        binding.personNameEt.setText(person.person_name)
        binding.personAgeEt.setText(person.person_age.toString())
        binding.personCityEt.setText(person.person_city)
        binding.saveBtn.text = if (person.pID > 0) "Update" else "Save"
    }

    private fun attachUiListener() {
        binding.saveBtn.setOnClickListener {
            val name = binding.personNameEt.text.toString()
            val age = binding.personAgeEt.text.toString()
            val city = binding.personCityEt.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty() && city.isNotEmpty()) {
                val person1 = Person(person?.pID ?: 0, name, age.toInt(), city)
                listener?.onSaveBtnClicked(person != null, person1)
            }
            dismiss()
        }
    }

    companion object {
        const val TAG = "AddEditPersonFragment"

        @JvmStatic
        fun newInstance(person: Person?) = AddEditPersonFragment().apply {
            arguments = Bundle().apply {
                putParcelable("person", person)
            }
        }
    }

    interface AddEditPersonListener {
        fun onSaveBtnClicked(isUpdate: Boolean, person: Person)
    }
}
