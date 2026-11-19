package com.example.meira_3tie_mobile.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meira_3tie_mobile.Home.data.api.CatFactApiClient
import com.example.meira_3tie_mobile.Home.data.api.PhotoApiClient
import com.example.meira_3tie_mobile.Home.pertemuan_4.FourthActivity
import com.example.meira_3tie_mobile.Home.pertemuan_67.SeventhActivity
import com.example.meira_3tie_mobile.Home.pertemuan_10.TenthActivity
import com.example.meira_3tie_mobile.Home.pertemuan_11.EleventhActivity
import com.example.meira_3tie_mobile.Home.photo.PhotoAdapter
import com.example.meira_3tie_mobile.databinding.FragmentHomeBinding

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
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
        activity.supportActionBar?.title = "Home"


        val sharedPref = requireContext()
            .getSharedPreferences("user_pref", MODE_PRIVATE)

        // TOMBOL PRAKTIKUM
        binding.btnToFourth.setOnClickListener {
            startActivity(
                Intent(requireContext(), FourthActivity::class.java)
            )
        }

        binding.btnToSeventh.setOnClickListener {
            startActivity(
                Intent(requireContext(), SeventhActivity::class.java)
            )
        }

        binding.btnToTenth.setOnClickListener {
            startActivity(
                Intent(requireContext(), TenthActivity::class.java)
            )
        }

        binding.btnToEleventh.setOnClickListener {
            startActivity(
                Intent(requireContext(), EleventhActivity::class.java)
            )
        }


        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    sharedPref.edit().clear().apply()
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        loadCatFact()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

//        binding.rvGallery.layoutManager =
//            LinearLayoutManager(requireContext())

//        binding.rvGallery.layoutManager =
//            LinearLayoutManager(
//                requireContext(),
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )

        binding.rvGallery.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.rvGallery.isNestedScrollingEnabled = false

        loadPhoto()
    }

    private fun loadCatFact() {

        binding.tvCatFact.text = "Loading cat fact..."
        binding.btnRefresh.isEnabled = false

        viewLifecycleOwner.lifecycleScope.launch {

            try {
                val response = CatFactApiClient
                    .apiService
                    .getCatFact()

                _binding?.tvCatFact?.text = response.fact

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _binding?.tvCatFact?.text =
                    "Gagal mengambil fakta kucing. Periksa internet."

            } finally {
                _binding?.btnRefresh?.isEnabled = true
            }
        }
    }

    private fun loadPhoto() {

        binding.tvGalleryStatus.text = "Memuat foto..."
        binding.tvGalleryStatus.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {

            try {
                val photos = PhotoApiClient
                    .apiService
                    .getPhotos()

                val ui = _binding ?: return@launch

                val photoAdapter = PhotoAdapter(photos)

                ui.rvGallery.adapter = photoAdapter
                ui.tvGalleryStatus.visibility = View.GONE

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _binding?.tvGalleryStatus?.text =
                    "Gagal memuat galeri foto. Periksa internet."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
