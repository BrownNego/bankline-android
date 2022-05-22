package br.ao.bankline_android.data.remote

import br.ao.bankline_android.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineApi {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountHandleId: Int): List<Movimentacao>
}