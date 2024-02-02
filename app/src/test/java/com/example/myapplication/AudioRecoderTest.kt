import android.media.MediaRecorder
import com.example.myapplication.model.AudioRecorder
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AudioRecorderTest {

    @Test
    fun startRecording_startsMediaRecorder() {
        val mediaRecorder = mock(MediaRecorder::class.java)
        val mediaRecorderFactory = { mediaRecorder }
        val audioRecorder = AudioRecorder(mediaRecorderFactory)

        audioRecorder.startRecording("test.3gp")

        verify(mediaRecorder).start()
    }

    @Test
    fun stopRecording_stopsAndReleasesMediaRecorder() {
        val mediaRecorder = mock(MediaRecorder::class.java)
        val mediaRecorderFactory = { mediaRecorder }
        val audioRecorder = AudioRecorder(mediaRecorderFactory)

        audioRecorder.startRecording("test.3gp")
        audioRecorder.stopRecording()

        verify(mediaRecorder).stop()
        verify(mediaRecorder).release()
    }
}
