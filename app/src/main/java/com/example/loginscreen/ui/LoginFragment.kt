package com.example.loginscreen.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.loginscreen.R
import com.example.loginscreen.databinding.FragmentLoginBinding
import com.example.loginscreen.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var loginViewModel: LoginViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.activateNextButton.observe(viewLifecycleOwner) {
            if (it) {
                binding.nextButton.isEnabled = true
                binding.nextButton.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#3700B3"))
                loginViewModel.saveEntries(
                    binding.edittextPan.text.toString(),
                )

            } else {
                binding.nextButton.isEnabled = false
                binding.nextButton.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#66585858"))
            }
        }
        loginViewModel.panNumber.observe(viewLifecycleOwner) {
            binding.edittextPan.setText(it)
        }
        binding.edittextDay.doOnTextChanged { _, _, _, _ ->
            textChangeListener(binding.edittextMont)
        }
        binding.edittextMont.doOnTextChanged { _, _, _, _ ->
            textChangeListener(binding.edittextMont)
        }
        binding.edittextYear.doOnTextChanged { _, _, _, _ ->
            textChangeListener(binding.edittextMont)
        }
        binding.edittextPan.doOnTextChanged { _, _, _, _ ->
            textChangeListener(binding.edittextMont)
        }
        binding.nextButton.setOnClickListener {
            if (it.isEnabled) {
                findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)
            }
        }

        setScroll()

    }

    private fun setScroll() {
        binding.edittextYear.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                scrollUpTheContainer(binding.edittextPan)
            }
        }

        binding.edittextPan.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                scrollUpTheContainer(binding.edittextPan)
            }
        }
        binding.edittextMont.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                scrollUpTheContainer(binding.edittextMont)
            }
        }
        binding.edittextYear.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                scrollUpTheContainer(binding.edittextYear)
            }
        }
    }

    private fun scrollUpTheContainer(view: View) =
        with(binding.scrollview) {
            postDelayed({
                smoothScrollTo(0, 400)
            }, 300)
        }

    private fun textChangeListener(view: EditText) {
        var validDate = false
        var validPan = false

        validDate = loginViewModel.validateDateOfBirth(
            binding.edittextDay.text.toString(),
            binding.edittextMont.text.toString(),
            binding.edittextYear.text.toString()
        )

        binding.edittextPan.text?.toString()?.let { validPan = loginViewModel.validatePAN(it) }

        loginViewModel.activateNextButton.value = validDate && validPan
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}