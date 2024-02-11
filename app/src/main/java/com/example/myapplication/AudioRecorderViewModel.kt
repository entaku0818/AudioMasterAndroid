import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.AudioRecorder
import kotlinx.coroutines.launch

class AudioRecorderViewModel(
    private val audioRecorder: AudioRecorder,
    application: Application
) : AndroidViewModel(application) {

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

