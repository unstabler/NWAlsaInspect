package pl.unstabler.nwalsainspect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private Button updateButton;

    private LogcatAlsaInspector logcatAlsaInspector = new LogcatAlsaInspector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.statusText = findViewById(R.id.statusText);
        this.updateButton = findViewById(R.id.updateButton);

        this.updateButton.setOnClickListener(v -> this.updateInspectionResult());
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateInspectionResult();
    }

    private void updateInspectionResult() {
        InspectionResult inspectionResult = logcatAlsaInspector.inspect();

        statusText.setText(String.format(
                "format => %s\n" +
                "rate => %d\n" +
                "channels => %d\n" +
                "bufferTime => %d\n" +
                "bufferBytes => %d\n",
                inspectionResult.getFormat(),
                inspectionResult.getRate(),
                inspectionResult.getChannels(),
                inspectionResult.getBufferTime(),
                inspectionResult.getBufferBytes()
        ));
    }
}
