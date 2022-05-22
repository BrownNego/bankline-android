package br.ao.bankline_android.ui.statement

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.ao.bankline_android.databinding.ActivityBankStatementBinding
import br.ao.bankline_android.domain.Correntista
import br.ao.bankline_android.domain.Movimentacao
import br.ao.bankline_android.domain.TipoMovimentacao

class BankStatementActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ACCOUNT_HOLDER = "br.ao.bankline_android.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy{
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy{
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatment()
    }

    private fun findBankStatment() {
        val dataSet = ArrayList<Movimentacao>()
        dataSet.add(Movimentacao(1, "03/05/2022 09:24:55", "Lorem Ipsum", 1000.0, TipoMovimentacao.RECEITA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:24:55", "Lorem Ipsum", 1000.0, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:24:55", "Lorem Ipsum", 1000.0, TipoMovimentacao.RECEITA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:24:55", "Lorem Ipsum", 1000.0, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "03/05/2022 09:24:55", "Lorem Ipsum", 1000.0, TipoMovimentacao.RECEITA, 1))
        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)
    }
}