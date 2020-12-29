package com.example.studentmanager.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.R
import com.example.studentmanager.adapters.GradesAdapter
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_raport.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class RaportFragment : Fragment() {
    private lateinit var viewModel: StudentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_raport, container, false)

        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateHelper = DateHelper()
        val dateText = getString(R.string.grades_today) + " " + dateHelper.getTodayDateText()
        tvDatesToday.text = dateText

        lifecycleScope.launch {
            val todayTime = LocalDateTime.now()
            val yesterdayTime = LocalDateTime.now().minusDays(1)
            val todayDate = Date.from(todayTime.atZone(ZoneId.systemDefault()).toInstant())
            val yesterdayDate = Date.from(yesterdayTime.atZone(ZoneId.systemDefault()).toInstant())

            val adapter = GradesAdapter(viewModel.getGradesBetweenDates(yesterdayDate, todayDate))
            recyclerViewRapport.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewRapport.adapter = adapter
        }
    }
}