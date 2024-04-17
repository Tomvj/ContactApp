package com.example.contacttask.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.contacttask.Retrofit.APIInterface
import com.example.contacttask.Responsemodel.Result
import com.example.contacttask.Retrofit.RetrofitInstance

class ContactsPaging() : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 1
            val response =  RetrofitInstance.instance.getRandomUsers(25, page, "gender,name,picture,phone,cell,id,email")
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else response.info.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

