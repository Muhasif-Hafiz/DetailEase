package com.muhasib.detailscollectionwithroomdb


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhasib.detailscollectionwithroomdb.databinding.SingleItemBinding

class PersonDetailsAdapter(private val listener: PersonDetailsClickListener) :
    ListAdapter<Person, PersonDetailsAdapter.PersonDetailsViewHolder>(DiffUtilCallback()) {

    inner class PersonDetailsViewHolder(private val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.editBtn.setOnClickListener {
                listener.onEditClickListener(getItem(adapterPosition))
            }
            binding.deleteBtn.setOnClickListener {
                listener.onDeleteClickListener(getItem(adapterPosition))
            }
        }

        fun bind(person: Person) {
            binding.personNameTv.text = person.person_name
            binding.personCityTv.text = person.person_city
            binding.personAgeTv.text = person.person_age.toString()
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem.pID == newItem.pID
        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDetailsViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface PersonDetailsClickListener {
        fun onEditClickListener(person: Person)
        fun onDeleteClickListener(person: Person)
    }
}
