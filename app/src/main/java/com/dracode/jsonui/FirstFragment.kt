package com.dracode.jsonui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dracode.jsonui.databinding.FragmentFirstBinding
import com.dracode.jsonui.view.loadJsonUi

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonFirst.loadJsonUi(binding.rootLayout, "{\n" +
                "  \"isEnabled\": true,\n" +
                "  \"constraints\": {\n" +
                "    \"bottomToTopOf\": \"text\",\n" +
                "    \"bottomToBottomOf\": \"\",\n" +
                "    \"topToTopOf\": \"parent\",\n" +
                "    \"topToBottomOf\": \"\",\n" +
                "    \"endToEndOf\": \"\",\n" +
                "    \"endToStartOf\": \"\",\n" +
                "    \"startToStartOf\": \"\",\n" +
                "    \"startToEndOf\": \"\"\n" +
                "  },\n" +
                "  \"margin\": {\n" +
                "    \"top\": 10,\n" +
                "    \"bottom\": 10,\n" +
                "    \"right\": 10, \n" +
                "    \"left\": 10\n" +
                "  },\n" +
                "    \"padding\": {\n" +
                "    \"top\": 10,\n" +
                "    \"bottom\": 10,\n" +
                "    \"right\": 10, \n" +
                "    \"left\": 10\n" +
                "  }\n" +
                "}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}