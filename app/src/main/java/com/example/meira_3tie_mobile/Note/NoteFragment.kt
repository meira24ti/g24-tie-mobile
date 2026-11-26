package com.example.meira_3tie_mobile.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meira_3tie_mobile.Home.data.AppDatabase
import com.example.meira_3tie_mobile.Home.data.entity.NoteEntity
import com.example.meira_3tie_mobile.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: NoteAdapter

    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as AppCompatActivity

        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Note"

        db = AppDatabase.getInstance(requireContext())

        adapter = NoteAdapter(
            notes,
            this
        )

        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvNotes.adapter = adapter

        val divider = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )

        binding.rvNotes.addItemDecoration(divider)

        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NoteFormActivity::class.java
                )
            )
        }
    }

    private fun fetchNotes() {

        viewLifecycleOwner.lifecycleScope.launch {

            val data = db.noteDao().getAll()

            if (_binding == null) {
                return@launch
            }

            notes.clear()
            notes.addAll(data)

            adapter.notifyDataSetChanged()

            binding.tvEmpty.visibility =
                if (notes.isEmpty()) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }

    override fun onResume() {
        super.onResume()

        if (_binding != null && ::adapter.isInitialized) {
            fetchNotes()
        }
    }

    fun deleteNote(note: NoteEntity) {

        viewLifecycleOwner.lifecycleScope.launch {

            db.noteDao().delete(note)

            fetchNotes()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
