package ni.org.ics.zpo.v2.appmovil.listeners;

public interface UploadListener {
    void uploadComplete(String result);
    void progressUpdate(String message, int progress, int max);
}
