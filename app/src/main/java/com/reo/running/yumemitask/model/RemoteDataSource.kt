package com.reo.running.yumemitask.model

class RemoteDataSource {
    suspend fun getRepository(): List<Github>? {
        try {
            val data = APIClient.retrofit.fetchRepository()
            if (data.isSuccessful) {
                return data.body()
            } else {
                throw Exception()
            }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }
}