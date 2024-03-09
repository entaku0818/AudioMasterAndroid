

import android.app.Application
import android.media.MediaRecorder
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.model.AudioRecorder
import java.io.File

class AudioRecorderViewModel(application: Application) : AndroidViewModel(application) {

    private val outputFile = application.filesDir.absolutePath + File.separator + "recorded_audio.3gp"

    private val audioRecorder = AudioRecorder(
        outputFile = outputFile
    )

    fun startRecording() {
        audioRecorder.startRecording()
    }

    fun stopRecording() {
        audioRecorder.stopRecording()
    }

    fun playRecordedFile() {
        audioRecorder.playRecordedFile()
    }

    override fun onCleared() {
        super.onCleared()
        audioRecorder.stopRecording()

    }
}
