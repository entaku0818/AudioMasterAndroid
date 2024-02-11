import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.AudioRecorder
import kotlinx.coroutines.launch

class AudioRecorderViewModel(
    private val audioRecorder: AudioRecorder
) : ViewModel() {

    fun startRecording(outputFile: String) {
        viewModelScope.launch {
            audioRecorder.startRecording(outputFile)
        }
    }

    fun stopRecording() {
        viewModelScope.launch {
            audioRecorder.stopRecording()
        }
    }

    fun playRecordedFile(outputFile: String) {
        viewModelScope.launch {
            audioRecorder.playRecordedFile(outputFile)
        }
    }
}

