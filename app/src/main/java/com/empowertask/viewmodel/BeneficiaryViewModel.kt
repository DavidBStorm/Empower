import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.empowertask.model.Beneficiary
import com.empowertask.repository.BeneficiaryRepository

class BeneficiaryViewModel(private val repository: BeneficiaryRepository) : ViewModel() {

    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()
    val beneficiaries: LiveData<List<Beneficiary>> get() = _beneficiaries

    private val _selectedBeneficiary = MutableLiveData<Beneficiary>()
    val selectedBeneficiary: LiveData<Beneficiary> get() = _selectedBeneficiary

    fun loadBeneficiaries() {
        _beneficiaries.value = repository.getBeneficiaries()
    }

    fun selectBeneficiary(beneficiary: Beneficiary) {
        _selectedBeneficiary.value = beneficiary
    }
}
