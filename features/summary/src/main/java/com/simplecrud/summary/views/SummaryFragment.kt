package com.simplecrud.summary.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simplecrud.commonui.extensions.getStringFromRawResource
import com.simplecrud.summary.R
import com.simplecrud.summary.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {

    // Binding //

    private lateinit var binding: FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSummaryBinding.bind(view)
        val details = com.example.commonui.R.raw.summary_description.getStringFromRawResource(resources)
        details?.let {
            binding.tvDescription.text = it
        }
    }
}