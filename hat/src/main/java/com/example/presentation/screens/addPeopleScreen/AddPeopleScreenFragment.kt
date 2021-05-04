package com.example.presentation.screens.addPeopleScreen

import SwipeGestures.SwipeGestureManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAddPeopleScreenBinding
import com.example.presentation.screens.addPeopleScreen.epoxy.EpoxyPeopleController
import com.example.presentation.screens.addWordsScreen.AddWordsScreenFragment
import com.example.presentation.screens.base.BaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
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
        val recyclerAdapterSwipeGestures =
            SwipeGestureManager(SwipeGestureManager.SwipeCallbackRight {
                removePlayer(it)
            })
        recyclerAdapterSwipeGestures.apply {
            setBackgroundColorLeft(ColorDrawable(Color.RED))
            setIconRight(
                ContextCompat.getDrawable(
                    requireContext(),
                    android.R.drawable.ic_menu_delete
                )
            )
        }
        val itemTouchHelper = ItemTouchHelper(recyclerAdapterSwipeGestures)
        itemTouchHelper.attachToRecyclerView(binding.recycleView)

        launchWhenCreated {
            viewModel.stateFlow.onEach {
                updateState(it)
            }.launchIn(this)
            viewModel.commandFlow.onEach {
                handleCommand(it)
            }.launchIn(this)
        }

        binding.toolbar.setNavigationOnClickListener {
            router.exit()
        }
        binding.toolbar.title = "PLAYERS"

    }

    private fun handleCommand(playerCommand: AddPlayerCommand) {
        when (playerCommand) {
            AddPlayerCommand.RemovePlayerError -> {}
            AddPlayerCommand.AddPlayerError -> {}
        }
    }

    private fun removePlayer(idx: Int) {
        // Вычитаем единицу, так как первый элемент в списке это не игрок
        viewModel.removePeople(idx - 1)
    }

    private fun updateState(stateAddPeople: AddPeopleScreenState) {
        controller.setData(stateAddPeople)
    }

    override fun addPlayer(name: String) {
        viewModel.addPlayer(name)
    }

    override fun addWord(name: String) {
        router.navigateTo(AddWordsScreenFragment.Screen(name))
    }

    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return AddPeopleScreenFragment()
        }
    }

}