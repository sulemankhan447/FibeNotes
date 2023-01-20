package com.fibenotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fibenotes.NoteAdapter
import com.fibenotes.NotesViewModel
import com.fibenotes.R
import com.fibenotes.databinding.FragmentNotesListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotesListFragment : Fragment() {

    private lateinit var binding: FragmentNotesListBinding

    private lateinit var adapter:NoteAdapter

    private val notesViewModel: NotesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_notes_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        setUpListener()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        adapter = NoteAdapter()
        binding.recyclerNotes.adapter = adapter
    }

    private fun setUpObserver() {
        notesViewModel.notesLiveData.observe(viewLifecycleOwner) { noteList ->
            if(noteList.isEmpty()){
                binding.grpNoNote.visibility = View.VISIBLE
            }
            else{
                binding.grpNoNote.visibility = View.GONE
                binding.recyclerNotes.visibility = View.VISIBLE
                adapter.submitList(noteList)
            }
        }

    }

    private fun setUpListener() {
        binding.icAddEditNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_addEditNoteFragment)
        }
    }


}