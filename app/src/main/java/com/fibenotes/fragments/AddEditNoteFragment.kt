package com.fibenotes.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fibenotes.NotesViewModel
import com.fibenotes.R
import com.fibenotes.database.Note
import com.fibenotes.databinding.FragmentAddEditNoteBinding
import com.fibenotes.listener.ValidationListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEditNoteFragment : Fragment(), ValidationListener {

    companion object {
        const val NOTE_MODEL = "NOTE_MODEL"
    }


    lateinit var binding: FragmentAddEditNoteBinding

    private val notesViewModel: NotesViewModel by activityViewModels()

    private var noteModel: Note? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_add_edit_note,
            container,
            false
        )
        notesViewModel.validationListener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        setUpListener()
    }

    private fun getIntentData() {
        noteModel = arguments?.getParcelable(NOTE_MODEL)
        setUpUI()
    }

    private fun setUpUI() {
        if (noteModel?.isAdd == false) {
            binding.toolbar.toolbar.title = getString(R.string.edit_note)
            binding.edtNoteTitle.text = Editable.Factory.getInstance().newEditable(noteModel?.title)
            binding.edtNoteDescription.text =
                Editable.Factory.getInstance().newEditable(noteModel?.description)
        } else {
            binding.toolbar.toolbar.title = getString(R.string.add_note)
        }
    }

    private fun getNoteRequestModel() = Note(
        id = noteModel?.id ?: 0,
        title = binding.edtNoteTitle.text.toString().trim(),
        description = binding.edtNoteDescription.text.toString().trim()
    )


    private fun setUpListener() {
        binding.toolbar.ivDeleteNote.setOnClickListener {
            notesViewModel.deleteNote(getNoteRequestModel())
            findNavController().popBackStack()
        }
        binding.btnSubmit.setOnClickListener {
            notesViewModel.validateNote(getNoteRequestModel())
        }
    }

    override fun onFailure(msg: Int) {
        Toast.makeText(requireActivity(), getString(msg), Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {
        if (noteModel?.isAdd == false) {
            notesViewModel.updateNote(getNoteRequestModel())
        } else {
            notesViewModel.createNote(getNoteRequestModel())
        }
        findNavController().popBackStack()


    }

}