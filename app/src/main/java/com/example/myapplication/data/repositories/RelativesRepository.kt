package com.example.geneticcalc.data.repositories


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geneticcalc.data.API.RetrofitFactory
import com.example.geneticcalc.data.API.TypeCodeAPI
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.data.datasourse.RelativesDataSource
import com.example.geneticcalc.data.models.PlaceholderPost
import com.example.geneticcalc.data.protocols.RelativesProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class RelativesRepository(context: Context) :
    RelativesProtocol {
    private val dataSource: RelativesDataSource

    init {
        dataSource = RelativesDataSource(context)
    }

    override fun relativesList(): LiveData<List<RelativesEntity>> {
        return dataSource.relativesList
    }

    override fun getRelativesItem(id: Int): LiveData<RelativesEntity> {
        return dataSource.getRelativesItem(id)
    }

    override fun addRelative() {
        dataSource.addRelative()
    }

    override fun deleteRelative(id: Int) {
        dataSource.deleteRelative(id)
    }

    override fun updateRelative(
        id: Int,
        relativesType: String?,
        eyeColor: String?,
        hairColor: String?,
        dateofBirth: String?,
        bloodType: String?
    ) {
        dataSource.updateRelative(id, relativesType, eyeColor, hairColor, dateofBirth, bloodType)
    }

    override val post: LiveData<PlaceholderPost>
        get() {
            val liveData = MutableLiveData<PlaceholderPost>()

            CoroutineScope(Dispatchers.IO).launch {
                liveData.value = RetrofitFactory.apiService.post()
            }
            return liveData
        }

    override fun pushPost(): LiveData<PlaceholderPost> {
        val liveData = MutableLiveData<PlaceholderPost>()
//        CoroutineScope(Dispatchers.IO).launch {
//            liveData.value = RetrofitFactory.apiService.pushPost()
//        }
        return liveData
    }

    override val allPosts: MutableLiveData<List<PlaceholderPost>>
        get() {
            val retrofit: Retrofit? = RetrofitFactory.retrofit
            val typeCodeAPI = retrofit?.create(TypeCodeAPI::class.java)
            val call: Call<List<PlaceholderPost?>?>? = typeCodeAPI?.allPosts
            val getLD = MutableLiveData<List<PlaceholderPost>>()
            call?.enqueue(object : Callback<List<PlaceholderPost>> {
                override fun onResponse(
                    call: Call<List<PlaceholderPost>>,
                    response: Response<List<PlaceholderPost>>
                ) {
                    if (response.isSuccessful) {
                        val post = response.body()!!
                        getLD.setValue(post)
                    }
                }

                override fun onFailure(call: Call<List<PlaceholderPost>>, t: Throwable) {}
            })
            return getLD
        }
}

private fun <T> Call<T>?.enqueue(callback: Callback<List<PlaceholderPost>>) {
    TODO("Not yet implemented")
}

