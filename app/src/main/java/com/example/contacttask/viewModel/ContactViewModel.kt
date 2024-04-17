import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.contacttask.NetworkClass
import com.example.contacttask.Retrofit.APIInterface
import com.example.contacttask.pagination.ContactsPaging


class ContactViewModel() : ViewModel(){

    val usersPager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        ContactsPaging()
    }.flow.cachedIn(viewModelScope)

    private var currentQuery = ""
    private var currentPage = 1
    private val itemsPerPage = 5



}