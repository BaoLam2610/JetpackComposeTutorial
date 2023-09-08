package com.lambao.tutorial.ui

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import com.lambao.tutorial.data.local.BeerEntity
import javax.inject.Inject

class BeerViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
) : ViewModel() {
}