package com.example.presentation.screens.addPeopleScreen

import SwipeGestures.SwipeGestureManager
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAddPeopleScreenBinding
import com.example.presentation.screens.addPeopleScreen.epoxy.EpoxyPeopleController
import com.example.presentation.screens.base.BaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class AddPeopleScreenFragment : BaseFragment(R.layout.fragment_add_people_screen),
    EpoxyPeopleController.PlayerListener {

    private lateinit var binding: FragmentAddPeopleScreenBinding

    private lateinit var viewModel: AddPeopleScreenViewModel

    private val controller = EpoxyPeopleController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPeopleScreenBinding.bind(view)
        viewModel = obtainViewModel()
        binding.recycleView.setController(controller)
        val recyclerAdapterSwipeGestures = SwipeGestureManager(SwipeGestureManager.SwipeCallbackRight {
            Toast.makeText(requireContext(), "ASD", Toast.LENGTH_SHORT).show()
        })
        val itemTouchHelper = ItemTouchHelper(recyclerAdapterSwipeGestures)
        itemTouchHelper.attachToRecyclerView(binding.recycleView)

        launchWhenCreated {
            viewModel.stateFlow.collect {
                updateState(it)
            }
        }
    }

    private fun updateState(stateAddPeople: AddPeopleScreenState) {
        controller.setData(stateAddPeople)
    }

    override fun addPlayer(name: String) {
        viewModel.addPlayer(name)
    }

    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return AddPeopleScreenFragment()
        }
    }

}