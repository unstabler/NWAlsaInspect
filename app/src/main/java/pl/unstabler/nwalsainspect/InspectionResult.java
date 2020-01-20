package pl.unstabler.nwalsainspect;

public class InspectionResult {
    private String format;
    private int rate;
    private int bufferTime;
    private int bufferBytes;
    private int channels;

    public InspectionResult(String format, int rate, int bufferTime, int bufferBytes, int channels) {
        this.format = format;
        this.rate = rate;
        this.bufferTime = bufferTime;
        this.bufferBytes = bufferBytes;
        this.channels = channels;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getBufferTime() {
        return bufferTime;
    }

    public void setBufferTime(int bufferTime) {
        this.bufferTime = bufferTime;
    }

    public int getBufferBytes() {
        return bufferBytes;
    }

    public void setBufferBytes(int bufferBytes) {
        this.bufferBytes = bufferBytes;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }
}
