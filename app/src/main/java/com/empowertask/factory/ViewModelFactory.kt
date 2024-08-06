import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.empowertask.repository.BeneficiaryRepository

class BeneficiaryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeneficiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeneficiaryViewModel(BeneficiaryRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
