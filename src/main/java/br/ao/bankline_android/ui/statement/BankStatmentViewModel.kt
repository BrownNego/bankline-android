package br.ao.bankline_android.ui.statement

import androidx.lifecycle.ViewModel
import br.ao.bankline_android.data.BanklineRepository

class BankStatmentViewModel : ViewModel() {
    fun findBankStatement(accountHolderId: Int) =
        BanklineRepository.findBankStatement(accountHolderId)
}