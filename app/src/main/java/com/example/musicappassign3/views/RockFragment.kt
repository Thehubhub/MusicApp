package com.example.musicappassign3.views

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicappassign3.adapters.RockAdapter
import com.example.musicappassign3.databinding.FragmentRockBinding
import com.example.musicappassign3.model.RockItem
import com.example.musicappassign3.model.Rocks
import com.example.musicappassign3.presenters.RockPresenter
import com.example.musicappassign3.presenters.RockPresenterContract
import com.example.musicappassign3.presenters.RockViewContract

class RockFragment : Fragment(), RockViewContract {

    private val binding by lazy {
        FragmentRockBinding.inflate(layoutInflater)
    }

    private val rockAdapter by lazy {
        RockAdapter(onTabClicked = {})
    }

        private val rockPresenter: RockPresenterContract by lazy {
            RockPresenter(requireContext(), this)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment

            binding.songRecycler.apply {
                layoutManager = GridLayoutManager(
                    requireContext(),
                    1,
                    GridLayoutManager.VERTICAL,
                    false
                )
                adapter = rockAdapter
            }

            rockPresenter.checkNetwork()

            return binding.root
        }

        override fun onResume() {
            super.onResume()

            rockPresenter.getRockTrack()
        }

        override fun onDestroyView() {
            super.onDestroyView()

            rockPresenter.destroy()
        }


        override fun loadingRockTrack(isLoading: Boolean) {
            binding.songRecycler.visibility = View.GONE
        }

        override fun rockSuccess(rockCollectionList: List<RockItem>) {
            binding.songRecycler.visibility = View.VISIBLE
            rockAdapter.updateRockList(rockCollectionList)
        }

        override fun rockTrackError(throwable: Throwable) {
            binding.songRecycler.visibility = View.GONE
            AlertDialog.Builder(requireContext())
                .setTitle("AN ERROR HAS OCCURRED")
                .setMessage(throwable.localizedMessage)
                .setPositiveButton("DISMISS") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .create()
                .show()
        }

        companion object {
        }

}