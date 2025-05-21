package dev.havlicektomas.core.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.havlicektomas.core.domain.finance.FinanceTransaction

@Entity
data class TransactionPendingSyncEntity(
    @Embedded
    val transaction: TransactionEntity,
    @PrimaryKey(autoGenerate = false)
    val transactionId: String = transaction.id,
    val userId: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TransactionPendingSyncEntity

        if (transaction != other.transaction) return false
        if (transactionId != other.transactionId) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = transaction.hashCode()
        result = 31 * result + transactionId.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}
