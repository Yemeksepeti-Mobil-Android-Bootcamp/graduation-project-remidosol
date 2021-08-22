package com.remidosol.yemeksepeti_graduation_remidosol.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.switchmaterial.SwitchMaterial
import com.remidosol.yemeksepeti_graduation_remidosol.R
import com.remidosol.yemeksepeti_graduation_remidosol.data.entity.core.User
import com.remidosol.yemeksepeti_graduation_remidosol.databinding.HomeFragmentBinding
import com.remidosol.yemeksepeti_graduation_remidosol.utils.Resource
import com.remidosol.yemeksepeti_graduation_remidosol.utils.ThemeUtils
import dagger.hilt.android.AndroidEntryPoint
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: HomeFragmentBinding

    private lateinit var viewModel: HomeViewModel

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)

//        val navController = findNavController(this)
//        val menuItems = arrayOf(
//            CbnMenuItem(
//                R.drawable.ic_cart_24,
//                R.drawable.avd_cart,
//                R.id.cartFragment
//            ),
//            CbnMenuItem(
//                R.drawable.ic_home_24, // the icon
//                R.drawable.avd_home, // the AVD that will be shown in FAB
//                R.id.homeFragment // optional if you use Jetpack Navigation
//            ),
//            CbnMenuItem(
//                R.drawable.ic_restaurant_24,
//                R.drawable.avd_restaurant,
//                R.id.restaurantFragment
//            ),
//        )
//        _binding.bottomNavigationView.setupWithNavController(navController)
//        _binding.bottomNavigationView.setMenuItems(menuItems, 2)

        // For modal drawer
//        user = requireArguments().getParcelable("user")!!

        user = viewModel.getUser()!!

        requireActivity().setActionBar(_binding.root.findViewById(R.id.topAppBar))

        Glide
            .with(_binding.root.context)
            .load(user.profile!!.avatarUrl)
            .centerCrop()
            .placeholder(CircularProgressDrawable(_binding.root.context))
            .into(_binding.root.findViewById(R.id.profileImageView))

//        findNavController().navigate()
//        val bottomNavigation = _binding.root.findViewById<MeowBottomNavigation>(R.id.bottomNavigation)
//        bottomNavigation.setOnClickMenuListener {
//            when (it.id){
//                1 -> navController.navigate(R.id.homeFragment)
//                2 -> navController.navigate(R.id.cartFragment)
//                3 -> navController.navigate(R.id.settingsFragment)
//            }
//        }
//        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
//        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_cart))
//        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_message))
//        bottomNavigation.show(1)
//        bottomNavigation.setCount(2, 2.toString())
//        bottomNavigation.clearCount(2)

        isDark()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(_binding.root)
    }

    override fun onResume() {
        super.onResume()

        isDark()
    }

    override fun onStart() {
        super.onStart()

        isDark()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

//        val darkThemeNavItem: MenuItem = menu.findItem(R.id.nav_theme)
        val logoutNavItem: MenuItem = menu.findItem(R.id.nav_logout)

        logoutNavItem.setOnMenuItemClickListener {
            AlertDialog.Builder(_binding.root.context)
                .setTitle("You will be logged out!")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { dialog, button ->
                    viewModel.logout().observe(viewLifecycleOwner, Observer {
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                //
                            }
                            Resource.Status.SUCCESS -> {
                                Toast.makeText(
                                    _binding.root.context,
                                    it.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                viewModel.getSharedPreferences(_binding.root.context)
                                    .saveToken("")
                                viewModel.removeUser()
                                findNavController().navigate(
                                    R.id.action_homeFragment_to_loginFragment
                                )
                            }
                            Resource.Status.ERROR -> {
                                AlertDialog.Builder(_binding.root.context)
                                    .setTitle("Error")
                                    .setMessage("${it.message}")
                                    .setPositiveButton("Ok") { dialog, button ->
                                        dialog.dismiss()
                                    }.show()
                            }
                        }
                    })
                }
                .setNegativeButton("No") { dialog, button ->
                    dialog.dismiss()
                }.show()

            true
        }
    }

    private fun initViews(view: View) {

        val switchTheme: SwitchMaterial = view.findViewById(R.id.switchTheme)

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.setText(R.string.dark)
                viewModel.setTheme(ThemeUtils.DARK)

                ThemeUtils(viewModel.getSharedPreferences(_binding.root.context)).changeTheme(
                    _binding.root.context as AppCompatActivity,
                    ThemeUtils.DARK
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.setText(R.string.light)
                viewModel.setTheme(ThemeUtils.LIGHT)
                ThemeUtils(viewModel.getSharedPreferences(_binding.root.context)).changeTheme(
                    _binding.root.context as AppCompatActivity,
                    ThemeUtils.LIGHT
                )
            }
        }
    }

    private fun isDark() {

        val switchTheme: SwitchMaterial = _binding.root.findViewById(R.id.switchTheme)

        if (viewModel.checkTheme() == ThemeUtils.DARK) {
            switchTheme.isChecked = true
            switchTheme.setText(R.string.dark)
        } else {
            switchTheme.isChecked = false
            switchTheme.setText(R.string.light)
        }
    }
}