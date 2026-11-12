package com.example.meira_3tie_mobile.Message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.meira_3tie_mobile.R
import com.example.meira_3tie_mobile.Message.tutorial.TutorialMessageActivity
import com.example.meira_3tie_mobile.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // DATA MESSAGE PERTEMUAN 10
    private val messageList = listOf(
        MessageModel(
            "Alya",
            "Halo! Apa kabar?",
            "https://i.pravatar.cc/150?img=1",
            "25"
        ),
        MessageModel(
            "Budi",
            "Sudah makan?",
            "https://i.pravatar.cc/150?img=2",
            "25"
        ),
        MessageModel(
            "Citra",
            "Jangan lupa tugasnya ya!",
            "https://i.pravatar.cc/150?img=3",
            "25"
        ),
        MessageModel(
            "Dika",
            "Besok kita rapat jam 9",
            "https://i.pravatar.cc/150?img=4",
            "25"
        ),
        MessageModel(
            "Eka",
            "Nice job kemarin!",
            "https://i.pravatar.cc/150?img=5",
            "25"
        ),
        MessageModel(
            "Fajar",
            "Lagi ngapain?",
            "https://i.pravatar.cc/150?img=6",
            "25"
        ),
        MessageModel(
            "Gita",
            "Boleh minta tolong?",
            "https://i.pravatar.cc/150?img=7",
            "25"
        ),
        MessageModel(
            "Hana",
            "Lihat email ya",
            "https://i.pravatar.cc/150?img=8",
            "25"
        ),
        MessageModel(
            "Irfan",
            "Oke noted",
            "https://i.pravatar.cc/150?img=9",
            "25"
        ),
        MessageModel(
            "Joko",
            "Sampai jumpa besok",
            "https://i.pravatar.cc/150?img=10",
            "25"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMessageBinding.inflate(
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

        // 1. Mengaktifkan Toolbar Message
        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Message"
            }

        // 2. Mengaktifkan Option Menu
        setHasOptionsMenu(true)

        // 3. Menampilkan ListView Message
        val adapter = MessageAdapter(
            requireContext(),
            messageList
        )

        binding.listMessageItems.adapter = adapter
    }

    // 4. Menampilkan menu Info pada Toolbar
    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(
            R.menu.message_toolbar_menu,
            menu
        )
    }

    // 5. Aksi ketika menu Info ditekan
    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean {

        return when (item.itemId) {

            R.id.action_tutorial -> {

                val intent = Intent(
                    requireContext(),
                    TutorialMessageActivity::class.java
                )

                startActivity(intent)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
