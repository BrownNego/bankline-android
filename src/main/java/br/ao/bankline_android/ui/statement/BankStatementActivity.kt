package br.ao.bankline_android.ui.statement

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import br.ao.bankline_android.data.State
import br.ao.bankline_android.databinding.ActivityBankStatementBinding
import br.ao.bankline_android.domain.Correntista
import br.ao.bankline_android.domain.Movimentacao
import br.ao.bankline_android.domain.TipoMovimentacao
import com.google.android.material.snackbar.Snackbar

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "br.ao.bankline_android.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER)
            ?: throw IllegalArgumentException()
    }

    private val viewModel by viewModels<BankStatmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatment()

        binding.srlBankStatement.setOnRefreshListener { findBankStatment() }
    }

    private fun findBankStatment() {
        viewModel.findBankStatement(accountHolder.id).observe(this) { state ->
            when (state) {
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show()}
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> binding.srlBankStatement.isRefreshing = true
            }
        }
    }
}