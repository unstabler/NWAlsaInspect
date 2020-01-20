package pl.unstabler.nwalsainspect;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class LogcatAlsaInspector {
    public LogcatAlsaInspector() {

    }

    public InspectionResult inspect() {
        HashMap<String, String> rawInspectionResult = new HashMap<>();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "/system/bin/logcat", "-d", "-b", "main", "Alsa:I", "-s", "-v", "brief"
            );
            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
            Process logcatProcess = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(":");
                if (splitLine.length != 3) {
                    continue;
                }

                String name = splitLine[1].trim();
                String value = splitLine[2].trim();

                rawInspectionResult.put(name, value);
            }

            reader.close();
            int exitCode = logcatProcess.waitFor();
            Log.d("NWAlsaInspect", String.format("exitCode => %d", exitCode));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return reprAsInspectionResult(rawInspectionResult);
    }

    private InspectionResult reprAsInspectionResult(Map<String, String> rawResult) {
        String format = rawResult.containsKey("format") ? rawResult.get("format") : "unknown";
        int rate = rawResult.containsKey("rate") ? Integer.parseInt(rawResult.get("rate")) : -1;
        int bufferTime = rawResult.containsKey("buffer_time") ? Integer.parseInt(rawResult.get("buffer_time")) : -1;
        int bufferBytes = rawResult.containsKey("buffer_bytes") ? Integer.parseInt(rawResult.get("buffer_bytes")) : -1;
        int channels = rawResult.containsKey("channels") ? Integer.parseInt(rawResult.get("channels")) : -1;


        return new InspectionResult(
                format,
                rate,
                bufferTime,
                bufferBytes,
                channels
        );
    }

}
